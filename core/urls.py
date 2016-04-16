from django.conf.urls import url
from rest_framework.urlpatterns import format_suffix_patterns

from core.Api.Auth import Login
from core.Api.Update import update_filter, changed_location
from core.Api.get_people import Near_people

urlpatterns = [
    url(r'^api/auth/$', Login.as_view()),
    url(r'^api/get_people/$', Near_people.as_view()),

    url(r'^api/update_filter/$', update_filter.as_view()),

    url(r'^api/update_location/$', changed_location.as_view()),

]

# urlpatterns = format_suffix_patterns(urlpatterns)