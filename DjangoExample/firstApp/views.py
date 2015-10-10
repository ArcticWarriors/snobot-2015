from django.shortcuts import render
from django.http import HttpResponse

from .models import Person, Pet


def index(request):

    people = Person.objects.all()
    context = {"all_people": people}
    return render(request, 'firstApp/index.html', context)


def all_data(request):
    output = "Hello World! My First <a href='https://www.youtube.com/watch?v=eUdM9vrCbow'>Django</a> App!"
    return HttpResponse(output)


def view_person(request, person_id):

    person = Person.objects.get(id=person_id)
    context = {"my_name": person.personName, "my_age": person.personAge, "my_pets": person.pet_set.all()}
    return render(request, 'firstApp/person.html', context)


def view_pet(request, pet_id):

    pet = Pet.objects.get(id=pet_id)
    context = {"pet_name": pet.petName}
    return render(request, 'firstApp/pet.html', context)
