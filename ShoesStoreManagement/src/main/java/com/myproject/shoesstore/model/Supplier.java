package com.myproject.shoesstore.model;

import java.util.Date;

/**
 *
 * @author Tadaboh;
 */
public class Supplier implements ExcelExportable{
    private String supplierCode;
    private String supplierName;
    private String tradeName;
    private String address;
    private String phone;
    private String email;

    public Supplier() {
    }

    public Supplier( String supplierCode, String supplierName, String tradeName, String address, String phone, String email) {
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
        this.tradeName = tradeName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
    

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Supplier code: " + supplierCode + " - Supplier name"  + supplierName;  
    }
    
    @Override
    public Object[] toExcelRow() {
        return new Object[]{supplierCode, supplierName,tradeName, address,phone,email};
    }

    @Override
    public String[] getColumnHeaders() {
        return new String[]{"Mã nhà cung cấp","Tên nhà cung cấp","Tên giao dịch","Địa chỉ","SĐT","Email"};
        
        
    }
}
