package com.myproject.shoesstore.validation;

import com.myproject.shoesstore.model.Discount;
import com.myproject.shoesstore.service.DiscountService;

/**
 *
 * @author Tadaboh;
 */
public class DiscountValidation {
    public static boolean validDiscount(Discount discount) {
        if (ValidationUtil.checkNullOrEmpty(discount.getDiscountCode())) {
            return false;
        }

        if (ValidationUtil.validName(discount.getTitle())) {
            return false;
        }

        if (ValidationUtil.checkNullOrEmpty(discount.getDescript())) {
            return false;
        }

        if (ValidationUtil.validDouble(discount.getDiscountAmount())) {
            return false;
        }

        if (ValidationUtil.checkNullOrEmpty(discount.getDescript())) {
            return false;
        }

        return true;
    }

    public static boolean validCreateDiscountCode(String code, DiscountService discountService ) {
        
        if (discountService.getCode(code) == null) {
            return false;

        }
        
        return true;

    }
}
