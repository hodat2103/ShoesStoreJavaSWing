package com.myproject.shoesstore.validation;

import com.myproject.shoesstore.model.Supplier;
import com.myproject.shoesstore.service.SupplierService;

/**
 *
 * @author Tadaboh;
 */
public class SupplierValidation {
    public static boolean validSupplier(Supplier supplier) {
        if (ValidationUtil.checkNullOrEmpty(supplier.getSupplierCode())) {
            return false;
        }

        if (ValidationUtil.validName(supplier.getSupplierName())) {
            return false;
        }
        if (ValidationUtil.validName(supplier.getTradeName())) {
            return false;
        }

        if (ValidationUtil.validPhone(supplier.getPhone())) {
            return false;
        }

        if (ValidationUtil.validEmail(supplier.getEmail())) {
            return false;
        }

        if (ValidationUtil.checkNullOrEmpty(supplier.getAddress())) {
            return false;
        }

        return true;
    }

    public static boolean validCreateSupplierCode(String code, SupplierService supplierService) {
        
        if (supplierService.getCode(code) == null) {
            return false;

        }
        
        return true;

    }
}
