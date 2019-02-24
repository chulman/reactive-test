package com.chulman.mvc.controller;

import com.chulman.mvc.pojo.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    SequenceGenerator sequenceGenerator;

    @GetMapping("/bean")
    public String getBeans(){
        return sequenceGenerator.getSequence();
    }
}

