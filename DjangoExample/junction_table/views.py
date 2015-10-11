from django.shortcuts import render
from junction_table.models import Game, GameGenre, Players

# Create your views here.


def index(request):

    context = {}
    return render(request, 'junction_table/index.html', context)


def view_all_games(request):

    context = {"games": Game.objects.all()}
    return render(request, 'junction_table/viewGames.html', context)


def view_all_players(request):

    context = {"players": Players.objects.all()}
    return render(request, 'junction_table/viewPlayers.html', context)


def view_all_genres(request):

    context = {"genres": GameGenre.objects.all()}
    return render(request, 'junction_table/viewGenres.html', context)


def view_game(request, game_id):

    game = Game.objects.get(id=game_id)

    context = {"game": game}
    return render(request, 'junction_table/viewGame.html', context)


def view_genre(request, genre_id):

    genre = GameGenre.objects.get(id=genre_id)

    context = {"genre": genre}
    return render(request, 'junction_table/viewGenre.html', context)


def view_player(request, player_id):

    player = Players.objects.get(id=player_id)
    games = Game.objects.filter(players__id=player_id)

    context = {"player": player, "games": games}
    return render(request, 'junction_table/viewPlayer.html', context)
