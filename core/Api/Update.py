from rest_framework import status
from rest_framework.response import Response
from rest_framework.views import APIView

from core.Api import Utils
from core.Api.Utils import get_user_vk_response, update_in_db_vk





class changed_location(APIView):
    def post(self, request):
        token = request.POST['token']
        user = Utils.get_user(get_user_vk_response(token)['id'])

        if user is not None:
            latitude = float(request.POST['latitude'])
            longitude = float(request.POST['longitude'])

            location = user.get_location

            location.latitude = latitude
            location.longitude = longitude

            location.save()

            user.location = location

            user.save()

            update_in_db_vk(user)
        return Response(status=status.HTTP_200_OK)


class update_filter(APIView):
    @staticmethod
    def post(request):
        token = request.POST['token']
        user = Utils.get_user(get_user_vk_response(token)['id'])

        if user is not None:
            age = int(request.POST['age'])

            user.age = age

            age_from = int(request.POST['age_from'])
            age_to = int(request.POST['age_to'])
            sex_want = int(request.POST['sex_want'])

            user.filter.age_from = age_from
            user.filter.age_to = age_to
            user.filter.sex_need = sex_want

            user.filter.save()
            user.save()


            return Response(status=status.HTTP_200_OK)

