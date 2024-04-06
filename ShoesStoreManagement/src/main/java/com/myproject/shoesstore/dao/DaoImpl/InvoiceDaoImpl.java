package com.myproject.shoesstore.dao.DaoImpl;

import com.myproject.shoesstore.connection.ConnectSQL;
import com.myproject.shoesstore.dao.InvoiceDao;
import com.myproject.shoesstore.model.Invoice;
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
public class InvoiceDaoImpl implements InvoiceDao{
private Connection conn = ConnectSQL.getConnection();
    private PreparedStatement ps = null;
    @Override
    public List<Invoice> getList() {
        String sql = "SELECT * FROM tbl_invoice";
        List<Invoice> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Invoice invoice = new Invoice();

                invoice.setInvoiceCode(rs.getString("invoice_code"));
                invoice.setCustomerCode(rs.getString("customer_code"));
                invoice.setProductCode(rs.getString("product_code"));
                invoice.setEmployeeCode(rs.getString("employee_code"));
                invoice.setDateBuy(rs.getDate("date_buy"));
                invoice.setQuantity(rs.getInt("quantity"));
                invoice.setDiscountCode(rs.getString("discount_code"));
                invoice.setPrice(rs.getDouble("price"));

                list.add(invoice);
            }

            

            return list;
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public int createOrUpdate(Invoice invoice) {
        String sql = "INSERT INTO tbl_invoice(invoice_code, customer_code,product_code,employee_code, date_buy, quantity, discount_code, price) VALUES (?,?,?,?,?,?,?,?) "
                + "ON DUPLICATE KEY UPDATE customer_code = VALUES(customer_code), product_code = VALUES(product_code), employee_code = VALUES(employee_code), date_buy = VALUES(date_buy), quantity = VALUES(quantity), discount_code = VALUES(discount_code), price = VALUES(price), invoice_code = ?;";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, invoice.getInvoiceCode());
            ps.setString(2, invoice.getCustomerCode());
            ps.setString(3, invoice.getProductCode());
            ps.setString(4, invoice.getEmployeeCode());
            ps.setDate(5, new Date(invoice.getDateBuy().getTime()));
            ps.setInt(6, invoice.getQuantity());
            ps.setString(7, invoice.getDiscountCode());
            ps.setDouble(8, invoice.getPrice());
            ps.setString(9, invoice.getInvoiceCode());

            int rows = ps.executeUpdate();

         

            return rows;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String invoiceCode) {
        String sql = "DELETE FROM tbl_invoice WHERE invoice_code = ?";
        try {
            ps = conn.prepareCall(sql);
            
            ps.setString(1, invoiceCode);
            ps.execute();
            int rows = ps.executeUpdate();
            
            return rows;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
}
