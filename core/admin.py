from django.contrib import admin
from .models import *
# Register your models here.
admin.site.register(User)
admin.site.register(Groups)
admin.site.register(Filter)
admin.site.register(Location)
admin.site.register(Wish)