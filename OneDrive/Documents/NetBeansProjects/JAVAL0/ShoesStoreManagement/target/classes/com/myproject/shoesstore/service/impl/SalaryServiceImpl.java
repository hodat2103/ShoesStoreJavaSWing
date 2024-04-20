package com.myproject.shoesstore.service.impl;

import com.myproject.shoesstore.dao.DaoImpl.SalaryDaoImpl;
import com.myproject.shoesstore.dao.SalaryDao;
import com.myproject.shoesstore.model.Salary;
import com.myproject.shoesstore.service.SalaryService;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public class SalaryServiceImpl implements SalaryService{
    private SalaryDao salaryDao = null;

    public SalaryServiceImpl() {
        this.salaryDao = (SalaryDao) new SalaryDaoImpl();
    }
    
    @Override
    public List<Salary> getList() {
        return salaryDao.getList();
    }

    @Override
    public int createOrUpdate(Salary salary) {
        return salaryDao.createOrUpdate(salary);
    }

    @Override
    public int delete(String salaryCode) {
        return salaryDao.delete(salaryCode);
    }

    @Override
    public List<String> getListEmployeeCode() {
        return salaryDao.getListEmployeeCode();
    }

    @Override
    public Salary getCode(String code) {
        return salaryDao.getCode(code);
    }
    
}
