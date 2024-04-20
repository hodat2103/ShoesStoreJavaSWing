package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.model.Employee;
import com.myproject.shoesstore.service.EmployeeService;
import com.myproject.shoesstore.service.impl.EmployeeServiceImpl;
import com.myproject.shoesstore.util.TableModelUtilEmployee;
import com.myproject.shoesstore.view.JFrame.EmployeeJFrame;
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
public class EmployeeManagementController {

    private final JPanel jpnView;
    private final JButton btnAdd;
    private final JLabel btnReload;
    private final JLabel btnExportExcel;
    private final JTextField jtfSearch;

    TableModelUtilEmployee tableModelUtilEmployee = null;
    private final EmployeeService employeeService;

    private final String[] listColumn;
    private TableRowSorter<TableModel> rowSorter = null;

    public EmployeeManagementController(JPanel jpnView, JButton btnAdd,JLabel btnReload,JLabel btnExportExcel, JTextField jtfSearch) {
        this.listColumn = new String[]{"STT", "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "Vị trí", "SĐT", "Địa chỉ"};
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnReload = btnReload;
        this.btnExportExcel = btnExportExcel;
        this.jtfSearch = jtfSearch;

        this.tableModelUtilEmployee = new TableModelUtilEmployee();
        this.employeeService = new EmployeeServiceImpl();
    }

    public void setDataToTable() {
        List<Employee> listItem = employeeService.getList();
        DefaultTableModel model = new TableModelUtilEmployee().setTableEmployee(listItem, listColumn);

        JTable jTable = new JTable(model);

        rowSorter = new TableRowSorter<>(jTable.getModel());
        jTable.setRowSorter(rowSorter);

        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String txtSearch = jtfSearch.getText();
                if (txtSearch.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtSearch));
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
                if (e.getClickCount() == 2 && jTable.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                    int selectedRowIndex = jTable.getSelectedRow();
                    selectedRowIndex = jTable.convertRowIndexToModel(selectedRowIndex);

                    Employee employee = new Employee();
                    employee.setEmployeeCode(model.getValueAt(selectedRowIndex, 1).toString());
                    employee.setName(model.getValueAt(selectedRowIndex, 2).toString());
                    employee.setDate((Date) model.getValueAt(selectedRowIndex, 3));
                    employee.setGender(model.getValueAt(selectedRowIndex, 4).toString().equalsIgnoreCase("Nam"));
                    employee.setPosition(model.getValueAt(selectedRowIndex, 5).toString());
                    employee.setPhone(model.getValueAt(selectedRowIndex, 6).toString() != null
                            ? model.getValueAt(selectedRowIndex, 6).toString() : "");
                    employee.setAddress(model.getValueAt(selectedRowIndex, 7).toString() != null
                            ? model.getValueAt(selectedRowIndex, 7).toString() : "");

                    EmployeeJFrame frame = new EmployeeJFrame(employee);
                    frame.setTitle("Thông tin nhân viên");
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
                            int option = JOptionPane.showConfirmDialog(jTable, "Bạn có muốn xóa khách hàng này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                employeeService.delete(code);
                                setDataToTable();
                            }
                        }
                    }
                }
            }

        });

        jTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        jTable.getTableHeader().setPreferredSize(new Dimension(60, 40));
        jTable.setRowHeight(30);
        jTable.validate();
        jTable.repaint();

//        jTable.getColumnModel().getColumn(1).setMaxWidth(50);
//        jTable.getColumnModel().getColumn(1).setMinWidth(50);
//        jTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable.getColorModel();

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JLabel) rendererComponent).setHorizontalAlignment(JLabel.CENTER);

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
                new EmployeeJFrame (new Employee()).setVisible(true);
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
                List<Employee> employeeList = employeeService.getList();
                ExcelExporter.exportToExcel(employeeList, "D:/Files/employees.xlsx");

            }
            
        });
    }
    
}
