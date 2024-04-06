package com.myproject.shoesstore.dao;

import com.myproject.shoesstore.model.Employee;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public interface EmployeeDao {
    List<Employee> getList();
    
    public int createOrUpdate(Employee employee);
    
    public int delete(String employeeCode);
    
    public String getEmployeeNameByCode(String employeeCode);
    public List<String> getListEmployeeCode();
}
