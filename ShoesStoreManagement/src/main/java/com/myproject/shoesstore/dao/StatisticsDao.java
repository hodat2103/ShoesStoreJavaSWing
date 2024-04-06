package com.myproject.shoesstore.dao;

import com.myproject.shoesstore.bean.BeanProduct;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public interface StatisticsDao {
    public List<BeanProduct> getListByProduct();
}
