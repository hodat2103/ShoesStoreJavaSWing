package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.model.Customer;
import com.myproject.shoesstore.model.Employee;
import com.myproject.shoesstore.service.EmployeeService;
import com.myproject.shoesstore.service.impl.EmployeeServiceImpl;
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
public class EmployeeController {
    private final JButton btnSave;
    private final JButton btnAddCode;
    private final JTextField jtfEmployeeCode;
    private final JTextField jtfName;
    private final JDateChooser jdcDate;
    private final JRadioButton jrbMale;
    private final JRadioButton jrbFemale;
    private final JTextField jtfPosition;
    private final JTextField jtfPhone;
    private final JTextArea jtaAddress;
    private final JLabel jlbMsg;
    
    Employee employee= null;
    EmployeeService employeeService = null;

    public EmployeeController(JButton btnSave, JButton btnAddCode, JTextField jtfEmployeeCode, JTextField jtfName, JDateChooser jdcDate, JRadioButton jrbMale, JRadioButton jrbFemale, JTextField jtfPosition, JTextField jtfPhone, JTextArea jtaAddress,JLabel jlbMsg) {
        this.btnSave = btnSave;
        this.btnAddCode = btnAddCode;
        this.jtfEmployeeCode = jtfEmployeeCode;
        this.jtfName = jtfName;
        this.jdcDate = jdcDate;
        this.jrbMale = jrbMale;
        this.jrbFemale = jrbFemale;
        this.jtfPosition = jtfPosition;
        this.jtfPhone = jtfPhone;
        this.jtaAddress = jtaAddress;
        this.jlbMsg = jlbMsg;
        
        this.employeeService = new EmployeeServiceImpl();
    }
    public void setView(Employee employee) {
        this.employee = employee;

        jtfEmployeeCode.setText(employee.getEmployeeCode());
        jtfName.setText(employee.getName());
        jdcDate.setDate(employee.getDate());
        if(employee.isGender()){
            jrbMale.setSelected(true);
            jrbFemale.setSelected(false);
        }else{
            jrbMale.setSelected(false);
            jrbFemale.setSelected(true);
        }
        jtfPosition.setText(employee.getPosition());
        jtfPhone.setText(employee.getPhone());
        jtaAddress.setText(employee.getAddress());
    }
    public void setEvent(){
        btnSave.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!checkNotNull()){
                    jlbMsg.setText("Vui lòng nhập đầy đủ dữ liệu !");
                }else{
                    employee.setEmployeeCode(jtfEmployeeCode.getText());
                    employee.setName(jtfName.getText());
                    employee.setDate(jdcDate.getDate());
                    employee.setGender(jrbMale.isSelected());
                    employee.setPosition(jtfPosition.getText());
                    employee.setPhone(jtfPhone.getText());
                    employee.setAddress(jtaAddress.getText());
                    if(showDialog()){
                        int rows = employeeService.createOrUpdate(employee);
                        System.out.println(rows);
                        if (rows > 0) {

                            jlbMsg.setText("Xử lý dữ liệu nhân viên thành công ");
                            jlbMsg.setForeground(Color.green);
                        } else {
                            jlbMsg.setText("Xử lý dữ liệu nhân viên thất bại");
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
                String code = setEmployeeCode();
                jtfEmployeeCode.setText(code);
                
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
    public String setEmployeeCode(){
        StringBuilder employeeCode = new StringBuilder();
        Random random = new Random();

        employeeCode.append("NVSS");
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(10);
            employeeCode.append(randomNumber);
        }
        return employeeCode.toString();
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
