package com.chulman.access.jdbc.vehicle;

import javax.sql.DataSource;


import com.mysql.jdbc.Driver;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;


@Configuration
public class VehicleConfiguration {


    // 접속을 매번 새로 열기때문에 좋지않다.
//    @Bean
//    public DataSource dataSource() {
//        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//        dataSource.setDriverClass(Driver.class);
//        dataSource.setUrl("jdbc:mysql://localhost:3306/mysql?verifyServerCertificate=false&useSSL=true");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//        return dataSource;
//
//    }

    //Apache Common 혹은 Hickari 등의 데이터소스는 풀링을 지원한다.
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mysql?verifyServerCertificate=false&useSSL=true");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setMinimumIdle(2);
        dataSource.setMinimumIdle(5);
        return dataSource;

    }

    @Bean
    public VehicleDao vehicleDao(DataSource dataSource) {
        return new PlainJdbcVehicleDao(dataSource);
    }


    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplateVehicleDao jdbcTemplateVehicleDao(DataSource dataSource) {
        return new JdbcTemplateVehicleDao(jdbcTemplate(dataSource));
    }


}
