'''
Created on Oct 9, 2015

@author: PJ
'''
from django.conf.urls import url
from . import views


urlpatterns = [
               url(r'^$', views.index, name='index'),
               url(r'^person/(?P<person_id>[0-9]+)$', views.view_person, name='personPage'),
               url(r'^pet/(?P<pet_id>[0-9]+)$', views.view_pet, name='petPage'),
              ]
