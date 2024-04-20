package com.myproject.shoesstore.service;

import com.myproject.shoesstore.model.Invoice;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public interface InvoiceService {

    public List<Invoice> getList();

    public int createOrUpdate(Invoice invoice);

    public int delete(String invoiceCode);

    public Invoice getCode(String code);
    
    public int getQuantityProduct();

}
