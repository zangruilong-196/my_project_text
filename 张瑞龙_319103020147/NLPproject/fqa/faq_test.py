from sklearn.feature_extraction.text import CountVectorizer
import math
from fqa.data_process import segmentation
import numpy as np
np.seterr(divide='ignore',invalid='ignore')


count_vec = CountVectorizer()


def count_cos_similarity(vec_1, vec_2):
    if len(vec_1) != len(vec_2):
        return 0

    s = sum(vec_1[i] * vec_2[i] for i in range(len(vec_2)))
    den1 = math.sqrt(sum([pow(number, 2) for number in vec_1]))
    den2 = math.sqrt(sum([pow(number, 2) for number in vec_2]))
    return s / (den1 * den2)


def cos_sim(sentence1, sentence2):
    sentences = [sentence1, sentence2]
    # print(count_vec.fit_transform(sentences).toarray())  # 输出特征向量化后的表示
    # print(count_vec.get_feature_names())  # 输出的是切分的词， 输出向量各个维度的特征含义

    vec_1 = count_vec.fit_transform(sentences).toarray()[0]
    vec_2 = count_vec.fit_transform(sentences).toarray()[1]
    # print(len(vec_1), len(vec_2))
    return count_cos_similarity(vec_1, vec_2)


def get_answer(sentence1):
    sentence1 = segmentation(sentence1)
    score = []
    for idx, sentence2 in enumerate(
            open("D:\\user\\PycharmProjects\\NLPproject\\fqa\\data\\questionsout.txt", 'r', encoding='utf-8')):
        # print('idx: {}, sentence2: {}'.format(idx, sentence2))
        # print('idx: {}, cos_sim: {}'.format(idx, cos_sim(sentence1, sentence2)))
        score.append(cos_sim(sentence1, sentence2))
    if len(set(score)) == 1:
        print('抱歉，小骨也不知道。')
    else:
        index = score.index(max(score))
        file = open("D:\\user\\PycharmProjects\\NLPproject\\fqa\\data\\answers.txt", 'r', encoding='utf-8').readlines()
        print(file[index])


'''
while True:
    sentence1 = input('请输入您需要问的问题(输入q退出)：\n')
    if sentence1 == 'q':
        break
    else:
        get_answer(sentence1)
   
'''


def fqa_qa():
    while True:
        sentence1 = input('请输入您需要问的问题(输入q退出)：\n')
        if sentence1 == 'q':
            break
        else:
            get_answer(sentence1)
