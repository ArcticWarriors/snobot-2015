from django.db import models


class Person(models.Model):

    personName = models.CharField(max_length=200)
    personAge = models.IntegerField(default=14)

    def is_a_minor(self):
        return self.personAge

    def __str__(self):
        return "%s (%s)" % (self.personName, self.personAge)


class Pet(models.Model):

    myOwner = models.ForeignKey(Person)
    petName = models.CharField(max_length=200)

    def __str__(self):
        return "%s" % (self.petName)
