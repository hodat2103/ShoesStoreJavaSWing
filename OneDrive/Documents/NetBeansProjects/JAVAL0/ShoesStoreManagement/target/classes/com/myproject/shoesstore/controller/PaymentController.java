package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.model.InvoiceDetail;
import com.myproject.shoesstore.model.Payment;
import com.myproject.shoesstore.service.PaymentService;
import com.myproject.shoesstore.service.impl.PaymentServiceImpl;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author Tadaboh;
 */
public class PaymentController {

    private final JButton btnExportPDF;
    private final JTextField jtfCustomerPay;
    private final JLabel jlbRefund;
    Payment payment = null;
    PaymentService paymentService = null;

    private String formattedDateTime = "";

    public PaymentController(JButton btnExportPDF,JTextField jtfCustomerPay, JLabel jlbRefund) {
        this.btnExportPDF = btnExportPDF;

        this.jtfCustomerPay = jtfCustomerPay;
        this.jlbRefund = jlbRefund;
        this.payment = new Payment();
        this.paymentService = new PaymentServiceImpl();
    }

    public void setView()   {

        

        
    }


    public String setNowDateTime() {

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        formattedDateTime = dateFormat.format(now);

        return formattedDateTime;
    }
    
    public void setEvent(InvoiceDetail invoiceDetail) {
        btnExportPDF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                

                if (checkNotNull()) {
                    payment.setPaymentCode(setPaymentCode());
                    payment.setInvoiceCode(invoiceDetail.getInvoiceCode());
                    
                    payment.setDatePay(setNowDateTime());
                    payment.setTotal(invoiceDetail.getTotal());
                    double customerPay = Double.parseDouble(jtfCustomerPay.getText());
                    double refund = Double.parseDouble( jlbRefund.getText());

                    payment.setCustomerPay(customerPay);
                    payment.setRefund(refund);

                    int rows = paymentService.create(payment);
                    if (rows > 0) {
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi thanh toán !!");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ dữ liệu  ");
                }

                
                
                PDFExporter.exportToPDF(payment, invoiceDetail, "D:/Files/InvoicePDF"+setPaymentCode());
                
                
            }

        });
    }

    private String setPaymentCode() {
        StringBuilder paymentCode = new StringBuilder();
        Random random = new Random();

        paymentCode.append("TTSS");
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(10);
            paymentCode.append(randomNumber);
        }
        return paymentCode.toString();
    }

    private boolean checkNotNull() {
        return jtfCustomerPay.getText() != null && !jtfCustomerPay.getText().equalsIgnoreCase("");
    }
}
