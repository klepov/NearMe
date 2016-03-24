#coding: utf-8
# -*- coding: utf-8 -*-

import json

from rest_framework import status
from rest_framework.response import Response
from rest_framework.views import APIView

from core.Api import Utils
from core.Api.Utils import get_user, get_vk_auth, get_user_vk_response


def setup_latlng_vk(lat,lng,token):
    vk = get_vk_auth(token)
    HOURS_3 = 10800
    param = {
        'latitude':lat,
        'longitude':lng,
        'timeout':HOURS_3,
        'radius':4,
        'fields':'photo_max, sex,bdate'
    }

    response = vk.method('users.getNearby',param)

    return response

def get_photo(token):
    vk = get_vk_auth(token)
    # response = vk.method('users.getNearby',param)


class Near_people(APIView):

    def post(self,request):
        token = request.POST['token']
        user = Utils.get_user(get_user_vk_response(token)['id'])

        if user == None:
            Response(status=status.HTTP_401_UNAUTHORIZED)

        filter = user.get_filter

        # дефолтные настройки
        age_from = 14
        age_to = 25
        sex = 1

        if filter is not None:
            age_from = filter.age_from
            age_to= filter.age_to
            sex = filter.sex_need

        response = setup_latlng_vk(lat = lat,lng = lng,token=user.vk_token)

        good_people = []
        for i in response['items']:
            try:
                age_response = 2016 - int(i['bdate'].split('.')[2])
            except:
                age_response = False
            if i['sex'] == sex and  age_from < age_response <age_to:
                good_people.append(i)

        return Response(good_people)
        # return Response(json.dumps(good_people))




