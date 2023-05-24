from django.shortcuts import render

# Create your views here.

from django.shortcuts import redirect, HttpResponse

from xiaogu import models

from django.shortcuts import render, redirect, HttpResponse
from django.shortcuts import render
from xiaogu.models import WangUser

from django import forms
# Create your views here.
from xiaogu import models

import jieba

jieba.setLogLevel(jieba.logging.INFO)
import jieba.posseg
import jieba.analyse
import jieba.finalseg
import jieba.lac_small
import networkx as nx
import matplotlib
import re
import collections
import numpy as np
import jieba
import wordcloud
from PIL import Image
import matplotlib.pyplot as plt
import jieba.posseg as psg

from gensim.models import word2vec
# 引入日志配置
import logging

logging.basicConfig(format='%(asctime)s : %(levelname)s : %(message)s', level=logging.INFO)

matplotlib.rcParams['font.sans-serif'] = ['SimHei']


##定义UserForm表单用于注册和登录页面，ChangeForm表单用于修改密码页面

class UserForm(forms.Form):
    username = forms.CharField(label='用户名')
    password = forms.CharField(label='密码', widget=forms.PasswordInput())


# last_login = forms.DateTimeField()

class ChangeForm(forms.Form):
    username = forms.CharField(label='用户名')
    old_password = forms.CharField(label='原密码', widget=forms.PasswordInput())
    new_password = forms.CharField(label='新密码', widget=forms.PasswordInput())


'''
def regist(request):
    if request.method == 'POST':
        uf = UserForm(request.POST)
        if uf.is_valid():
            username = uf.cleaned_data['username']
            password = uf.cleaned_data['password']
            user = WangUser.objects.filter(username = username)
            if user:
                info = '用户名已存在!'
                return HttpResponse(info)
            elif len(user) == 0:
                info = '注册成功!'
                user = WangUser()
                user.username = username
                user.password = password
                user.save()
        return HttpResponse(info)
    else:
        uf = UserForm()
        return render(request, 'regist.html', locals())
'''


def login(request):
    if request.method == 'POST':
        username = request.POST.get('username')
        password = request.POST.get('password')
        obj_user = models.WangUser.objects.filter(username=username, password=password)
        if obj_user:
            return redirect('shouye')
        error = '用户名或密码错误'
        return render(request, 'login.html', locals())
    else:
        return render(request, 'login.html')


def change_pass(request):
    if request.method == 'POST':
        uf = ChangeForm(request.POST)
        if uf.is_valid():
            username = uf.cleaned_data['username']
            old_password = uf.cleaned_data['old_password']
            new_password = uf.cleaned_data['new_password']
            user = WangUser.objects.filter(username=username)
            if user:
                passwd = WangUser.objects.filter(username=username, password=old_password)
                if passwd:
                    WangUser.objects.filter(username=username, password=old_password).update(password=new_password)
                    info = '密码修改成功!'
                else:
                    info = '请检查原密码是否输入正确!'
            elif len(user) == 0:
                info = '请检查用户名是否正确!'
        return HttpResponse(info)
    else:
        uf = ChangeForm()
        return render(request, 'change.html', {'uf': uf})


def index(request):
    return render(request, "index.html")


