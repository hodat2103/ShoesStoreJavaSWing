package com.myproject.shoesstore.dao;

import com.myproject.shoesstore.model.Customer;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public interface CustomerDao {
    
    public List<Customer> getList();
    
    public int createOrUpdate(Customer customer);
    
    public int delete(String customerCode);

    public String getCustomerNameByCode(String customerCode);
    
    public List<String> getListCustomerCode();
}
