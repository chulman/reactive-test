package com.chulman.mvc.fundamental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.chulman.mvc.fundamental.*")   //componentScan을 통해 controller 어노테이션을 감지
public class FundmentalApplication {
    public static void main(String[] args){
        SpringApplication.run(FundmentalApplication.class, args);
    }
}
