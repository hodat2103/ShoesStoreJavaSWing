package com.myproject.shoesstore.service.impl;

import com.myproject.shoesstore.dao.DaoImpl.SupplierDaoImpl;
import com.myproject.shoesstore.dao.SupplierDao;
import com.myproject.shoesstore.model.Supplier;
import com.myproject.shoesstore.service.SupplierService;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public class SupplierServiceImpl implements SupplierService{

    SupplierDao supplierDao = null;

    public SupplierServiceImpl() {
        supplierDao = new SupplierDaoImpl();
    }
    
    
    @Override
    public List<Supplier> getList() {
        return supplierDao.getList();
    }

    @Override
    public int createOrUpdate(Supplier supplier) {
        return supplierDao.createOrUpdate(supplier);
    }

    @Override
    public int delete(String supplierCode) {
        return supplierDao.delete(supplierCode);
    }
    
}