# 查看本书人物热度前10排名
def gensim_top10(path):
    # 读取文件
    fn = open(path, 'r', encoding='utf-8')
    string_data = fn.read()
    fn.close()

    # 文本预处理
    pattern = re.compile(u'\t|\n|\.|-|:|;|\)|\(|\?|"|\“|\：|\。|\，|\！|\；|\’|\”|\……|\···|\？')  # 定义正则表达式匹配模式
    string_data = re.sub(pattern, '', string_data)  # 将符合模式的字符去除

    # 文本分词
    seg_list_exact = jieba.cut(string_data, cut_all=False)  # 精确模式分词

    object_list = {}
    excludes = {"自己", "什么", "师父", "知道", "没有", "一个", "可是", "看着", "怎么",
                "只是", "不是", "还是", "可以", "他们", "长留", "突然", "已经", "那么", "一样",
                "就是", "还有", "不要", "这个", "不会", "起来", "但是", "这样", "出来", "弟子", "然后", "这么", "那个", "一直",
                "仿佛", "觉得", "一切", "不过", "时候", "因为", "有些", "不能", "所以", "开始", "我们", "可能", "身上", "妖神", "依旧", "想要",
                "眼睛", "为什么", "如此", "蛮荒", "为了", "神器", "现在", "慢慢", "微微", "虽然", "身子", "声音", "心里", "茅山", "姐姐", "一般",
                "看见", "感觉", "掌门", "应该", "办法", "终于", "一声", "如果", "众人", "如今"}
    # 自定义去除词库

    for word in seg_list_exact:
        # if word not in remove_words:
        #   object_list.append(word) # 分词追加到列表

        if len(word) == 1:

            continue

        elif word == "骨头" or word == "小骨":

            rword = "花千骨"

        elif word == "白子" or word == "子画" or word == "画" or word == "尊上":

            rword = "白子画"

        elif word == "东方" or word == "卿":

            rword = "东方彧卿"

        elif word == "漫天":

            rword = "霓漫天"

        elif word == "阡陌":

            rword = "杀阡陌"

        else:

            rword = word

        object_list[rword] = object_list.get(rword, 0) + 1

    for word in excludes:
        del (object_list[word])

    items = list(object_list.items())

    items.sort(key=lambda x: x[1], reverse=True)

    renword = []
    rencount = []

    wordcount = {}

    fo = open("人物出场次数.txt", "a")

    for i in range(10):
        word, count = items[i]

        word = str(word)

        count = str(count)

        fo.write(word)

        fo.write(' ')

        fo.write(count)

        fo.write('\n')

        renword.append(word)
        rencount.append(count)

        print(word, count)

    fo.close()

    wordcount['renwu'] = list(renword)
    wordcount['cishu'] = list(rencount)

    return wordcount


# 读取文本
def read_txt(path):
    file = open(path, 'r', encoding='utf-8')
    txt = file.read()
    file.close()
    return txt


# 词性统计（写入文档）
def sda():
    import jieba.posseg as psg
    text = open("D:\\user\\PycharmProjects\\NLPproject\\text\\花千骨.txt", 'r', encoding='utf-8', errors='ignore').read()
    seg = psg.cut(text)
    file = open("词性.txt", 'a+')
    for ele in seg:
        file.writelines(ele)


# 停词文档
def stopwordslist(filepath):
    stopwords = [line.strip() for line in open(filepath, 'r', encoding='utf-8').readlines()]
    return stopwords


# 分词生成人物（写入文档）
def write_txt():
    seg_list_exact = jieba.lcut(read_txt())  # 使用精确模式对文本进行分词counts = {}     # 通过键值对的形式存储词语及其出现的次数
    object_list = {}
    excludes = {"自己", "什么", "师父", "知道", "没有", "一个", "可是", "看着", "怎么",
                "只是", "不是", "还是", "可以", "他们", "长留", "突然", "已经", "那么", "一样",
                "就是", "还有", "不要", "这个", "不会", "起来", "但是", "这样", "出来", "弟子", "然后", "这么", "那个", "一直",
                "仿佛", "觉得", "一切", "不过", "时候", "因为", "有些", "不能", "所以", "开始", "我们", "可能", "身上", "妖神", "依旧", "想要",
                "眼睛", "为什么", "如此", "蛮荒", "为了", "神器", "现在", "慢慢", "微微", "虽然", "身子", "声音", "心里", "茅山", "姐姐", "一般",
                "看见", "感觉", "掌门", "应该", "办法", "终于", "一声", "如果", "众人", "如今"}
    # 自定义去除词库

    for word in seg_list_exact:
        # if word not in remove_words:
        #   object_list.append(word) # 分词追加到列表

        if len(word) == 1:

            continue

        elif word == "骨头" or word == "小骨":

            rword = "花千骨"

        elif word == "白子" or word == "子画" or word == "画" or word == "尊上":

            rword = "白子画"

        elif word == "东方" or word == "卿":

            rword = "东方彧卿"

        elif word == "漫天":

            rword = "霓漫天"

        elif word == "阡陌":

            rword = "杀阡陌"

        else:

            rword = word

        object_list[rword] = object_list.get(rword, 0) + 1

    for word in excludes:
        del (object_list[word])

    items = list(object_list.items())

    items.sort(key=lambda x: x[1], reverse=True)

    # 根据词语出现的次数进行从大到小排序
    f = open("词频统计.txt", "w")  # 写入文件
    for i in range(len(items)):
        word, count = items[i]
        f.writelines("{0:<5}{1:>5}n".format(word, count))
    f.close()


