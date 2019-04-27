package com.chulman.access.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.chulman.access.jdbc.*")
public class RunJdbcApplication {
    public static void main(String[] args){
        SpringApplication.run(RunJdbcApplication.class, args);
    }
}

