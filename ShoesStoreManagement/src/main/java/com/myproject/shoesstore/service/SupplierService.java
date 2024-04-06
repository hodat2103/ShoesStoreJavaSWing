package com.myproject.shoesstore.service;

import com.myproject.shoesstore.model.Supplier;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public interface SupplierService {
    public List<Supplier> getList();
    
    public int createOrUpdate(Supplier supplier); 
    
    public int delete(String supplierCode);
}
