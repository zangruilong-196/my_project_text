from django.http import HttpResponse
from django.shortcuts import redirect
from django.shortcuts import render
from django.urls import reverse

import pandas as pd
import seaborn as sns
from sklearn.linear_model import LinearRegression
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from numpy.linalg import inv
from numpy import dot
from pandas.core.frame import DataFrame


# Create your views here.

def zhuce(request):
    return render(request, 'zhuce.html')


'''
def zcdo(request):
    name = request.POST['name']
    password = request.POST['password']
    result = UsersMessage.objects.create(
        name=name,
        password=password
    )
    if result:
        return render(request, 'denglu.html')
    else:
        return HttpResponse("添加失败")'''


def zcdo(request):
    if request.method == "GET":
        return render(request, 'denglu.html')
    else:
        username = request.POST.get('username')
        pwd = request.POST.get('password')
        if username == "zrl" and pwd == "12345":
            return HttpResponse('ok')
        else:
            return redirect(reverse('zhucedo'))


def denglu(request):
    return render(request, 'denglu.html')


'''def dldo(request):
    username = request.POST['name']
    user_password = request.POST['password']
    result = UsersMessage.objects.filter(name=username, password=user_password)
    if result:
        return HttpResponse('ok')
    else:
        return HttpResponse('sorry')
'''


def dldo(request):
    if request.method == "GET":
        return render(request, 'shuoye.html')
    else:
        username = request.POST.get('username')
        pwd = request.POST.get('password')
        if username == "zrl" and pwd == "12345":
            return render(request, 'shuoye.html')
        else:
            return redirect(reverse('dldo'))


def shuju(request):
    if request.method == "GET":
        return render(request, 'shuju.html')
    else:
        return render(request, 'shuju.html')


def guangyu(request):
    if request.method == "GET":
        return render(request, 'guanyu.html')
    else:
        return render(request, 'guanyu.html')


def shuoye(request):
    if request.method == "GET":
        return render(request, 'shuoye.html')
    else:
        return render(request, 'shuoye.html')


def zc(request):
    if request.method == "GET":
        return render(request, 'zhuce.html')
    else:
        return render(request, 'zhuce.html')


