package com.myproject.shoesstore.dao;

import com.myproject.shoesstore.model.Supplier;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public interface SupplierDao {
    List<Supplier> getList();
    
    public int createOrUpdate(Supplier supplier);
    
    public int delete(String supplierCode);
}
