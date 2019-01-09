package com.chulman.reactive.webflux.annotation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class BasicController {

    @GetMapping("/hello/mono")
    public Mono<Message> helloMono() {
        Message message = new Message();
        message.setContent("hello world");
        return Mono.just(message);
    }

    @GetMapping("/hello/flux")
    public Flux<Message> helloFlux() {

        Message message = new Message();
        message.setContent("hello world");

        Message message2 = new Message();
        message2.setContent("hello world2");

        return Flux.just(message, message2);
    }
}
