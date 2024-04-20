package com.myproject.shoesstore.dao;

import com.myproject.shoesstore.model.Payment;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public interface PaymentDao {
    public List<Payment> getList();
    
    public int create(Payment payment);
    
    public int delete(String paymentCode);
    
    public double getTotalRenueve();
}
