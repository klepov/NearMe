from rest_framework.views import APIView

from core.Api import Utils
from core.Api.Utils import get_user_vk_response


class Execute_wish(APIView):
    def post(self, request):
        token = request.POST['token']
        user = Utils.get_user(get_user_vk_response(token)['id'])
        pass

class Black_list(APIView):
    def post(self, request):
        print("asd")
        pass