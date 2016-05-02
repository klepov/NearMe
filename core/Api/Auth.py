from rest_framework import permissions, status
from rest_framework.response import Response
from rest_framework.views import APIView
from vk_api import json

from core.Api.Utils import get_user, get_vk_auth, get_user_vk_response, update_in_db_vk, get_fullpick
from core.models import User,\
    Groups, Filter, Location, Wish, ExecuteWish, Who_execute_wish,Black_list


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

class In_shk(APIView):
    @staticmethod
    def post(request):
        print("asd")


class Login(APIView, permissions.BasePermission):
    @staticmethod
    def post(request):
        token = request.POST['token']
        latitude = request.POST['latitude']
        longitude = request.POST['longitude']

        response = get_user_vk_response(token)

        id = response['id']
        user = get_user(id)

        if user is None:
            # если юзера нет в бд
            # то сохранить его и получить список групп

            user = User(vk_id=response['id'],
                        name=response['first_name'],
                        second_name=response['last_name'],
                        sex=response['sex'],
                        vk_token=token)

            user.groups = save_groups(get_response_group(token))
            filter = Filter(age_from=0, age_to=0, sex_need=0)
            filter.save()
            user.filter = filter
            location = Location(latitude=latitude, longitude=longitude)
            location.save()
            wish = Wish(wish="")
            wish.save()

            user.location = location
            user.wish = wish

            execute_wish = ExecuteWish(items="")
            execute_wish.save()
            user.executeWish = execute_wish

            who_execute_wish = Who_execute_wish(items="")
            who_execute_wish.save()
            user.who_execute_Wish = who_execute_wish

            black_list = Black_list(items="")
            black_list.save()
            user.black_list = black_list

            user.save()

            get_fullpick(user)

            data = {"code": 99}

            return Response(data)

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

        update_in_db_vk(user)

        print('sd')

        if user.filter.age_from == 0:
            data = {'code':99}
            return response(status=status.HTTP_200_OK,data = data)

        data = {'code':777}

        return Response(status=status.HTTP_200_OK,data=data)
