package com.myproject.shoesstore.dao;

import com.myproject.shoesstore.model.Discount;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public interface DiscountDao {
     public List<Discount> getList();
    
    public int createOrUpdate(Discount discount);
    
    public int delete(String discountCode);
    
    public List<String> getListDiscountCode();
    
    public float getDiscountAmountByCode(String discountCode);
}
