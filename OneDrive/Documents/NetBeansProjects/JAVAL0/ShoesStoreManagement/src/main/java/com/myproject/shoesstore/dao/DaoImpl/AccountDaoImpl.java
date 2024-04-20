package com.myproject.shoesstore.dao.DaoImpl;

import com.myproject.shoesstore.connection.ConnectSQL;
import com.myproject.shoesstore.dao.AccountDao;
import com.myproject.shoesstore.model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Tadaboh;
 */
public class AccountDaoImpl implements AccountDao {

    @Override
    public Account login(String username, String password) {
        Connection conn = ConnectSQL.getConnection();
        String sql = "SELECT * FROM tbl_account WHERE username LIKE ? AND password LIKE ?";
        Account account = null;
        try {
            PreparedStatement ps =  conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                account = new Account();
                account.setAccountCode(rs.getInt("account_code"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
            }
            ps.close();
            conn.close();
            return account;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
