package com.chulman.mvc.fundamental.locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/*
    SPRING MVC에서 User Locale은 LocaleResolver가 식별한다.
    DispatcherServlet이 자동 감지하려면 Locale Resolver Bean을 LocaleResolver라고 명명한다.
    DispatcherServlet당 하나의 LocaleResolver만 구현 가능하다.
 */
@Configuration
public class LocaleConfig implements WebMvcConfigurer {

    // SessionLocaleResolver - 세션에 사전 정의된 속성에 따라 해석, 속성이 없으면 accept-language 헤더로 기본 로케일 설정
    @Bean
    public LocaleResolver sessionLocaleResolver(){
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(new Locale("en"));
        return sessionLocaleResolver;
    }

    // CookieLocaleResolver - 유저 브라우저 쿠키 값에 따라 해석
    @Bean
    public LocaleResolver localeResolver(){
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setCookieName("language");
        cookieLocaleResolver.setCookieMaxAge(3600); //쿠키를 유지할 시간
        cookieLocaleResolver.setDefaultLocale(new Locale("en"));
        return cookieLocaleResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }


}
