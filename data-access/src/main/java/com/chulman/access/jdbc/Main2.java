package com.chulman.access.jdbc;

import com.chulman.access.jdbc.vehicle.JdbcTemplateVehicleDao;
import com.chulman.access.jdbc.vehicle.Vehicle;
import com.chulman.access.jdbc.vehicle.VehicleConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;


public class Main2 {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(VehicleConfiguration.class);

        JdbcTemplateVehicleDao vehicleDao = context.getBean(JdbcTemplateVehicleDao.class);

        String color = vehicleDao.getColor("TEM0001");
        //red
        System.err.println(color);
        Vehicle vehicle = new Vehicle("1","RED", 4, 4);
        Vehicle vehicle2 = new Vehicle("2","BLUE", 4, 4);
        Vehicle vehicle3 = new Vehicle("3","GREEN", 4, 4);
        Vehicle vehicle4 = new Vehicle("4","YELLOW", 4, 4);
        vehicleDao.insert(Arrays.asList(vehicle,vehicle2, vehicle3, vehicle4));




    }

}