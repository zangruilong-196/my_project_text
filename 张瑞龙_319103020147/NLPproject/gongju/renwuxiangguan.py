import jieba
import matplotlib.pyplot as plt
import wordcloud
import networkx as nx
import matplotlib
import jieba.posseg as psg

matplotlib.rcParams['font.sans-serif'] = ['SimHei']




# 读取文本
def read_txt():
    file = open("D:\\user\\PycharmProjects\\NLPproject\\text\\花千骨.txt",'r',encoding='utf-8')
    txt = file.read()
    file.close()
    return txt


# 词性统计（写入文档）
def sda():
    import jieba.posseg as psg
    text = open("D:\\user\\PycharmProjects\\NLPproject\\text\\花千骨.txt",'r',encoding='utf-8', errors='ignore').read()
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
def creat_wordcloud():
    f_0 = open("词频统计.txt", 'r')
    bg_pic = plt.imread("D:\\user\\PycharmProjects\\NLPproject\\gongju\\花千骨.jpg")
    text = f_0.read()
    f_0.close()
    wcloud = wordcloud.WordCloud(font_path="simfang.ttf",
                                 background_color="white", width=800,
                                 max_words=200,
                                 mask=bg_pic,
                                 height=600,
                                 margin=2,
                                 ).generate(text)
    wcloud.to_file("花千骨cloud.jpg")
    plt.imshow(wcloud)
    plt.axis('off')
    plt.show()


# 生成人物关系图（全按书上抄的）
def creat_relationship():
    Names = ['花千骨', '白子画', '东方彧卿', '糖宝', '杀阡陌', '竹染', '十一', ' 霓漫天 ', '摩严', '朔风']
    relations = {}
    lst_para = (read_txt()).split('n')  # lst_para是每一段
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
    plt.show()


def main():
    write_txt()
    creat_wordcloud()
    creat_relationship()


if __name__ == '__main__':
    main()