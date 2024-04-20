package com.myproject.shoesstore.service.impl;

import com.myproject.shoesstore.dao.DaoImpl.ProductDaoImpl;
import com.myproject.shoesstore.dao.ProductDao;
import com.myproject.shoesstore.model.Product;
import com.myproject.shoesstore.service.ProductService;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public class ProductServiceImpl implements ProductService{
    private ProductDao productDao;

    public ProductServiceImpl() {
        this.productDao = new ProductDaoImpl();
    }
    
    @Override
    public List<Product> getList() {
        return productDao.getList();
    }

    @Override
    public int createOrUpdate(Product product) {
        return productDao.createOrUpdate(product);
    }

    @Override
    public int delete(String productCode) {
        return productDao.delete(productCode);
    }

    @Override
    public List<String> getListSupplierCode() {
        return productDao.getListSupplierCode();
    }

    @Override
    public double getPriceByCode(String productCode) {
        return productDao.getPriceByCode(productCode);
    }

    @Override
    public List<String> getListProductCode() {
        return productDao.getListProductCode();
    }

    @Override
    public String getProductNameByCode(String productCode) {
        return productDao.getProductNameByCode(productCode);
    }

    @Override
    public Product getCode(String code) {
        return productDao.getCode(code);
    }

    @Override
    public int updateQuantityProduct(String productCode, int quantity) {
        return productDao.updateQuantityProduct(productCode, quantity);
    }
    
}
