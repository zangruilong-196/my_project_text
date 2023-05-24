from django.http import HttpResponseRedirect, JsonResponse
from django.shortcuts import render

# Create your views here.


from django.shortcuts import render, redirect, HttpResponse
from django import forms
from bookmall import models
from django.views.decorators.csrf import csrf_exempt
from django.contrib import messages

import urllib.request
import xlwt
import re
from bs4 import BeautifulSoup
import os
import xlrd
from collections import Counter


def jiexi(url):  # 解析网页
    head = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36 Edg/100.0.1185.39"
    }  # 冲破障碍，直达网站
    request = urllib.request.Request(url, headers=head)
    html = ""  # 创建一个空字符串进行接收网页信息
    try:  # 检查是否有报错
        response = urllib.request.urlopen(request)
        html = response.read().decode("utf-8")
        print(html)
    except urllib.error.URError as e:
        if hasattr(e, "code"):
            print(e.code)
        if hasattr(e, "reason"):
            print(e.reason)
    return html


def getdata(baseural):  # 获取网站数据，即源码解析后的数据提取
    datalist = []
    for i in range(1, 51):  # 1~50页的网页
        url = baseural + str(i)  # 网页后面进行加减，从而进行模拟翻页
        # print(url)
        html = jiexi(url)
        soup = BeautifulSoup(html, "html.parser")  # 用的是html.parser解析器
        for item in soup.find_all('div', class_="rank_d_list borderB_c_dsh clearfix"):
            data = []
            print(item)
            print("item:!!!!!!!!")
            item = str(item)

            title = re.findall(name, item)[0]  # 小说名提取
            # print(title)

            zuoz = re.findall(aut, item)[0]  # 小说作者提取
            # print(zuoz)
            leix = re.findall(cate, item)[0]  # 小说类型提取
            # print(leix)
            lianjie = re.findall(link, item)[0]  # 小说链接提取
            # print(lianjie)
            img = re.findall(photo, item)[0]  # 小说封面图片提取
            # print(img)
            yue_piao = re.findall(yuepiao, item)[0]
            print(yue_piao)
            # 数据加入到列表里
            data.append(title)
            data.append(zuoz)
            data.append(leix)
            data.append(lianjie)
            data.append(img)
            data.append(yue_piao)
            datalist.append(data)
    # 去除重复元素
    datalist_2 = []
    for li in datalist:
        if li not in datalist_2:
            datalist_2.append(li)
    print(datalist_2)
    print(len(datalist_2))
    return datalist_2  # 返回数据


def savedata(datalist, filename):  # 数据保存
    book = xlwt.Workbook(encoding="utf-8", style_compression=0)  # 创建workbook对象
    sheet = book.add_sheet('小说', cell_overwrite_ok=True)  # 创建工作表
    col = ("小说名", "作者", "类型", "小说链接", "图片链接", "月票")
    len_dl = len(datalist)
    for i in range(0, 6):  # 五列数据的名称填写
        sheet.write(0, i, col[i])
    for i in range(0, len_dl):  # 1000条数据提取
        data = datalist[i]
        for j in range(0, 6):
            sheet.write(i + 1, j, data[j])
    book.save(filename)  # 保存到给定名称的excel表格中


def zh_pc_main():
    baseural = "http://www.zongheng.com/rank/details.html?rt=1&d=1&p="  # 爬取的纵横小说网站——月票榜
    jiexi(baseural)
    filename = "小说排行榜.xls"  # 创建工作表名称
    datalist = getdata(baseural)
    savedata(datalist, filename)


# 利用re.compile创建规则
name = re.compile(r'<div class="rank_d_b_name" title="(.*?)">')
aut = re.compile(r'<div class="rank_d_b_cate" title="(.*?)">')
photo = re.compile(r'src="(.*?)"/>', re.S)
link = re.compile(r'href="(.*?)" target="_blank">')
cate = re.compile(r'<a target="_blank">(.*?)<')
yuepiao = re.compile(r'<div class="rank_d_b_ticket">(.*?)<')


def webspider(request):
    zh_pc_main()
    messages.error(request, '爬虫爬取完毕！数据更新成功')
    return redirect('login')


def reader_zh_top10_book():
    # 按行读取excel表格
    filename = os.path.abspath('小说排行榜.xls')
    book = xlrd.open_workbook(filename)
    sheets = book.sheets()
    print(sheets)  # 所有的表对象
    names = book.sheet_names()
    print(names)  # 所有的表名
    sheet1 = book.sheet_by_index(0)
    column_rows = sheet1.nrows  # 行数
    print('行数:{}'.format(column_rows))
    column_number = sheet1.ncols  # 列数
    print('列数:{}'.format(column_number))

    data2 = []
    for row in range(column_number):
        data = sheet1.col_values(row)  # 按列读取,读出来是list
        print(data)
        data2.append(data)

    zh_labe_list = data2[2]

    # 方法一：利用字典dict来统计各元素出现的次数
    zh_labe_dict = {}
    for key in zh_labe_list:
        zh_labe_dict[key] = zh_labe_dict.get(key, 0) + 1
    print(zh_labe_dict)

    zh_labe_key = list(zh_labe_dict.keys())
    zh_labe_values = list(zh_labe_dict.values())

    del zh_labe_key[0]
    del zh_labe_values[0]

    zh_bookname_list = data2[0]
    zh_yuepiao_list = data2[5]

    del zh_bookname_list[0]  # 根据索引删除
    del zh_yuepiao_list[0]

    zh_new_yuepiao_list = []
    for yp in zh_yuepiao_list:
        zh_new_yuepiao_list.append(int(yp))
    zh_yuepiao_list_int = zh_new_yuepiao_list

    print(zh_yuepiao_list_int)

    zh_bookname_top10 = zh_bookname_list[:10]
    zh_yuepiao_top10 = zh_yuepiao_list_int[:10]

    print("===============================================")
    print(zh_bookname_top10)
    print(zh_yuepiao_top10)

    zh_book_dic_top10 = {}

    zh_book_dic_top10['zh_bookname_top10'] = list(zh_bookname_top10)
    zh_book_dic_top10['zh_yuepiao_top10'] = list(zh_yuepiao_top10)

    return zh_book_dic_top10


def reader_zh_book_list():
    # 按行读取excel表格
    filename = os.path.abspath('小说排行榜.xls')
    book = xlrd.open_workbook(filename)
    sheets = book.sheets()
    print(sheets)  # 所有的表对象
    names = book.sheet_names()
    print(names)  # 所有的表名
    sheet1 = book.sheet_by_index(0)
    column_rows = sheet1.nrows  # 行数
    print('行数:{}'.format(column_rows))
    column_number = sheet1.ncols  # 列数
    print('列数:{}'.format(column_number))

    zh_book_list_s = []

    for row in range(column_rows):
        data = sheet1.row_values(row)  # 按行读取,读出来是list
        print(data)
        zh_book_list_s.append(data)
    print("=========================================================")
    print(zh_book_list_s)
    del zh_book_list_s[0]  # 根据索引删除
    print("=========================================================")
    print(zh_book_list_s)

    print("=========================================================")
    zh_book_list = []
    for z_i_p in zh_book_list_s:
        dic_name = ['bookname', 'author', 'label', 'data', 'img', 'yuepiao']
        d1 = zip(dic_name, z_i_p)  # 压缩，聚合可迭代对象中的元素
        print(d1)
        zh_book_list.append(dict(d1))
    print("=========================================================")
    print(zh_book_list)

    return zh_book_list


