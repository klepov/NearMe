from rest_framework import status
from rest_framework.response import Response
from rest_framework.views import APIView

from core.Api import Utils
from core.Api.Utils import get_user_vk_response


class Get_my_profile(APIView):

    def post(self, request):
        token = request.POST['token']
        user = Utils.get_user(get_user_vk_response(token)['id'])

        data = Utils.infalte_specific_usermodel(user)

        return Response (status=status.HTTP_200_OK,data=data)