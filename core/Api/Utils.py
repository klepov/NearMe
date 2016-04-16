import vk_api
from rest_framework import status
from rest_framework.response import Response

from core.models import User
HOURS = 259200  # 72часа


def get_user(id_user):
    try:
        user = User.objects.get(vk_id=id_user)
    except User.DoesNotExist:
        user = None

    return user


def get_vk_auth(vk_token):
    vk = vk_api.VkApi(token=vk_token)
    try:
        vk.authorization()
        method = vk.get_api()

    except vk_api.AuthorizationError:
        return Response(status=status.HTTP_200_OK)

    return method


def get_user_vk_response(token):
    auth = get_vk_auth(token)
    response = auth.users.get(fields="sex")[0]
    return response


def update_in_db_vk(user):
    latitude = user.location.latitude
    longitude = user.location.longitude
    timeout = HOURS
    radius = 3

    auth = get_vk_auth(user.vk_token)
    response = auth.users.getNearby(latitude=latitude,
                                    longitude=longitude,
                                    timeout=timeout,
                                    radius=radius,
                                    fields="sex, photo_max")

    return response