def reader_zh_label():
    # 按行读取excel表格
    filename = os.path.abspath('小说排行榜.xls')
    book = xlrd.open_workbook(filename)
    sheets = book.sheets()
    print(sheets)  # 所有的表对象
    names = book.sheet_names()
    print(names)  # 所有的表名
    sheet1 = book.sheet_by_index(0)
    column_rows = sheet1.nrows  # 行数
    print('行数:{}'.format(column_rows))
    column_number = sheet1.ncols  # 列数
    print('列数:{}'.format(column_number))

    data2 = []
    for row in range(column_number):
        data = sheet1.col_values(row)  # 按列读取,读出来是list
        print(data)
        data2.append(data)

    zh_labe_list = data2[2]
    zh_labe_list2 = zh_labe_list[1:]
    print(zh_labe_list2)
    print("=====================================")

    # 方法一：利用字典dict来统计各元素出现的次数
    zh_labe_dict = {}
    for key in zh_labe_list2:
        zh_labe_dict[key] = zh_labe_dict.get(key, 0) + 1
    print(zh_labe_dict)

    zh_labe_key = list(zh_labe_dict.keys())
    zh_labe_values = list(zh_labe_dict.values())

    print(zh_labe_key)
    print(zh_labe_values)

    zh_label_dic_top = {}
    zh_label_dic_top['labename'] = list(zh_labe_key)
    zh_label_dic_top['labenum'] = list(zh_labe_values)

    return zh_label_dic_top


def boy_ping():
    filename = os.path.abspath('小说排行榜.xls')
    book = xlrd.open_workbook(filename)
    sheets = book.sheets()
    print(sheets)  # 所有的表对象
    names = book.sheet_names()
    print(names)  # 所有的表名
    sheet1 = book.sheet_by_index(0)
    column_rows = sheet1.nrows  # 行数
    print('行数:{}'.format(column_rows))
    column_number = sheet1.ncols  # 列数
    print('列数:{}'.format(column_number))

    data2 = []
    for row in range(column_number):
        data = sheet1.col_values(row)  # 按列读取,读出来是list
        print(data)
        data2.append(data)

    zh_labe_list = data2[2]
    zh_labe_list2 = zh_labe_list[1:]
    print(zh_labe_list2)
    print("=====================================")

    # 方法一：利用字典dict来统计各元素出现的次数
    zh_labe_dict = {}
    for key in zh_labe_list2:
        zh_labe_dict[key] = zh_labe_dict.get(key, 0) + 1
    print(zh_labe_dict)

    boy_ping_list_num = zh_labe_dict['玄幻'] + zh_labe_dict['仙侠'] + zh_labe_dict['修真'] + zh_labe_dict['游戏'] + \
                        zh_labe_dict['变身'] + zh_labe_dict['进化'] + zh_labe_dict['星际'] + zh_labe_dict['武侠'] + \
                        zh_labe_dict['奇幻'] + zh_labe_dict['末世'] + zh_labe_dict['灵异'] + zh_labe_dict['惊悚'] + \
                        zh_labe_dict['科幻'] + zh_labe_dict['二次元'] + zh_labe_dict['推理']
    return boy_ping_list_num


def gril_ping():
    filename = os.path.abspath('小说排行榜.xls')
    book = xlrd.open_workbook(filename)
    sheets = book.sheets()
    print(sheets)  # 所有的表对象
    names = book.sheet_names()
    print(names)  # 所有的表名
    sheet1 = book.sheet_by_index(0)
    column_rows = sheet1.nrows  # 行数
    print('行数:{}'.format(column_rows))
    column_number = sheet1.ncols  # 列数
    print('列数:{}'.format(column_number))

    data2 = []
    for row in range(column_number):
        data = sheet1.col_values(row)  # 按列读取,读出来是list
        print(data)
        data2.append(data)

    zh_labe_list = data2[2]
    zh_labe_list2 = zh_labe_list[1:]
    print(zh_labe_list2)
    print("=====================================")

    # 方法一：利用字典dict来统计各元素出现的次数
    zh_labe_dict = {}
    for key in zh_labe_list2:
        zh_labe_dict[key] = zh_labe_dict.get(key, 0) + 1
    print(zh_labe_dict)

    gril_ping_list_num = zh_labe_dict['玄幻'] + zh_labe_dict['仙侠'] + zh_labe_dict['都市'] + zh_labe_dict['异术'] + \
                         zh_labe_dict['异世'] + zh_labe_dict['穿梭'] + zh_labe_dict['奇幻'] + zh_labe_dict['武侠'] + \
                         zh_labe_dict['历史'] + zh_labe_dict['重生'] + zh_labe_dict['推理'] + zh_labe_dict['商战'] + \
                         zh_labe_dict['同人']
    return gril_ping_list_num



def login(request):
    if request.method == 'POST':
        username = request.POST.get('username')
        password = request.POST.get('password')
        obj_user = models.smartUser.objects.filter(username=username, password=password)
        if obj_user:

            # 将当前登录成功的用户信息写入session中
            print(obj_user[0].id)
            request.session['id'] = obj_user[0].id
            if username:
                request.session['name'] = username
            if password:
                request.session['password'] = password

            # 重定向到后台管理首页
            return redirect('shouye')

        else:
            # 登录失败
            messages.error(request, '用户名或密码不正确')
            return render(request, 'login.html')

    else:
        return render(request, 'login.html')


def register(request):
    if request.method == 'POST':
        username = request.POST.get('username')
        email = request.POST.get('email')
        password = request.POST.get('password')
        user_list = models.smartUser.objects.filter(username=username)
        if user_list:
            messages.error(request, '该用户名已存在')
            return render(request, 'login.html')
        else:
            username = models.smartUser.objects.create(username=username, password=password, email=email)
            username.save()
            # return redirect('login')
            return render(request, 'login.html')
    else:
        return render(request, 'denglu.html')


def love_list(request):
    uid = request.session['id']
    liulan_list_love = models.liulan.objects.filter(who_ll_id=uid)

    love_lable_list = []
    for love in liulan_list_love:
        n1 = love.ll_relation_book.label_1
        n2 = love.ll_relation_book.label_2
        n3 = love.ll_relation_book.label_3
        love_lable_list.append(n1)
        love_lable_list.append(n2)
        love_lable_list.append(n3)
    print(love_lable_list)

    # 去除空值
    love_lable_list = [i for i in love_lable_list if i != '']

    print('==================')
    print(love_lable_list)
    # 传入列表
    labe_like_list = Counter(love_lable_list)
    # 使用most_common()函数
    result = labe_like_list.most_common()
    # 将结果打印出来
    print(result)
    # 将出现最多的元素以及出现次数打印出来
    print('出现最多的元素为：{}，出现的次数为{}'.format(result[0][0], result[0][1]))

    print(result[0][0])

    die = {'i': 123, 'e': 456}
    print(die['i'])





