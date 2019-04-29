package com.chulman.access.jdbc.vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlainJdbcVehicleDao implements VehicleDao {

    private static final String INSERT_SQL = "INSERT INTO VEHICLE(COLOR, WHEEL, SEAT, VEHICLE_NO)" +
            " VALUES(?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE VEHICLE SET COLOR=?";

    private static final String SELECT_ONE = "SELECT * FROM VEHICLE WHERE VEHICLE_NO=?";
    private static final String SELECT_ALL = "SELECT * FROM VEHICLE";
    private static final String DELETE_SQL = "DELETE FROM VEHICLE WHERE VEHICLE_NO=?";


    private DataSource dataSource;

    public PlainJdbcVehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * try-with-resource 메커니즘을 적용한 이코드는 사용한 리소스를 자동으로 닫는다.
     * 보통 DAO 인터페이스 구현체에서는 RuntimeException의 하위 예외로 감싼다.
     * @param vehicle
     */
    @Override
    public void insert(Vehicle vehicle) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT_SQL);
            ps.setString(1, vehicle.getColor());
            ps.setInt(2, vehicle.getWheel());
            ps.setInt(3, vehicle.getSeat());
            ps.setString(4, vehicle.getVehicleNo());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void insert(Iterable<Vehicle> vehicles) {
        vehicles.forEach(this::insert);
    }

    @Override
    public void update(Vehicle vehicle) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATE_SQL);
            ps.setString(1, vehicle.getColor());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(Vehicle vehicle) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETE_SQL);
            ps.setString(1, vehicle.getVehicleNo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        Connection conn = null;
        Vehicle vehicle = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_ONE);
            ps.setString(1, vehicleNo);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                vehicle = toVehicle(rs);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicle;
    }

    @Override
    public List<Vehicle> findAll() {
        Connection conn = null;
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vehicles.add(toVehicle(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vehicles;
    }

    public static Vehicle toVehicle(ResultSet rs) throws SQLException {
        return new Vehicle(rs.getString("VEHICLE_NO"), rs.getString("COLOR"),
                rs.getInt("WHEEL"), rs.getInt("SEAT"));
    }
}
