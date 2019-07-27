from PIL import Image
from pylab import *

im = array(Image.open('./images/1.jpg').convert('L'))

# 轮廓
figure()
gray()
contour(im, origin='image')

# 直方图
# im 是一个二维数组，im.flatten() 将其转换为一维数组
# 128 表示直方图上 bin 的数目
figure()
hist(im.flatten(), 128)

# 手工标记
figure()
imshow(im)
print('please click 2 points')
x = ginput(2)
print('you clicked: ' + str(x))

show()