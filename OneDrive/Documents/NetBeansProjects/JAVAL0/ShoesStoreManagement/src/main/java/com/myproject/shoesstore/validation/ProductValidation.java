package com.myproject.shoesstore.validation;

import com.myproject.shoesstore.model.Product;
import com.myproject.shoesstore.service.ProductService;

/**
 *
 * @author Tadaboh;
 */
public class ProductValidation {
    public static boolean validProduct(Product product) {
        if (ValidationUtil.checkNullOrEmpty(product.getProductCode())) {
            return false;
        }

        if (ValidationUtil.validName(product.getProductName())) {
            return false;
        }

        if (ValidationUtil.checkNullOrEmpty(product.getDescript())) {
            return false;
        }

        if (ValidationUtil.checkNullOrEmpty(product.getSupplierCode())) {
            return false;
        }

        
        if (ValidationUtil.validDouble(product.getPrice())) {
            return false;
        }
        if (ValidationUtil.validInteger(product.getQuantity())) {
            return false;
        }
        

        return true;
    }

    public static boolean validCreateProductCode(String code, ProductService productService) {
        
        if (productService.getCode(code) == null) {
            return false;

        }
        
        return true;

    }
}
