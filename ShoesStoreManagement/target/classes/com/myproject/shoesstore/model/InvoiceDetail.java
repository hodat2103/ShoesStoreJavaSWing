package com.myproject.shoesstore.model;

import java.util.Date;

/**
 *
 * @author Tadaboh;
 */
public class InvoiceDetail {
    private String invoiceCode;
    private String customerCode;
    private String cutomerName;
    private String productName;
    private String employeeName;
    private double price;
    private int quantity;
    private Date dateBuy;
    private String discountCode;
    private double total;

    public InvoiceDetail() {
    }

    public InvoiceDetail(String invoiceCode, String customerCode, String cutomerName, String productName, String employeeName, double price, int quantity, Date dateBuy, String discountCode, double total) {
        this.invoiceCode = invoiceCode;
        this.customerCode = customerCode;
        this.cutomerName = cutomerName;
        this.productName = productName;
        this.employeeName = employeeName;
        this.price = price;
        this.quantity = quantity;
        this.dateBuy = dateBuy;
        this.discountCode = discountCode;
        this.total = total;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCutomerName() {
        return cutomerName;
    }

    public void setCutomerName(String cutomerName) {
        this.cutomerName = cutomerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return invoiceCode+ " - "+customerCode + " - "+cutomerName+ " - "+productName+ " - "+employeeName+ " - "+discountCode ;
    }
    
}
