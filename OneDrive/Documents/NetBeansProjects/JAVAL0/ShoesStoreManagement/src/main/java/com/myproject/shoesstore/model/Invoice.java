package com.myproject.shoesstore.model;

import java.util.Date;

/**
 *
 * @author Tadaboh;
 */
public class Invoice implements ExcelExportable{
    private String invoiceCode;
    private String productCode;
    private String customerCode;
    private String employeeCode;
    private Date dateBuy;
    private int quantity;
    private String discountCode;
    private double price;

    public Invoice() {
    }

    public Invoice(String invoiceCode, String productCode, String customerCode, String employeeCode, Date dateBuy, int quantity, String discountCode, double price) {
        this.invoiceCode = invoiceCode;
        this.productCode = productCode;
        this.customerCode = customerCode;
        this.employeeCode = employeeCode;
        this.dateBuy = dateBuy;
        this.quantity = quantity;
        this.discountCode = discountCode;
        this.price = price;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return invoiceCode;
    }
    
    @Override
    public Object[] toExcelRow() {
        return new Object[]{invoiceCode, customerCode,productCode, employeeCode,dateBuy,price,quantity,discountCode};
    }

    @Override
    public String[] getColumnHeaders() {
        return new String[]{"Mã hóa đơn","Mã khách hàng","Mã sản phẩm","Mã nhân viên","Ngày mua","Giá","Số lượng","Mã giảm giá"};
        
        
    }

    
}