def shouye(request):
    if request.method == 'POST':

        return render(request, 'shouye.html')
    else:

        zh_book_list = reader_zh_book_list()
        zh_book_list_top10 = zh_book_list[:10]
        zh_book_list_top_more = zh_book_list[10:]
        zh_book_dic_top10 = reader_zh_top10_book()
        zh_label_dic_top = reader_zh_label()

        boy_ping_list_num = boy_ping()
        gril_ping_list_num = gril_ping()


        tiezi_list = models.Diary.objects.all()

        smartbookslist = models.smartbook.objects.all()

        lady_bookslist_1 = models.smartbook.objects.filter(label_1="都市娱乐")
        lady_bookslist_2 = models.smartbook.objects.filter(label_2="都市娱乐")
        lady_bookslist_3 = models.smartbook.objects.filter(label_3="都市娱乐")
        lady_bookslist_4 = models.smartbook.objects.filter(label_1="武侠仙侠")
        lady_bookslist_5 = models.smartbook.objects.filter(label_2="武侠仙侠")
        lady_bookslist_6 = models.smartbook.objects.filter(label_3="武侠仙侠")
        lady_bookslist_7 = models.smartbook.objects.filter(label_1="次元幻想")
        lady_bookslist_8 = models.smartbook.objects.filter(label_2="次元幻想")
        lady_bookslist_9 = models.smartbook.objects.filter(label_3="次元幻想")

        lady_list = list(lady_bookslist_1) + list(lady_bookslist_2) + list(lady_bookslist_3) + list(
            lady_bookslist_4) + list(lady_bookslist_5) + list(lady_bookslist_6) + list(lady_bookslist_7) + list(
            lady_bookslist_8) + list(lady_bookslist_9)

        print(lady_list)
        lady_bookslist = list(set(lady_list))  # 内置函数set(),创建一个无序不重复元素集，删除重复元素

        man_bookslist_1 = models.smartbook.objects.filter(label_1="奇幻玄幻")
        man_bookslist_2 = models.smartbook.objects.filter(label_2="奇幻玄幻")
        man_bookslist_3 = models.smartbook.objects.filter(label_3="奇幻玄幻")
        man_bookslist_4 = models.smartbook.objects.filter(label_1="历史军事")
        man_bookslist_5 = models.smartbook.objects.filter(label_2="历史军事")
        man_bookslist_6 = models.smartbook.objects.filter(label_3="历史军事")
        man_bookslist_7 = models.smartbook.objects.filter(label_1="武侠仙侠")
        man_bookslist_8 = models.smartbook.objects.filter(label_2="武侠仙侠")
        man_bookslist_9 = models.smartbook.objects.filter(label_3="武侠仙侠")

        man_list = list(man_bookslist_1) + list(man_bookslist_2) + list(man_bookslist_3) + list(man_bookslist_4) + list(
            man_bookslist_5) + list(man_bookslist_6) + list(man_bookslist_7) + list(man_bookslist_8) + list(
            man_bookslist_9)

        man_bookslist = list(set(man_list))  # 内置函数set(),创建一个无序不重复元素集，删除重复元素

        fl_qh_list_1 = models.smartbook.objects.filter(label_1="奇幻玄幻")
        fl_qh_list_2 = models.smartbook.objects.filter(label_2="奇幻玄幻")
        fl_qh_list_3 = models.smartbook.objects.filter(label_3="奇幻玄幻")

        fl_qh = list(fl_qh_list_1) + list(fl_qh_list_2) + list(fl_qh_list_3)
        fl_qh_list = list(set(fl_qh))

        fl_wx_list_1 = models.smartbook.objects.filter(label_1="武侠仙侠")
        fl_wx_list_2 = models.smartbook.objects.filter(label_2="武侠仙侠")
        fl_wx_list_3 = models.smartbook.objects.filter(label_3="武侠仙侠")

        fl_wx = list(fl_wx_list_1) + list(fl_wx_list_2) + list(fl_wx_list_3)
        fl_wx_list = list(set(fl_wx))

        fl_lz_list_1 = models.smartbook.objects.filter(label_1="历史军事")
        fl_lz_list_2 = models.smartbook.objects.filter(label_2="历史军事")
        fl_lz_list_3 = models.smartbook.objects.filter(label_3="历史军事")

        fl_lz = list(fl_lz_list_1) + list(fl_lz_list_2) + list(fl_lz_list_3)
        fl_lz_list = list(set(fl_lz))

        fl_dy_list_1 = models.smartbook.objects.filter(label_1="都市娱乐")
        fl_dy_list_2 = models.smartbook.objects.filter(label_2="都市娱乐")
        fl_dy_list_3 = models.smartbook.objects.filter(label_3="都市娱乐")

        fl_dy = list(fl_dy_list_1) + list(fl_dy_list_2) + list(fl_dy_list_3)
        fl_dy_list = list(set(fl_dy))

        fl_jt_list_1 = models.smartbook.objects.filter(label_1="竞技同人")
        fl_jt_list_2 = models.smartbook.objects.filter(label_2="竞技同人")
        fl_jt_list_3 = models.smartbook.objects.filter(label_3="竞技同人")

        fl_jt = list(fl_jt_list_1) + list(fl_jt_list_2) + list(fl_jt_list_3)
        fl_jt_list = list(set(fl_jt))

        fl_ky_list_1 = models.smartbook.objects.filter(label_1="科幻游戏")
        fl_ky_list_2 = models.smartbook.objects.filter(label_2="科幻游戏")
        fl_ky_list_3 = models.smartbook.objects.filter(label_3="科幻游戏")

        fl_ky = list(fl_ky_list_1) + list(fl_ky_list_2) + list(fl_ky_list_3)
        fl_ky_list = list(set(fl_ky))

        fl_xl_list_1 = models.smartbook.objects.filter(label_1="悬疑灵异")
        fl_xl_list_2 = models.smartbook.objects.filter(label_2="悬疑灵异")
        fl_xl_list_3 = models.smartbook.objects.filter(label_3="悬疑灵异")

        fl_xl = list(fl_xl_list_1) + list(fl_xl_list_2) + list(fl_xl_list_3)
        fl_xl_list = list(set(fl_xl))

        fl_ch_list_1 = models.smartbook.objects.filter(label_1="次元幻想")
        fl_ch_list_2 = models.smartbook.objects.filter(label_2="次元幻想")
        fl_ch_list_3 = models.smartbook.objects.filter(label_3="次元幻想")

        fl_ch = list(fl_ch_list_1) + list(fl_ch_list_2) + list(fl_ch_list_3)
        fl_ch_list = list(set(fl_ch))

        fl_kj_list_1 = models.smartbook.objects.filter(label_1="科普教育")
        fl_kj_list_2 = models.smartbook.objects.filter(label_2="科普教育")
        fl_kj_list_3 = models.smartbook.objects.filter(label_3="科普教育")

        fl_kj = list(fl_kj_list_1) + list(fl_kj_list_2) + list(fl_kj_list_3)
        fl_kj_list = list(set(fl_kj))

        fl_qd_list_1 = models.smartbook.objects.filter(label_1="期刊读物")
        fl_qd_list_2 = models.smartbook.objects.filter(label_2="期刊读物")
        fl_qd_list_3 = models.smartbook.objects.filter(label_3="期刊读物")

        fl_qd = list(fl_qd_list_1) + list(fl_qd_list_2) + list(fl_qd_list_3)
        fl_qd_list = list(set(fl_qd))

        user_id = request.session.get('id')  # 获取session信息
        print(user_id)
        shujia_book_list = models.goumai.objects.filter(who_gm_id=user_id)
        sj_book_list = []
        for sj_book in shujia_book_list:
            sj_book_list = sj_book_list + list(models.smartbook.objects.filter(id=sj_book.gm_relation_book_id))

        uid = request.session['id']

        user_xx_list = models.smartUser.objects.filter(id=uid)

        username = user_xx_list[0].username
        email = user_xx_list[0].email
        age = user_xx_list[0].age
        sex = user_xx_list[0].sex
        xianju = user_xx_list[0].xianju
        jianjie = user_xx_list[0].jianjie
        touxiang = user_xx_list[0].touxiang
        year = user_xx_list[0].year
        month = user_xx_list[0].month
        data = user_xx_list[0].data
        money = user_xx_list[0].money

        liulan_list = models.liulan.objects.filter(who_ll_id=uid)
        ll_book_list = []
        for ll_book in liulan_list:
            ll_book_list = ll_book_list + list(models.smartbook.objects.filter(id=ll_book.ll_relation_book_id))

        gomai_ls_list = models.goumai.objects.filter(who_gm_id=uid)
        gm_ls_book_list = []
        for gm_book in gomai_ls_list:
            gm_ls_book_list = gm_ls_book_list + list(models.smartbook.objects.filter(id=gm_book.gm_relation_book_id))

        wode_zp_list = models.smartbook.objects.filter(who_id=uid)

        wode_sc_list = models.collect.objects.filter(who_clt_id=uid)
        wode_sc_book_list = []
        for wode_sc_book in wode_sc_list:
            wode_sc_book_list = wode_sc_book_list + list(
                models.smartbook.objects.filter(id=wode_sc_book.clt_relation_book_id))

        wode_tiezi_list = models.Diary.objects.filter(diary_author_id=uid)

        # 通过浏览记录表反向查询书籍表统计出那种题材看的最多

        liulan_list_love = models.liulan.objects.filter(who_ll_id=uid)

        love_lable_list = []
        for love in liulan_list_love:
            n1 = love.ll_relation_book.label_1
            n2 = love.ll_relation_book.label_2
            n3 = love.ll_relation_book.label_3
            love_lable_list.append(n1)
            love_lable_list.append(n2)
            love_lable_list.append(n3)
        print(love_lable_list)

        # 去除空值
        love_lable_list = [i for i in love_lable_list if i != '']

        print('==================')
        print(love_lable_list)
        # 传入列表
        labe_like_list = Counter(love_lable_list)
        # 使用most_common()函数
        result = labe_like_list.most_common()
        # 将结果打印出来
        print(result)
        # 将出现最多的元素以及出现次数打印出来
        # print('出现最多的元素为：{}，出现的次数为{}'.format(result[0][0], result[0][1]))
        if result == []:
            most_love_label = "ʕ  •ᴥ•ʔ……"
        else:
            most_love_label = result[0][0]

        zh_love_book_list = []
        if most_love_label == '奇幻玄幻':
            for love_zh_book in zh_book_list:
                if love_zh_book['label'] == '玄幻' or love_zh_book['label'] == '奇幻' or love_zh_book['label'] == '修真':
                    zh_love_book_list.append(love_zh_book)
        elif most_love_label == '武侠仙侠':
            for love_zh_book in zh_book_list:
                if love_zh_book['label'] == '仙侠' or love_zh_book['label'] == '武侠':
                    zh_love_book_list.append(love_zh_book)
        elif most_love_label == '历史军事':
            for love_zh_book in zh_book_list:
                if love_zh_book['label'] == '历史' or love_zh_book['label'] == '架空':
                    zh_love_book_list.append(love_zh_book)
        elif most_love_label == '都市娱乐':
            for love_zh_book in zh_book_list:
                if love_zh_book['label'] == '都市' or love_zh_book['label'] == '商战' or love_zh_book['label'] == '现实' or \
                        love_zh_book['label'] == '重生':
                    zh_love_book_list.append(love_zh_book)
        elif most_love_label == '竞技同人':
            for love_zh_book in zh_book_list:
                if love_zh_book['label'] == '同人':
                    zh_love_book_list.append(love_zh_book)
        elif most_love_label == '科幻游戏':
            for love_zh_book in zh_book_list:
                if love_zh_book['label'] == '游戏' or love_zh_book['label'] == '变身' or love_zh_book['label'] == '进化' or \
                        love_zh_book['label'] == '星际' or love_zh_book['label'] == '穿梭' or love_zh_book[
                    'label'] == '末世' or \
                        love_zh_book['label'] == '科幻':
                    zh_love_book_list.append(love_zh_book)
        elif most_love_label == '悬疑灵异':
            for love_zh_book in zh_book_list:
                if love_zh_book['label'] == '推理' or love_zh_book['label'] == '灵异' or love_zh_book['label'] == '惊悚':
                    zh_love_book_list.append(love_zh_book)
        elif most_love_label == '悬疑灵异':
            for love_zh_book in zh_book_list:
                if love_zh_book['label'] == '推理' or love_zh_book['label'] == '灵异' or love_zh_book['label'] == '惊悚':
                    zh_love_book_list.append(love_zh_book)
        elif most_love_label == '次元幻想':
            for love_zh_book in zh_book_list:
                if love_zh_book['label'] == '变身' or love_zh_book['label'] == '进化' or love_zh_book['label'] == '异术' or \
                        love_zh_book['label'] == '异世' or love_zh_book['label'] == '二次元':
                    zh_love_book_list.append(love_zh_book)

        return render(request, 'shouye.html', locals())  # locals(),
        # 它返回的字典对所有局部变量名称与值相对应。注意包括了所有的局部变量


