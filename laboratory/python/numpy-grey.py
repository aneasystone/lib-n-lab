from PIL import Image
from numpy import array
from pylab import *

im = array(Image.open('./images/1.jpg').convert('L'))

# 灰度值反相
im2 = 255 - im

# 将灰度值变换到 100...200 之间
im3 = 100.0 * im / 255.0 + 100

im4 = 255.0 * (im/255.0)**2

imshow(im2)
figure()
imshow(im3)
figure()
imshow(im4)
show()
