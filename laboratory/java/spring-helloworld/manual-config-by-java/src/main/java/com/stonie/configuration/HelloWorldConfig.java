package com.stonie.configuration;

import com.stonie.beans.HelloWorldService;
import com.stonie.beans.impl.HelloWorldServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfig {
    @Bean
    public HelloWorldService helloWorldService() {
        return new HelloWorldServiceImpl();
    }
}
