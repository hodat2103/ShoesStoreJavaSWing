package com.myproject.shoesstore.service;

import com.myproject.shoesstore.model.Product;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public interface ProductService {
    public List<Product> getList();
    
    public int createOrUpdate(Product product); 
    
    public int delete(String productCode);
    
    public List<String> getListSupplierCode();
    public List<String> getListProductCode();

    public double getPriceByCode(String productCode);
    public String getProductNameByCode(String productCode);
    
    
}