# 生成词云
def wordcloud_output(path):
    # 读取文件
    fn = open(path, 'r', encoding='utf-8')
    string_data = fn.read()
    fn.close()

    # 文本预处理
    pattern = re.compile(u'\t|\n|\.|-|:|;|\)|\(|\?|"|\“|\：|\。|\，|\！|\；|\’|\”|\……|\···|\？')  # 定义正则表达式匹配模式
    string_data = re.sub(pattern, '', string_data)  # 将符合模式的字符去除

    # 文本分词
    seg_list_exact = jieba.cut(string_data, cut_all=False)  # 精确模式分词
    object_list = []
    remove_words = [u'的', u'，', u'和', u'是', u'随着', u'对于', u'对', u'等', u'能', u'都', u'。', u' ', u'、', u'中', u'在', u'了',
                    u'通常', u'如果', u'我们', u'需要', u'她', u'他', u'你', u'我', u'也', u'着', u'不', u'需要', u'没有', u'可是', u'知道',
                    u'呢', u'只是', u'却', u'吧', u'一样', u'那', u'所以', u'有', u'自己', u'虽然', u'再', u'要', u'吃', u'说',
                    u'人', u'就', u'又', u'什么', u'被', u'这', u'啊', u'一个', u'去', u'还', u'几乎', u'把', u'到', u'会',
                    u'可以', u'得', u'而', u'看着', u'它', u'只', u'么', u'想', u'怎么', u'看', u'一直', u'一切', u'事'
        , u'看到', u'因为', u'为了', u'可能', u'还是', u'将', u'他们', u'很', u'好', u'最', u'那么', u'然后', u'慢慢'
        , u'谁', u'现在', u'看见', u'这样', u'最好', u'回去', u'像', u'给', u'不过', u'一下', u'有些', u'但是', u'那个'
        , u'就是', u'就算', u'上', u'来', u'杀', u'让']  # 自定义去除词库

    for word in seg_list_exact:
        if word not in remove_words:
            object_list.append(word)  # 分词追加到列表

    # 词频统计
    word_counts = collections.Counter(object_list)
    word_counts_top10 = word_counts.most_common(10)  # 获取前10最高频的词
    print("前十高频词：")
    print(word_counts_top10)
    word_counts_top10 = str(word_counts_top10)

    # 词频展示
    mask = np.array(Image.open("D:\\user\\PycharmProjects\\NLPproject\\gongju\\image22.jpeg"))  # 定义词频背景
    wc = wordcloud.WordCloud(
        font_path='simfang.ttf',  # 设置字体格式
        mask=mask,  # 设置背景图
        max_words=200,  # 最多显示词数
        max_font_size=150,  # 字体最大值
        background_color='white',
        width=800, height=600,
    )

    wc.generate_from_frequencies(word_counts)
    plt.imshow(wc)
    plt.axis('off')
    plt.savefig('static\\wordcloud.jpg')


