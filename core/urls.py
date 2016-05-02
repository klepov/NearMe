from django.conf.urls import url, include
from rest_framework.urlpatterns import format_suffix_patterns

from core.Api.Auth import Login, In_shk
from core.Api.Update import update_filter, changed_location
from core.Api.get_people import Near_people
from core.Api.person_profile import Get_my_profile
from core.Api.execute_wish import Execute_wish, Black_list, Get_list_who_execute_wish

urlpatterns = [

    url(r'^api/auth/$', Login.as_view()),

    url(r'^api/wish/execute_wish/$', Execute_wish.as_view()),

    url(r'^api/wish/black_list/$', Black_list.as_view()),

    url(r'^api/get_my_profile/$',Get_my_profile.as_view()),

    url(r'^api/get_people/$', Near_people.as_view()),

    # кто исполняеть желания
    url(r'^api/get_my_execute_wish/$', Get_list_who_execute_wish.as_view()),

    url(r'^api/update_filter/$', update_filter.as_view()),

    url(r'^api/update_location/$', changed_location.as_view()),

    url(r'^api/inShk/$', In_shk.as_view()),


]

# urlpatterns = format_suffix_patterns(urlpatterns)