@csrf_exempt  # 装饰器，此次请求忽略csrf校验
def book_xiangxi(request, B_ID=None, U_ID=None):
    if request.method == 'POST':
        bid = request.POST.get('bid')  # 前端获取被点击的小说id
        uid = request.POST.get('uid')  # session中提取是哪个用户要保存，即当前登录的用户

        # 收藏夹操作
        # 如果收藏夹里尚未有这个收藏：那就收藏这个条目
        # 如果收藏夹里已经有了，那就取消收藏
        fav_list = models.collect.objects.filter(clt_relation_book_id=bid, who_clt_id=uid)  # 找出所有该用户的收藏
        if fav_list:
            models.collect.objects.filter(clt_relation_book_id=bid, who_clt_id=uid).delete()
            return HttpResponse('♡')
        else:
            collect = models.collect.objects.create(clt_relation_book_id=bid, who_clt_id=uid)
            collect.save()
            return HttpResponse('♥')

    else:
        book_id = request.GET['ID']
        UID = request.GET['UID']

        liulan_save = models.liulan.objects.create(ll_relation_book_id=book_id, who_ll_id=UID)
        liulan_save.save()

        if book_id != 'x' and UID != 'x':
            booklist = models.smartbook.objects.filter(id=book_id)
            fabu_id = booklist[0].who_id
            fabulist = models.smartUser.objects.filter(id=fabu_id)

            # 收藏
            fav_list = models.collect.objects.filter(clt_relation_book_id=book_id, who_clt_id=UID)  # 找出所有该用户的收藏
            if fav_list:  # 检查有没有该小说id
                fs = 1  # 已收藏
            else:
                fs = 0  # 未收藏

            # 点赞
            dz_list = models.dianzan.objects.filter(dz_relation_book_id=book_id, who_dz_id=UID)  # 找出所有该用户的点赞
            if dz_list:  # 检查有没有该小说id
                ds = 1  # 已点赞
            else:
                ds = 0  # 未点赞

            shoucang_num = models.collect.objects.filter(clt_relation_book_id=book_id,
                                                         who_clt_id=UID).count  # 查询总数返回int

            dianzan_num = models.dianzan.objects.filter(dz_relation_book_id=book_id, who_dz_id=UID).count  # 查询总数返回int

            fav = {'fav_status': fs, 'dianz_status': ds, 'shoucang_num': shoucang_num, 'dianzan_num': dianzan_num}
            comment_list = models.pinglun.objects.filter(comment_book_id=book_id)  # 从数据库找出该文章的评论数据对象

            return render(request, 'book_xiangxi.html', locals(), {"fav": fav})
        else:
            book_id = B_ID
            UID = U_ID

            booklist = models.smartbook.objects.filter(id=book_id)
            fabu_id = booklist[0].who_id
            fabulist = models.smartUser.objects.filter(id=fabu_id)

            # 收藏
            fav_list = models.collect.objects.filter(clt_relation_book_id=book_id, who_clt_id=UID)  # 找出所有该用户的收藏
            if fav_list:  # 检查有没有该小说id
                fs = 1  # 已收藏
            else:
                fs = 0  # 未收藏

            # 点赞
            dz_list = models.dianzan.objects.filter(dz_relation_book_id=book_id, who_dz_id=UID)  # 找出所有该用户的点赞
            if dz_list:  # 检查有没有该小说id
                ds = 1  # 已点赞
            else:
                ds = 0  # 未点赞

            shoucang_num = models.collect.objects.filter(clt_relation_book_id=book_id).count  # 查询总数返回int

            dianzan_num = models.dianzan.objects.filter(dz_relation_book_id=book_id).count  # 查询总数返回int

            fav = {'fav_status': fs, 'dianz_status': ds, 'shoucang_num': shoucang_num, 'dianzan_num': dianzan_num}

            comment_list = models.pinglun.objects.filter(comment_book_id=book_id)  # 从数据库找出该文章的评论数据对象

            return render(request, 'book_xiangxi.html', locals(), {"fav": fav})


