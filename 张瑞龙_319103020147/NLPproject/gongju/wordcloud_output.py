import re
import collections
import numpy as np
import jieba
import wordcloud
from PIL import Image
import matplotlib.pyplot as plt
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
object_list = []
remove_words = [u'的', u'，',u'和', u'是', u'随着', u'对于', u'对',u'等',u'能',u'都',u'。',u' ',u'、',u'中',u'在',u'了',
                u'通常',u'如果',u'我们',u'需要',u'她',u'他',u'你',u'我',u'也',u'着',u'不',u'需要',u'没有',u'可是',u'知道',
                u'呢',u'只是',u'却',u'吧',u'一样',u'那',u'所以',u'有',u'自己',u'虽然',u'再',u'要',u'吃',u'说',
                u'人',u'就',u'又',u'什么',u'被',u'这',u'啊',u'一个',u'去',u'还',u'几乎',u'把',u'到',u'会',
                u'可以',u'得',u'而',u'看着',u'它',u'只',u'么',u'想',u'怎么',u'看',u'一直',u'一切',u'事'
                ,u'看到',u'因为',u'为了',u'可能',u'还是',u'将',u'他们',u'很',u'好',u'最',u'那么',u'然后',u'慢慢'
                ,u'谁',u'现在',u'看见',u'这样',u'最好',u'回去',u'像',u'给',u'不过',u'一下',u'有些',u'但是',u'那个'
                ,u'就是',u'就算',u'上',u'来',u'杀',u'让'] # 自定义去除词库

for word in seg_list_exact:
    if word not in remove_words:
        object_list.append(word) # 分词追加到列表

# 词频统计
word_counts = collections.Counter(object_list)
word_counts_top10 = word_counts.most_common(10) # 获取前10最高频的词
print("前十高频词：")
print (word_counts_top10)
word_counts_top10 = str(word_counts_top10)

# 词频展示
mask = np.array(Image.open('image22.jpeg')) # 定义词频背景
wc = wordcloud.WordCloud(
    font_path='simfang.ttf', # 设置字体格式
    mask=mask, # 设置背景图
    max_words=200, # 最多显示词数
    max_font_size=150, # 字体最大值
    background_color='white',
    width=800, height=600,
)

wc.generate_from_frequencies(word_counts)
plt.imshow(wc)
plt.axis('off')
plt.show()
wc.to_file('wordcloud.png')


'''


def wordcloud_output():
    # 读取文件
    fn = open("D:\\user\\PycharmProjects\\NLPproject\\text\\花千骨.txt", 'r', encoding='utf-8')
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
    plt.show()
    wc.to_file('wordcloud.png')