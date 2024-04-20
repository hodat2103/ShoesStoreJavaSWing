package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.model.Supplier;
import com.myproject.shoesstore.service.SupplierService;
import com.myproject.shoesstore.service.impl.SupplierServiceImpl;
import com.myproject.shoesstore.validation.SupplierValidation;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Tadaboh;
 */
public class SupplierController {

    private JButton btnSave;
    private JButton btnAddCode;
    private JTextField jtfSupplierCode;
    private JTextField jtfSupplierName;
    private JTextField jtfTradeName;

    private JTextArea jtaAddress;
    private JTextField jtfPhone;
    private JTextField jtfEmail;
    private JLabel jlbMsg;

    Supplier supplier = null;
    SupplierService suplierService = null;
    SupplierManagementController supplierManagementController = null;

    public SupplierController(JButton btnSave, JButton btnAddCode, JTextField jtfSupplierCode, JTextField jtfSupplierName, JTextField jtfTradeName, JTextArea jtaAddress, JTextField jtfPhone, JTextField jtfEmail, JLabel jlbMsg) {
        this.btnSave = btnSave;
        this.btnAddCode = btnAddCode;
        this.jtfSupplierCode = jtfSupplierCode;
        this.jtfSupplierName = jtfSupplierName;
        this.jtfTradeName = jtfTradeName;
        this.jtaAddress = jtaAddress;
        this.jtfPhone = jtfPhone;
        this.jtfEmail = jtfEmail;
        this.jlbMsg = jlbMsg;

        this.suplierService = new SupplierServiceImpl();
        this.supplierManagementController = new SupplierManagementController();
    }

    public void setView(Supplier supplier) {
        this.supplier = supplier;

        jtfSupplierCode.setText(supplier.getSupplierCode());
        jtfSupplierName.setText(supplier.getSupplierName());
        jtfTradeName.setText(supplier.getTradeName());

        jtfPhone.setText(supplier.getPhone());
        jtaAddress.setText(supplier.getAddress());
        jtfEmail.setText(supplier.getEmail());

    }

    public void setEvent() {
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                supplier.setSupplierCode(jtfSupplierCode.getText());
                supplier.setSupplierName(jtfSupplierName.getText());
                supplier.setTradeName(jtfTradeName.getText());
                supplier.setAddress(jtaAddress.getText());
                supplier.setPhone(jtfPhone.getText());
                supplier.setEmail(jtfEmail.getText());
                if (SupplierValidation.validSupplier(supplier)) {

                    if (showDialog()) {
                        if (SupplierValidation.validCreateSupplierCode(jtfSupplierCode.getText(), suplierService)) {
                            int rows = suplierService.createOrUpdate(supplier);
                            System.out.println(rows);
                            if (rows > 0) {
//                            supplierManagementController.setDataToTable();
                                jlbMsg.setText("Xử lý dữ liệu nhà cung cấp thành công ");
                                jlbMsg.setForeground(Color.green);

                            } else {
                                jlbMsg.setText("Xử lý dữ liệu cung cấp hàng thất bại");
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
                String code = setSupplierCode();
                jtfSupplierCode.setText(code);

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

    public String setSupplierCode() {
        StringBuilder supplierCode = new StringBuilder();
        Random random = new Random();

        supplierCode.append("NCCS");
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(10);
            supplierCode.append(randomNumber);
        }
        return supplierCode.toString();
    }

    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn muốn cập nhật dữ liệu hay không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }

    private boolean checkNotNull() {
        return jtfSupplierName.getText() != null && !jtfSupplierName.getText().equalsIgnoreCase("");
    }

}
