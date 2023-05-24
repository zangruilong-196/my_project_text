from django.conf.urls import url
import bookmall.views as views
from django.urls import path

from django.conf.urls.static import static
from django.conf import settings

urlpatterns = [
                  path('login/', views.login, name='login'),
                  path('register/', views.register, name='register'),
                  url('shouye/', views.shouye, name='shouye'),
                  url('addbook/', views.addbook, name='addbook'),
                  url('book_xiangxi/', views.book_xiangxi, name='book_xiangxi'),
                  path('dianzan/', views.dianzan, name='dianzan'),
                  path('shoucang/', views.shoucang, name='shoucang'),
                  path('reade_book/', views.reade_book, name='reade_book'),
                  path('gomai/', views.gomai, name='gomai'),
                  path('gomai_qd/', views.gomai_qd, name='gomai_qd'),
                  path('chongzhi/', views.chongzhi, name='chongzhi'),
                  path('xiugai_geren_xinxi/', views.xiugai_geren_xinxi, name='xiugai_geren_xinxi'),
                  url('liulan_delete/', views.liulan_delete, name='liulan_delete'),
                  url('collect_delete/', views.collect_delete, name='collect_delete'),
                  url('xiugai_zuopin/', views.xiugai_zuopin, name='xiugai_zuopin'),
                  url('sousuo/', views.sousuo, name='sousuo'),
                  url('pinglun/', views.pinglun, name='pinglun'),
                  path('tiezi/', views.tiezi, name='tiezi'),
                  path('xiugai_tiezi/', views.xiugai_tiezi, name='xiugai_tiezi'),
                  path('tiezi_delete/', views.tiezi_delete, name='tiezi_delete'),






                  path('webspider/', views.webspider, name='webspider'),
                  url('love_list/', views.love_list, name='love_list'),
              ] + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
