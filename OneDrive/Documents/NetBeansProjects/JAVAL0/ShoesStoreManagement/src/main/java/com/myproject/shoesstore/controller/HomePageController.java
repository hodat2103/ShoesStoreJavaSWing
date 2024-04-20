package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.service.CustomerService;
import com.myproject.shoesstore.service.InvoiceService;
import com.myproject.shoesstore.service.PaymentService;
import com.myproject.shoesstore.service.impl.CustomerServiceImpl;
import com.myproject.shoesstore.service.impl.InvoiceServiceImpl;
import com.myproject.shoesstore.service.impl.PaymentServiceImpl;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.swing.JLabel;

/**
 *
 * @author Tadaboh;
 */
public class HomePageController {
    private final JLabel jlbTotalRenueve;
    private final JLabel jlbTotalProduct;
    private final JLabel jlbTotalCustomer;
    
    CustomerService customerService = null;
    InvoiceService invoiceService = null;
    PaymentService paymentService = null;

    public HomePageController(JLabel jlbTotalRenueve, JLabel jlbTotalProduct, JLabel jlbTotalCustomer) {
        this.jlbTotalRenueve = jlbTotalRenueve;
        this.jlbTotalProduct = jlbTotalProduct;
        this.jlbTotalCustomer = jlbTotalCustomer;
        
        this.customerService = new CustomerServiceImpl();
        this.invoiceService = new InvoiceServiceImpl();
        this.paymentService = new PaymentServiceImpl();
    }
    
    public void setView(){
        int totalCustomer = customerService.getQuantityCustomer();
        jlbTotalCustomer.setText(String.valueOf(totalCustomer));
        
        int totalProduct = invoiceService.getQuantityProduct();
        jlbTotalProduct.setText(String.valueOf(totalProduct));
        
        double totalRenueve = paymentService.getTotalRenueve();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        
        jlbTotalRenueve.setText(String.valueOf(decimalFormat.format(totalRenueve)));
    }
}
