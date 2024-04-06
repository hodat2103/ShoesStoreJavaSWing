package com.myproject.shoesstore.service.impl;

import com.myproject.shoesstore.dao.CustomerDao;
import com.myproject.shoesstore.dao.DaoImpl.CustomerDaoImpl;
import com.myproject.shoesstore.model.Customer;
import com.myproject.shoesstore.service.CustomerService;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public class CustomerServiceImpl implements CustomerService{
    
    private CustomerDao customerDao = null;

    public CustomerServiceImpl() {
        customerDao = new CustomerDaoImpl();
    }
    
    
    @Override
    public List<Customer> getList() {
        return customerDao.getList();
    }

    @Override
    public int createOrUpdate(Customer customer) {
        return customerDao.createOrUpdate(customer);
    }

    @Override
    public int delete(String customerCode) {
        return customerDao.delete(customerCode);
    }

    @Override
    public List<String> getListCustomerCode() {
        return customerDao.getListCustomerCode();
    }

    @Override
    public String getCustomerNameByCode(String customerCode) {
        return customerDao.getCustomerNameByCode(customerCode);
    }

    
    
}
