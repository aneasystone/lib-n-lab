package com.stonie;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HelloWorld implements CommandLineRunner {

    @Override
    public void run(String... args) {
        if (args.length > 0) {
            System.out.println("Hello " + args[0] + ".");
        } else {
            System.out.println("Hello noone.");
        }
    }
}