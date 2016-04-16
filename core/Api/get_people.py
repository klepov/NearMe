# coding: utf-8
# -*- coding: utf-8 -*-

import json

from rest_framework import status
from rest_framework.response import Response
from rest_framework.views import APIView

from core.Api import Utils
from core.Api.Utils import get_user_vk_response, update_in_db_vk
from core.models import User




class Near_people(APIView):
    found = []
    list_from_db = []

    def post(self, request):
        token = request.POST['token']
        user = Utils.get_user(get_user_vk_response(token)['id'])

        if user == None:
            Response(status=status.HTTP_401_UNAUTHORIZED)

        filter = user.get_filter

        if filter is not None:
            age_from = filter.age_from
            age_to = filter.age_to
            sex = filter.sex_need

        sql_quer = "age <= {0} OR age <= {1} AND sex = {2}".format(age_from, age_to, sex)
        get_from_db = User.objects.extra(where=[sql_quer])


        response = update_in_db_vk(user)


        for user in get_from_db:
            self.list_from_db.append(user.vk_id)

        for user_vk in response['items']:
            print(user)
            user_vk['photo_max'] = 'https://pp.vk.me/c629104/v629104372/3c3f7/91DesV4Y--U.jpg'
            user_vk['interest'] = '5'
            user_vk['group'] = '10'
            self.found.append(user_vk)

            result = user_vk['id'] in self.list_from_db
            if result:
                self.found.append(user_vk)

        send_people = {}

        send_people['count'] = len(self.found)
        send_people['items'] = self.found


        return Response(send_people,status=status.HTTP_200_OK)
