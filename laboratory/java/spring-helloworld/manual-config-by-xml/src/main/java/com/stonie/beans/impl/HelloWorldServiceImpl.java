package com.stonie.beans.impl;

import com.stonie.beans.HelloWorldService;

public class HelloWorldServiceImpl implements HelloWorldService {
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
