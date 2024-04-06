package com.myproject.shoesstore.service.impl;

import com.myproject.shoesstore.dao.DaoImpl.InvoiceDaoImpl;
import com.myproject.shoesstore.dao.InvoiceDao;
import com.myproject.shoesstore.model.Invoice;
import com.myproject.shoesstore.service.InvoiceService;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public class InvoiceServiceImpl implements InvoiceService{
    private InvoiceDao invoiceDao = null;

    public InvoiceServiceImpl() {
        this.invoiceDao = new InvoiceDaoImpl();
    }
    
    
    @Override
    public List<Invoice> getList() {
        return invoiceDao.getList();
    }

    @Override
    public int createOrUpdate(Invoice invoice) {
        return invoiceDao.createOrUpdate(invoice);
    }

    @Override
    public int delete(String invoiceCode) {
        return invoiceDao.delete(invoiceCode);
    }
    
}
