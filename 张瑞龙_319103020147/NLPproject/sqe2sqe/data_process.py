# 定义函数进行数据预处理，获得满足编码解码
# 输入输出数据格式的数据集；

import pandas as pd


#
def token_chinese(texts):
    texts_token = []
    for sentence in texts:
        sentence_list = list(sentence)
        texts_token.append(sentence_list)
    return texts_token


def get_token_dict(token_list):
    token_dict = {
        '<PAD>': 0,
        '<BOS>': 1,
        '<EOS>': 2,

    }  # 添加特殊字符
    for tokens in token_list:
        for token in tokens:
            if token not in token_dict:
                token_dict[token] = len(token_dict)
    return token_dict


def data_processing():
    df = pd.read_table('D:\\user\\PycharmProjects\\自然语言处理\\sqe2sqe2问答\\data\\chatterbot.tsv', engine='python',header=None).iloc[:, :, ]
    df.columns = ['questions', 'answers']

    #
    question_texts = df.questions.values.tolist()
    answer_texts = df.answers.values.tolist()

    question_tokens = token_chinese(question_texts)
    answer_tokens = token_chinese(answer_texts)

    token_dict_idx = get_token_dict(question_tokens + answer_tokens)
    token_dict = {v: k for k, v in token_dict_idx.items()}
    token_dict_max_len = len(token_dict)

    encode_tokens = [['<BOS>'] + tokens + ['<EOS>'] for tokens in question_tokens]
    decode_tokens = [['<BOS>'] + tokens + ['<EOS>'] for tokens in answer_tokens]
    output_tokens = [tokens + ['<EOS>', '<PAD>'] for tokens in answer_tokens]

    question_max_len = max(map(len, encode_tokens))
    answer_max_len = max(map(len, decode_tokens))
    encode_tokens = [tokens + ['<PAD>'] * (question_max_len - len(tokens)) for tokens in encode_tokens]
    decode_tokens = [tokens + ['<PAD>'] * (answer_max_len - len(tokens)) for tokens in decode_tokens]
    output_tokens = [tokens + ['<PAD>'] * (answer_max_len - len(tokens)) for tokens in output_tokens]

    encode_input = [list(map(lambda x: token_dict_idx[x], tokens)) for tokens in encode_tokens]
    decode_input = [list(map(lambda x: token_dict_idx[x], tokens)) for tokens in decode_tokens]
    decode_output = [list(map(lambda x: token_dict_idx[x], tokens)) for tokens in output_tokens]

    return question_texts, answer_texts, encode_input, decode_input, decode_output, token_dict, token_dict_idx, token_dict_max_len, question_max_len, answer_max_len
