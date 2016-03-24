from rest_framework import permissions, status
from rest_framework.response import Response
from rest_framework.views import APIView
from vk_api import vk_api, json

from core.Api.Utils import get_user, get_vk_auth, get_user_vk_response
from core.models import User, Groups, Filter, Location


def get_response_group(vk_token):
    vk = get_vk_auth(vk_token)
    try:
        response = vk.groups.get(fields='id')
    except:
        return None
    return response


def save_groups(response):
    if response is None:
        return
    count = response['count']
    items = response['items']
    groups = Groups(count=count, items=items)
    groups.save()
    return groups


class Login(APIView, permissions.BasePermission):
    def post(self, request):
        token = request.POST['token']
        latitude = request.POST['latitude']
        longitude = request.POST['longitude']

        response = get_user_vk_response(token)

        id = response['id']
        user = get_user(id)

        if user is None:
            # если юзера нет в бд
            # то сохранить его и получить список групп

            # if group is None:
            #     return Response(status=status.HTTP_403_FORBIDDEN)
            # user = User(vk_id = id, vk_token=token, groups = group)
            # user.save()

            user = User(vk_id=response['id'],
                        name=response['first_name'],
                        second_name=response['last_name'],
                        sex=response['sex'],
                        vk_token=token)

            user.groups = save_groups(get_response_group(token))
            filter = Filter(age_from=16, age_to=21, sex_need=1)
            filter.save()
            user.filter = filter
            location = Location(latitude=latitude, longitude=longitude)
            location.save()
            user.location = location
            user.save()

            # todo add in nearBy

            data = {}
            data["code"] = "9"
            return Response(json.dumps(data))

        else:
            # обновить группы если есть изменения
            groups = user.get_group
            response = get_response_group(token)
            if response is None:
                return Response(status=status.HTTP_403_FORBIDDEN)
            new_count = response['count']

            if groups.count != new_count:
                groups.items = response['items']
                groups.count = new_count

                groups.save()

        return Response(status=status.HTTP_200_OK)


