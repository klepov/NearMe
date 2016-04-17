from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns

from core.Api.Auth import Login
from core.Api.Update import update_filter, changed_location
from core.Api.get_people import Near_people
from core.Api.person_profile import Get_my_profile
from core.Api.execute_wish import Execute_wish, Black_list

urlpatterns = [

    url(r'^api/auth/$', Login.as_view()),

    url(r'^api/wish/execute_wish/$', Execute_wish.as_view()),

    url(r'^api/wish/black_list/$', Black_list.as_view()),

    url(r'^api/get_my_profile/$',Get_my_profile.as_view()),

    url(r'^api/get_people/$', Near_people.as_view()),

    url(r'^api/update_filter/$', update_filter.as_view()),

    url(r'^api/update_location/$', changed_location.as_view()),

]

# urlpatterns = format_suffix_patterns(urlpatterns)