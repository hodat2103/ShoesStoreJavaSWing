package com.myproject.shoesstore.service;

import com.myproject.shoesstore.model.Payment;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public interface PaymentService {
    public List<Payment> getList();
    
    public int create(Payment payment);
    
    public int delete(String paymentCode);
    
    public double getTotalRenueve();
}