def xianxin():
    # 通过read_csv来读取我们的目的数据集
    # adv_data = pd.read_csv("../data/wuhudata.csv")
    lia = [135.14, 139.19, 139.7, 139.87, 137.26, 140.12, 138.43, 132.71, 133.7, 142.02,
           83.54, 84.88, 83.04, 75.4, 80.04, 76.47, 75.95, 56.57, 68.04, 67.75,
           67.67, 62.78, 74.45, 63.32, 61.27, 61.1, 60.78, 59.84, 58.76, 58.49,
           57.98]
    cai = [172.7, 110.1, 120.32, 143.21, 157.32, 176.83, 233.68, 213.99, 178.91, 139.97,
           94.84, 69.41, 54.39, 43.91, 34.15, 27.83, 20.78, 17.34, 15.67, 12.87,
           12.54, 11.81, 6.84, 6.32, 12.84, 10.64, 8.42, 6.88, 5.63, 5.03,
           5.91]
    gon = [83084.1, 78161.4, 75763.2, 72146.4, 69023.2, 59785.3, 54522.2, 44812.,
           47699.9, 41946.5, 36593.7, 32157.3, 28053.4, 22964.7, 18972., 16177.4,
           14165.5, 11398.2, 9322.16, 8695.41, 8255., 8061., 7492.34, 6192.89,
           5550.26, 4599.7, 3640.26, 2578.82, 2257.97, 1955.11, 1798.55]
    ren = [364.44, 377.8, 374.8, 369.6, 375.8, 381.6, 384.5, 384.5, 383.43, 385.36,
           229.5, 230.1, 230.79, 230.46, 229.03, 226.88, 224.56, 223.82, 221.74, 220.26,
           218.47, 216.4, 215.1, 213.95, 212.59, 211.27, 209.28, 207.66, 206.12, 204.2,
           202.1]
    gd = [3753.02, 3618.26, 3278.53, 3065.52, 2699.44, 2457.32, 2309.55, 2099.53, 1880.,
          1658.24, 1108.59, 902., 749.65, 582.3, 479.72, 400.65, 345.07, 284.85,
          245.51, 218.77, 200.58, 193.17, 186.76, 164.94, 132.06, 100.88, 75.09,
          52.79, 38.36, 31.52, 31.86]
    yea = [2020., 2019., 2018., 2017., 2016., 2015., 2014., 2013., 2012., 2011., 2010., 2009.,
           2008., 2007., 2006., 2005., 2004., 2003., 2002., 2001., 2000., 1999., 1998., 1997.,
           1996., 1995., 1994., 1993., 1992., 1991., 1990.]

    adv_data_1 = {
        "year": yea,
        "liangshi": lia,
        "caizheng": cai,
        "gongzi": gon,
        "renko": ren,
        "gdp": gd
    }

    adv_data = DataFrame(adv_data_1)
    # 清洗不需要的数据
    new_adv_data = adv_data.iloc[:, 1:]

    test = new_adv_data
    X = test.iloc[:, [0, 1, 2, 3]]
    Y = test.iloc[:, 4]
    theta = dot(dot(inv(dot(X.T, X)), X.T), Y) #权重

    # print('权重', theta)  # 权重

    # 得到我们所需要的数据集且查看其前几列以及数据形状
    # print('head:', new_adv_data.head(), '\nShape:', new_adv_data.shape)
    # print(new_adv_data)
    # 数据描述
    # print(new_adv_data.describe())
    # 缺失值检验
    # print(new_adv_data[new_adv_data.isnull() == True].count())

    # new_adv_data.boxplot()

    # 相关系数矩阵 r(相关系数) = x和y的协方差/(x的标准差*y的标准差) == cov（x,y）/σx*σy
    # 相关系数0~0.3弱相关0.3~0.6中等程度相关0.6~1强相关
    # print(new_adv_data.corr())

    # 建立散点图来查看数据集里的数据分布 seaborn的pairplot函数绘制X的每一维度和对应Y的散点图。通过设置size和aspect参数来调节显示的大小和比例。
    # 通过加入一个参数kind='reg'，seaborn可以添加一条最佳拟合直线和95%的置信带。
    # sns.pairplot(new_adv_data, x_vars=['', 'liangshi', 'caizheng',
    # 'gongzi', 'renko'], y_vars='gdp', height=7, aspect=1, kind='reg')

    # plt.savefig("pairplot.jpg")
    # plt.show()

    # 利用sklearn里面的包来对数据集进行划分，以此来创建训练集和测试集
    # train_size表示训练集所占总数据集的比例
    X_train, X_test, Y_train, Y_test = train_test_split(new_adv_data.iloc[:, :4], new_adv_data.gdp, train_size=.80)

    # print("原始数据特征:", new_adv_data.iloc[:, :4].shape,
    #      ",训练数据特征:", X_train.shape,
    #      ",测试数据特征:", X_test.shape)

    # print("原始数据标签:", new_adv_data.gdp.shape,
    #      ",训练数据标签:", Y_train.shape,
    #      ",测试数据标签:", Y_test.shape)

    ystz = new_adv_data.iloc[:, :4].shape
    xltz = X_train.shape
    cstz = X_test.shape

    ysbq = new_adv_data.gdp.shape
    xlbq = Y_train.shape
    csbq = Y_test.shape

    model = LinearRegression()

    model.fit(X_train, Y_train)

    a = model.intercept_  # 截距

    b = model.coef_  # 回归系数

    # print("最佳拟合线:截距", a, ",回归系数：", b)
    # 截距 -1151.7824886023247 ,回归系数： [-27.39778113  -1.7189473    0.04788965  12.99560485]

    # gdp = -1151.7824886023247+ -27.39778113∗liangshi + -1.7189473∗caizheng + 0.04788965∗gongzi + 12.99560485*renko

    # R方检测
    # 决定系数r平方
    # 对于评估模型的精确度
    # y误差平方和 = Σ(y实际值 - y预测值)^2
    # y的总波动 = Σ(y实际值 - y平均值)^2
    # 有多少百分比的y波动没有被回归拟合线所描述 = SSE/总波动
    # 有多少百分比的y波动被回归线描述 = 1 - SSE/总波动 = 决定系数R平方
    # 对于决定系数R平方来说1） 回归线拟合程度：有多少百分比的y波动刻印有回归线来描述(x的波动变化)
    # 2）值大小：R平方越高，回归模型越精确(取值范围0~1)，1无误差，0无法完成拟合

    score = model.score(X_test, Y_test)

    # print(score)

    # 对线性回归进行预测

    Y_pred = model.predict(X_test)

    # print(Y_pred)

    # plt.plot(range(len(Y_pred)), Y_pred, 'b', label="predict")
    # 显示图像
    # plt.savefig("predict.jpg")
    # plt.show()

    # plt.figure()
    # plt.plot(range(len(Y_pred)), Y_pred, 'b', label="predict")
    # plt.plot(range(len(Y_pred)), Y_test, 'r', label="test")
    # plt.legend(loc="upper right")  # 显示图中的标签
    # plt.xlabel("the number of gdp")
    # plt.ylabel('value of ddp')
    # plt.savefig("ROC.jpg")

    # plt.show()

    liangshi = new_adv_data.values[:, 0]
    caizheng = new_adv_data.values[:, 1]
    gongzi = new_adv_data.values[:, 2]
    renko = new_adv_data.values[:, 3]
    gdp = new_adv_data.values[:, 4]
    year = adv_data.values[:, 0]

    cz = ["粮食", "财政", "工资", "人口"]
    dict_return = {}
    dict_return['Y_pred'] = list(Y_pred)  # 预测结果
    dict_return['theta'] = list(theta)  # 权重
    dict_return['ystz'] = list(ystz)  # 原始数据特征
    dict_return['xltz'] = list(xltz)  # 训练数据特征
    dict_return['cstz'] = list(cstz)  # 测试数据特征
    dict_return['ysbq'] = list(ysbq)  # 原始数据标签
    dict_return['xlbq'] = list(xlbq)  # 训练数据标签
    dict_return['csbq'] = list(csbq)  # 测试数据标签
    dict_return['a'] = a  # 截距
    dict_return['b'] = list(b)  # 相关系数
    dict_return['score'] = score  # R方检测
    dict_return['liangshi'] = list(liangshi)  # 粮食
    dict_return['caizheng'] = list(caizheng)  # 财政
    dict_return['gongzi'] = list(gongzi)  # 工资
    dict_return['renko'] = list(renko)  # 人口
    dict_return['gdp'] = list(gdp)  # GDP
    dict_return['year'] = list(year)  # 年份
    dict_return['cz'] = cz
    dict_return['R'] = [{'name': 'R值', 'value': score}, {'name': '误差', 'value': (1 - score)}]
    return dict_return


