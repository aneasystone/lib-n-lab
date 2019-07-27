from PIL import Image
from pylab import *

# 加载图像
im = array(Image.open('./images/1.jpg'))
imshow(im)

# 标记点
x = [30, 40, 40, 60]
y = [30, 40, 50, 40]
plot(x, y, 'r*')

# 线连接
plot(x[:3], y[:3])

# 标题
title('Captcha Image')
show()
