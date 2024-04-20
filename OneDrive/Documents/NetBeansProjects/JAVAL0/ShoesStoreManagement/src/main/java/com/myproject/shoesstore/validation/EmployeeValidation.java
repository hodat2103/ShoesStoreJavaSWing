package com.myproject.shoesstore.validation;

import com.myproject.shoesstore.model.Employee;
import com.myproject.shoesstore.service.EmployeeService;

/**
 *
 * @author Tadaboh;
 */
public class EmployeeValidation {
    public static boolean validEmployee(Employee employee) {
        if (ValidationUtil.checkNullOrEmpty(employee.getEmployeeCode())) {
            return false;
        }

        if (ValidationUtil.validName(employee.getName())) {
            return false;
        }

        if (ValidationUtil.checkNullOrEmpty(employee.getAddress())) {
            return false;
        }

        if (ValidationUtil.validPhone(employee.getPhone())) {
            return false;
        }

        if (ValidationUtil.checkNullOrEmpty(employee.getAddress())) {
            return false;
        }

        return true;
    }

    public static boolean validCreateEmployeeCode(String code, EmployeeService employeeService) {
        
        if (employeeService.getCode(code) == null) {
            return false;

        }
        
        return true;

    }
}
