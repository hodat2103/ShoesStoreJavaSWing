package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.model.Salary;
import com.myproject.shoesstore.service.SalaryService;
import com.myproject.shoesstore.service.impl.SalaryServiceImpl;
import com.myproject.shoesstore.util.TableModelUtilSalary;
import com.myproject.shoesstore.view.JFrame.SalaryJFrame;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Tadaboh;
 */
public class SalaryManagementController {
    private final JPanel jpnView;
    private final JButton btnAdd;
    private final JLabel btnReload;
    private final JLabel btnExportExcel;
    private final JTextField jtfSearch;
    private TableModelUtilSalary tableModelUtil = null;
    private final SalaryService salaryService;
    private final ExcelExporter excelExporter;
    
    private final String[] listColumn = {"STT","Mã lương","Mã nhân viên","Tên nhân viên","Ngày hiệu lực","Lương cơ bản","Phụ cấp","Thưởng","Phạt", "Lương thực lĩnh","Ghi chú"};
    
    private TableRowSorter<TableModel> rowSorter = null;
    public SalaryManagementController(JPanel jpnView, JButton btnAdd, JLabel btnReload,JLabel btnExportExcel, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnReload = btnReload;
        this.btnExportExcel = btnExportExcel;
        this.jtfSearch = jtfSearch;
        
        this.tableModelUtil = new TableModelUtilSalary();
        this.salaryService = new SalaryServiceImpl();
        this.excelExporter = new ExcelExporter();
        
    }
    
    public void setDataToTable(){
        List<Salary> listItem = salaryService.getList();
        DefaultTableModel model = new TableModelUtilSalary().setTableSalary(listItem, listColumn);
        
        JTable jTable = new JTable(model);
        
        rowSorter = new TableRowSorter<>(jTable.getModel());
        jTable.setRowSorter(rowSorter);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.add(jTable);
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String txtSearch = jtfSearch.getText();
                if(txtSearch.trim().length() == 0){
                    rowSorter.setRowFilter(null);
                }else{
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+ txtSearch));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String txtSearch = jtfSearch.getText();
                if (txtSearch.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtSearch));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
 
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2 && jTable.getSelectedRow() != -1){
                    DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                    int selectedRowIndex = jTable.getSelectedRow();
                    selectedRowIndex = jTable.convertRowIndexToModel(selectedRowIndex);
                    
                    Salary salary = new Salary();
                    salary.setSalaryCode(model.getValueAt(selectedRowIndex, 1).toString());
                    salary.setEmplpoyeeCode(model.getValueAt(selectedRowIndex, 2).toString());
                    salary.setEmployeeName(model.getValueAt(selectedRowIndex, 3).toString());
                    salary.setEffectiveDate((Date) model.getValueAt(selectedRowIndex, 4));
                    salary.setBasicSalary(Double.parseDouble(model.getValueAt(selectedRowIndex, 5).toString()));
                    salary.setSubsidy(Double.parseDouble(model.getValueAt(selectedRowIndex, 6).toString()));
                    salary.setBonuses(Double.parseDouble(model.getValueAt(selectedRowIndex,7).toString()));
                    salary.setForfeit(Double.parseDouble(model.getValueAt(selectedRowIndex, 8).toString()));
                    salary.setNetSalary(Double.parseDouble(model.getValueAt(selectedRowIndex, 9).toString()));
                    salary.setDescript(model.getValueAt(selectedRowIndex, 10).toString());
                 
                    
                    SalaryJFrame frame = new SalaryJFrame(salary);
                    frame.setTitle("Thông tin bảng lương");
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                       
                    
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = jTable.rowAtPoint(e.getPoint());
                    int column = jTable.columnAtPoint(e.getPoint());
                    if (row >= 0 && column >= 0) {
                        Object codeObject = jTable.getValueAt(row,1);
                        if (codeObject != null) {
                            String code = codeObject.toString();
                            System.out.println(code);
                            int option = JOptionPane.showConfirmDialog(jTable, "Bạn có muốn xóa dòng dữ liệu này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                salaryService.delete(code);
                                setDataToTable();
                            }
                        }
                    }
                }
            }
            
            
        });
        
        jTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        jTable.getTableHeader().setPreferredSize(new Dimension(60, 40));
        jTable.setRowHeight(30);
        jTable.validate();
        jTable.repaint();
        
//        jTable.getColumnModel().getColumn(1).setMaxWidth(50);
//        jTable.getColumnModel().getColumn(1).setMinWidth(50);
//        jTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable.getColorModel();
        
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel)rendererComponent).setHorizontalAlignment(JLabel.CENTER);
                
                return rendererComponent;
            }
            
        };
        jTable.setDefaultRenderer(Object.class, renderer);
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(jTable);
        scroll.setPreferredSize(new Dimension(1000, 400));
        jpnView.removeAll();
        jpnView.setLayout(new CardLayout());
        jpnView.add(scroll);
        jpnView.validate();
        jpnView.repaint();
    }
    public void setEvent(){
        btnAdd.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                new SalaryJFrame (new Salary()).setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdd.setBackground(new Color(0,200,83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAdd.setBackground(new Color(100, 221, 23));

            }
            
            
        });
        btnReload.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
              setDataToTable();
            }
            
        });
        btnExportExcel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                List<Salary> salaryList = salaryService.getList();
                ExcelExporter.exportToExcel(salaryList, "D:/Files/salaries.xlsx");

            }
            
        });
    }
}
