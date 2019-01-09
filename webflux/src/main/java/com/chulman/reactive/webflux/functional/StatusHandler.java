package com.chulman.reactive.webflux.functional;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class StatusHandler {

    public Mono<ServerResponse> getResponse(ServerRequest request) {
        Mono<Response> respMono = Mono.just(new Response(HttpStatus.OK));
        return ServerResponse.ok().body(respMono, Response.class);
    }
}
