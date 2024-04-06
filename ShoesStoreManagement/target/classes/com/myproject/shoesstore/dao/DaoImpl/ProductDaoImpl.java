package com.myproject.shoesstore.dao.DaoImpl;

import com.myproject.shoesstore.connection.ConnectSQL;
import com.myproject.shoesstore.dao.ProductDao;
import com.myproject.shoesstore.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Tadaboh;
 */
public class ProductDaoImpl implements ProductDao{
    private Connection conn = ConnectSQL.getConnection();
    private PreparedStatement ps = null;
    @Override
    public List<Product> getList() {
        String sql = "SELECT * FROM tbl_product";
        List<Product> list = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();

                product.setProductCode(rs.getString("product_code"));
                product.setProductName(rs.getString("product_name"));
                product.setImage(rs.getBytes("image"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setSupplierCode(rs.getString("supplier_code"));
                product.setDescript(rs.getString("descript"));

                list.add(product);
            }

            

            return list;
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public int createOrUpdate(Product product) {
        String sql = "INSERT INTO tbl_product(product_code, product_name, image, price, quantity, supplier_code, descript) VALUES (?,?,?,?,?,?,?) "
                + "ON DUPLICATE KEY UPDATE product_name = VALUES(product_name), image = VALUES(image), price = VALUES(price), quantity = VALUES(quantity), supplier_code = VALUES(supplier_code), descript = VALUES(descript), product_code = ?;";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, product.getProductCode());
            ps.setString(2, product.getProductName());
            ps.setBytes(3, product.getImage());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getQuantity());
            ps.setString(6, product.getSupplierCode());
            ps.setString(7, product.getDescript());
            ps.setString(8, product.getProductCode());

            int rows = ps.executeUpdate();

         

            return rows;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String productCode) {
         String sql = "DELETE FROM tbl_product WHERE product_code = ?";
        try {
            ps = conn.prepareCall(sql);
            
            ps.setString(1, productCode);
            ps.execute();
            int rows = ps.executeUpdate();
            
            return rows;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<String> getListSupplierCode() {
        String sql = "SELECT supplier_code FROM tbl_supplier";
        List<String> list = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               

               String supplierCode = rs.getString("supplier_code");
               

                list.add(supplierCode);
            }

            

            return list;
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public String getProductNameByCode(String productCode) {
        String sql;
        sql = "SELECT product_name FROM tbl_product WHERE product_code = ?";
        String productName = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, productCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                productName = rs.getString("product_name");
            }

            return productName; 
        } catch (SQLException ex) {
        }
        return null;
    }

    @Override
    public double getPriceByCode(String productCode) {
        String sql;
        sql = "SELECT price FROM tbl_product WHERE product_code = ?";
        double price = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, productCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                price = Double.parseDouble(rs.getString("price"));
            }

            return price; 
        } catch (SQLException ex) {
        }
        return 0;
    }

    @Override
    public List<String> getListProductCode() {
        String sql = "SELECT product_code FROM tbl_product";
        List<String> list = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               

               String productCode = rs.getString("product_code");
               

                list.add(productCode);
            }

            

            return list;
        } catch (Exception ex) {

        }
        return null;
    }
    
    

    
}
