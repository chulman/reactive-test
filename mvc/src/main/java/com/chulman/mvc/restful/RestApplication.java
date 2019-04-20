package com.chulman.mvc.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.chulman.mvc.restful.*")
public class RestApplication {
    public static void main(String[] args){
        SpringApplication.run(RestApplication.class, args);
    }
}