@csrf_exempt  # 装饰器，此次请求忽略csrf校验
def dianzan(request):
    bid3 = request.POST.get('bid3')  # 前端获取被点击的小说id
    uid3 = request.POST.get('uid3')  # session中提取是哪个用户要保存，即当前登录的用户

    print('bid3:', bid3)
    print("uid3:", uid3)

    # 点赞夹操作
    # 如果点赞夹里尚未有这个收藏：那就点赞这个条目
    # 如果点赞夹里已经有了，那就取消点赞
    dianzan_list = models.dianzan.objects.filter(dz_relation_book_id=bid3, who_dz_id=uid3)  # 找出所有该用户的收藏
    if dianzan_list:
        models.dianzan.objects.filter(dz_relation_book_id=bid3, who_dz_id=uid3).delete()

        return HttpResponse('点赞')

    else:
        dz_tb = models.dianzan.objects.create(dz_relation_book_id=bid3, who_dz_id=uid3)
        dz_tb.save()
        return HttpResponse('已点赞')


@csrf_exempt  # 装饰器，此次请求忽略csrf校验
def shoucang(request):
    bid = request.GET['bid']  # 前端获取被点击的小说id
    uid = request.GET['uid']  # session中提取是哪个用户要保存，即当前登录的用户

    print('bid:', bid)
    print("uid:", uid)

    # 收藏夹操作
    # 如果收藏夹里尚未有这个收藏：那就收藏这个条目
    # 如果收藏夹里已经有了，那就取消收藏
    fav_list = models.collect.objects.filter(clt_relation_book_id=bid, who_clt_id=uid)  # 找出所有该用户的收藏
    if fav_list:
        models.collect.objects.filter(clt_relation_book_id=bid, who_clt_id=uid).delete()

        return HttpResponse('♡')

    else:
        collect = models.collect.objects.create(clt_relation_book_id=bid, who_clt_id=uid)
        collect.save()
        return HttpResponse('♥')


@csrf_exempt  # 装饰器，此次请求忽略csrf校验
def pinglun(request):
    if request.session['id']:
        comment_content = request.POST.get('comment_content')  # 评论内容
        article_id = request.POST.get('article_id')  # 评论文章
        pid = request.POST.get('pid')  # 父级评论
        author_id = request.session['id']  # 获取当前用户的ID

        models.pinglun.objects.create(comment_pinglun=comment_content, pre_comment_id=pid, comment_book_id=article_id,
                                      comment_author_id=author_id)  # 将提交的数据保存到数据库中

        article = list(
            models.pinglun.objects.values('id', 'comment_pinglun', 'comment_time', 'comment_author_id',
                                          'comment_book_id', 'pre_comment_id'
                                          ))  # 以键值对的形式取出评论对象，并且转化为列表list类型

        return JsonResponse(article, safe=False)  # JsonResponse返回JSON字符串，自动序列化，如果不是字典类型，则需要添加safe参数为False
    else:
        return redirect('login')


def reade_book(request):
    if request.method == 'POST':
        return render(request, 'reade_book.html')
    else:
        bid = request.GET['ID']  # 前端获取被点击的小说id
        uid = request.GET['UID']  # session中提取是哪个用户要读，即当前登录的用户

        gomai_list = models.goumai.objects.filter(gm_relation_book_id=bid, who_gm_id=uid)

        smartbook_list = models.smartbook.objects.filter(id=bid)
        if gomai_list:
            print(smartbook_list[0].bookdata)

            book_name = smartbook_list[0].bookname
            book_author = smartbook_list[0].author

            path = 'media/' + str(smartbook_list[0].bookdata)

            booktxt = []

            f = open(path, 'r', encoding='utf-8', errors="ignore")
            attack_method = f.read()
            attack_method = attack_method.split('\n')
            attack_method = list(dict.fromkeys(attack_method))  ## 这里是去重
            f.close()

            return render(request, 'reade_book.html', {"attack_method": attack_method, 'book_name': book_name})
        else:
            # 用户未购买该书，跳转到购买页面
            messages.error(request, '抱歉，您还没有购买该书籍')
            book_name = smartbook_list[0].bookname
            book_author = smartbook_list[0].author
            book_img = smartbook_list[0].bookimg
            book_price = smartbook_list[0].price
            fabu_ren = smartbook_list[0].who_id

            gomai_ren = uid

            book_id = bid

            fabu_name = models.smartUser.objects.filter(id=fabu_ren)[0].username
            gomai_name = models.smartUser.objects.filter(id=gomai_ren)[0].username

            gomai = {'book_name': book_name, 'book_author': book_author,
                     'book_img': book_img, 'book_price': book_price,
                     'fabu_ren': fabu_name, 'gomai_ren': gomai_name,
                     'fabu_ren_id': fabu_ren, 'gomai_ren_id': gomai_ren,
                     'book_id': book_id}

            return render(request, 'gomai.html', {"gomai": gomai})


def gomai(request):
    if request.method == 'POST':
        bid = request.POST.get('book_id')
        uid = request.POST.get('user_id')

        smartbook_list = models.smartbook.objects.filter(id=bid)

        book_name = smartbook_list[0].bookname
        book_author = smartbook_list[0].author
        book_img = smartbook_list[0].bookimg
        book_price = smartbook_list[0].price
        fabu_ren = smartbook_list[0].who_id

        gomai_ren = uid

        book_id = bid

        fabu_name = models.smartUser.objects.filter(id=fabu_ren)[0].username
        gomai_name = models.smartUser.objects.filter(id=gomai_ren)[0].username

        gomai = {'book_name': book_name, 'book_author': book_author,
                 'book_img': book_img, 'book_price': book_price,
                 'fabu_ren': fabu_name, 'gomai_ren': gomai_name,
                 'fabu_ren_id': fabu_ren, 'gomai_ren_id': gomai_ren,
                 'book_id': book_id}

        return render(request, 'gomai.html', {"gomai": gomai})
    else:
        return render(request, 'gomai.html')


