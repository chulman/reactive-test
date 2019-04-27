package com.chulman.access.jdbc.vehicle;

import javax.sql.DataSource;
import java.util.List;

public class PlainJdbcVehicleDao implements VehicleDao {

    private  DataSource dataSource;

    public PlainJdbcVehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Vehicle vehicle) {

    }

    @Override
    public void insert(Iterable<Vehicle> vehicles) {

    }

    @Override
    public void update(Vehicle vehicle) {

    }

    @Override
    public void delete(Vehicle vehicle) {

    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        return null;
    }

    @Override
    public List<Vehicle> findAll() {
        return null;
    }
}
