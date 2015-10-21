from django.db import models

# Create your models here.


class GameGenre(models.Model):

    genreName = models.CharField(max_length=100)
    genreDescription = models.CharField(max_length=300)

    def __str__(self):
        return "%s" % (self.genreName)


class Game(models.Model):

    gameName = models.CharField(max_length=100)
    genre = models.ForeignKey(GameGenre)

    def __str__(self):
        return "%s, %s" % (self.gameName, self.genre)


class Players(models.Model):

    playerName = models.CharField(max_length=100)
    games = models.ManyToManyField(Game)

    def __str__(self):
        return "%s" % (self.playerName)