def gomai_qd(request):
    if request.method == 'POST':
        return render(request, 'gomai.html')
    else:
        book_id = request.GET['book_id']
        gomai_id = request.GET['gomai_id']
        fabu_id = request.GET['fabu_id']

        gm_su = {'book_id': book_id, 'gomai_id': gomai_id}

        book_price = models.smartbook.objects.filter(id=book_id)[0].price
        gomai_ren_money = models.smartUser.objects.filter(id=gomai_id)[0].money
        fabu_ren_money = models.smartUser.objects.filter(id=fabu_id)[0].money

        book_img = models.smartbook.objects.filter(id=book_id)[0].bookimg
        book_name = models.smartbook.objects.filter(id=book_id)[0].bookname
        book_author = models.smartbook.objects.filter(id=book_id)[0].author

        gm_su_2 = {'book_id': book_id, 'gomai_id': gomai_id, 'book_img': book_img, 'book_name': book_name,
                   'book_author': book_author}

        if book_price == '':
            book_price = 0
        else:
            book_price = int(book_price)

        if gomai_ren_money == '':
            gomai_ren_money = 0
        else:
            gomai_ren_money = int(gomai_ren_money)

        if fabu_ren_money == '':
            fabu_ren_money = 0
        else:
            fabu_ren_money = int(fabu_ren_money)

        if gomai_ren_money < book_price:
            messages.error(request, '抱歉，您的货币不够哦~')

            return render(request, 'chongzhi.html', {'gm_su': gm_su})

        else:
            messages.error(request, '购买成功')

            fb_money = fabu_ren_money + book_price
            gm_money = gomai_ren_money - book_price

            gomai_ren = models.smartUser.objects.get(id=gomai_id)
            gomai_ren.money = str(gm_money)
            gomai_ren.save()

            fabu_ren = models.smartUser.objects.get(id=fabu_id)
            fabu_ren.money = str(fb_money)
            fabu_ren.save()

            gomai_list = models.goumai.objects.create(gm_relation_book_id=book_id, who_gm_id=gomai_id)
            gomai_list.save()

            return render(request, 'gomai_success.html', {'gm_su_2': gm_su_2})


def chongzhi(request):
    if request.method == 'POST':
        money = request.POST.get('money')
        uid = request.POST.get('user_id')

        cz_ren = models.smartUser.objects.get(id=uid)
        cz_ren_m = models.smartUser.objects.filter(id=uid)[0].money
        if cz_ren_m == '':
            cz_money = 0 + int(money)
        else:
            cz_money = int(cz_ren_m) + int(money)

        cz_ren.money = str(cz_money)
        cz_ren.save()

        # 重定向到后台管理首页
        return redirect('shouye')
    else:
        return render(request, 'chongzhi.html')


def addbook(request):
    if request.method == 'POST':
        bookname = request.POST.get('bookname')
        author = request.POST.get('author')
        price = request.POST.get('price')

        kind1 = request.POST.get('kind-1')
        kind2 = request.POST.get('kind-2')
        kind3 = request.POST.get('kind-3')
        kind4 = request.POST.get('kind-4')
        kind5 = request.POST.get('kind-5')
        kind6 = request.POST.get('kind-6')
        kind7 = request.POST.get('kind-7')
        kind8 = request.POST.get('kind-8')
        kind9 = request.POST.get('kind-9')
        kind10 = request.POST.get('kind-10')

        kindlist = []
        kindlist.append(kind1)
        kindlist.append(kind2)
        kindlist.append(kind3)
        kindlist.append(kind4)
        kindlist.append(kind5)
        kindlist.append(kind6)
        kindlist.append(kind7)
        kindlist.append(kind8)
        kindlist.append(kind9)
        kindlist.append(kind10)
        print(kindlist)

        klist = [k for k in kindlist if k != None]
        print(klist)
        print(len(klist))

        if len(klist) == 3:
            k1 = klist[0]
            k2 = klist[1]
            k3 = klist[2]
        elif len(klist) == 2:
            k1 = klist[0]
            k2 = klist[1]
            k3 = ''
        elif len(klist) == 1:
            k1 = klist[0]
            k2 = ''
            k3 = ''

        jianjie = request.POST.get('jianjie')
        fabu = request.POST.get('fabu-id')
        print(fabu)

        bookimg = request.FILES.get('bookimg')

        bookdata = request.FILES.get('bookdata')

        print(jianjie)

        if bookname == '':
            messages.error(request, '您还没有填写书名')
            return render(request, 'addbook.html')
        elif author == '':
            messages.error(request, '您还没有填写作者')
            return render(request, 'addbook.html')
        elif price == '':
            messages.error(request, '您还没有填写价格')
            return render(request, 'addbook.html')
        elif jianjie == '':
            messages.error(request, '您还没有填写简介')
            return render(request, 'addbook.html')
        elif bookimg == None:
            messages.error(request, '您还没有上传封面')
            return render(request, 'addbook.html')
        elif bookdata == None:
            messages.error(request, '您还没有上传文档文件')
            return render(request, 'addbook.html')

        else:
            book_list = models.smartbook.objects.filter(bookname=bookname, author=author, who_id=fabu)

            error_name = []

            if book_list:
                messages.error(request, '该书您已经发布过了')
                return render(request, 'addbook.html')
            else:
                newbook = models.smartbook.objects.create(bookname=bookname, price=price, author=author,
                                                          jianjie=jianjie,
                                                          label_1=k1, label_2=k2, label_3=k3,
                                                          bookimg=bookimg, bookdata=bookdata, who_id=fabu)
                newbook.save()
                return redirect('shouye')
    else:
        return render(request, 'addbook.html')


def xiugai_geren_xinxi(request):
    if request.method == 'POST':
        sex = request.POST.get('sex')
        age = request.POST.get('age')
        year = request.POST.get('year')
        month = request.POST.get('month')
        data = request.POST.get('data')
        xianju = request.POST.get('xianju')
        email = request.POST.get('email')
        touxiang = request.FILES.get('touxiang_img')
        jianjie = request.POST.get('jianjie')
        uid = request.session['id']

        if sex == None:
            messages.error(request, '请至少选择一种性别啊')
            # 重定向到后台管理首页
            return redirect('xiugai_geren_xinxi')

        else:
            if touxiang == None:
                update_use = models.smartUser.objects.filter(id=uid).update(sex=sex, age=age, year=year, month=month,
                                                                            data=data, xianju=xianju, email=email,
                                                                            jianjie=jianjie)

                messages.error(request, '个人信息修改成功')
                # 重定向到后台管理首页
                return redirect('shouye')
            else:

                try:
                    user_xx_list = models.smartUser.objects.filter(id=uid)
                    touxiang_old = user_xx_list[0].touxiang
                    print(touxiang_old)
                    path = "media/" + str(touxiang_old)
                    print(path)
                    os.remove(path)  # 删除文件
                except:
                    print("删除失败")
                update_use = models.smartUser.objects.filter(id=uid).update(sex=sex, age=age, year=year, month=month,
                                                                            data=data, xianju=xianju, email=email,
                                                                            jianjie=jianjie)

                update_use_save = models.smartUser.objects.get(id=uid)
                update_use_save.touxiang = touxiang
                update_use_save.save()

                messages.error(request, '个人信息修改成功')
                # 重定向到后台管理首页
                return redirect('shouye')



    else:
        # os.remove("media/project_img/beijing.jpg") 删除文件

        uid = request.session['id']

        user_xx_list = models.smartUser.objects.filter(id=uid)

        username = user_xx_list[0].username
        email = user_xx_list[0].email
        age = user_xx_list[0].age
        sex = user_xx_list[0].sex
        xianju = user_xx_list[0].xianju
        jianjie = user_xx_list[0].jianjie
        touxiang = user_xx_list[0].touxiang
        year = user_xx_list[0].year
        month = user_xx_list[0].month
        data = user_xx_list[0].data

        return render(request, 'xiugai_geren_xinxi.html', locals())


def liulan_delete(request):
    if request.method == 'POST':
        return render(request, 'shouye.html')
    else:
        id = request.GET['ID']
        models.liulan.objects.get(id=id).delete()
        messages.error(request, "删除成功")
        # 重定向到后台管理首页
        return redirect('shouye')


def collect_delete(request):
    if request.method == 'POST':
        return render(request, 'shouye.html')
    else:
        id = request.GET['ID']
        models.collect.objects.get(id=id).delete()
        messages.error(request, "删除成功")
        # 重定向到后台管理首页
        return redirect('shouye')


