package com.chulman.mvc.fundamental.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/welcome/*")
public class WelcomeController {

    @PostMapping("add")
    public String add(Model model){
        return "add";
    }

    @DeleteMapping("delete")
    public String delete(Model model){
        return "delete";
    }

    @GetMapping("member/{member}")
    public String get(@PathVariable("member") String member){
        System.out.println("GetMapping:" + member);
        return "getMember";
    }

    @PutMapping("update/{member}")
    public String update(@PathVariable String member){
        System.out.println("update member:" + member);
        return "getMember";
    }

    @RequestMapping(value = {"display/google", "display/googles"})
    public String anotherRequest(){
        return "http://www.google.com:";
    }


}
