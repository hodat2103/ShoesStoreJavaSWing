package com.myproject.shoesstore.dao.DaoImpl;

import com.myproject.shoesstore.bean.BeanProduct;
import com.myproject.shoesstore.connection.ConnectSQL;
import com.myproject.shoesstore.dao.StatisticsDao;
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
public class StatisticsDaoImpl implements StatisticsDao{
    private Connection conn = ConnectSQL.getConnection();
    private PreparedStatement ps = null;
    @Override
    public List<BeanProduct> getListByProduct() {
        String sql = "SELECT date_buy, COUNT(*) as quantity FROM tbl_invoice GROUP BY date_buy";
        try {
            List<BeanProduct> list = new ArrayList<>();
            ps = conn.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                BeanProduct beanProduct = new BeanProduct();
                beanProduct.setQuantityProduct(rs.getInt("quantity"));
                beanProduct.setDateSell(rs.getString("date_buy"));
                list.add(beanProduct);
                     
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(StatisticsDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
              
    }
    
}
