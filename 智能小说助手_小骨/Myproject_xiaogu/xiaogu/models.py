from django.db import models


# Create your models here.


# Create your models here.
class WangUser(models.Model):
    username = models.CharField(max_length=20)  # 用户名
    password = models.CharField(max_length=20)  # 密码
    email = models.CharField(max_length=20)  # 邮箱


class WangBooks(models.Model):
    bookname = models.CharField(max_length=30)
    price = models.CharField(max_length=20)
    authors = models.CharField(max_length=30)
    kind = models.CharField(max_length=30)
    img = models.FileField(upload_to='img/')
    data = models.FileField(upload_to='txt/')


class UserBooks(models.Model):
    user = models.CharField(max_length=20)
    bookname = models.CharField(max_length=30)
    authors = models.CharField(max_length=30)
    kind = models.CharField(max_length=30)
    img = models.CharField(max_length=30)
    data = models.CharField(max_length=30)


class BookDetails(models.Model):
    bookname = models.CharField(max_length=30)
    details = models.CharField(max_length=200)
    number = models.CharField(max_length=20)
