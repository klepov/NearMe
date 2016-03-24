from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns

from core.Api.Auth import Login
from core.Api.Update import Update_filter, Changed_location, Change_age
from core.Api.get_people import Near_people

urlpatterns = [
    url(r'^api/auth/$', Login.as_view()),
    url(r'^api/get_people/$', Near_people.as_view()),

    url(r'^api/update_filter/$', Update_filter.as_view()),
    url(r'^api/update_age/$', Change_age.as_view()),

    url(r'^api/update_location/$', Changed_location.as_view()),

]

# urlpatterns = format_suffix_patterns(urlpatterns)