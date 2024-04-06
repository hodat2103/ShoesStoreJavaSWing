package com.myproject.shoesstore.dao;

import com.myproject.shoesstore.model.Salary;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public interface SalaryDao {
    public List<Salary> getList();
    
    public int createOrUpdate(Salary salary);
    
    public int delete(String salaryCode);
    
    public List<String> getListEmployeeCode();
}
