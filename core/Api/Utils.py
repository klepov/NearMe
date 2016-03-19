import vk_api

from core.models import User


def get_user(id):
    user = None
    try:
        user = User.objects.get(vk_id=id)
    except User.DoesNotExist:
        pass
    return user


def get_vk_auth(vk_token):
    vk = vk_api.VkApi(token=vk_token)

    try:
        vk.authorization()
    except vk_api.AuthorizationError as error_msg:
        print(error_msg)

    return vk
