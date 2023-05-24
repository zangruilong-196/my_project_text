from gongju.wordcloud_output import wordcloud_output
from gongju.gensim_xiangguan_top10 import gensim_top10
from gongju.renwuxiangguan import main
from fqa.faq_test import fqa_qa
from gongju.xiangshi_2 import xiangshi2
from gongju.xiangshi_3 import xiangshi3

while True:
    print("你好，我是您的书友小骨。在读完《花千骨》这本书后，想和小骨讨论什么呢？ ₍₍ ◝(・ω・)◟ ⁾⁾")
    print("1.查看本书人物热度前10排名")
    print("2.查看全书用词热度词云")
    print("3.查看人物热度词云与人物权重关系")
    print("4.没事，想找小骨聊聊天")
    print("5.查询角色相似度")
    print("6.查询与角色相关的前10个词")

    ip = input("请输入：")

    if ip == "1" or ip == "1." or ip == "查看本书人物热度前10排名" or ip == "1.查看本书人物热度前10排名":
        gensim_top10()
        break
    elif ip == "2" or ip == "2." or ip == "查看全书用词热度词云" or ip == "2.查看全书用词热度词云":
        wordcloud_output()
        break
    elif ip == "3" or ip == "3." or ip == "查看人物热度词云与人物权重关系" or ip == "3.查看人物热度词云与人物权重关系":
        main()
        break
    elif ip == "4" or ip == "4." or ip == "没事，想找小骨聊聊天" or ip == "4.没事，想找小骨聊聊天":
        fqa_qa()
        break
    elif ip == "5" or ip == "5." or ip == "查询角色相似度" or ip == "5.查询角色相似度":
        xiangshi2()
        break
    elif ip == "6" or ip == "6." or ip == "查询与角色相关的前10个词" or ip == "6.查询与角色相关的前10个词":
        xiangshi3()
        break