import jieba


def stopword_list():
    stopwords = [line.strip() for line in open('stopword.txt', encoding='utf-8').readlines()]
    return stopwords


def seg_with_stop(sentence):
    sentence_seg = jieba.cut(sentence.strip())
    stopwords = stopword_list()
    out_string = ''
    for word in sentence_seg:
        if word not in stopwords:
            if word != '\t':
                out_string += word
                out_string += " "
    return out_string


def segmentation(sentence):
    sentence_seg = jieba.cut(sentence.strip())
    out_string = ''
    for word in sentence_seg:
        out_string += word
        out_string += " "
    return out_string


inputQ = open("D:\\user\\PycharmProjects\\NLPproject\\fqa\\data\\questions.txt", 'r', encoding='utf-8')
outputQ = open("D:\\user\\PycharmProjects\\NLPproject\\fqa\\data\\questionsout.txt", 'w', encoding='utf-8')
inputA = open('D:\\user\\PycharmProjects\\NLPproject\\fqa\\data\\answers.txt', 'r', encoding='utf-8')
outputA = open("D:\\user\\PycharmProjects\\NLPproject\\fqa\\data\\answersout.txt", 'w', encoding='utf-8')

for line in inputQ:
    line_seg = segmentation(line)
    outputQ.write(line_seg + '\n')

outputQ.close()
inputQ.close()

for line in inputA:
    line_seg = segmentation(line)
    outputA.write(line_seg + '\n')

outputA.close()
inputA.close()
