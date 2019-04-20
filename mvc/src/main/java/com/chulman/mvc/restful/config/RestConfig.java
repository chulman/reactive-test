package com.chulman.mvc.restful.config;

import com.chulman.mvc.restful.vo.Member;
import com.chulman.mvc.restful.vo.Members;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.chulman.mvc.restful.*")
public class RestConfig {

    @Bean
    public View memberTemplate(){
        return new MarshallingView(jaxb2Marshaller());
    }


    //jaxb(Java Architecture for XML Binding)
    // dependency spring-boot-oxm
    @Bean
    public Marshaller jaxb2Marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Members.class, Member.class);
        return marshaller;
    }

    @Bean
    public ViewResolver ViewResolver(){
        return new BeanNameViewResolver();
    }

    @Bean
    public View jsonMemberTemplate(){
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setPrettyPrint(true);
        return view;
    }

}
