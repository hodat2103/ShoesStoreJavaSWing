package com.myproject.shoesstore.dao.DaoImpl;

import com.myproject.shoesstore.connection.ConnectSQL;
import com.myproject.shoesstore.dao.SalaryDao;
import com.myproject.shoesstore.model.Salary;
import com.myproject.shoesstore.model.Salary;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public class SalaryDaoImpl implements SalaryDao {

    private Connection conn = ConnectSQL.getConnection();
    private PreparedStatement ps = null;
    @Override
    public List<Salary> getList() {
        String sql = "SELECT * FROM tbl_salary";
        List<Salary> list = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Salary salary = new Salary();

                salary.setSalaryCode(rs.getString("salary_code"));
                salary.setEmplpoyeeCode(rs.getString("employee_code"));
                salary.setEmployeeName(rs.getString("employee_name"));
                salary.setEffectiveDate(rs.getDate("effective_date"));
                salary.setBasicSalary(rs.getDouble("basic_salary"));
                salary.setSubsidy(rs.getDouble("subsidy"));
                salary.setBonuses(rs.getDouble("bonuses"));
                salary.setForfeit(rs.getDouble("forfeit"));
                salary.setNetSalary(rs.getDouble("net_salary"));
                salary.setDescript(rs.getString("descript"));

                list.add(salary);
            }

            

            return list;
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public int createOrUpdate(Salary salary) {
        String sql = "INSERT INTO tbl_salary(salary_code, employee_code, employee_name, effective_date, basic_salary, subsidy, bonuses, forfeit, net_salary, descript) VALUES (?,?,?,?,?,?,?,?,?,?) "
                + "ON DUPLICATE KEY UPDATE  employee_code = VALUES(employee_code), employee_name = VALUES(employee_name), effective_date = VALUES(effective_date), basic_salary = VALUES(basic_salary), subsidy = VALUES(subsidy), bonuses = VALUES(bonuses),forfeit = VALUES(forfeit), net_salary = VALUES(net_salary), descript = VALUES(descript), salary_code = ?;";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, salary.getSalaryCode());
            ps.setString(2, salary.getEmplpoyeeCode());
            ps.setString(3, salary.getEmployeeName());
            ps.setDate(4, new Date (salary.getEffectiveDate().getTime()));
            ps.setDouble(5, salary.getBasicSalary());
            ps.setDouble(6, salary.getSubsidy());
            ps.setDouble(7, salary.getBonuses());
            ps.setDouble(8, salary.getForfeit());
            ps.setDouble(9, salary.getNetSalary());
            ps.setString(10, salary.getDescript());
            ps.setString(11, salary.getSalaryCode());

            int rows = ps.executeUpdate();

         

            return rows;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String salaryCode) {
        String sql = "DELETE FROM tbl_salary WHERE salary_code = ?";
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, salaryCode);
            ps.execute();
            int rows = ps.executeUpdate();
            
            return rows;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
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
    public Salary getCode(String code) {
String sql = "SELECT invoice_code FROM tbl_invoice WHERE invoice_code = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, code);
            Salary invoice = new Salary();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                invoice.setSalaryCode(rs.getString("invoice_code"));
            }

            return invoice;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;    }
    
}
