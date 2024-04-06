package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.model.Discount;
import com.myproject.shoesstore.model.Discount;
import com.myproject.shoesstore.service.DiscountService;
import com.myproject.shoesstore.service.impl.DiscountServiceImpl;
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
public class DiscountController {
    private final JButton btnSave;
    private final JButton btnAddCode;
    private final JTextField jtfDiscountCode;
    private final JTextField jtfTitle;
    private final JDateChooser jdcDateStart;
    private final JDateChooser jdcDateEnd;
   
    private final JTextField jtfDiscountAmount;
    private final JTextArea jtaDescript;
    private final JLabel jlbMsg;
    
    Discount discount = null;
    DiscountService discountService = null;

    public DiscountController(JButton btnSave, JButton btnAddCode, JTextField jtfDiscountCode, JTextField jtfTitle, JDateChooser jdcDateStart, JDateChooser jdcDateEnd, JTextField jtfDiscountAmount, JTextArea jtaDescript, JLabel jlbMsg) {
        this.btnSave = btnSave;
        this.btnAddCode = btnAddCode;
        this.jtfDiscountCode = jtfDiscountCode;
        this.jtfTitle = jtfTitle;
        this.jdcDateStart = jdcDateStart;
        this.jdcDateEnd = jdcDateEnd;
        this.jtfDiscountAmount = jtfDiscountAmount;
        this.jtaDescript = jtaDescript;
        this.jlbMsg = jlbMsg;
        
        this.discountService = new DiscountServiceImpl();
    }
    
    public void setView(Discount discount) {
        this.discount = discount;

        jtfDiscountCode.setText(discount.getDiscountCode());
        jtfTitle.setText(discount.getTitle());
        jdcDateStart.setDate(discount.getDateStart());
        jdcDateEnd.setDate(discount.getDateEnd());
       
        jtfDiscountAmount.setText(String.valueOf( discount.getDiscountAmount()));
        jtaDescript.setText(discount.getDescript());
    }
    
    public void setEvent(){
        btnSave.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!checkNotNull()){
                    jlbMsg.setText("Vui lòng nhập đầy đủ dữ liệu !");
                }else{
                    discount.setDiscountCode(jtfDiscountCode.getText());
                    discount.setTitle(jtfTitle.getText());
                    discount.setDateStart(jdcDateStart.getDate());
                    discount.setDateEnd(jdcDateEnd.getDate());
                    discount.setDiscountAmount(Float.parseFloat(jtfDiscountAmount.getText()));
                    discount.setDescript(jtaDescript.getText());
                    if(showDialog()){
                        int rows = discountService.createOrUpdate(discount);
                        System.out.println(rows);
                        if (rows > 0) {

                            jlbMsg.setText("Xử lý dữ liệu khách hàng thành công ");
                            jlbMsg.setForeground(Color.green);
                        } else {
                            jlbMsg.setText("Xử lý dữ liệu khách hàng thất bại");
                            jlbMsg.setForeground(Color.red);
                    } 
                    }
                    
                   
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSave.setBackground(new Color(0,200,83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSave.setBackground(new Color(100, 221, 23));

            }
            
            
        });
        
        btnAddCode.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                String code = setDiscountCode();
                jtfDiscountCode.setText(code);
                
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAddCode.setBackground(new Color(0,200,83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAddCode.setBackground(new Color(100, 221, 23));

            }
        });
    }
    public String setDiscountCode(){
        StringBuilder discountCode = new StringBuilder();
        Random random = new Random();

        discountCode.append("KMSS");
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(10);
            discountCode.append(randomNumber);
        }
        return discountCode.toString();
    }
    
    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn muốn cập nhật dữ liệu hay không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
    private boolean checkNotNull() {
        return jtfTitle.getText() != null && !jtfTitle.getText().equalsIgnoreCase("");
    }
    public java.sql.Date covertDateToDateSql(Date d) {
        return new java.sql.Date(d.getTime());
    }
    
    
}
