package com.chulm.reactive.rxjava.controller;

import com.chulm.reactive.rxjava.sample.User;
import com.chulm.reactive.rxjava.sample.UserSearchService;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HomeController {

    @Resource
    UserSearchService searchService;

    @RequestMapping("/")
    public String index(){
        return "home";
    }

    @RequestMapping("/{name}")
    public User findName(@PathVariable String name){

        //Observer는 자기가 관심있는 객체의 상태가 변한 경우 통지(notify)를 받기 원하는 객체
        //searchService.getAllSampleUsers().subscribe(System.out::println);
        searchService.getAllSampleUsers().filter(user -> name.equalsIgnoreCase(user.getName()))
                                         .subscribe(System.out::println);

        return searchService.getAllSampleUsers().filter(user -> name.equalsIgnoreCase(user.getName())).blockingFirst();

    }
}