# 生成人物关系图（全按书上抄的）
def creat_relationship(path):
    Names = ['花千骨', '白子画', '东方彧卿', '糖宝', '杀阡陌', '竹染', '十一', ' 霓漫天 ', '摩严', '朔风']
    relations = {}
    lst_para = (read_txt(path)).split('n')  # lst_para是每一段
    for text in lst_para:
        for name_0 in Names:
            if name_0 in text:
                for name_1 in Names:
                    if name_1 in text and name_0 != name_1 and (name_1, name_0) not in relations:
                        relations[(name_0, name_1)] = relations.get((name_0, name_1), 0) + 1
    maxRela = max([v for k, v in relations.items()])
    relations = {k: v / maxRela for k, v in relations.items()}
    # return relations
    plt.figure(figsize=(15, 15))
    G = nx.Graph()
    for k, v in relations.items():
        G.add_edge(k[0], k[1], weight=v)
        # 筛选权重大于0.6的边
    elarge = [(u, v) for (u, v, d) in G.edges(data=True) if d['weight'] > 0.6]
    # 筛选权重大于0.3小于0.6的边
    emidle = [(u, v) for (u, v, d) in G.edges(data=True) if (d['weight'] > 0.3) & (d['weight'] <= 0.6)]
    # 筛选权重小于0.3的边
    esmall = [(u, v) for (u, v, d) in G.edges(data=True) if d['weight'] <= 0.3]
    # 设置图形布局
    pos = nx.spring_layout(G)
    # 设置节点样式
    nx.draw_networkx_nodes(G, pos, alpha=0.8, node_size=1200)
    # 设置大于0.6的边的样式
    nx.draw_networkx_edges(G, pos, edgelist=elarge, width=2.5, alpha=0.9, edge_color='g')
    # 0.3~0.6
    nx.draw_networkx_edges(G, pos, edgelist=emidle, width=1.5, alpha=0.6, edge_color='y')
    # <0.3
    nx.draw_networkx_edges(G, pos, edgelist=esmall, width=1, alpha=0.4, edge_color='b', style='dashed')
    nx.draw_networkx_labels(G, pos, font_size=12)
    plt.axis('off')
    plt.title("花千骨人物权重图")
    plt.savefig("static\\花千骨人物权重图.jpg")


# 角色相似性
def xiangshi2(name1, name2):
    sentences = word2vec.Text8Corpus('D:\\user\\PycharmProjects\\NLPproject\\text\\花千骨2.txt')
    sentences1 = word2vec.LineSentence('D:\\user\\PycharmProjects\\NLPproject\\text\\花千骨2.txt')
    model = word2vec.Word2Vec(sentences, min_count=1)
    model1 = word2vec.Word2Vec(sentences1, min_count=1)

    a = model.wv.similarity(name1, name2)

    return a


# 计算并查看与角色相关的前10个词
def xiangshi3(name):
    sentences = word2vec.Text8Corpus('D:\\user\\PycharmProjects\\django_projects\\Myproject_xiaogu\\txtdata\\花千骨2.txt')
    sentences1 = word2vec.LineSentence(
        'D:\\user\\PycharmProjects\\django_projects\\Myproject_xiaogu\\txtdata\\花千骨2.txt')
    model = word2vec.Word2Vec(sentences, min_count=1)
    model1 = word2vec.Word2Vec(sentences1, min_count=1)

    b = model.wv.most_similar(name, topn=10)
    return b


'''
def login(request):
    if request.method == 'POST':
        username = request.POST.get('username')
        password = request.POST.get('password')
        obj_user = models.WangUser.objects.filter(username=username, password=password)
        if obj_user:
            return redirect('shouye')
        error = '用户名和密码错误'
    return render(request, 'login.html', locals())


def index(request):
    return render(request, 'index.html')

'''


