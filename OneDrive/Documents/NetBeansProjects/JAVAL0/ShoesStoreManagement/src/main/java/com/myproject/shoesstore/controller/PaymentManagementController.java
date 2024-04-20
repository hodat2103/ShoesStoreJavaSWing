package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.model.Payment;
import com.myproject.shoesstore.service.PaymentService;
import com.myproject.shoesstore.service.impl.PaymentServiceImpl;
import com.myproject.shoesstore.util.TableModelUtilPayment;
import com.myproject.shoesstore.view.JFrame.PaymentJFrame;
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
public class PaymentManagementController {
    private final JPanel jpnView;
    private final JLabel btnExportExcel;
    private final JTextField jtfSearch;
    private TableModelUtilPayment tableModelUtil = null;
    private final PaymentService paymentService;
   
    private final String[] listColumn = {"STT","Mã thanh toán","Mã hóa đơn","Ngày TT","Tiền TT","Tiền khách đưa","Tiền trả lại"};
    
    private TableRowSorter<TableModel> rowSorter = null;
    public PaymentManagementController(JPanel jpnView,JLabel btnExportExcel, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnExportExcel = btnExportExcel;
        this.jtfSearch = jtfSearch;
        
        this.tableModelUtil = new TableModelUtilPayment();
        this.paymentService = new PaymentServiceImpl();
        
    }
    
    public void setDataToTable(){
        List<Payment> listItem = paymentService.getList();
        DefaultTableModel model = new TableModelUtilPayment().setTablePayment(listItem, listColumn);
        
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
//                if(e.getClickCount() == 2 && jTable.getSelectedRow() != -1){
//                    DefaultTableModel model = (DefaultTableModel) jTable.getModel();
//                    int selectedRowIndex = jTable.getSelectedRow();
//                    selectedRowIndex = jTable.convertRowIndexToModel(selectedRowIndex);
//                    
//                    Payment payment = new Payment();
//                    payment.setPaymentCode(model.getValueAt(selectedRowIndex, 1).toString());
//                    payment.setInvoiceCode(model.getValueAt(selectedRowIndex, 2).toString());
//                    payment.setDatePay( model.getValueAt(selectedRowIndex, 3).toString());
//                    payment.setTotal(Double.parseDouble(model.getValueAt(selectedRowIndex, 4).toString()));
//                    payment.setCustomerPay(Double.parseDouble(model.getValueAt(selectedRowIndex, 5).toString()));
//                    payment.setRefund(Double.parseDouble(model.getValueAt(selectedRowIndex, 6).toString()));
//                    
//                   
//                       
//                    
//                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = jTable.rowAtPoint(e.getPoint());
                    int column = jTable.columnAtPoint(e.getPoint());
                    if (row >= 0 && column >= 0) {
                        Object codeObject = jTable.getValueAt(row,1);
                        if (codeObject != null) {
                            String code = codeObject.toString();
                            System.out.println(code);
                            int option = JOptionPane.showConfirmDialog(jTable, "Bạn có muốn xóa dữ liêu này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                paymentService.delete(code);
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
        
        
        btnExportExcel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                List<Payment> paymentList = paymentService.getList();
                ExcelExporter.exportToExcel(paymentList, "D:/Files/payments.xlsx");
            }
            
        });
    }
}
