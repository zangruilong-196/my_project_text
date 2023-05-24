# 利用保存的模型进行问答，并给出部分问答测试
# 结果对比情况等。
import os
import warnings
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing import sequence
import numpy as np
from sqe2sqe.data_process import data_processing

warnings.filterwarnings('ignore')
os.environ["CUDA_VISIBLE_DEVICES"] = "-1"
os.environ["TF_CPP_MIN_LOG_LEVEL"] = "3"

question_texts, answer_texts,encode_input,decode_input,decode_output,token_dict,token_dict_idx,token_dict_max_len,question_max_len,answer_max_len = data_processing()

model = load_model("D:\\user\\PycharmProjects\\NLPproject\\sqe2sqe\\save_model.h5")
encode_infer = load_model("D:\\user\\PycharmProjects\\NLPproject\\sqe2sqe\\encoder_infer.h5")
decode_infer = load_model("D:\\user\\PycharmProjects\\NLPproject\\sqe2sqe\\decoder_infer.h5")

def predict_qa(question):

    encode_output,question_h,question_c = encode_infer.predict(x=question,verbose=1)

    answer = np.zeros((1,answer_max_len))
    answer[0,0] = token_dict_idx['<BOS>']
    predict_seq = []
    flag = 0
    i = 0

    while flag != 1:
        predict,predict_h,predict_c = decode_infer.predict([answer,question_h,question_c,encode_output])
        char_idx = np.argmax(predict[0,-1,:])
        predict_seq.append(token_dict[char_idx])

        if char_idx == token_dict_idx['<EOS>'] or i > answer_max_len:
            flag = 1

        answer = np.zeros((1,answer_max_len))
        answer[0,0] = char_idx
        question_h = predict_h
        question_c = predict_c
        i +=1


    if '<EOS>' in predict_seq:
        predict_seq.remove('<EOS>')
    result = ''.join(predict_seq)
    return result


input_questions = np.array(encode_input)

for i in range(10,20):
    predict_answer = predict_qa(input_questions[i:i+1,:])
    print("问:%d" % i,question_texts[i])
    print("答:",answer_texts[i])
    print("预测回答：",predict_answer)
print('\n')


def process_input_question(question):
    question = ['<BOS>'] + list(question) + ['<EOS>']
    question = np.array([token_dict_idx[w] for w in question])
    question = sequence.pad_sequences([question],maxlen=question_max_len,padding='post',truncating='post')

    return question

while True:
    input_ques = input("请输入问题：\n")
    seq = process_input_question(input_ques)
    answer = predict_qa(seq)
    print("预测回答：",answer)
    print('\n')



