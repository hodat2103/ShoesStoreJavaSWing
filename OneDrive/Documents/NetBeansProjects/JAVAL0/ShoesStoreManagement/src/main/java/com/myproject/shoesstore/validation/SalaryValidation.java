package com.myproject.shoesstore.validation;

import com.myproject.shoesstore.model.Salary;
import com.myproject.shoesstore.service.SalaryService;

/**
 *
 * @author Tadaboh;
 */
public class SalaryValidation {
    public static boolean validSalary(Salary salary) {
        if (ValidationUtil.checkNullOrEmpty(salary.getSalaryCode())) {
            return false;
        }

        if (ValidationUtil.checkNullOrEmpty(salary.getEmplpoyeeCode())) {
            return false;
        }

        if (ValidationUtil.validName(salary.getEmployeeName())) {
            return false;
        }

        if (ValidationUtil.checkNullOrEmpty(salary.getDescript())) {
            return false;
        }

        
        if (ValidationUtil.validDouble(salary.getBasicSalary())) {
            return false;
        }
        
        if (ValidationUtil.validDouble(salary.getBonuses())) {
            return false;
        }
        
        if (ValidationUtil.validDouble(salary.getForfeit())) {
            return false;
        }
        
        if (ValidationUtil.validDouble(salary.getNetSalary())) {
            return false;
        }
        
        if (ValidationUtil.validDouble(salary.getSubsidy())) {
            return false;
        }
        
        

        return true;
    }

    public static boolean validCreateSalaryCode(String code, SalaryService salaryService) {
        
        if (salaryService.getCode(code) == null) {
            return false;

        }
        
        return true;

    }
}
