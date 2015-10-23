from django.shortcuts import render

# Create your views here.


def index(request):

    return render(request, 'Scouting2013/index.html')
