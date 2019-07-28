package com.stonie;

import com.stonie.beans.HelloWorldService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("helloworld-config.xml");
        HelloWorldService bean = (HelloWorldService) context.getBean("helloWorldService");
        bean.sayHello("World");
        context.close();
    }
}
