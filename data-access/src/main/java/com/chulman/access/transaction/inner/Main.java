package com.chulman.access.transaction.inner;

import com.chulman.access.transaction.bookshop.BookShop;
import com.chulman.access.transaction.config.TransactionConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(TransactionConfig.class);
        BookShop bookShop = context.getBean(BookShop.class);
        bookShop.purchase("0001", "user1");
    }
}
