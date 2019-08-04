package com.chulman.access.transaction.config;

import com.chulman.access.transaction.bookshop.BookShop;
import com.chulman.access.transaction.bookshop.BookShopCashier;
import com.chulman.access.transaction.bookshop.Cashier;
import com.chulman.access.transaction.inner.TransactionalJdbcBookShop;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

// ioc 컨테이너에 선언된 빈들을 찾아 @Transactional 을 붙인 메서드 중 public 메서드를 가져와 어드바이스를 적용한다.
@EnableTransactionManagement
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

    @Bean
    public Cashier cashier(){
        BookShopCashier bookShopCashier = new BookShopCashier();
        bookShopCashier.setBookShop(bookShop());
        return bookShopCashier;
    }


}
