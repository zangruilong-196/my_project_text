from gensim.models import word2vec
# 引入日志配置
import logging

logging.basicConfig(format='%(asctime)s : %(levelname)s : %(message)s', level=logging.INFO)

# 构建并训练模型
'''
sentences = word2vec.Text8Corpus('D:\\user\\PycharmProjects\\NLPproject\\text\\花千骨2.txt')
sentences1 = word2vec.LineSentence('D:\\user\\PycharmProjects\\NLPproject\\text\\花千骨2.txt')
model = word2vec.Word2Vec(sentences, min_count=1)
model1 = word2vec.Word2Vec(sentences1, min_count=1)

name1 = input("请输入 角色_1 姓名：")
name2 = input("请输入 角色_2 姓名：")

print(name1 + "和" + name2 + "的相似度为：")
a = model.wv.similarity(name1, name2)
print(a)
'''


def xiangshi2():
    sentences = word2vec.Text8Corpus('D:\\user\\PycharmProjects\\NLPproject\\text\\花千骨2.txt')
    sentences1 = word2vec.LineSentence('D:\\user\\PycharmProjects\\NLPproject\\text\\花千骨2.txt')
    model = word2vec.Word2Vec(sentences, min_count=1)
    model1 = word2vec.Word2Vec(sentences1, min_count=1)

    name1 = input("请输入 角色_1 姓名：")
    name2 = input("请输入 角色_2 姓名：")

    print(name1 + "和" + name2 + "的相似度为：")
    a = model.wv.similarity(name1, name2)
    print(a)
