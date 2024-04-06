package com.myproject.shoesstore.model;

import java.util.Date;

/**
 *
 * @author Tadaboh;
 */
public class Employee implements ExcelExportable{
    private String employeeCode;
    private String name;
    private Date date;
    private boolean gender;
    private String position;
    private String phone;
    private String address;

    public Employee() {
    }

    public Employee(String employeeCode, String name, Date date, boolean gender, String position, String phone, String address) {
        this.employeeCode = employeeCode;
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.position = position;
        this.phone = phone;
        this.address = address;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
    @Override
    public String toString() {
        return "Employee COde: " + employeeCode + " - Name: " + name;
    }
    @Override
    public Object[] toExcelRow() {
        return new Object[]{employeeCode, name,date, gender,position,phone, address};
    }

    @Override
    public String[] getColumnHeaders() {
        return new String[]{"Mã nhân viên","Tên nhân viên","Ngày sinh","Giới tính","Vị trí","SĐT","Địa chỉ"};
        
        
    }
}
