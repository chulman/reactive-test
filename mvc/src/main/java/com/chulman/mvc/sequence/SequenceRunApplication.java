package com.chulman.mvc.sequence;

import com.chulman.mvc.sequence.pojo.SequenceDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SequenceRunApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SequenceRunApplication.class, args);
        SequenceDao dao = context.getBean("SequenceDao",SequenceDao.class);
        System.err.println(dao.getSequnce());
    }
}