def register(request):
    if request.method == 'POST':
        username = request.POST.get('username')
        email = request.POST.get('email')
        password = request.POST.get('password')
        user_list = models.WangUser.objects.filter(username=username)
        error_name = []
        if user_list:
            error_name = '用户名已经存在'
            return render(request, 'register.html', {'error_name': error_name})
        else:
            username = models.WangUser.objects.create(username=username, password=password, email=email)
            username.save()
            return redirect('login')
    else:
        return render(request, 'register.html')
    # return render(request,'register.html')


def shuoye(request):
    if request.method == "GET":
        # wangbooks = models.WangBooks.objects.filter(bookname='花千骨')
        objs = models.UserBooks.objects.all()

        return render(request, 'shouye.html', locals())
    else:
        return render(request, 'shouye.html')


def zhushou(request):
    if request.method == "GET":
        return render(request, 'zhushou.html')
    else:
        return render(request, 'zhushou.html')


def gengduo(request):
    if request.method == "GET":
        return render(request, 'gengduo.html')
    else:
        return render(request, 'gengduo.html')


def renwu_top10(request):
    if request.method == "GET":
        return render(request, 'renwu_top10.html')
    else:
        bookname = request.POST.get('bookname')

        book_list = models.WangBooks.objects.filter(bookname=bookname)





        error_name = []
        if book_list:
            path = 'media/' + str(book_list[0].data)
            renwutop10 = gensim_top10(path)
            return render(request, 'renwu_top10_out.html',{'renwutop10': renwutop10})

        else:
            error_name = '书库中找不到该书'
            return render(request, 'renwu_top10.html', {'error_name': error_name})




def renwu_top10_out(request):
    if request.method == "GET":

        renwutop10 = gensim_top10()

        return render(request, 'renwu_top10_out.html', {'renwutop10': renwutop10})
    else:

        renwutop10 = gensim_top10()

        return render(request, 'renwu_top10_out.html', {'renwutop10': renwutop10})


def renwu_guanxi(request):
    if request.method == "GET":
        return render(request, 'renwu_guanxi.html')
    else:
        bookname = request.POST.get('bookname')

        book_list = models.WangBooks.objects.filter(bookname=bookname)




        error_name = []
        if book_list:
            path = 'media/' + str(book_list[0].data)
            creat_relationship(path)
            return render(request, 'renwu_guanxi_out.html')

        else:
            error_name = '书库中找不到该书'
            return render(request, 'renwu_guanxi.html', {'error_name': error_name})




def renwu_guanxi_out(request):
    if request.method == "GET":

        creat_relationship()
        return render(request, 'renwu_guanxi_out.html')
    else:

        creat_relationship()
        return render(request, 'renwu_guanxi_out.html')


def juese_xiangshi(request):
    if request.method == "GET":
        return render(request, 'juese_xiangshi.html')
    else:
        return render(request, 'juese_xiangshi.html')


def juese_xiangshi_out(request):
    if request.method == "GET":
        name1 = request.GET.get('name1')
        name2 = request.GET.get('name2')

        a = xiangshi2(name1, name2)
        return render(request, 'juese_xiangshi_out.html', {'a': a})
    else:
        name1 = request.GET.get('name1')
        name2 = request.GET.get('name2')

        a = xiangshi2(name1, name2)
        return render(request, 'juese_xiangshi_out.html', {'a': a})


def juese_xiangguanci_10(request):
    if request.method == "GET":
        return render(request, 'juese_xiangguanci_10.html')
    else:
        return render(request, 'juese_xiangguanci_10.html')


def juese_xiangguanci_10_out(request):
    if request.method == "GET":

        name = request.GET.get('name')

        b = xiangshi3(name)
        return render(request, 'juese_xiangguanci_10_out.html', {'b': b})
    else:
        return render(request, 'juese_xiangguanci_10_out.html')


