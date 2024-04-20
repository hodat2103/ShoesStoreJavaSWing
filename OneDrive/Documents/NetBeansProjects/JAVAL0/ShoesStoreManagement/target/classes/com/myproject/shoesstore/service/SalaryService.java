package com.myproject.shoesstore.service;

import com.myproject.shoesstore.model.Salary;
import java.util.List;

/**
 *
 * @author Tadaboh;
 */
public interface SalaryService {

    public List<Salary> getList();

    public int createOrUpdate(Salary salary);

    public int delete(String salaryCode);

    public List<String> getListEmployeeCode();

    public Salary getCode(String code);

}
