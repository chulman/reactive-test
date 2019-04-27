package com.chulman.access.jdbc.vehicle;

import javax.sql.DataSource;


import com.mysql.jdbc.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;



@Configuration
public class VehicleConfiguration {

    @Bean
    public VehicleDao vehicleDao(DataSource dataSource) {
        return new PlainJdbcVehicleDao(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/mysql?verifyServerCertificate=false&useSSL=true");
        dataSource.setUsername("mysql");
        dataSource.setPassword("mysql");
        return dataSource;

    }
}