def quanshu_wordcloud(request):
    if request.method == "GET":
        return render(request, 'quanshu_wordcloud.html')
    else:
        bookname = request.POST.get('bookname')

        book_list = models.WangBooks.objects.filter(bookname=bookname)

        error_name = []
        if book_list:
            path = 'media/' + str(book_list[0].data)
            wordcloud_output(path)
            return render(request, 'quanshu_wordcloud_out.html')

        else:
            error_name = '书库中找不到该书'
            return render(request, 'quanshu_wordcloud.html', {'error_name': error_name})





def quanshu_wordcloud_out(request):
    if request.method == "GET":
        return render(request, 'quanshu_wordcloud_out.html')
    else:
        return render(request, 'quanshu_wordcloud_out.html')


def geren(request):
    if request.method == "GET":
        username = request.GET.get('username')

        return render(request, 'geren.html', {'username': username})
    else:
        return render(request, 'geren.html')


def addbook(request):
    if request.method == 'POST':
        bookname = request.POST.get('bookname')
        price = request.POST.get('price')
        authors = request.POST.get('authors')
        kind = request.POST.get('kind')
        img = request.FILES.get('img')
        data = request.FILES.get('data')

        book_list = models.WangBooks.objects.filter(bookname=bookname)
        error_name = []
        if book_list:
            error_name = '该书名已经存在'
            return render(request, 'addbook.html', {'error_name': error_name})
        else:
            newbook = models.WangBooks.objects.create(bookname=bookname, price=price, authors=authors, kind=kind,
                                                      img=img, data=data)
            newbook.save()
        return redirect('shucheng')
    else:
        return render(request, 'addbook.html')


def changebook1(request):
    if request.method == "POST":

        bookname1 = request.POST.get('bookname1')

        bookname = request.POST.get('bookname')
        price = request.POST.get('price')
        authors = request.POST.get('authors')
        kind = request.POST.get('kind')
        img = request.FILES.get('img')
        data = request.FILES.get('data')

        BOOK = models.WangBooks.objects.get(bookname=bookname1)

        BOOK.bookname = bookname
        BOOK.price = price
        BOOK.authors = authors
        BOOK.kind = kind
        BOOK.img = img
        BOOK.data = data
        BOOK.save()

        return redirect('shucheng')
    else:

        i = request.GET['ID']

        n = request.GET['NAME']
        a = request.GET['AUTHORS']

        return render(request, 'changebook.html', {'n': n})


def deletebook(request):
    if request.method == "POST":

        return redirect('shucheng')
    else:

        i = request.GET['ID']
        models.WangBooks.objects.get(id=i).delete()

        return redirect('shucheng')

def sousuo(request):
    if request.method == "POST":

        return render(request, 'sousuo.html')
    else:

        bookname = request.GET['bookname']
        book_list = models.WangBooks.objects.filter(bookname=bookname).all()

        print(book_list[0].id)
        print(book_list[0].bookname)
        print(book_list[0].price)
        print(book_list[0].authors)
        print(book_list[0].img)




        id = book_list[0].id
        bookname = book_list[0].bookname
        price = book_list[0].price
        authors = book_list[0].authors
        img = book_list[0].img
        data = book_list[0].data

        book={'id':id, 'bookname':bookname,'price':price,'authors':authors,'img':img,'data':data}


        error_name = []
        if book_list:
            return render(request, 'sousuo.html',{'book':book})

        else:
            error_name = '为找到该书'
            return render(request, 'shucheng.html', {'error_name': error_name})


def goumai(request):
    if request.method == "POST":
        bookname = request.POST.get('bookname')
        authors = request.POST.get('authors')
        kind = request.POST.get('kind')
        img = request.POST.get('img')
        data = request.POST.get('data')
        user = request.POST.get('user')

        BOOK = models.UserBooks.objects.create(user=user,bookname=bookname, authors=authors, kind=kind,
                                                      img=img, data=data)

        BOOK.save()




        return redirect('shouye')
    else:

        n = request.GET['BOOKNAME']
        a = request.GET['AUTHORS']
        k = request.GET['KIND']
        m = request.GET['IMG']
        d = request.GET['DATA']

        book = {'bookname': n, 'authors': a, 'kind': k, 'img': m, 'data': d}

        print(book)

        return render(request, 'goumai.html', {'book': book})




