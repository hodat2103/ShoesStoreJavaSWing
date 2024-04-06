package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.model.Invoice;
import com.myproject.shoesstore.model.Invoice;
import com.myproject.shoesstore.model.InvoiceDetail;
import com.myproject.shoesstore.service.CustomerService;
import com.myproject.shoesstore.service.DiscountService;
import com.myproject.shoesstore.service.EmployeeService;
import com.myproject.shoesstore.service.InvoiceService;
import com.myproject.shoesstore.service.ProductService;
import com.myproject.shoesstore.service.impl.CustomerServiceImpl;
import com.myproject.shoesstore.service.impl.DiscountServiceImpl;
import com.myproject.shoesstore.service.impl.EmployeeServiceImpl;
import com.myproject.shoesstore.service.impl.InvoiceServiceImpl;
import com.myproject.shoesstore.service.impl.ProductServiceImpl;
import com.myproject.shoesstore.view.JFrame.PaymentJFrame;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Tadaboh;
 */
public class InvoiceController {

    private final JButton btnSave;
    private final JButton btnAddCode;
    private final JTextField jtfInvoiceCode;
    private final JDateChooser jdcDateBuy;
    private final JTextField jtfPrice;
    private final JTextField jtfQuantity;
    private final JComboBox<String> jcbProductCode;
    private final JComboBox<String> jcbCustomerCode;
    private final JComboBox<String> jcbEmployeeCode;
    private final JComboBox<String> jcbDiscountCode;
    private final JLabel jlbTotal;
    private final JLabel jlbMsg;
    private float selectedDiscountAmount = 0;
    Invoice invoice = null;
    InvoiceService invoiceService = null;
    ProductService productService = null;
    CustomerService customerService = null;
    EmployeeService employeeService = null;
    DiscountService discountService = null;

    public InvoiceController(JButton btnSave, JButton btnAddCode, JTextField jtfInvoiceCode, JDateChooser jdcDateBuy, JTextField jtfPrice, JTextField jtfQuantity, JComboBox<String> jcbProductCode, JComboBox<String> jcbCustomerCode, JComboBox<String> jcbEmployeeCode, JComboBox<String> jcbDiscountCode, JLabel jlbTotal, JLabel jlbMsg) {
        this.btnSave = btnSave;
        this.btnAddCode = btnAddCode;
        this.jtfInvoiceCode = jtfInvoiceCode;
        this.jdcDateBuy = jdcDateBuy;
        this.jtfPrice = jtfPrice;
        this.jtfQuantity = jtfQuantity;
        this.jcbProductCode = jcbProductCode;
        this.jcbCustomerCode = jcbCustomerCode;
        this.jcbEmployeeCode = jcbEmployeeCode;
        this.jcbDiscountCode = jcbDiscountCode;
        this.jlbTotal = jlbTotal;
        this.jlbMsg = jlbMsg;

        this.invoiceService = new InvoiceServiceImpl();
        this.productService = new ProductServiceImpl();
        this.customerService = new CustomerServiceImpl();
        this.employeeService = new EmployeeServiceImpl();
        this.discountService = new DiscountServiceImpl();

    }