def query(request):
    # 请求方式为get
    if request.method == 'GET':
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju.html', {'dict_return': dict_return})
    else:
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju.html', {'dict_return': dict_return})


def query1(request):
    # 请求方式为get
    if request.method == 'GET':
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju1.html', {'dict_return': dict_return})
    else:
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju1.html', {'dict_return': dict_return})


def query2(request):
    # 请求方式为get
    if request.method == 'GET':
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju2.html', {'dict_return': dict_return})
    else:
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju2.html', {'dict_return': dict_return})


def query3(request):
    # 请求方式为get
    if request.method == 'GET':
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju3.html', {'dict_return': dict_return})
    else:
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju3.html', {'dict_return': dict_return})


def query4(request):
    # 请求方式为get
    if request.method == 'GET':
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju4.html', {'dict_return': dict_return})
    else:
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju4.html', {'dict_return': dict_return})


def query5(request):
    # 请求方式为get
    if request.method == 'GET':
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju5.html', {'dict_return': dict_return})
    else:
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju5.html', {'dict_return': dict_return})


def query6(request):
    # 请求方式为get
    if request.method == 'GET':
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju6.html', {'dict_return': dict_return})
    else:
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju6.html', {'dict_return': dict_return})


def query7(request):
    # 请求方式为get
    if request.method == 'GET':
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju7.html', {'dict_return': dict_return})
    else:
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju7.html', {'dict_return': dict_return})


def query8(request):
    # 请求方式为get
    if request.method == 'GET':
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju8.html', {'dict_return': dict_return})
    else:
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju8.html', {'dict_return': dict_return})


def query9(request):
    # 请求方式为get
    if request.method == 'GET':
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju9.html', {'dict_return': dict_return})
    else:
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju9.html', {'dict_return': dict_return})


def query10(request):
    # 请求方式为get
    if request.method == 'GET':
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju10.html', {'dict_return': dict_return})
    else:
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju10.html', {'dict_return': dict_return})


def query11(request):
    # 请求方式为get
    if request.method == 'GET':
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju11.html', {'dict_return': dict_return})
    else:
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju11.html', {'dict_return': dict_return})


def query12(request):
    # 请求方式为get
    if request.method == 'GET':
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju12.html', {'dict_return': dict_return})
    else:
        dict_return = xianxin()
        # 传入前端
        return render(request, 'shuju12.html', {'dict_return': dict_return})


def query13(request):
    # 请求方式为get
    if request.method == 'GET':
        dict_return = xianxin()
        # 传入前端
        return render(request, 'yuce.html', {'dict_return': dict_return})
    else:
        dict_return = xianxin()
        # 传入前端
        return render(request, 'yuce.html', {'dict_return': dict_return})


def query14(request):
    # 请求方式为get
    if request.method == 'GET':
        dict_return = xianxin()
        # 传入前端
        return render(request, 'yuce2.html', {'dict_return': dict_return})
    else:
        dict_return = xianxin()
        # 传入前端
        return render(request, 'yuce2.html', {'dict_return': dict_return})
