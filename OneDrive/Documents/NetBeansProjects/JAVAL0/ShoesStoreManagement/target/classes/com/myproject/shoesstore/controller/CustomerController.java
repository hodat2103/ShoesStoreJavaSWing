package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.model.Customer;
import com.myproject.shoesstore.service.CustomerService;
import com.myproject.shoesstore.service.impl.CustomerServiceImpl;
import com.myproject.shoesstore.validation.CustomerValidation;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Tadaboh;
 */
public class CustomerController {

    private final JButton btnSave;
    private final JButton btnAddCode;
    private final JTextField jtfCustomerCode;
    private final JTextField jtfName;
    private final JDateChooser jdcDate;
    private final JRadioButton jrbMale;
    private final JRadioButton jrbFemale;
    private final JTextField jtfPhone;
    private final JTextArea jtaAddress;
    private final JLabel jlbMsg;
    Customer customer = null;
    CustomerService customerService = null;

    public CustomerController(JButton btnSave, JButton btnAddCode, JTextField jtfCustomerCode, JTextField jtfName, JDateChooser jdcDate, JRadioButton jrbMale, JRadioButton jrbFemale, JTextField jtfPhone, JTextArea jtaAddress, JLabel jlbMsg) {
        this.btnSave = btnSave;
        this.btnAddCode = btnAddCode;
        this.jtfCustomerCode = jtfCustomerCode;
        this.jtfName = jtfName;
        this.jdcDate = jdcDate;
        this.jrbMale = jrbMale;
        this.jrbFemale = jrbFemale;
        this.jtfPhone = jtfPhone;
        this.jtaAddress = jtaAddress;
        this.jlbMsg = jlbMsg;
        this.customerService = new CustomerServiceImpl();
    }

    public void setView(Customer customer) {
        this.customer = customer;

        jtfCustomerCode.setText(customer.getCustomerCode());
        jtfName.setText(customer.getName());
        jdcDate.setDate(customer.getDate());
        if (customer.isGender()) {
            jrbMale.setSelected(true);
            jrbFemale.setSelected(false);
        } else {
            jrbMale.setSelected(false);
            jrbFemale.setSelected(true);
        }
        jtfPhone.setText(customer.getPhone());
        jtaAddress.setText(customer.getAddress());
    }

    public void setEvent() {
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                customer.setCustomerCode(jtfCustomerCode.getText());
                customer.setName(jtfName.getText());
                customer.setDate(jdcDate.getDate());
                customer.setGender(jrbMale.isSelected());
                customer.setPhone(jtfPhone.getText());
                customer.setAddress(jtaAddress.getText());

                if (CustomerValidation.validCustomer(customer)) {
                    if (showDialog()) {
                        if (CustomerValidation.validCreateEmployeeCode(jtfCustomerCode.getText(), customerService)) {
                            int rows = customerService.createOrUpdate(customer);
                            if (rows > 0) {

                                jlbMsg.setText("Xử lý dữ liệu khách hàng thành công ");
                                jlbMsg.setForeground(Color.green);
                            } else {
                                jlbMsg.setText("Xử lý dữ liệu khách hàng thất bại");
                                jlbMsg.setForeground(Color.red);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Mã đã tồn tại !");
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ dữ liệu hoặc xem lại dữ liệu đã nhập vào!");
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
                String code = setCustomerCode();
                jtfCustomerCode.setText(code);

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

    public String setCustomerCode() {
        StringBuilder customerCode = new StringBuilder();
        Random random = new Random();

        customerCode.append("KHSS");
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(10);
            customerCode.append(randomNumber);
        }
        return customerCode.toString();
    }

    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn muốn cập nhật dữ liệu hay không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }

    private boolean checkNotNull() {
        return jtfName.getText() != null && !jtfName.getText().equalsIgnoreCase("");
    }

    public java.sql.Date covertDateToDateSql(Date d) {
        return new java.sql.Date(d.getTime());
    }
}
