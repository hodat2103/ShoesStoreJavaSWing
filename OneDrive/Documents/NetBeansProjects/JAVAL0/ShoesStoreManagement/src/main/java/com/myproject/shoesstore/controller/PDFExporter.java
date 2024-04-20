package com.myproject.shoesstore.controller;


import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.myproject.shoesstore.model.InvoiceDetail;
import com.myproject.shoesstore.model.Payment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Tadaboh;
 */
public class PDFExporter {
        public static final String FONT_PATH = "D:/Files/arial.ttf";

    public static void exportToPDF(Payment payment,InvoiceDetail invoiceDetail, String filePath) {
        try {
            // create document PDF
            PdfWriter writer = new PdfWriter(new FileOutputStream(new File(filePath)));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            document.setLeftMargin(200);

            // create font for title
            PdfFont font = PdfFontFactory.createFont(FONT_PATH, PdfEncodings.IDENTITY_H, pdf);

            Paragraph title = new Paragraph("THÔNG TIN THANH TOÁN").setFontSize(18).setBold();
            document.add(title);

            document.setFont(font);

            // add data to documment
            document.add(new Paragraph("Mã hóa đơn     : " + payment.getInvoiceCode()));
            document.add(new Paragraph("Mã khách hàng  : " + invoiceDetail.getCustomerCode()));
            document.add(new Paragraph("Tên khách hàng : " + invoiceDetail.getCutomerName()));
            document.add(new Paragraph("Tên sản phẩm   : " + invoiceDetail.getProductName()));
            document.add(new Paragraph("NV thanh toán  : " + invoiceDetail.getEmployeeName()));
            document.add(new Paragraph("Giá            : " + invoiceDetail.getPrice()+ " VNĐ"));
            document.add(new Paragraph("Số lượng       : " + invoiceDetail.getQuantity()));
            document.add(new Paragraph("Mã giảm giá    : " + invoiceDetail.getDiscountCode()));
            document.add(new Paragraph("Ngày thanh toán: " + payment.getDatePay()));
            
            document.add(new Paragraph("----------------------------------"));
            
            document.add(new Paragraph("Tổng thanh toán: " + payment.getTotal() + " VNĐ").setBold());
            document.add(new Paragraph("Tiền khách đưa : " + payment.getCustomerPay()+ " VNĐ"));
            document.add(new Paragraph("Tiền trả khách : " + payment.getRefund() + " VNĐ").setBold());

            document.close();
            JOptionPane.showMessageDialog(null, "Xuất PDF thành công! Mở đường dẫn " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
