from django.shortcuts import render
from Scouting2013.models import Team

# Create your views here.


def index(request):
    
    return render(request, 'Scouting2013/index.html')


def all_teams(request):
    
    all_team_types = Team.objects.all()
    
    context = {"teams": all_team_types, "black_magic": 666 }
    
    return render(request, 'Scouting2013/all_teams.html', context)


def view_team(request, team_id):
    
    this_team = Team.objects.get(id=team_id)
    
    print this_team
    
    return render(request, 'Scouting2013/all_teams.html')