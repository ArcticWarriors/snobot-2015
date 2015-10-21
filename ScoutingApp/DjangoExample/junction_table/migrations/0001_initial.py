# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Game',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('gameName', models.CharField(max_length=100)),
            ],
        ),
        migrations.CreateModel(
            name='GameGenre',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('genreName', models.CharField(max_length=100)),
                ('genreDescription', models.CharField(max_length=300)),
            ],
        ),
        migrations.CreateModel(
            name='Players',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('playerName', models.CharField(max_length=100)),
                ('games', models.ManyToManyField(to='junction_table.Game')),
            ],
        ),
        migrations.AddField(
            model_name='game',
            name='genre',
            field=models.ForeignKey(to='junction_table.GameGenre'),
        ),
    ]
