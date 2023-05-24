from django.conf.urls import url
import xiaogu.views as views
from django.urls import path

from django.conf.urls.static import static
from django.conf import settings

urlpatterns = [

    # path('login/', views.login, name='login'),
    path('index/', views.index, name='index'),
    path('register/', views.register, name='register'),
    path('shouye/', views.shuoye, name='shouye'),
    path('zhushou/', views.zhushou, name='zhushou'),
    path('gengduo/', views.gengduo, name='gengduo'),
    path('renwu_top10/', views.renwu_top10, name='renwu_top10'),
    path('renwu_top10_out/', views.renwu_top10_out, name='renwu_top10_out'),
    path('renwu_guanxi/',views.renwu_guanxi,name='renwu_guanxi'),
    path('renwu_guanxi_out/',views.renwu_guanxi_out,name='renwu_guanxi_out'),
    path('juese_xiangshi/',views.juese_xiangshi,name='juese_xiangshi'),
    path('juese_xiangshi_out/',views.juese_xiangshi_out,name='juese_xiangshi_out'),
    path('juese_xiangguanci_10/',views.juese_xiangguanci_10,name='juese_xiangguanci_10'),
    path('juese_xiangguanci_10_out/',views.juese_xiangguanci_10_out,name='juese_xiangguanci_10_out'),
    path('quanshu_wordcloud/',views.quanshu_wordcloud,name='quanshu_wordcloud'),
    path('quanshu_wordcloud_out/',views.quanshu_wordcloud_out,name='quanshu_wordcloud_out'),
    path('geren/', views.geren , name='geren'),
    path('shucheng/',views.shucheng,name='shucheng'),
    path('addbook/',views.addbook,name='addbook'),
    url(r'^deletebook/',views.deletebook,name='deletebook'),
    url(r'^changebook/', views.changebook1, name='changebook'),
    url(r'^goumai/',views.goumai,name='goumai'),

    url(r'^readbook/',views.readbook,name='readbook'),

    path('liaotian/',views.liaotian,name='liaotian'),

    path('bookdetails/',views.bookdetails,name='bookdetails'),

    url(r'^sousuo/',views.sousuo,name='sousuo'),
    url(r'^kind/',views.kind,name='kind'),
    url(r'^menbook/',views.menbook,name='menbook'),
    url(r'^womenbook/',views.womenbook,name='womenbook'),
    url(r'^xiangxi/',views.xiangxi,name='xiangxi'),








    path('login/',views.login,name='login'),
    path('change/',views.change_pass,name='change'),
    # path('regist/',views.regist,name='regist'),



]+static(settings.MEDIA_URL,document_root=settings.MEDIA_ROOT)