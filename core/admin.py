from django.contrib import admin
from .models import *
# Register your models here.
admin.site.register(User)
admin.site.register(Groups)
admin.site.register(Filter)
admin.site.register(Location)
admin.site.register(Wish)
admin.site.register(ExecuteWish)
admin.site.register(Who_execute_wish)
admin.site.register(Black_list)
