package com.myproject.shoesstore.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Tadaboh;
 */
public class Customer implements ExcelExportable{
    private String customerCode;
    private String name;
    private Date date;
    private boolean gender;
    private String phone;
    private String address;

    public Customer() {
    }

    public Customer(String customerCode, String name, Date date, boolean gender, String phone, String address) {
        this.customerCode = customerCode;
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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
        return customerCode + " - " + name;
    }

    @Override
    public Object[] toExcelRow() {
        return new Object[]{customerCode, name,date, gender,phone,address};
    }

    @Override
    public String[] getColumnHeaders() {
        return new String[]{"Mã khách hàng","Tên khách hàng","Ngày sinh","Giới tính","SĐT","Địa chỉ"};
        
        
    }
    

    
}

