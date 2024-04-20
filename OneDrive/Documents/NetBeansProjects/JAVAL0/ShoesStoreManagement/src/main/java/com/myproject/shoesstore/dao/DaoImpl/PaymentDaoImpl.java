package com.myproject.shoesstore.dao.DaoImpl;

import com.myproject.shoesstore.connection.ConnectSQL;
import com.myproject.shoesstore.dao.PaymentDao;
import com.myproject.shoesstore.model.Payment;
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
public class PaymentDaoImpl implements PaymentDao {

    private Connection conn = ConnectSQL.getConnection();
    private PreparedStatement ps = null;

    @Override
    public List<Payment> getList() {
        String sql = "SELECT * FROM tbl_payment";
        List<Payment> list = new ArrayList<>();

        try {

            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();

                payment.setPaymentCode(rs.getString("payment_code"));
                payment.setInvoiceCode(rs.getString("invoice_code"));
                payment.setDatePay(rs.getString("date_pay"));
                payment.setTotal(rs.getDouble("total"));
                payment.setCustomerPay(rs.getDouble("customer_pay"));
                payment.setRefund(rs.getDouble("refund"));

                list.add(payment);
            }

            return list;
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public int create(Payment payment) {
        String sql = "INSERT INTO tbl_payment(payment_code, invoice_code, date_pay, total, customer_pay, refund) VALUES (?,?,?,?,?,?)";

        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, payment.getPaymentCode());
            ps.setString(2, payment.getInvoiceCode());
            ps.setString(3, payment.getDatePay());
            ps.setDouble(4, payment.getTotal());
            ps.setDouble(5, payment.getCustomerPay());
            ps.setDouble(6, payment.getRefund());

            int rows = ps.executeUpdate();

            return rows;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return 0;
    }

    @Override
    public int delete(String paymentCode) {
        String sql = "DELETE FROM tbl_payment WHERE payment_code = ? ";

        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, paymentCode);

            ps.execute();

            int rows = ps.executeUpdate();

            return rows;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public double getTotalRenueve() {
        String sql = "SELECT SUM(total) as total_renueve FROM tbl_payment";
        double total  = 0;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total_renueve");
            }

            return total;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

}
