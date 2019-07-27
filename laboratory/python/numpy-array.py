from PIL import Image
from numpy import array

im = array(Image.open('./images/1.jpg'))
print(im.shape, im.dtype)

# im[i,j,k] 表示位于 i 行 j 列 第 k 个颜色通道的像素值
print(im[0,0,0], im[0,0,1], im[0,0,2])

# 第 1 行所有像素
print(im[0,:])

# 第 1 列所有像素
print(im[:,0])

# 第 5-10 行，5-10 列
print(im[4:9,4:9])

# 倒数第 2 行
print(im[-2,:])
# print(im[-2])
