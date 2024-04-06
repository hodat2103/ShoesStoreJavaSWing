package com.myproject.shoesstore.model;

import java.util.Date;

/**
 *
 * @author Tadaboh;
 */
public class Salary implements ExcelExportable{
    private String salaryCode;
    private String emplpoyeeCode;
    private String employeeName;
    private Date effectiveDate;
    private double basicSalary;
    private double subsidy;
    private double bonuses;
    private double forfeit;
    private double netSalary;
    private String descript;

    public Salary() {
    }

    public Salary(String salaryCode, String emplpoyeeCode, String employeeName, Date effectiveDate, double basicSalary, double subsidy, double bonuses, double forfeit, double netSalary,String descript) {
        this.salaryCode = salaryCode;
        this.emplpoyeeCode = emplpoyeeCode;
        this.employeeName = employeeName;
        this.effectiveDate = effectiveDate;
        this.basicSalary = basicSalary;
        this.subsidy = subsidy;
        this.bonuses = bonuses;
        this.forfeit = forfeit;
        this.netSalary = netSalary;
        this.descript = descript;
    }

    public String getSalaryCode() {
        return salaryCode;
    }

    public void setSalaryCode(String salaryCode) {
        this.salaryCode = salaryCode;
    }

    public String getEmplpoyeeCode() {
        return emplpoyeeCode;
    }

    public void setEmplpoyeeCode(String emplpoyeeCode) {
        this.emplpoyeeCode = emplpoyeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(double subsidy) {
        this.subsidy = subsidy;
    }

    public double getBonuses() {
        return bonuses;
    }

    public void setBonuses(double bonuses) {
        this.bonuses = bonuses;
    }

    public double getForfeit() {
        return forfeit;
    }

    public void setForfeit(double forfeit) {
        this.forfeit = forfeit;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    @Override
    public String toString() {
        return salaryCode;
    }
    
    @Override
    public Object[] toExcelRow() {
        return new Object[]{salaryCode, emplpoyeeCode,employeeName, effectiveDate,basicSalary,subsidy,bonuses,forfeit,descript,netSalary,};
    }

    @Override
    public String[] getColumnHeaders() {
        return new String[]{"Mã lương","Mã nhân viên","Tên nhân viên","Ngày hiệu lực","Lương cơ bản","Phụ cấp","Thưởng","Phạt","Ghi chú","Thực lĩnh"};
        
        
    }
    
}
