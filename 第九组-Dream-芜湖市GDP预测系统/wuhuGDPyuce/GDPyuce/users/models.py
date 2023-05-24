from django.db import models


# Create your models here.
class UsersMessage(models.Model):
    objects = models.Manager()
    name = models.CharField(max_length=50)  # 账户
    password = models.CharField(max_length=50)  # 密码


