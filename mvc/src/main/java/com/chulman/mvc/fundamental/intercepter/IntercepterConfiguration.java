package com.chulman.mvc.fundamental.intercepter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfiguration implements WebMvcConfigurer {

    @Bean
    public FundmentalIntercepter fundmentalIntercepter(){
        return new FundmentalIntercepter();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(fundmentalIntercepter()).addPathPatterns("/welcome/*/*").excludePathPatterns("/anybody");
//        registry.addInterceptor(fundmentalIntercepter());
    }


}
