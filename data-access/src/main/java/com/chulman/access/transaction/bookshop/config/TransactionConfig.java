package com.chulman.access.transaction.bookshop.config;

import com.chulman.access.transaction.bookshop.BookShop;
import com.chulman.access.transaction.bookshop.TransactionalJdbcBookShop;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
public class TransactionConfig {

    @Bean
    public DataSource dataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:test");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");

        DataSource dataSource = dataSourceBuilder.build();
        // schema init
        Resource initSchema = new ClassPathResource("bookshop/schema.sql");
        Resource initData = new ClassPathResource("bookshop/data.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, initData);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);

        return dataSource;
    }

    //JDBC DataSourceTransactionManager
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }

    // transactionTemplate은 thread-safe 한 객체
    @Bean
    public TransactionTemplate transactionTemplate(){
        return new TransactionTemplate(dataSourceTransactionManager());
    }

    @Bean
    public BookShop bookShop(){
        TransactionalJdbcBookShop shop = new TransactionalJdbcBookShop();
        shop.setDataSource(dataSource());
        shop.setTransactionManager(dataSourceTransactionManager());
        shop.setTransactionTemplate(transactionTemplate());
        return shop;
    }




}
