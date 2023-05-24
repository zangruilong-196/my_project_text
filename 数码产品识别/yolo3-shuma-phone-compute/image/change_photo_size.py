import cv2 as cv
from PIL import Image
orl_img = cv.imread('type.jpg', cv.IMREAD_UNCHANGED)
# img = cv.GaussianBlur(orl_img, (17,17), 0)
img = cv.resize(orl_img,(1563,875))
# img = cv.GaussianBlur(orl_img, (27, 27), 0)
cv.imwrite('dan2.jpg', img)

# cv.imshow("src", img)
# cv.waitKey(0)
