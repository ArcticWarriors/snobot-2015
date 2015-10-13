'''
Created on Oct 9, 2015

@author: PJ
'''
from django.conf.urls import url
from . import views


urlpatterns = [
               url(r'^$', views.index, name='index'),
               url(r'^players/$', views.view_all_players, name='all_players'),
               url(r'^players/(?P<player_id>[0-9]+)$', views.view_player, name='view_player'),
               url(r'^games/$', views.view_all_games, name='all_games'),
               url(r'^games/(?P<game_id>[0-9]+)$', views.view_game, name='view_genre'),
               url(r'^genres/$', views.view_all_genres, name='alL_genres'),
               url(r'^genres/(?P<genre_id>[0-9]+)$', views.view_genre, name='view_genre'),

               url(r'^create_genre$', views.create_genre, name='create_genre'),
               url(r'^create_game$', views.create_game, name='create_game'),
               url(r'^create_player$', views.create_player, name='create_player'),
               url(r'^(?P<player_id>[0-9]+)/add_game_to_player$', views.add_game_to_player, name='add_game_to_player'),
               ]
