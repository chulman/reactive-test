package com.chulman.mvc.sequence.controller;

import com.chulman.mvc.sequence.pojo.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
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

