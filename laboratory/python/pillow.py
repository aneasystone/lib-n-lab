from PIL import Image

im = Image.open('./images/1.jpg')
im.show()  # 调用系统默认程序打开图片

# 灰度
im = Image.open('./images/1.jpg')
gray = im.convert('L')
gray.save('./images/2.jpg')

# 缩略图
im = Image.open('./images/3.jpg')
im.thumbnail((128, 128))
im.save('./images/4.jpg')

# 裁剪和粘贴
im1 = Image.open('./images/1.jpg')
region = im1.crop((32, 5, 67, 27))
region = region.transpose(Image.ROTATE_90)
im3 = Image.open('./images/3.jpg')
im3.paste(region, (500,500))
im3.save('./images/5.jpg')

# 尺寸和旋转
Image.open('./images/1.jpg')\
    .resize((160,60))\
    .rotate(45)\
    .save('./images/6.jpg')
