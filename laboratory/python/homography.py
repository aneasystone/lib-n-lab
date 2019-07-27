#!/usr/bin/env python
# -*- coding: utf-8 -*-
# Created by aneasystone
from numpy import vstack
from numpy import ones
from numpy import array


def normalize(points):
    """ 归一化 """
    for row in points:
        row /= points[-1]
    return points


def make_homog(points):
    """ 转换为齐次坐标表示 """
    return vstack((points, ones((1, points.shape[1]))))


ps = array([[1,2],[3,4]])
print(make_homog(ps))
print(normalize(ps))