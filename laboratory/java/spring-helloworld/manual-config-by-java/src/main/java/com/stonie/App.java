package com.stonie;

import com.stonie.beans.HelloWorldService;
import com.stonie.configuration.HelloWorldConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main( String[] args ) {
        //
        // 方式一：使用 AnnotationConfigApplicationContext 构造函数直接注册
        //
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfig.class);

        //
        // 方式二：使用 register() + refresh() 方法注册
        //
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(HelloWorldConfig.class);
        context.refresh();

        HelloWorldService bean = (HelloWorldService) context.getBean("helloWorldService");
        bean.sayHello("World");
        context.close();
    }
}
