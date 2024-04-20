package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.model.Account;
import com.myproject.shoesstore.service.AccountService;
import com.myproject.shoesstore.service.impl.AccountServiceImpl;
import com.myproject.shoesstore.view.Login;
import com.myproject.shoesstore.view.MainJFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.prefs.Preferences;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Tadaboh;
 */
public class LoginController {

    private final JFrame jFrame;
    private final JButton btnSubmit;
    private final JTextField jtfUsername;
    private final JTextField jtfPassword;

    private AccountService accountService = null;

    public LoginController(JFrame jFrame, JButton btnSubmit,JTextField jtfUsername, JTextField jtfPassword) {
        this.jFrame = jFrame;
        this.btnSubmit = btnSubmit;
        this.jtfUsername = jtfUsername;
        this.jtfPassword = jtfPassword;

        accountService = new AccountServiceImpl();
    }

    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (jtfUsername.getText().length() == 0
                            || jtfPassword.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu bắt buộc!");
                    } else {
                        Account account = accountService.login(jtfUsername.getText(), jtfPassword.getText());
                        if (account == null) {
                            JOptionPane.showMessageDialog(null, "Tên đăng nhập và mật khẩu không đúng!");

                        } else {

                            jFrame.dispose();
                            MainJFrame frame = new MainJFrame();
                            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                            frame.setVisible(true);

                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        

    }

}
