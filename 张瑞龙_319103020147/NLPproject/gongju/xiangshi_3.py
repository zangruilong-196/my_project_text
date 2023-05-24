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

name = input("请输入 角色_1 姓名：")

print("与" + name + "相关的前10个词：")
b = model.wv.most_similar(name, topn=10)
print(b)

'''



def xiangshi3():
    sentences = word2vec.Text8Corpus('D:\\user\\PycharmProjects\\NLPproject\\text\\花千骨2.txt')
    sentences1 = word2vec.LineSentence('D:\\user\\PycharmProjects\\NLPproject\\text\\花千骨2.txt')
    model = word2vec.Word2Vec(sentences, min_count=1)
    model1 = word2vec.Word2Vec(sentences1, min_count=1)

    name = input("请输入角色姓名：")

    print("与" + name + "相关的前10个词：")
    b = model.wv.most_similar(name, topn=10)
    print(b)