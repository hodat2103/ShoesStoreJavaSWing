package com.myproject.shoesstore.service;

import com.myproject.shoesstore.model.Discount;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public interface DiscountService {
     public List<Discount> getList();
    
    public int createOrUpdate(Discount discount);
    
    public int delete(String discountCode);
    public List<String> getListDiscountCode();
    public float getDiscountAmountByCode(String discountCode);
}
