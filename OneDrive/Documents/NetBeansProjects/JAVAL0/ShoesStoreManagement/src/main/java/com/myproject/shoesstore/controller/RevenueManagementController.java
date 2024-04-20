package com.myproject.shoesstore.controller;

import static com.microsoft.schemas.office.excel.STObjectType.Enum.table;
import com.myproject.shoesstore.model.Payment;
import com.myproject.shoesstore.service.PaymentService;
import com.myproject.shoesstore.service.impl.PaymentServiceImpl;
import com.myproject.shoesstore.util.TableModelUtilPayment;
import com.myproject.shoesstore.util.TableModelUtilRevenue;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
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
public class RevenueManagementController {

    private JPanel jpnView;
    private JButton btnCaculatate;
    private JTextField jtfSearch;
    private TableModelUtilRevenue tableModelUtil = null;
    private PaymentService paymentService = null;

    private final String[] listColumn = {"STT", "Mã hóa đơn", "Ngày Thanh toán", "Tiền thanh toán"};

    private TableRowSorter<TableModel> rowSorter = null;

    public RevenueManagementController(JPanel jpnView, JTextField jtfSearch, JButton btnCaculatate) {
        this.jpnView = jpnView;
        this.jtfSearch = jtfSearch;
        this.btnCaculatate = btnCaculatate;
        this.tableModelUtil = new TableModelUtilRevenue();
        this.paymentService = new PaymentServiceImpl();

    }

    public RevenueManagementController() {
        this.tableModelUtil = new TableModelUtilRevenue();

        this.paymentService = new PaymentServiceImpl();
    }

    public void setDataToTable() {
        List<Payment> listItem = paymentService.getList();
        DefaultTableModel model = new TableModelUtilRevenue().setTableRevenue(listItem, listColumn);

        JTable jTable = new JTable(model);

        rowSorter = new TableRowSorter<>(jTable.getModel());
        jTable.setRowSorter(rowSorter);

        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.add(jTable);
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String txtSearch = jtfSearch.getText();
                if (txtSearch.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    // Tìm kiếm theo cột ngày thanh toán (chỉ mục cột 2)
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtSearch, 2));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String txtSearch = jtfSearch.getText();
                if (txtSearch.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    // Tìm kiếm theo cột ngày thanh toán (chỉ mục cột 2)
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtSearch, 2));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        btnCaculatate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Tổng doanh thu là " + calculateTotalPayment(jTable) + " VNĐ", "Thông báo", JOptionPane.OK_OPTION);
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

    public String calculateTotalPayment(JTable table) {
        BigDecimal totalPayment = BigDecimal.ZERO;
        int columnIndex = 3;

        int rowCount = table.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            Object value = table.getValueAt(i, columnIndex);
            if (value instanceof Double) {
                totalPayment = totalPayment.add(BigDecimal.valueOf((Double) value));
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        return decimalFormat.format(totalPayment);
    }
}
