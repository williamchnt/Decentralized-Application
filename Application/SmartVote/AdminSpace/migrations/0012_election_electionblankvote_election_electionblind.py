# Generated by Django 4.1.2 on 2023-01-11 14:50

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ("AdminSpace", "0011_election_electionenddate_election_electionstartdate"),
    ]

    operations = [
        migrations.AddField(
            model_name="election",
            name="ElectionBlankVote",
            field=models.BooleanField(default=False),
        ),
        migrations.AddField(
            model_name="election",
            name="ElectionBlind",
            field=models.BooleanField(default=False),
        ),
    ]
