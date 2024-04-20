package com.myproject.shoesstore.dao.DaoImpl;

import com.myproject.shoesstore.connection.ConnectSQL;
import com.myproject.shoesstore.dao.DiscountDao;
import com.myproject.shoesstore.model.Discount;
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
public class DiscountDaoImpl implements DiscountDao {

    private final Connection conn = ConnectSQL.getConnection();
    private PreparedStatement ps = null;

    @Override
    public List<Discount> getList() {
        String sql = "SELECT * FROM tbl_discount";
        List<Discount> list = new ArrayList<>();

        try {
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Discount discount = new Discount();

                    discount.setDiscountCode(rs.getString("discount_code"));
                    discount.setTitle(rs.getString("title"));
                    discount.setDateStart(rs.getDate("date_start"));
                    discount.setDateEnd(rs.getDate("date_end"));
                    discount.setDiscountAmount(rs.getFloat("discount_amount"));
                    discount.setDescript(rs.getString("descript"));

                    list.add(discount);
                }

            }

            return list;
        } catch (SQLException ex) {
        }
        return null;
    }
//    public static void main(String[] args) {
//        DiscountDao dao = new DiscountDaoImpl();
//        System.out.println(dao.getList());
//    }

    @Override
    public int createOrUpdate(Discount discount) {
        String sql = "INSERT INTO tbl_discount(discount_code, title, date_start, date_end, discount_amount, descript) VALUES (?,?,?,?,?,?) "
                + "ON DUPLICATE KEY UPDATE title = VALUES(title), date_start = VALUES(date_start), date_end = VALUES(date_end), discount_amount = VALUES(discount_amount), descript = VALUES(descript), discount_code = ?;";

        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, discount.getDiscountCode());
            ps.setString(2, discount.getTitle());
            ps.setDate(3, new Date(discount.getDateStart().getTime()));
            ps.setDate(4, new Date(discount.getDateEnd().getTime()));
            ps.setFloat(5, discount.getDiscountAmount());
            ps.setString(6, discount.getDescript());
            ps.setString(7, discount.getDiscountCode());

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
    public int delete(String discountCode) {
        String sql = "DELETE FROM tbl_discount WHERE discount_code = ? ";

        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, discountCode);

            ps.execute();

            int rows = ps.executeUpdate();

            return rows;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<String> getListDiscountCode() {
        String sql = "SELECT discount_code FROM tbl_discount";
        List<String> list = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String discountCode = rs.getString("discount_code");

                list.add(discountCode);
            }

            return list;
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public float getDiscountAmountByCode(String discountCode) {
        String sql;
        sql = "SELECT discount_amount FROM tbl_discount WHERE discount_code = ?";
        float discountAmount = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, discountCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                discountAmount = Float.parseFloat(rs.getString("discount_amount"));
            }

            return discountAmount;
        } catch (SQLException ex) {
        }
        return 0;
    }

    @Override
    public Discount getCode(String code) {
        String sql = "SELECT discount_code FROM tbl_discount WHERE discount_code = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, code);
            Discount discount = new Discount();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                discount.setDiscountCode(rs.getString("discount_code"));
            }

            return discount;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Discount getDate(String code) {
        String sql = "SELECT date_start, date_end FROM tbl_discount WHERE discount_code = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, code);
            Discount discount = new Discount();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                discount.setDateStart(rs.getDate("date_start"));
                discount.setDateEnd(rs.getDate("date_end"));
            }

            return discount;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
