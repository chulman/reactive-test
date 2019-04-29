package com.chulman.access.jdbc.vehicle;


import org.springframework.jdbc.core.*;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//jdbc template 클래스는 여러가지 update() 템플릿이 오버로드 되어 있다.
// 기본적인 수정 작업에서 조금씩 파생된 하위 작업을 상이한 버전의 update()메소드들로 정의
// 몇 가지 콜백을 정의해 스프링에서는 하위 작업을 캡슐화함
public class JdbcTemplateVehicleDao implements VehicleDao {


    private static final String INSERT_SQL = "INSERT INTO VEHICLE(COLOR, WHEEL, SEAT, VEHICLE_NO)" +
            " VALUES(?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE VEHICLE SET COLOR=?";

    private static final String SELECT_ONE = "SELECT * FROM VEHICLE WHERE VEHICLE_NO=?";
    private static final String SELECT_ALL = "SELECT * FROM VEHICLE";
    private static final String DELETE_SQL = "DELETE FROM VEHICLE WHERE VEHICLE_NO=?";


    private JdbcTemplate jdbcTemplate;



    public JdbcTemplateVehicleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private void prepareStatement(PreparedStatement ps, Vehicle vehicle) throws SQLException {
        ps.setString(1, vehicle.getColor());
        ps.setInt(2, vehicle.getWheel());
        ps.setInt(3, vehicle.getSeat());
        ps.setString(4, vehicle.getVehicleNo());
    }

    @Override
    public void insert(Vehicle vehicle) {

//        jdbcTemplate.update(INSERT_SQL, ps -> prepareStatement(ps, vehicle));
        jdbcTemplate.update(INSERT_SQL, vehicle.getColor(), vehicle.getWheel(), vehicle.getSeat(), vehicle.getVehicleNo());
    }

    @Override
    public void insert(Iterable<Vehicle> vehicles) {
        vehicles.forEach(this::insert);
    }

    public void insert(Collection<Vehicle> vehicles) {
        jdbcTemplate.batchUpdate(INSERT_SQL, vehicles, vehicles.size(), this::prepareStatement);
    }

    @Override
    public void update(Vehicle vehicle) {
        jdbcTemplate.update(UPDATE_SQL, vehicle.getColor());
    }

    @Override
    public void delete(Vehicle vehicle) {
        jdbcTemplate.update(DELETE_SQL, vehicle.getVehicleNo());

    }


    //spring 은 query() 메소드에서 결과를 처리할 수 있게 rowCallbackHandler, RowMapper 두 인터페이스를 지원

    /*
        example 1 RowCallbackHandler
     */
//    @Override
//    public Vehicle findByVehicleNo(String vehicleNo) {
//        final Vehicle vehicle = new Vehicle();
//        jdbcTemplate.query(SELECT_ONE,
//                new RowCallbackHandler() {
//                    @Override
//                    public void processRow(ResultSet rs) throws SQLException {
//                        vehicle.setColor(rs.getString("COLOR"));
//                        vehicle.setWheel(rs.getInt("WHEEL"));
//                        vehicle.setSeat(rs.getInt("SEAT"));
//                        vehicle.setVehicleNo(rs.getString("VEHICLE_NO"));
//                    }
//                });
//        return vehicle;
//    }

    //Row Mapper<T>는 ResultSet의 로우를 하나씩 주어진 객체에 매핑시켜 단일.다중 로우 모두 사용할 수 있게끔
    //rowCallbackHandler를 더 일반화 시킨 인터페이스

   private class VehicleRowMapper implements RowMapper<Vehicle>{

       @Override
       public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
           return PlainJdbcVehicleDao.toVehicle(rs);
       }
   }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        return jdbcTemplate.queryForObject(SELECT_ONE, new VehicleRowMapper(), vehicleNo);
    }



    @Override
    public List<Vehicle> findAll() {
        return jdbcTemplate.query(SELECT_ALL, BeanPropertyRowMapper.newInstance(Vehicle.class));
    }

    //단일 값 매핑
    public String getColor(String vehicleNo){
       String sql = "SELECT COLOR FROM VEHICLE WHERE VEHICLE_NO=?";
       return jdbcTemplate.queryForObject(sql, String.class, vehicleNo);
    }
}
