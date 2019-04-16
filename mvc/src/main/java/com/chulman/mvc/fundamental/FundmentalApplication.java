package com.chulman.mvc.fundamental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.*")
public class FundmentalApplication {
    public static void main(String[] args){
        SpringApplication.run(FundmentalApplication.class, args);
    }
}
