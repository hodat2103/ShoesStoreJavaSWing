package com.myproject.shoesstore.service.impl;

import com.myproject.shoesstore.dao.DaoImpl.EmployeeDaoImpl;
import com.myproject.shoesstore.dao.EmployeeDao;
import com.myproject.shoesstore.model.Employee;
import com.myproject.shoesstore.service.EmployeeService;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public class EmployeeServiceImpl implements EmployeeService{
    EmployeeDao employeeDao = null;

    public EmployeeServiceImpl() {
        this.employeeDao = new EmployeeDaoImpl();
    }
    
    @Override
    public List<Employee> getList() {
        return employeeDao.getList();
    }

    @Override
    public int createOrUpdate(Employee employee) {
        return employeeDao.createOrUpdate(employee);
    }

    @Override
    public int delete(String employeeCode) {
        return employeeDao.delete(employeeCode);
    }

    @Override
    public String getEmployeeNameByCode(String employeeCode) {
        return employeeDao.getEmployeeNameByCode(employeeCode);
    }

    @Override
    public List<String> getListEmployeeCode() {
        return employeeDao.getListEmployeeCode();
    }
    
}
