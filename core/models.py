from django.db import models
from vk_api import vk_api


class Cinema(models.Model):
    type_genre = models.IntegerField()
    name_genre = models.CharField(max_length = 100)

class Music(models.Model):
    type_genre = models.IntegerField()
    name_genre = models.CharField(max_length = 100)

# class Book(models.Model):
#     type_genre = models.IntegerField()
#     name_genre = models.CharField(max_length = 100)
#
# class Sports(models.Model):
#     type_genre = models.IntegerField()
#     name_genre = models.CharField(max_length = 100)
#
# class Hobby(models.Model):
#     type_genre = models.IntegerField()
#     name_genre = models.CharField(max_length = 100)
#
# class Genre(models.Model):
#     pass


class Groups(models.Model):
    count = models.IntegerField()
    items = models.TextField()

class User(models.Model):
    vk_id = models.IntegerField()
    vk_token = models.CharField(max_length=255)
    groups = models.OneToOneField(Groups)
    black_list = models.TextField(blank=True)

    def getGroup(self):
        # vk = vk_api.VkApi(token=self.vk_token)
        #
        # try:
        #     vk.authorization()
        # except vk_api.AuthorizationError as error_msg:
        #     print(error_msg)
        #
        # response = vk.method('groups.get', {'fields': ('id')})
        # count = response['count']
        # items = response['items']
        # groups = Groups(count=count,items=items)
        # groups.save()
        # self.groups = groups
        return self.groups

    # cinema = models.ForeignKey(Cinema,related_name='cinema')


