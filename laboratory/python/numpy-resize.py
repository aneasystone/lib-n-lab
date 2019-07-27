from PIL import Image
from numpy import uint8
from pylab import *


def resize(arr, size):
    im = Image.fromarray(uint8(arr))
    return array(im.resize(size))

im = array(Image.open('./images/1.jpg').convert('L'))
rim = resize(im, (50,10))

imshow(rim)
show()