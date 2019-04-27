package com.chulman.access.jdbc.vehicle;

import java.util.List;

public interface VehicleDao {
    void insert(Vehicle vehicle);
    void insert(Iterable<Vehicle> vehicles);
    void update(Vehicle vehicle);
    void delete(Vehicle vehicle);
    Vehicle findByVehicleNo(String vehicleNo);

    List<Vehicle> findAll();
}