def readbook(request):
    if request.method == "GET":
        path = 'media/' + request.GET['PATH']
        booktxt = []

        f = open(path, 'r', encoding='utf-8')
        attack_method = f.read()
        attack_method = attack_method.split('\n')
        attack_method = list(dict.fromkeys(attack_method))  ## 这里是去重
        f.close()



        return render(request, 'readbook.html',{"attack_method": attack_method})
    else:
        return render(request, 'readbook.html')


def mao_pao(array):
    for i in range(1, len(array)):
        for j in range(0, len(array) - i):
            if array[j] > array[j + 1]:
                array[j], array[j + 1] = array[j + 1], array[j]
    return array


def shucheng(request):
    if request.method == "GET":

        wangbooks = models.WangBooks.objects.all()


        '''print(wangbooks)

        BookDetails = models.BookDetails.objects.all()

        Details = {}
        num = []
        for n in BookDetails:
            Details[n.number] = n.bookname
            num.append(n.number)
        print(Details)
        print(num)

        num = list(map(int,num))
        print(num)

        n_li = mao_pao(num)
        n_li.reverse()
        print(n_li)

        num2 = list(map(str,n_li))
        print(num2)

        B_name = []
        for n_2 in num2:
            B_name.append(Details[n_2])
        print(B_name)

        Booklist = {'B_name':B_name}
        '''

        return render(request, 'shucheng.html', locals())
    else:
        return render(request, 'shucheng.html')




def liaotian(request):
    if request.method == "GET":
        return render(request, 'liaotian.html')
    else:
        return render(request, 'liaotian.html')


def bookdetails(request):
    if request.method == "GET":
        return render(request, 'bookdetails.html')
    else:

        bookname = request.POST.get('bookname')
        details = request.POST.get('details')
        number = request.POST.get('number')


        book_list = models.BookDetails.objects.filter(bookname=bookname)
        error_name = []
        if book_list:
            error_name = '该书名已经存在'
            return render(request, 'bookdetails.html', {'error_name': error_name})
        else:
            newbook = models.BookDetails.objects.create(bookname=bookname, details=details, number=number)
            newbook.save()
        return redirect('bookdetails')



def kind(request):
    if request.method == "GET":
        kind='仙侠'
        wangbooks = models.WangBooks.objects.filter(kind=kind)
        return render(request, 'kind.html',locals())
    else:
        kind = request.POST.get('kind')
        wangbooks = models.WangBooks.objects.filter(kind=kind)

        return render(request, 'kind.html',locals())

def menbook(request):
    if request.method == "GET":
        kind='玄幻'
        wangbooks = models.WangBooks.objects.filter(kind=kind)

        kind2 = '武侠'
        wangbooks2 = models.WangBooks.objects.filter(kind=kind2)

        kind3 = '恐怖'
        wangbooks3 = models.WangBooks.objects.filter(kind=kind3)

        return render(request, 'menbook.html',locals())
    else:


        return render(request, 'menbook.html')

def womenbook(request):
    if request.method == "GET":
        kind='仙侠'
        wangbooks = models.WangBooks.objects.filter(kind=kind)
        kind2 = '言情'
        wangbooks2 = models.WangBooks.objects.filter(kind=kind2)

        return render(request, 'womenbook.html',locals())
    else:
        return render(request, 'womenbook.html')


def xiangxi(request):
    if request.method == "GET":
        bookname = request.GET['NAME']
        wangbooks = models.WangBooks.objects.filter(bookname=bookname)
        bookdetails = models.BookDetails.objects.filter(bookname=bookname)

        return render(request, 'xiangxi.html',locals())
    else:
        return render(request, 'xiangxi.html')