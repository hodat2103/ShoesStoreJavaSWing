package com.myproject.shoesstore.validation;

import com.myproject.shoesstore.model.Customer;
import com.myproject.shoesstore.service.CustomerService;
import javax.swing.JOptionPane;

/**
 *
 * @author Tadaboh;
 */
public class CustomerValidation {

    public static boolean validCustomer(Customer customer) {
        if (ValidationUtil.checkNullOrEmpty(customer.getCustomerCode())) {
            return false;
        }

        if (ValidationUtil.validName(customer.getName())) {
            return false;
        }

        if (ValidationUtil.checkNullOrEmpty(customer.getAddress())) {
            return false;
        }

        if (ValidationUtil.validPhone(customer.getPhone())) {
            return false;
        }

        if (ValidationUtil.checkNullOrEmpty(customer.getAddress())) {
            return false;
        }

        return true;
    }

    public static boolean validCreateEmployeeCode(String code, CustomerService customerService) {
        
        if (customerService.getCustomerCode(code) == null) {
            return false;

        }
        
        return true;

    }
}