    public void setView(Invoice invoice) {
        this.invoice = invoice;

        jtfInvoiceCode.setText(invoice.getInvoiceCode());
        List<String> listProductCode = productService.getListProductCode();

        String[] arrayProduct = listProductCode.toArray(new String[0]);

        DefaultComboBoxModel<String> modelProduct = new DefaultComboBoxModel<>(arrayProduct);
        jcbProductCode.setModel(modelProduct);
        jcbProductCode.setSelectedItem(invoice.getProductCode());
        jcbProductCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProductCode = (String) jcbProductCode.getSelectedItem();
                double selectedProductPrice = productService.getPriceByCode(selectedProductCode);
                jtfPrice.setText(String.valueOf(selectedProductPrice));
            }

        });
        List<String> listCustomerCode = customerService.getListCustomerCode();
        String[] arrayCustomer = listCustomerCode.toArray(new String[0]);
        DefaultComboBoxModel<String> modelCustomer = new DefaultComboBoxModel<>(arrayCustomer);
        jcbCustomerCode.setModel(modelCustomer);
        jcbCustomerCode.setSelectedItem(invoice.getCustomerCode());

        List<String> listEmployeeCode = employeeService.getListEmployeeCode();
        String[] arrayEmployee = listEmployeeCode.toArray(new String[0]);
        DefaultComboBoxModel<String> modelEmployee = new DefaultComboBoxModel<>(arrayEmployee);
        jcbEmployeeCode.setModel(modelEmployee);
        jcbEmployeeCode.setSelectedItem(invoice.getEmployeeCode());

        List<String> listDiscounteCode = discountService.getListDiscountCode();
        String[] arrayDiscount = listDiscounteCode.toArray(new String[0]);
        DefaultComboBoxModel<String> modelDiscount = new DefaultComboBoxModel<>(arrayDiscount);
        jcbDiscountCode.setModel(modelDiscount);
        jcbDiscountCode.setSelectedItem(invoice.getDiscountCode());

        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Date date = java.sql.Date.valueOf(currentDate.format(formatter));
        jdcDateBuy.setDate(date);
        jtfPrice.setText(String.valueOf(invoice.getPrice()));
        System.out.println(String.valueOf(invoice.getPrice()));
        jtfQuantity.setText(String.valueOf(invoice.getQuantity()));

        jtfPrice.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                calculateTotalInvoice();
            }
        });
        jtfQuantity.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                calculateTotalInvoice();
            }
        });

        jcbDiscountCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDiscountCode = (String) jcbDiscountCode.getSelectedItem();
                selectedDiscountAmount = discountService.getDiscountAmountByCode(selectedDiscountCode);
                calculateTotalInvoice();

            }

        });

    }

    public void setEvent() {

        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!checkNotNull()) {
                    jlbMsg.setText("Vui lòng nhập đầy đủ dữ liệu !");
                } else {
                    invoice.setInvoiceCode(jtfInvoiceCode.getText());

                    int selectedIndexProduct = jcbProductCode.getSelectedIndex();
                    String selectedProductCode = jcbProductCode.getItemAt(selectedIndexProduct);
                    invoice.setProductCode(selectedProductCode);

                    int selectedIndexCustomer = jcbCustomerCode.getSelectedIndex();
                    String selectedCustomerCode = jcbCustomerCode.getItemAt(selectedIndexCustomer);
                    invoice.setCustomerCode(selectedCustomerCode);

                    int selectedIndexEmployee = jcbEmployeeCode.getSelectedIndex();
                    String selectedEmployeeCode = jcbEmployeeCode.getItemAt(selectedIndexEmployee);
                    invoice.setEmployeeCode(selectedEmployeeCode);

                    invoice.setDateBuy(jdcDateBuy.getDate());
                    invoice.setPrice(Double.parseDouble(jtfPrice.getText()));
                    invoice.setQuantity(Integer.parseInt(jtfQuantity.getText()));

                    int selectedIndexDiscount = jcbDiscountCode.getSelectedIndex();
                    String selectedDsicountCode = jcbDiscountCode.getItemAt(selectedIndexDiscount);
                    invoice.setDiscountCode(selectedDsicountCode);

                    invoice.setDateBuy(jdcDateBuy.getDate());
                    System.out.println(jdcDateBuy.getDate());
                    invoice.setPrice(Double.parseDouble(jtfPrice.getText()));
                    invoice.setQuantity(Integer.parseInt(jtfQuantity.getText()));
                    if (showDialog()) {
                        int rows = invoiceService.createOrUpdate(invoice);
                        System.out.println(rows);
                        if (rows > 0) {

                            jlbMsg.setText("Xử lý dữ liệu hóa đơn thành công ");
                            jlbMsg.setForeground(Color.green);

                            InvoiceDetail invoiceDetail = new InvoiceDetail();

                            String customerCode = invoice.getCustomerCode();
                            double price = invoice.getPrice();
                            int quantity = invoice.getQuantity();
                            String discountCode = invoice.getDiscountCode();
                            Date dateBuy = invoice.getDateBuy();
                            float discountAmount = discountService.getDiscountAmountByCode(discountCode);
                            double total = calculateTotalInvoice(price, quantity, discountAmount);

                            invoiceDetail.setInvoiceCode(invoice.getInvoiceCode());
                            invoiceDetail.setCustomerCode(customerCode);
                            invoiceDetail.setCutomerName(customerService.getCustomerNameByCode(customerCode));
                            invoiceDetail.setProductName(productService.getProductNameByCode(invoice.getProductCode()));
                            invoiceDetail.setEmployeeName(employeeService.getEmployeeNameByCode(invoice.getEmployeeCode()));
                            invoiceDetail.setPrice(price);
                            invoiceDetail.setQuantity(quantity);
                            invoiceDetail.setDateBuy(dateBuy);
                            invoiceDetail.setDiscountCode(discountCode);
                            invoiceDetail.setTotal(total);
                            System.out.println(invoiceDetail);
                            new PaymentJFrame(invoiceDetail).setVisible(true);


                        } else {
                            jlbMsg.setText("Xử lý dữ liệu hóa đơn thất bại");
                            jlbMsg.setForeground(Color.red);
                        }
                    }

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSave.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSave.setBackground(new Color(100, 221, 23));

            }

        });

        btnAddCode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String code = setInvoiceCode();
                jtfInvoiceCode.setText(code);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAddCode.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAddCode.setBackground(new Color(100, 221, 23));

            }
        });
    }

    public String setInvoiceCode() {
        StringBuilder invoiceCode = new StringBuilder();
        Random random = new Random();

        invoiceCode.append("HDSS");
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(10);
            invoiceCode.append(randomNumber);
        }
        return invoiceCode.toString();
    }

    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn muốn cập nhật dữ liệu hay không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }

    private boolean checkNotNull() {
        return jtfInvoiceCode.getText() != null && !jtfInvoiceCode.getText().equalsIgnoreCase("");
    }

    public java.sql.Date covertDateToDateSql(Date d) {
        return new java.sql.Date(d.getTime());
    }

    public void calculateTotalInvoice() {
        try {
            double price = Double.parseDouble(jtfPrice.getText());

            int quantity = Integer.parseInt(jtfQuantity.getText());

            double totalBeforeDiscount = price * quantity;
            double discount = totalBeforeDiscount * (selectedDiscountAmount / 100);
            double totalAfterDiscount = totalBeforeDiscount - discount;
            double total = Math.round(totalAfterDiscount * 100.0) / 100.0;

            jlbTotal.setText(String.valueOf(total));
        } catch (NumberFormatException ex) {
            jlbTotal.setText("Error");
        }
    }

    public double calculateTotalInvoice(double price, int quantity, float discountAmount) {

        double totalBeforeDiscount = price * quantity;
        double discount = totalBeforeDiscount * (discountAmount / 100);
        double totalAfterDiscount = totalBeforeDiscount - discount;
        return Math.round(totalAfterDiscount * 100.0) / 100.0;

    }

}
