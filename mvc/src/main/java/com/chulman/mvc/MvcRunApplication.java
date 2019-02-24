package com.chulman.mvc;

import com.chulman.mvc.pojo.SequenceDao;
import com.chulman.mvc.pojo.SequenceGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class MvcRunApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MvcRunApplication.class, args);
        SequenceDao dao = context.getBean("SequenceDao",SequenceDao.class);
        System.err.println(dao.getSequnce());
    }
}
