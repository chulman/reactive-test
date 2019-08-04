package com.chulman.access.transaction.annotation;

import com.chulman.access.transaction.bookshop.Cashier;
import com.chulman.access.transaction.config.TransactionConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(TransactionConfig.class);
        Cashier cashier = context.getBean(Cashier.class);
        List<String> isbnList = Arrays.asList(new String[]{"0001", "0002"});
        cashier.checkout(isbnList,"user1");
    }
}
