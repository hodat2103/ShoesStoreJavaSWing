package com.myproject.shoesstore.service.impl;

import com.myproject.shoesstore.dao.DaoImpl.PaymentDaoImpl;
import com.myproject.shoesstore.dao.PaymentDao;
import com.myproject.shoesstore.model.Payment;
import com.myproject.shoesstore.service.PaymentService;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public class PaymentServiceImpl implements PaymentService{
    PaymentDao paymentDao = null;

    public PaymentServiceImpl() {
        this.paymentDao = new PaymentDaoImpl();
    }
    
    @Override
    public List<Payment> getList() {
        return paymentDao.getList();
    }

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public int delete(String paymentCode) {
        return paymentDao.delete(paymentCode);
    }

    @Override
    public double getTotalRenueve() {
        return paymentDao.getTotalRenueve();
    }
    
}
