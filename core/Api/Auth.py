from rest_framework import permissions,status
from rest_framework.response import Response
from rest_framework.views import APIView
from vk_api import vk_api

from core.Api.Utils import get_user, get_vk_auth
from core.models import User, Groups


def get_response_group(vk_token):
    vk = get_vk_auth(vk_token)
    response = vk.method('groups.get', {'fields': ('id')})
    return response


def save_groups(response):
    count = response['count']
    items = response['items']
    groups = Groups(count=count, items=items)
    groups.save()
    return groups


class Login(APIView, permissions.BasePermission):



    def post(self,request):
        id = request.POST['id']
        token = request.POST['token']

        user = get_user(id)
        if user is None:
            # если юзера нет в бд
            # то сохранить его и получить список групп
            group = save_groups(get_response_group(token))
            user = User(vk_id = id, vk_token=token, groups = group)
            user.save()
        else:
            # обновить группы если есть изменения
            groups = user.getGroup()
            count = groups.count
            response = get_response_group(token)
            new_count = response['count']

            if count != new_count:
                groups.items = response['items']
                groups.count = new_count

                groups.save()

        return Response(status=status.HTTP_200_OK)
