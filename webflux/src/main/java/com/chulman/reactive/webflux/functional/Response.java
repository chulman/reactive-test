package com.chulman.reactive.webflux.functional;

import org.springframework.http.HttpStatus;

public class Response {

    HttpStatus status;

    public Response() {
    }

    public Response(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
