package com.myproject.shoesstore.service.impl;

import com.myproject.shoesstore.bean.BeanProduct;
import com.myproject.shoesstore.dao.DaoImpl.StatisticsDaoImpl;
import com.myproject.shoesstore.dao.StatisticsDao;
import com.myproject.shoesstore.service.StatisticsService;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public class StatisticsServiceImpl implements StatisticsService{
    private StatisticsDao statisticsDao = null;

    public StatisticsServiceImpl() {
        this.statisticsDao = new StatisticsDaoImpl();
    }
    
    @Override
    public List<BeanProduct> getListByProduct() {
        return statisticsDao.getListByProduct();
    }
    
}
