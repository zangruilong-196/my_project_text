# Generated by Django 2.0 on 2022-05-11 01:08

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('xiaogu', '0002_auto_20220510_1729'),
    ]

    operations = [
        migrations.AlterField(
            model_name='wangbooks',
            name='img',
            field=models.FileField(upload_to='static/'),
        ),
    ]
