package com.stonie;

import com.stonie.beans.HelloWorldService;
import com.stonie.beans.TimeService;
import com.stonie.configuration.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main( String[] args ) {

        //
        // 方式一：使用 AnnotationConfigApplicationContext 构造函数直接注册
        //
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //
        // 方式二：使用 register() + refresh() 方法注册
        //
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //context.register(AppConfig.class);
        //context.refresh();

        //
        // 方式三：使用 scan() 方法扫描自动注册
        //
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.stonie.beans");
        context.refresh();

        HelloWorldService helloWorldService = (HelloWorldService) context.getBean("helloWorldService");
        helloWorldService.sayHello("World");

        TimeService timeService = (TimeService) context.getBean("timeService");
        System.out.println(timeService.getCurrentTime());

        context.close();
    }
}
