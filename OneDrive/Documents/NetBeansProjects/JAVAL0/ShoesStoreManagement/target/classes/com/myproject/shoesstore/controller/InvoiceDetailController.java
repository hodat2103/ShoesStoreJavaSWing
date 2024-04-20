package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.model.InvoiceDetail;
import com.myproject.shoesstore.view.JFrame.PaymentJFrame;
import com.myproject.shoesstore.view.PaymentJPanel;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Tadaboh;
 */
public class InvoiceDetailController {
    private final JButton btnPay;
    private final JLabel jlbInvoiceCode; 
    private final JLabel jlbCustomerCode; 
    private final JLabel jlbCustomerName; 
    private final JLabel jlbProductName; 
    private final JLabel jlbEmployeeName; 
    private final JLabel jlbPrice; 
    private final JLabel jlbQuantity; 
    private final JLabel jlbDateBuy; 
    private final JLabel jlbDiscountCode; 
    private final JLabel jlbTotal; 

    InvoiceDetail invoiceDetail = null;
    
    public InvoiceDetailController(JButton btnPay, JLabel jlbInvoiceCode, JLabel jlbCustomerCode, JLabel jlbCustomerName, JLabel jlbProductName, JLabel jlbEmployeeName, JLabel jlbprice, JLabel jlbquantity, JLabel jlbDateBuy, JLabel jlbDiscountCode, JLabel jlbTotal) {
        this.btnPay = btnPay;
        this.jlbInvoiceCode = jlbInvoiceCode;
        this.jlbCustomerCode = jlbCustomerCode;
        this.jlbCustomerName = jlbCustomerName;
        this.jlbProductName = jlbProductName;
        this.jlbEmployeeName = jlbEmployeeName;
        this.jlbPrice = jlbprice;
        this.jlbQuantity = jlbquantity;
        this.jlbDateBuy = jlbDateBuy;
        this.jlbDiscountCode = jlbDiscountCode;
        this.jlbTotal = jlbTotal;
    }
    
    public void setView(InvoiceDetail invoiceDetail){
        this.invoiceDetail = invoiceDetail;
        
        jlbInvoiceCode.setText(invoiceDetail.getInvoiceCode());
        jlbCustomerCode.setText(invoiceDetail.getCustomerCode());
        jlbCustomerName.setText(invoiceDetail.getCutomerName());
        jlbProductName.setText(invoiceDetail.getProductName());
        jlbEmployeeName.setText(invoiceDetail.getEmployeeName());
        jlbPrice.setText(String.valueOf(invoiceDetail.getPrice()));
        jlbQuantity.setText(String.valueOf(invoiceDetail.getQuantity()));
        jlbDateBuy.setText( String.valueOf(invoiceDetail.getDateBuy()));
        jlbDiscountCode.setText(invoiceDetail.getDiscountCode());
        jlbTotal.setText(String.valueOf(invoiceDetail.getTotal()));
    }
    
    public void setEvent(){
        btnPay.addMouseListener(new MouseAdapter(){
            
        });
    }
}
