package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.model.Salary;
import com.myproject.shoesstore.service.EmployeeService;
import com.myproject.shoesstore.service.SalaryService;
import com.myproject.shoesstore.service.impl.EmployeeServiceImpl;
import com.myproject.shoesstore.service.impl.SalaryServiceImpl;
import com.myproject.shoesstore.validation.SalaryValidation;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
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
public class SalaryController {

    private final JButton btnSave;
    private final JButton btnAddCode;
    private final JTextField jtfSalaryCode;
    private final JComboBox<String> jcbEmployeeCode;
    private final JTextField jtfEmployeeName;
    private final JDateChooser jdcEffectiveDate;

    private final JTextField jtfBasicSalary;
    private final JTextField jtfSubsidy;
    private final JTextField jtfBonuses;
    private final JTextField jtfForfeit;
    private final JTextField jtfNetSalary;
    private final JTextArea jtaDescript;
    private final JLabel jlbMsg;

    Salary salary = null;
    SalaryService salaryService = null;
    EmployeeService employeeService = null;

    public SalaryController(JButton btnSave, JButton btnAddCode, JTextField jtfSalaryCode, JComboBox<String> jcbEmployeeCode, JTextField jtfEmployeeName, JDateChooser jdcEffectiveDate, JTextField jtfBasicSalary, JTextField jtfSubsidy, JTextField jtfBonuses, JTextField jtfForfeit, JTextField jtfNetSalary, JTextArea jtaDescript, JLabel jlbMsg) {
        this.btnSave = btnSave;
        this.btnAddCode = btnAddCode;
        this.jtfSalaryCode = jtfSalaryCode;
        this.jcbEmployeeCode = jcbEmployeeCode;
        this.jtfEmployeeName = jtfEmployeeName;
        this.jdcEffectiveDate = jdcEffectiveDate;
        this.jtfBasicSalary = jtfBasicSalary;
        this.jtfSubsidy = jtfSubsidy;
        this.jtfBonuses = jtfBonuses;
        this.jtfForfeit = jtfForfeit;
        this.jtfNetSalary = jtfNetSalary;
        this.jtaDescript = jtaDescript;
        this.jlbMsg = jlbMsg;

        this.salaryService = new SalaryServiceImpl();
        this.employeeService = new EmployeeServiceImpl();
    }

    public void setView(Salary salary) {
        this.salary = salary;

        jtfSalaryCode.setText(salary.getSalaryCode());
        List<String> listSupplierCode = salaryService.getListEmployeeCode();

        String[] array = listSupplierCode.toArray(new String[0]);

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(array);
        jcbEmployeeCode.setModel(model);
        jcbEmployeeCode.setSelectedItem(salary.getEmplpoyeeCode());
        jcbEmployeeCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEmployeeCode = (String) jcbEmployeeCode.getSelectedItem();
                String selectedEmployeeName = employeeService.getEmployeeNameByCode(selectedEmployeeCode);
                System.out.println(selectedEmployeeName);
                jtfEmployeeName.setText(selectedEmployeeName);
            }

        });
        jtfEmployeeName.setText(employeeService.getEmployeeNameByCode(salary.getEmplpoyeeCode()));
        jdcEffectiveDate.setDate(salary.getEffectiveDate());

        jtfBasicSalary.setText(String.valueOf(salary.getBasicSalary()));
        jtfBasicSalary.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                calculateTotalSalary();
            }
        });
        jtfSubsidy.setText(String.valueOf(salary.getSubsidy()));
        jtfSubsidy.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                calculateTotalSalary();
            }
        });
        jtfBonuses.setText(String.valueOf(salary.getBonuses()));
        jtfBonuses.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                calculateTotalSalary();
            }
        });
        jtfForfeit.setText(String.valueOf(salary.getForfeit()));
        jtfForfeit.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                calculateTotalSalary();
            }
        });
        jtfNetSalary.setText(String.valueOf(salary.getNetSalary()));
        jtaDescript.setText(salary.getDescript());
    }

    public void setEvent() {

        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                salary.setSalaryCode(jtfSalaryCode.getText());
                int selectedIndex = jcbEmployeeCode.getSelectedIndex();
                String selectedEmployeeeCode = jcbEmployeeCode.getItemAt(selectedIndex);
                salary.setEmplpoyeeCode(selectedEmployeeeCode);
                salary.setEmployeeName(jtfEmployeeName.getText());
                salary.setEffectiveDate(jdcEffectiveDate.getDate());
                salary.setBasicSalary(Double.parseDouble(jtfBasicSalary.getText()));
                salary.setSubsidy(Double.parseDouble(jtfSubsidy.getText()));
                salary.setBonuses(Double.parseDouble(jtfBonuses.getText()));
                salary.setForfeit(Double.parseDouble(jtfForfeit.getText()));
                salary.setNetSalary(Double.parseDouble(jtfNetSalary.getText()));
                salary.setDescript(jtaDescript.getText());
                if (SalaryValidation.validSalary(salary)) {

                
                    if (showDialog()) {
                        if (SalaryValidation.validCreateSalaryCode(jtfSalaryCode.getText(), salaryService)) {
                            int rows = salaryService.createOrUpdate(salary);
                            System.out.println(rows);
                            if (rows > 0) {

                                jlbMsg.setText("Xử lý dữ liệu bảng lương thành công ");
                                jlbMsg.setForeground(Color.green);
                            } else {
                                jlbMsg.setText("Xử lý dữ liệu bảng lương thất bại");
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
                String code = setSalaryCode();
                jtfSalaryCode.setText(code);

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

    public String setSalaryCode() {
        StringBuilder salaryCode = new StringBuilder();
        Random random = new Random();

        salaryCode.append("SLSS");
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(10);
            salaryCode.append(randomNumber);
        }
        return salaryCode.toString();
    }

    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn muốn cập nhật dữ liệu hay không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }

    private boolean checkNotNull() {
        return jtfEmployeeName.getText() != null && !jtfEmployeeName.getText().equalsIgnoreCase("");
    }

    public java.sql.Date covertDateToDateSql(Date d) {
        return new java.sql.Date(d.getTime());
    }

   public void calculateTotalSalary() {
    try {
        double basicSalary = Double.parseDouble(jtfBasicSalary.getText());
        double subsidy = Double.parseDouble(jtfSubsidy.getText());
        double bonuses = Double.parseDouble(jtfBonuses.getText());
        double forfeit = Double.parseDouble(jtfForfeit.getText());

        BigDecimal basicSalaryDecimal = new BigDecimal(Double.toString(basicSalary));
        BigDecimal subsidyDecimal = new BigDecimal(Double.toString(subsidy));
        BigDecimal bonusesDecimal = new BigDecimal(Double.toString(bonuses));
        BigDecimal forfeitDecimal = new BigDecimal(Double.toString(forfeit));

        BigDecimal totalSalary = basicSalaryDecimal.add(subsidyDecimal)
                .add(bonusesDecimal)
                .subtract(forfeitDecimal);

        jtfNetSalary.setText(totalSalary.toString());
    } catch (NumberFormatException ex) {
        jtfNetSalary.setText("Error");
    }
}


}
