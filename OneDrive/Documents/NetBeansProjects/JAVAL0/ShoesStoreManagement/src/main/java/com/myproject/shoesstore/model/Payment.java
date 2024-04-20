package com.myproject.shoesstore.model;

import java.util.Date;

/**
 *
 * @author Tadaboh;
 */
public class Payment implements ExcelExportable{
    private String paymentCode;
    private String invoiceCode;
    private String datePay;
    private double total;
    private double customerPay;
    private double refund;

    public Payment() {
    }

    public Payment(String paymentCode, String invoiceCode, String datePay, double total, double customerPay, double refund) {
        this.paymentCode = paymentCode;
        this.invoiceCode = invoiceCode;
        this.datePay = datePay;
        this.total = total;
        this.customerPay = customerPay;
        this.refund = refund;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getDatePay() {
        return datePay;
    }

    public void setDatePay(String datePay) {
        this.datePay = datePay;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getCustomerPay() {
        return customerPay;
    }

    public void setCustomerPay(double customerPay) {
        this.customerPay = customerPay;
    }

    public double getRefund() {
        return refund;
    }

    public void setRefund(double refund) {
        this.refund = refund;
    }
    
    @Override
    public Object[] toExcelRow() {
        return new Object[]{paymentCode, invoiceCode,datePay, total,customerPay,refund};
    }

    @Override
    public String[] getColumnHeaders() {
        return new String[]{"Mã thanh toán","Mã hóa đơn","Ngày TT","Tiền TT","Tiền khách đưa","Tiền trả lại"};
        
        
    }
}
