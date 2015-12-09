from django.shortcuts import render
from Scouting2013.models import Team, Match

# Create your views here.


def index(request):

    return render(request, 'Scouting2013/index.html')


def all_teams(request):

    all_teams = Team.objects.all()

    context = {"teams": all_teams}

    return render(request, 'Scouting2013/all_teams.html', context)


def view_team(request, team_id):

    this_team = Team.objects.get(id=team_id)

    context = {"team": this_team}

    return render(request, 'Scouting2013/single_team.html', context)


def all_matches(request):
    
    all_matches = Match.objects.all()

    context = {"matches": all_matches}

    return render(request, 'Scouting2013/all_matches.html', context)


def view_match(request,match_id):

    return render(request, 'Scouting2013/single_match.html')
