import jieba
jieba.setLogLevel(jieba.logging.INFO)
import jieba.posseg
import jieba.analyse
import jieba.finalseg
import jieba.lac_small
import re
import collections
from gensim import corpora
import jieba.posseg as psg


def dataProcessing(oldfile, newfile):
    #1 读取文件
    with open(oldfile, 'r', encoding='utf-8') as f:
        txt = f.read()

    #2. 分词-->txtlist
    txtlist = jieba.lcut(txt)

    worlds = {"骨头":"花千骨","小骨":"花千骨",
              "白子":"白子画","子画":"白子画","画":"白子画","尊上":"白子画",
              "东方":"东方彧卿","卿": "东方彧卿",
              "漫天":"霓漫天","阡陌":"杀阡陌"
    }

    new_txtlist = [worlds[i] if i in worlds else i for i in txtlist]


    #3. 过滤分词后的结果-->标点符号、单字词
    newlist = []
    for i in new_txtlist:
        if len(i) > 1:
            newlist.append(i)

    #4.newlist转换为字符串
    text = ' '.join(newlist)
    text1 = newlist

    #5.将text写出到newfile中
    with open(newfile, 'w', encoding='utf-8') as f1:
        f1.write(text)
    return text, text1


# 打印姓名排名，由高到低
def girlsort(text1):
    dict = {}
    for t in text1:
        res = psg.cut(t)
        for item in res:
            if item.flag == 'nr' and item.word in dict:
                dict[item.word] += 1
            elif item.flag == 'nr' and item.word not in dict:
                dict[item.word] = 1
    name_count = sorted(dict.items(), key=lambda x: x[1], reverse=True)
    return name_count


oldfile = 'D:\\user\\PycharmProjects\\django_projects\\Myproject_xiaogu\\txtdata\\花千骨.txt'
newfile = 'D:\\user\\PycharmProjects\\django_projects\\Myproject_xiaogu\\txtdata\\花千骨2.txt'
text, text1 = dataProcessing(oldfile, newfile)
print("人名词频（从高到低）："+str(girlsort(text1)))
