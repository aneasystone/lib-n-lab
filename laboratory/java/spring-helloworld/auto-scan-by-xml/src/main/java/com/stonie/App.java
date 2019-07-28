package com.stonie;

import com.stonie.beans.HelloWorldService;
import com.stonie.beans.TimeService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");

        HelloWorldService helloWorldService = (HelloWorldService) context.getBean("helloWorldService");
        helloWorldService.sayHello("World");

        TimeService timeService = (TimeService) context.getBean("timeService");
        System.out.println(timeService.getCurrentTime());

        context.close();
    }
}
