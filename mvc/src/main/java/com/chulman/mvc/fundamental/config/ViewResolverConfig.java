package com.chulman.mvc.fundamental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.HashMap;
import java.util.Map;


@Configuration
@ComponentScan("com.chulman.mvc.fundamental.*")   //componentScan을 통해 controller 어노테이션을 감지
public class ViewResolverConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver(ContentNegotiationManager contentNegotiationManager){

        ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
        contentNegotiatingViewResolver.setContentNegotiationManager(contentNegotiationManager);
        return contentNegotiatingViewResolver;
    }



    //Http Accept 헤더를 조사해서 확장자를 구분하는 설정
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        Map<String, MediaType> mediaTypes =new HashMap<>();
        mediaTypes.put("html",MediaType.TEXT_HTML);
        mediaTypes.put("pdf",MediaType.APPLICATION_PDF);
        mediaTypes.put("xls",MediaType.valueOf("application/vnd.ms-excel"));
        mediaTypes.put("xml",MediaType.APPLICATION_XML);
        mediaTypes.put("json",MediaType.APPLICATION_JSON);
        configurer.mediaTypes(mediaTypes);
    }
}
