package com.chulman.access.jpa.course;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
public class CourseConfiguration {

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/course?verifyServerCertificate=false&useSSL=true");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setMinimumIdle(2);
        dataSource.setMinimumIdle(5);
        return dataSource;

    }


    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

//        localSessionFactoryBean.setMappingLocations(new ClassPathResource("com/chulman/acess/.../course.hbn.xml"));
        localSessionFactoryBean.setAnnotatedClasses(Course.class);

        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        localSessionFactoryBean.setDataSource(dataSource());
        return localSessionFactoryBean;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
//        properties.setProperty(AvailableSettings.URL, "jdbc:postgresql://localhost:6379");
        properties.put("hibernate.dialect",org.hibernate.dialect.DerbyTenSevenDialect.class.getName());
        properties.put("hibernate.show_sql",true);
        properties.put("hibernate.hbm2dll.auto","update");
        return properties;
    }
}
