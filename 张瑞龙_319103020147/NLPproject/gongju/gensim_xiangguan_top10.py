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

'''
# 读取文件
fn = open("D:\\user\\PycharmProjects\\NLPproject\\text\\花千骨.txt",'r',encoding='utf-8')
string_data = fn.read()
fn.close()

# 文本预处理
pattern = re.compile(u'\t|\n|\.|-|:|;|\)|\(|\?|"|\“|\：|\。|\，|\！|\；|\’|\”|\……|\···|\？') # 定义正则表达式匹配模式
string_data = re.sub(pattern, '', string_data) # 将符合模式的字符去除

# 文本分词
seg_list_exact = jieba.cut(string_data, cut_all = False) # 精确模式分词


object_list = {}
excludes = {"自己", "什么", "师父", "知道", "没有", "一个", "可是","看着","怎么",
            "只是","不是","还是","可以","他们","长留","突然","已经","那么","一样",
            "就是","还有","不要","这个","不会","起来","但是","这样","出来","弟子","然后","这么","那个","一直",
            "仿佛","觉得","一切","不过","时候","因为","有些","不能","所以","开始","我们","可能","身上","妖神","依旧","想要",
            "眼睛","为什么","如此","蛮荒","为了","神器","现在","慢慢","微微","虽然","身子","声音","心里","茅山","姐姐","一般",
            "看见","感觉","掌门","应该","办法","终于","一声","如果","众人","如今"}
# 自定义去除词库

for word in seg_list_exact:
    #if word not in remove_words:
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
    del(object_list[word])






items = list(object_list.items())

items.sort(key=lambda x:x[1], reverse=True)

fo = open("人物出场次数.txt", "a")

for i in range(10):

   word, count=items[i]

   word = str(word)

   count = str(count)

   fo.write(word)

   fo.write(' ')

   fo.write(count)

   fo.write('\n')

   print (word, count)

fo.close()

'''





def gensim_top10():
    # 读取文件
    fn = open("D:\\user\\PycharmProjects\\NLPproject\\text\\花千骨.txt", 'r', encoding='utf-8')
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

    fo = open("人物出场次数.txt", "a")

    for i in range(10):
        word, count = items[i]

        word = str(word)

        count = str(count)

        fo.write(word)

        fo.write(' ')

        fo.write(count)

        fo.write('\n')

        print(word, count)

    fo.close()