package com.chulman.mvc.fundamental.locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class LocaleMessageTextConfig {

    //ResourceBundleMessageSource를 통해 페이지를 여러 개 개발하는 것이 아닌 메시지를 언어별로 각각 로드할 수 있다.
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("messages");
        return resourceBundleMessageSource;
    }
}