def xiugai_zuopin(request):
    if request.method == 'POST':
        bookname = request.POST.get('bookname')
        author = request.POST.get('author')
        print(author)
        price = request.POST.get('price')
        kind1 = request.POST.get('kind-1')
        kind2 = request.POST.get('kind-2')
        kind3 = request.POST.get('kind-3')
        kind4 = request.POST.get('kind-4')
        kind5 = request.POST.get('kind-5')
        kind6 = request.POST.get('kind-6')
        kind7 = request.POST.get('kind-7')
        kind8 = request.POST.get('kind-8')
        kind9 = request.POST.get('kind-9')
        kind10 = request.POST.get('kind-10')

        kindlist = []
        kindlist.append(kind1)
        kindlist.append(kind2)
        kindlist.append(kind3)
        kindlist.append(kind4)
        kindlist.append(kind5)
        kindlist.append(kind6)
        kindlist.append(kind7)
        kindlist.append(kind8)
        kindlist.append(kind9)
        kindlist.append(kind10)
        print(kindlist)

        klist = [k for k in kindlist if k != None]
        print(klist)
        print(len(klist))

        if len(klist) == 3:
            k1 = klist[0]
            k2 = klist[1]
            k3 = klist[2]
        elif len(klist) == 2:
            k1 = klist[0]
            k2 = klist[1]
            k3 = ''
        elif len(klist) == 1:
            k1 = klist[0]
            k2 = ''
            k3 = ''
        else:
            k1 = ''
            k2 = ''
            k3 = ''

        jianjie = request.POST.get('jianjie')
        bid = request.POST.get('bid')
        print('bid' + bid)

        bookimg = request.FILES.get('bookimg')

        bookdata = request.FILES.get('bookdata')

        if bookname == '':
            error = '您还没有填写书名'
            return render(request, 'error.html', locals())
        elif author == '':
            error = '您还没有填写作者'
            return render(request, 'error.html', locals())
        elif price == '':
            error = '您还没有填写价格'
            return render(request, 'error.html', locals())
        elif jianjie == '':
            error = '您还没有填写简介'
            return render(request, 'error.html', locals())

        if k1 == '':
            if jianjie == '':
                if bookimg == None:
                    if bookdata == None:
                        update_zuopin = models.smartbook.objects.filter(id=bid).update(bookname=bookname, author=author,
                                                                                       price=price)
                        messages.error(request, '作品修改成功')
                        return render(request, 'shouye.html')

                    else:
                        try:
                            book_xx_list = models.smartUser.objects.filter(id=bid)
                            bookdata_old = book_xx_list[0].bookdata
                            print(bookdata_old)
                            path = "media/" + str(bookdata_old)
                            print(path)
                            os.remove(path)  # 删除文件
                        except:
                            print("删除失败")
                        update_zuopin = models.smartbook.objects.filter(id=bid).update(bookname=bookname, author=author,
                                                                                       price=price)
                        update_zuopin.bookdata = bookdata
                        update_zuopin.save()

                        messages.error(request, '作品信息修改成功')
                        return render(request, 'shouye.html')
                else:

                    if bookdata == None:

                        try:
                            book_xx_list = models.smartUser.objects.filter(id=bid)
                            bookimg_old = book_xx_list[0].bookimg
                            print(bookimg_old)
                            path = "media/" + str(bookimg_old)
                            print(path)
                            os.remove(path)  # 删除文件
                        except:
                            print("删除失败")

                        update_zuopin = models.smartbook.objects.filter(id=bid).update(bookname=bookname, author=author,
                                                                                       price=price)
                        update_zuopin.bookimg = bookimg
                        update_zuopin.save()

                        messages.error(request, '作品信息修改成功')
                        return render(request, 'shouye.html')

                    else:

                        try:
                            book_xx_list = models.smartUser.objects.filter(id=bid)
                            bookimg_old = book_xx_list[0].bookimg
                            print(bookimg_old)
                            path = "media/" + str(bookimg_old)
                            print(path)
                            os.remove(path)  # 删除文件
                        except:
                            print("删除失败")

                        try:
                            book_xx_list = models.smartUser.objects.filter(id=bid)
                            bookdata_old = book_xx_list[0].bookdata
                            print(bookdata_old)
                            path = "media/" + str(bookdata_old)
                            print(path)
                            os.remove(path)  # 删除文件
                        except:
                            print("删除失败")

                        update_zuopin = models.smartbook.objects.filter(id=bid).update(bookname=bookname, author=author,
                                                                                       price=price)
                        update_zuopin.bookimg = bookimg
                        update_zuopin.bookdata = bookdata
                        update_zuopin.save()

                        messages.error(request, '作品信息修改成功')
                        return render(request, 'shouye.html')
            else:
                if bookimg == None:
                    if bookdata == None:
                        update_zuopin = models.smartbook.objects.filter(id=bid).update(bookname=bookname, author=author,
                                                                                       jianjie=jianjie, price=price)
                        messages.error(request, '作品修改成功')
                        # 重定向到后台管理首页
                        return redirect('shouye')

                    else:
                        try:
                            book_xx_list = models.smartUser.objects.filter(id=bid)
                            bookdata_old = book_xx_list[0].bookdata
                            print(bookdata_old)
                            path = "media/" + str(bookdata_old)
                            print(path)
                            os.remove(path)  # 删除文件
                        except:
                            print("删除失败")
                        update_zuopin = models.smartbook.objects.filter(id=bid).update(bookname=bookname, author=author,
                                                                                       jianjie=jianjie, price=price)
                        update_zuopin.bookdata = bookdata
                        update_zuopin.save()

                        messages.error(request, '作品信息修改成功')
                        return render(request, 'shouye.html')
                else:

                    if bookdata == None:

                        try:
                            book_xx_list = models.smartUser.objects.filter(id=bid)
                            bookimg_old = book_xx_list[0].bookimg
                            print(bookimg_old)
                            path = "media/" + str(bookimg_old)
                            print(path)
                            os.remove(path)  # 删除文件
                        except:
                            print("删除失败")

                        update_zuopin = models.smartbook.objects.filter(id=bid).update(bookname=bookname, author=author,
                                                                                       jianjie=jianjie, price=price)
                        update_zuopin.bookimg = bookimg
                        update_zuopin.save()

                        messages.error(request, '作品信息修改成功')
                        # 重定向到后台管理首页
                        return redirect('shouye')

                    else:

                        try:
                            book_xx_list = models.smartUser.objects.filter(id=bid)
                            bookimg_old = book_xx_list[0].bookimg
                            print(bookimg_old)
                            path = "media/" + str(bookimg_old)
                            print(path)
                            os.remove(path)  # 删除文件
                        except:
                            print("删除失败")

                        try:
                            book_xx_list = models.smartUser.objects.filter(id=bid)
                            bookdata_old = book_xx_list[0].bookdata
                            print(bookdata_old)
                            path = "media/" + str(bookdata_old)
                            print(path)
                            os.remove(path)  # 删除文件
                        except:
                            print("删除失败")

                        update_zuopin = models.smartbook.objects.filter(id=bid).update(bookname=bookname, author=author,
                                                                                       jianjie=jianjie, price=price)
                        update_zuopin.bookimg = bookimg
                        update_zuopin.bookdata = bookdata
                        update_zuopin.save()

                        messages.error(request, '作品信息修改成功')
                        # 重定向到后台管理首页
                        return redirect('shouye')

        else:
            if jianjie == '':
                if bookimg == None:
                    if bookdata == None:
                        update_zuopin = models.smartbook.objects.filter(id=bid).update(bookname=bookname, author=author,
                                                                                       price=price)
                        messages.error(request, '作品修改成功')
                        # 重定向到后台管理首页
                        return redirect('shouye')

                    else:
                        try:
                            book_xx_list = models.smartUser.objects.filter(id=bid)
                            bookdata_old = book_xx_list[0].bookdata
                            print(bookdata_old)
                            path = "media/" + str(bookdata_old)
                            print(path)
                            os.remove(path)  # 删除文件
                        except:
                            print("删除失败")
                        update_zuopin = models.smartbook.objects.filter(id=bid).update(bookname=bookname, author=author,
                                                                                       price=price)
                        update_zuopin.bookdata = bookdata
                        update_zuopin.save()

                        messages.error(request, '作品信息修改成功')
                        # 重定向到后台管理首页
                        return redirect('shouye')
                else:

                    if bookdata == None:

                        try:
                            book_xx_list = models.smartUser.objects.filter(id=bid)
                            bookimg_old = book_xx_list[0].bookimg
                            print(bookimg_old)
                            path = "media/" + str(bookimg_old)
                            print(path)
                            os.remove(path)  # 删除文件
                        except:
                            print("删除失败")

                        update_zuopin = models.smartbook.objects.filter(id=bid).update(bookname=bookname, author=author,
                                                                                       price=price)
                        update_zuopin.bookimg = bookimg
                        update_zuopin.save()

                        messages.error(request, '作品信息修改成功')
                        # 重定向到后台管理首页
                        return redirect('shouye')

                    else:

                        try:
                            book_xx_list = models.smartUser.objects.filter(id=bid)
                            bookimg_old = book_xx_list[0].bookimg
                            print(bookimg_old)
                            path = "media/" + str(bookimg_old)
                            print(path)
                            os.remove(path)  # 删除文件
                        except:
                            print("删除失败")

                        try:
                            book_xx_list = models.smartUser.objects.filter(id=bid)
                            bookdata_old = book_xx_list[0].bookdata
                            print(bookdata_old)
                            path = "media/" + str(bookdata_old)
                            print(path)
                            os.remove(path)  # 删除文件
                        except:
                            print("删除失败")

                        update_zuopin = models.smartbook.objects.filter(id=bid).update(bookname=bookname, author=author,
                                                                                       price=price)
                        update_zuopin.bookimg = bookimg
                        update_zuopin.bookdata = bookdata
                        update_zuopin.save()

                        messages.error(request, '作品信息修改成功')
                        # 重定向到后台管理首页
                        return redirect('shouye')
            else:
                if bookimg == None:
                    if bookdata == None:
                        update_zuopin = models.smartbook.objects.filter(id=bid).update(bookname=bookname, author=author,
                                                                                       jianjie=jianjie, price=price)
                        messages.error(request, '作品修改成功')
                        # 重定向到后台管理首页
                        return redirect('shouye')

                    else:
                        try:
                            book_xx_list = models.smartUser.objects.filter(id=bid)
                            bookdata_old = book_xx_list[0].bookdata
                            print(bookdata_old)
                            path = "media/" + str(bookdata_old)
                            print(path)
                            os.remove(path)  # 删除文件
                        except:
                            print("删除失败")
                        update_zuopin = models.smartbook.objects.filter(id=bid).update(bookname=bookname, author=author,
                                                                                       jianjie=jianjie, price=price)
                        update_zuopin.bookdata = bookdata
                        update_zuopin.save()

                        messages.error(request, '作品信息修改成功')
                        # 重定向到后台管理首页
                        return redirect('shouye')
                else:

                    if bookdata == None:

                        try:
                            book_xx_list = models.smartUser.objects.filter(id=bid)
                            bookimg_old = book_xx_list[0].bookimg
                            print(bookimg_old)
                            path = "media/" + str(bookimg_old)
                            print(path)
                            os.remove(path)  # 删除文件
                        except:
                            print("删除失败")

                        update_zuopin = models.smartbook.objects.filter(id=bid).update(bookname=bookname, author=author,
                                                                                       jianjie=jianjie, price=price)
                        update_zuopin.bookimg = bookimg
                        update_zuopin.save()

                        messages.error(request, '作品信息修改成功')
                        # 重定向到后台管理首页
                        return redirect('shouye')

                    else:

                        try:
                            book_xx_list = models.smartUser.objects.filter(id=bid)
                            bookimg_old = book_xx_list[0].bookimg
                            print(bookimg_old)
                            path = "media/" + str(bookimg_old)
                            print(path)
                            os.remove(path)  # 删除文件
                        except:
                            print("删除失败")

                        try:
                            book_xx_list = models.smartUser.objects.filter(id=bid)
                            bookdata_old = book_xx_list[0].bookdata
                            print(bookdata_old)
                            path = "media/" + str(bookdata_old)
                            print(path)
                            os.remove(path)  # 删除文件
                        except:
                            print("删除失败")

                        update_zuopin = models.smartbook.objects.filter(id=bid).update(bookname=bookname, author=author,
                                                                                       jianjie=jianjie, price=price)
                        update_zuopin.bookimg = bookimg
                        update_zuopin.bookdata = bookdata
                        update_zuopin.save()

                        messages.error(request, '作品信息修改成功')
                        # 重定向到后台管理首页
                        return redirect('shouye')

    else:
        # 重定向到后台管理首页
        bid = request.GET['ID']
        zuo_pin_list = models.smartbook.objects.filter(id=bid)

        return render(request, 'xiugai_zuopin.html', locals())


