package com.myproject.shoesstore.dao.DaoImpl;

import com.myproject.shoesstore.connection.ConnectSQL;
import com.myproject.shoesstore.dao.EmployeeDao;
import com.myproject.shoesstore.model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private Connection conn = ConnectSQL.getConnection();
    private PreparedStatement ps = null;

    @Override
    public List<Employee> getList() {

        String sql = "SELECT * FROM tbl_employee";
        List<Employee> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();

                employee.setEmployeeCode(rs.getString("employee_code"));
                employee.setName(rs.getString("name"));
                employee.setDate(rs.getDate("date"));
                employee.setGender(rs.getBoolean("gender"));
                employee.setPosition(rs.getString("position"));
                employee.setPhone(rs.getString("phone"));
                employee.setAddress(rs.getString("address"));

                list.add(employee);
            }

            return list;
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public int createOrUpdate(Employee employee) {
        String sql = "INSERT INTO tbl_employee(employee_code, name, date, gender, position, phone, address) VALUES (?,?,?,?,?,?,?) "
                + "ON DUPLICATE KEY UPDATE name = VALUES(name), date = VALUES(date), gender = VALUES(gender), position = VALUES(position), phone = VALUES(phone), address = VALUES(address), employee_code = ?;";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getEmployeeCode());
            ps.setString(2, employee.getName());
            ps.setDate(3, new Date(employee.getDate().getTime()));
            ps.setBoolean(4, employee.isGender());
            ps.setString(5, employee.getPosition());
            ps.setString(6, employee.getPhone());
            ps.setString(7, employee.getAddress());
            ps.setString(8, employee.getEmployeeCode());

            int rows = ps.executeUpdate();

            return rows;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String employeeCode) {
        String sql = "DELETE FROM tbl_employee WHERE employee_code = ?";
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, employeeCode);
            ps.execute();
            int rows = ps.executeUpdate();

            return rows;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public String getEmployeeNameByCode(String employeeCode) {
        String sql = "SELECT name FROM tbl_employee WHERE employee_code = ?";
        String employeeName = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, employeeCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employeeName = rs.getString("name");
            }

            return employeeName;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getListEmployeeCode() {
        String sql = "SELECT employee_code FROM tbl_employee";
        List<String> list = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String employeeCode = rs.getString("employee_code");

                list.add(employeeCode);
            }

            return list;
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public Employee getCode(String code) {
        String sql = "SELECT employee_code FROM tbl_employee WHERE employee_code = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, code);
            Employee employee = new Employee();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee.setEmployeeCode(rs.getString("employee_code"));
            }

            return employee;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
