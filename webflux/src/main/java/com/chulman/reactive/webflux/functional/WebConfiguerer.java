package com.chulman.reactive.webflux.functional;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;


/**
 *RestController와 같은 역할
 */


@Configuration
@EnableWebFlux
public class WebConfiguerer {

    @Bean
    public RouterFunction<ServerResponse> routes(StatusHandler handler) {
        return RouterFunctions.route(GET("/status"), handler::getResponse);
    }
}
