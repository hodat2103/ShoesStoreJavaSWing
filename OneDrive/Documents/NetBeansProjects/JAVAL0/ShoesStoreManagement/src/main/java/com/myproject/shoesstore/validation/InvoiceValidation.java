package com.myproject.shoesstore.validation;

import com.myproject.shoesstore.model.Invoice;
import com.myproject.shoesstore.service.InvoiceService;

/**
 *
 * @author Tadaboh;
 */
public class InvoiceValidation {
    public static boolean validInvoice(Invoice invoice) {
        if (ValidationUtil.checkNullOrEmpty(invoice.getInvoiceCode())) {
            return false;
        }

        if (ValidationUtil.checkNullOrEmpty(invoice.getCustomerCode())) {
            return false;
        }

        if (ValidationUtil.checkNullOrEmpty(invoice.getDiscountCode())) {
            return false;
        }

        if (ValidationUtil.checkNullOrEmpty(invoice.getEmployeeCode())) {
            return false;
        }

        if (ValidationUtil.checkNullOrEmpty(invoice.getProductCode())) {
            return false;
        }
        if (ValidationUtil.validDouble(invoice.getPrice())) {
            return false;
        }
        if (ValidationUtil.validInteger(invoice.getQuantity())) {
            return false;
        }
        

        return true;
    }

    public static boolean validCreateInvoiceCode(String code, InvoiceService invoiceService) {
        
        if (invoiceService.getCode(code) == null) {
            return false;

        }
        
        return true;

    }
}
