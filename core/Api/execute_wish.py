from rest_framework import status
from rest_framework.response import Response
from rest_framework.views import APIView

from core.Api import Utils
from core.Api.Utils import get_user_vk_response, infalte_specific_usermodel
from core.models import User


class Execute_wish(APIView):
    def post(self, request):
        token = request.POST['token']
        user_id_who_execute = request.POST['user_id']
        user = Utils.get_user(get_user_vk_response(token)['id'])

        # user who execute
        old = user.get_wish_execute.items
        new = old + user_id_who_execute+","
        user.get_wish_execute.items = new
        user.get_wish_execute.save()
        user.save()


        # user who wish
        user_who_wish = User.objects.get(vk_id=user_id_who_execute)
        old = user_who_wish.get_who_execute_wish.items
        new = old + str(user.vk_id)+","
        user.who_execute_Wish.items = new
        user.who_execute_Wish.save()
        user.save()


        data = {"code": 777}

        return Response(data=data, status=status.HTTP_200_OK)


class Black_list(APIView):
    def post(self, request):

        token = request.POST['token']
        user_id_black_list = request.POST['user_id']
        user = Utils.get_user(get_user_vk_response(token)['id'])

        # user who execute
        old = user.get_black_list.items
        new = old + user_id_black_list + ","
        user.get_black_list.items = new
        user.get_black_list.save()
        user.save()

        data = {"code": 777}

        return Response(data=data, status=status.HTTP_200_OK)

class Get_list_who_execute_wish(APIView):
    def post(self, request):
        token = request.POST['token']
        user = Utils.get_user(get_user_vk_response(token)['id'])

        list = user.get_who_execute_wish.items.split(',')
        if len(list) > 1:
            list = [x.strip(' ') for x in list]
            list = [x for x in list if x]


        result = []
        for i in list:
            result.append(infalte_specific_usermodel(User.objects.get(vk_id=str(i))))

        send_people = {}

        send_people['count'] = len(result)
        send_people['items'] = result

        return Response(send_people, status=status.HTTP_200_OK)



