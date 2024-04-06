package com.myproject.shoesstore.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tadaboh;
 */
public class ConnectSQL {

    public static Connection getConnection() {
        Connection cons = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cons = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shoes_db", "root", "210303");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cons;
    }

//    public static void main(String[] args) {
//        System.out.println(getConnection());
//    }
}
