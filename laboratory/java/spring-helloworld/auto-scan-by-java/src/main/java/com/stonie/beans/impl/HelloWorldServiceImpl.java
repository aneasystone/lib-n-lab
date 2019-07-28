package com.stonie.beans.impl;

import com.stonie.beans.HelloWorldService;
import org.springframework.stereotype.Service;

@Service("helloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService {
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
