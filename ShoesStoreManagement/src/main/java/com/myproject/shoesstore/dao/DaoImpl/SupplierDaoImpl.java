package com.myproject.shoesstore.dao.DaoImpl;

import com.myproject.shoesstore.connection.ConnectSQL;
import com.myproject.shoesstore.dao.SupplierDao;
import com.myproject.shoesstore.model.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tadaboh;
 */
public class SupplierDaoImpl implements SupplierDao {
    private Connection conn = ConnectSQL.getConnection();
    PreparedStatement ps = null;
    @Override
    public List<Supplier> getList() {
        Connection conn = ConnectSQL.getConnection();
        String sql = "SELECT * FROM tbl_supplier";
        List<Supplier> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Supplier supplier = new Supplier();

                supplier.setSupplierCode(rs.getString("supplier_code"));
                supplier.setSupplierName(rs.getString("supplier_name"));
                supplier.setTradeName(rs.getString("trade_name"));
                supplier.setAddress(rs.getString("address"));
                supplier.setPhone(rs.getString("phone"));
                supplier.setEmail(rs.getString("email"));

                list.add(supplier);
            }

            ps.close();
            rs.close();
            conn.close();

            return list;
        } catch (Exception ex) {

        }
        return null;
    }
//    public static void main(String[] args) {
//        SupplierDao supplierDao = new SupplierDaoImpl();
//        System.out.println(supplierDao.getList());
//    }
    @Override
    public int createOrUpdate(Supplier supplier) {
        
        String sql = "INSERT INTO tbl_supplier(supplier_code, supplier_name, trade_name, address, phone, email) VALUES (?,?,?,?,?,?) "
                + "ON DUPLICATE KEY UPDATE supplier_name = VALUES(supplier_name), trade_name = VALUES(trade_name), address = VALUES(address), phone = VALUES(phone), email = VALUES(email), supplier_code = ?;";
        
        try {
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, supplier.getSupplierCode());
            ps.setString(2, supplier.getSupplierName());
            ps.setString(3, supplier.getTradeName());
            ps.setString(4, supplier.getAddress());
            ps.setString(5, supplier.getPhone());
            ps.setString(6, supplier.getEmail());
            ps.setString(7, supplier.getSupplierCode());
            
            ps.execute();
            
            int rows = ps.executeUpdate();
            
            
           
            
            return rows;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;

        }
    }

    @Override
    public int delete(String supplierCode) {
        String sql = "DELETE FROM tbl_supplier WHERE supplier_code = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,supplierCode);
            
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

}
