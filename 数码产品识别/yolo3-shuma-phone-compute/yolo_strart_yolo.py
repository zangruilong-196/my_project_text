import colorsys
import os
from timeit import default_timer as timer

import numpy as np
from keras import backend as K
from keras.models import load_model
from keras.layers import Input
from PIL import Image, ImageFont, ImageDraw

from yolo3.model import yolo_eval, yolo_body, tiny_yolo_body
from yolo3.utils import letterbox_image
import os
from keras.utils import multi_gpu_model


from yolo3.model import yolo_body
from keras.layers import Input
import cv2 as cv
from yolo import YOLO
from PIL import Image
import matplotlib.pyplot as plt


yolo = YOLO()

while True:
    img = input('请输入图片路径:')
    try:
        image = Image.open(img)
    except:
        print('Open Error!')
        continue
    else:
        rel_image = yolo.detect_image(image)
        rel_image.show()
yolo.close_session()

