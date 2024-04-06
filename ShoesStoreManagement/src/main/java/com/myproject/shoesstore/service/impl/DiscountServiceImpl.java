package com.myproject.shoesstore.service.impl;

import com.myproject.shoesstore.dao.DaoImpl.DiscountDaoImpl;
import com.myproject.shoesstore.dao.DiscountDao;
import com.myproject.shoesstore.model.Discount;
import com.myproject.shoesstore.service.DiscountService;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public class DiscountServiceImpl implements DiscountService{

    private DiscountDao discountDao = null;

    public DiscountServiceImpl() {
        this.discountDao = new DiscountDaoImpl();
    }
    
    
    
    @Override
    public List<Discount> getList() {
        return discountDao.getList();
    }

    @Override
    public int createOrUpdate(Discount discount) {
        return discountDao.createOrUpdate(discount);
    }

    @Override
    public int delete(String discountCode) {
        return discountDao.delete(discountCode);
    }

    @Override
    public List<String> getListDiscountCode() {
        return discountDao.getListDiscountCode();
    }

    @Override
    public float getDiscountAmountByCode(String discountCode) {
        return discountDao.getDiscountAmountByCode(discountCode);
    }
    
}
