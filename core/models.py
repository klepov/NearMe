from django.db import models
from geoposition.fields import GeopositionField
from vk_api import vk_api


class Cinema(models.Model):
    type_genre = models.IntegerField()
    name_genre = models.CharField(max_length=100)


class Music(models.Model):
    type_genre = models.IntegerField()
    name_genre = models.CharField(max_length=100)


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


class Filter(models.Model):
    age_from = models.IntegerField()
    age_to = models.IntegerField()
    sex_need = models.IntegerField()

    # def __str__(self):
    #     return "фильтр для " + self.user.get_name

class Location(models.Model):
    latitude = models.DecimalField(max_digits=9, decimal_places=6)
    longitude = models.DecimalField(max_digits=9, decimal_places=6)

class Wish(models.Model):
    wish = models.CharField(max_length=255)

    def __str__(self):
        return self.wish


class User(models.Model):
    vk_token = models.CharField(max_length=255)
    vk_id = models.IntegerField()
    name = models.CharField(max_length=100,blank=True)
    second_name = models.CharField(max_length=100,blank=True)
    age = models.IntegerField(null=True)
    sex = models.IntegerField()
    groups = models.OneToOneField(Groups)
    filter = models.OneToOneField(Filter)
    location = models.OneToOneField(Location)
    wish = models.OneToOneField(Wish)

    black_list = models.TextField(blank=True)


    @property
    def get_group(self):
        return self.groups

    @property
    def get_filter(self):
        return self.filter

    @property
    def get_location(self):
        return self.location

    @property
    def get_name(self):
        return self.name + " " + self.second_name

    @property
    def get_wish(self):
        return self.wish

    def __str__(self):
        return self.get_name
        # cinema = models.ForeignKey(Cinema,related_name='cinema')
