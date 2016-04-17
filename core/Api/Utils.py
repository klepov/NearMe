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
                                    fields="sex")

    return response

def inflate_usermodel(user, users):
    auth = get_vk_auth(user.vk_token)

    list_override = []

    for user_id in users:
        # todo сделать нормальную надувалку модели
        user_from_db = User.objects.get(vk_id=user_id['id'])

        id = user_id['id']
        req = auth.photos.get(owner_id=  id, album_id="profile", rev= 1, photo_sizes=1)
        main_photo = req['items'][0]['sizes'][-1]['src']
        user_id['photo_max'] = main_photo
        user_id['wish'] = user_from_db.get_wish.wish
        user_id['age'] = user_from_db.age

        list_override.append(user_id)

    return list_override

def get_fullpick(user):
    auth = get_vk_auth(user.vk_token)

    id = user.id
    req = auth.photos.get(users_id=id, album_id="profile", rev=1, photo_sizes=1)
    main_photo = req['items'][0]['sizes'][-1]['src']
    user.photo_max = main_photo
    user.save()

def infalte_specific_usermodel(user):

    data = {}
    data['id'] = user.vk_id
    data['photo_max'] = user.photo_max
    data['first_name'] = user.name
    data['last_name'] = user.second_name
    data['age'] = user.age
    data['sex'] = user.sex
    data['wish'] = user.wish.wish

    return data
