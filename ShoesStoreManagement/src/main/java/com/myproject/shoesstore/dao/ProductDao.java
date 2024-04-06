package com.myproject.shoesstore.dao;

import com.myproject.shoesstore.model.Product;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public interface ProductDao {
    public List<Product> getList();
    
    public int createOrUpdate(Product product);
    
    public int delete(String productCode);
    
    public List<String> getListSupplierCode();
    
    public List<String> getListProductCode();
    
    public String getProductNameByCode(String productCode);
    
    public double getPriceByCode(String productCode);
}
