from django.conf.urls import url
from . import views
from django.urls import path

urlpatterns = [
    path('zhuce/', views.zhuce),
    path('zcdo', views.zcdo, name='zhucedo'),
    path('denglu/', views.denglu, name='denglu'),
    url(r'dldo/', views.dldo, name='dldo'),
    path('shuju/', views.query, name='shuju'),
    path('shuju1/', views.query1, name='shuju1'),
    path('shuju2/', views.query2, name='shuju2'),
    path('shuju3/', views.query3, name='shuju3'),
    path('shuju4/', views.query4, name='shuju4'),
    path('shuju5/', views.query5, name='shuju5'),
    path('shuju6/', views.query6, name='shuju6'),
    path('shuju7/', views.query7, name='shuju7'),
    path('shuju8/', views.query8, name='shuju8'),
    path('shuju9/', views.query9, name='shuju9'),
    path('shuju10/', views.query10, name='shuju10'),
    path('shuju11/', views.query11, name='shuju11'),
    path('shuju12/', views.query12, name='shuju12'),
    path('yuce/', views.query13, name='yuce'),
    path('yuce2/', views.query14, name='yuce2'),
    path('guanyu/', views.guangyu, name='guanyu'),
    path('shuoye/', views.shuoye, name='shuoye'),
    path('zc/', views.zc, name='zc'),
]


