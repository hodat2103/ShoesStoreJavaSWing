package com.myproject.shoesstore.controller;

import static com.myproject.shoesstore.controller.ExcelExporter.exportToExcel;
import com.myproject.shoesstore.model.Customer;
import com.myproject.shoesstore.service.CustomerService;
import com.myproject.shoesstore.service.impl.CustomerServiceImpl;
import com.myproject.shoesstore.util.TableModelUtilCustomer;
import com.myproject.shoesstore.view.JFrame.CustomerJFrame;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Tadaboh;
 */
public class CustomerManagementController {
    private final JPanel jpnView;
    private final JButton btnAdd;
    private final JLabel btnReload;
    private final JLabel btnExportExcel;
    private final JTextField jtfSearch;
    private TableModelUtilCustomer tableModelUtil = null;
    private final CustomerService customerService;
   
    private final String[] listColumn = {"STT","Mã khách hàng","Họ tên","Ngày sinh","Giới tính","SĐT","Địa chỉ"};
    
    private TableRowSorter<TableModel> rowSorter = null;
    public CustomerManagementController(JPanel jpnView, JButton btnAdd, JLabel btnReload,JLabel btnExportExcel, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnReload = btnReload;
        this.btnExportExcel = btnExportExcel;
        this.jtfSearch = jtfSearch;
        
        this.tableModelUtil = new TableModelUtilCustomer();
        this.customerService = new CustomerServiceImpl();
        
    }
    
    public void setDataToTable(){
        List<Customer> listItem = customerService.getList();
        DefaultTableModel model = new TableModelUtilCustomer().setTableCustomer(listItem, listColumn);
        
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
                    
                    Customer customer = new Customer();
                    customer.setCustomerCode(model.getValueAt(selectedRowIndex, 1).toString());
                    customer.setName(model.getValueAt(selectedRowIndex, 2).toString());
                    customer.setDate((Date) model.getValueAt(selectedRowIndex, 3));
                    customer.setGender(model.getValueAt(selectedRowIndex, 4).toString().equalsIgnoreCase("Nam"));
                    customer.setPhone(model.getValueAt(selectedRowIndex, 5).toString()!= null ?
                            model.getValueAt(selectedRowIndex, 5).toString() : "");
                    customer.setAddress(model.getValueAt(selectedRowIndex, 6).toString() != null ?
                            model.getValueAt(selectedRowIndex, 6).toString() : "");
                    
                    CustomerJFrame frame = new CustomerJFrame(customer);
                    frame.setTitle("Thông tin khách hàng");
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
                                customerService.delete(code);
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
        

        jTable.getColorModel();
        
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                //show in jtable
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
                new CustomerJFrame (new Customer()).setVisible(true);
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
                List<Customer> customerList = customerService.getList();
                ExcelExporter.exportToExcel(customerList, "D:/Files/customers.xlsx");
            }
            
        });
    }
    
}
