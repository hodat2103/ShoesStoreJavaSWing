package com.myproject.shoesstore.dao.DaoImpl;

import com.myproject.shoesstore.connection.ConnectSQL;
import com.myproject.shoesstore.dao.CustomerDao;
import com.myproject.shoesstore.model.Customer;
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
public class CustomerDaoImpl implements CustomerDao{
    
    private Connection conn = ConnectSQL.getConnection();
    private PreparedStatement ps = null;
    @Override
    public List<Customer> getList() {
        
        String sql = "SELECT * FROM tbl_customer";
        List<Customer> list = new ArrayList<>();
        
        try{
            
                ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Customer customer = new Customer();
                
                customer.setCustomerCode(rs.getString("customer_code"));
                customer.setName(rs.getString("name"));
                customer.setDate(rs.getDate("date"));
                customer.setGender(rs.getBoolean("gender"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                
                list.add(customer);
            }
            
            
           
            
            return list;
        }catch(Exception ex){
            
        }
        return null;
    }
//    public static void main(String[] args) {
//        CustomerDao customerDao = new CustomerDaoImpl();
//        System.out.println(customerDao.getList());
//        
//    }

    @Override
    public int createOrUpdate(Customer customer) {
       
        String sql = "INSERT INTO tbl_customer(customer_code, name, date, gender, phone, address) VALUES (?,?,?,?,?,?) "
                + "ON DUPLICATE KEY UPDATE name = VALUES(name), date = VALUES(date), gender = VALUES(gender), phone = VALUES(phone), address = VALUES(address), customer_code = ?;";
        
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, customer.getCustomerCode());
            ps.setString(2, customer.getName());
            ps.setDate(3, new Date(customer.getDate().getTime()));
            ps.setBoolean(4, customer.isGender());
            ps.setString(5, customer.getPhone());
            ps.setString(6, customer.getAddress());
            ps.setString(7, customer.getCustomerCode());
            
            ps.execute();
            
            int rows = ps.executeUpdate();
            
            
            ps.close();
            conn.close();
            
            return rows;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;

        }
    }

    @Override
    public int delete(String customerCode) {
        String sql = "DELETE FROM tbl_customer WHERE customer_code = ? ";
        
        try {
            ps = conn.prepareCall(sql);
            
            ps.setString(1, customerCode);
            
            ps.execute();
            
            int rows = ps.executeUpdate();
            
            return rows;
        } catch (SQLException ex) {   
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public String getCustomerNameByCode(String customerCode) {
     String sql = "SELECT name FROM tbl_customer WHERE customer_code = ?";
        String customerName = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, customerCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customerName = rs.getString("name");
            }

            return customerName; 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null; 
    }

    @Override
    public List<String> getListCustomerCode() {
        String sql = "SELECT customer_code FROM tbl_customer";
        List<String> list = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               

               String customerCode = rs.getString("customer_code");
               

                list.add(customerCode);
            }

            

            return list;
        } catch (Exception ex) {

        }
        return null;
    }
    
}