def sousuo(request):
    if request.method == 'POST':
        keyword = request.POST.get('keyword')
        if keyword == '':
            messages.error(request, '你还没输入搜索词呢')
            # 重定向到后台管理首页
            return redirect('shouye')
        else:
            smartbookslist = models.smartbook.objects.filter(bookname=keyword)
            if smartbookslist:
                return render(request, 'sousuo.html', locals())
            else:
                smartbookslist2 = models.smartbook.objects.filter(author=keyword)
                return render(request, 'sousuo.html', locals())

    else:
        return render(request, 'sousuo.html', locals())


def tiezi(request):
    if request.method == 'POST':
        uid = request.session['id']
        tiezi = request.POST.get('tiezi')

        if tiezi == '':
            messages.error(request, '你还没写呢！')
            return redirect('tiezi')
        else:
            newtiezi = models.Diary.objects.create(diary_text=tiezi, diary_author_id=uid)
            newtiezi.save()
            messages.error(request, '发布成功')
            # 重定向到后台管理首页
            return redirect('shouye')
    else:
        uid = request.session['id']

        touxiang_list = models.smartUser.objects.filter(id=uid)
        touxiang = touxiang_list[0].touxiang
        return render(request, 'tiezi.html', locals())


def xiugai_tiezi(request):
    if request.method == 'POST':
        uid = request.session['id']
        tid = request.POST.get('tiezi-id')
        tiezi = request.POST.get('tiezi')

        if tiezi == '':
            messages.error(request, '你还没写呢！')
            return redirect('xiugai_tiezi')
        else:
            newtiezi = models.Diary.objects.filter(id=tid).update(diary_text=tiezi)
            messages.error(request, '修改成功')
            # 重定向到后台管理首页
            return redirect('shouye')
    else:
        uid = request.session['id']
        id = request.GET['ID']

        touxiang_list = models.smartUser.objects.filter(id=uid)
        touxiang = touxiang_list[0].touxiang
        print(touxiang)

        tiezi_text_list = models.Diary.objects.filter(id=id)
        tiezi_text = tiezi_text_list[0].diary_text

        print("===========================")
        print(tiezi_text)
        return render(request, 'xiugai_tiezi.html', locals())


def tiezi_delete(request):
    if request.method == 'POST':
        return render(request, 'shouye.html')
    else:
        id = request.GET['ID']
        models.Diary.objects.get(id=id).delete()
        messages.error(request, "删除成功")
        # 重定向到后台管理首页
        return redirect('shouye')
