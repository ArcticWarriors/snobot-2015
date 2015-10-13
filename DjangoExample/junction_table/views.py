from django.shortcuts import render, get_object_or_404
from junction_table.models import Game, GameGenre, Players
from django.http.response import HttpResponseRedirect
from django.core.urlresolvers import reverse

# Create your views here.


def index(request):

    context = {}
    return render(request, 'junction_table/index.html', context)


def view_all_games(request):

    context = {"games": Game.objects.all(), "available_genres": GameGenre.objects.all()}
    return render(request, 'junction_table/viewGames.html', context)


def view_all_players(request):

    context = {"players": Players.objects.all()}
    return render(request, 'junction_table/viewPlayers.html', context)


def view_all_genres(request):

    context = {"genres": GameGenre.objects.all()}
    return render(request, 'junction_table/viewGenres.html', context)


def view_game(request, game_id):

    game = get_object_or_404(Game, id=game_id)

    context = {"game": game}
    return render(request, 'junction_table/viewGame.html', context)


def view_genre(request, genre_id):

    genre = get_object_or_404(GameGenre, id=genre_id)

    context = {"genre": genre}
    return render(request, 'junction_table/viewGenre.html', context)


def view_player(request, player_id):

    player = get_object_or_404(Players, id=player_id)
    games = Game.objects.filter(players__id=player_id)
    available_games = Game.objects.all()

    context = {"player": player, "games": games, "available_games": available_games}
    return render(request, 'junction_table/viewPlayer.html', context)


def create_genre(request):

    try:
        GameGenre.objects.create(genreName=request.POST['genreName'], genreDescription=request.POST['genreDescription'])
    except:
        raise  # TODO: fix to something better

    return HttpResponseRedirect(reverse('junction_table:alL_genres'))


def create_game(request):

    try:
        genre = get_object_or_404(GameGenre, genreName=request.POST['gameGenre'])
        Game.objects.create(gameName=request.POST['gameName'], genre=genre)
    except:
        raise  # TODO: fix to something better

    return HttpResponseRedirect(reverse('junction_table:all_games'))


def create_player(request):

    try:
        Players.objects.create(playerName=request.POST['playerName'])
    except:
        raise  # TODO: fix to something better

    return HttpResponseRedirect(reverse('junction_table:all_players'))


def add_game_to_player(request, player_id):

    player = get_object_or_404(Players, id=player_id)
    game = get_object_or_404(Game, gameName=request.POST['gameName'])

    try:
        player.games.add(game)
    except:
        raise  # TODO: fix to something better

    return HttpResponseRedirect(reverse('junction_table:view_player', args=(player.id,)))
