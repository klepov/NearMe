import json

from rest_framework import status
from rest_framework.response import Response
from rest_framework.views import APIView

from core.Api.Utils import get_user, get_vk_auth


def setup_latlng_vk(lat,lng,token):
    vk = get_vk_auth(token)
    HOURS_3 = 10800
    param = {
        'latitude':lat,
        'longitude':lng,
        'timeout':HOURS_3,
        'radius':4,
        'fields':'photo_max, sex'


    }
    response = vk.method('users.getNearby',param)

    return response

def get_photo(token):
    vk = get_vk_auth(token)
    # response = vk.method('users.getNearby',param)


class Near_people(APIView):

    def get(self,request):
        id = request.GET['id']
        lat = request.GET['latitude']
        lng = request.GET['longitude']
        sex = int(request.GET['sex'])

        user = get_user(id)

        if user == None:
            Response(status=status.HTTP_401_UNAUTHORIZED)

        response = setup_latlng_vk(lat = lat,lng = lng,token=user.vk_token)

        good_people = []
        for i in response['items']:
            if i['sex'] == sex:
                good_people.append(i)

        return Response(json.dumps(good_people))




