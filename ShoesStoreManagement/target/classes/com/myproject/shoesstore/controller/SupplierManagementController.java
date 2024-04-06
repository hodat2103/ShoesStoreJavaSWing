package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.model.Supplier;
import com.myproject.shoesstore.service.SupplierService;
import com.myproject.shoesstore.service.impl.SupplierServiceImpl;
import com.myproject.shoesstore.util.TableModelUtilSupplier;
import com.myproject.shoesstore.view.JFrame.SupplierFrame;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class SupplierManagementController {

    private JPanel jpnView;
    private JButton btnAdd;
    private JLabel btnReload;
    private JLabel btnExportExcel;
    private JTextField jtfSearch ;
    private TableModelUtilSupplier tableModelUtilSupplier = null;
    private SupplierService supplierService;
    private ExcelExporter  excelExporter;

    private String[] listColumn = {"STT", "Mã NCC", "Tên NCC", "Tên giao dịch", "Địa chỉ", "SĐT", "Email"};

    private TableRowSorter<TableModel> rowSorter = null;

    public SupplierManagementController() {
    }

    public SupplierManagementController(JPanel jpnView, JButton btnAdd,JLabel btnReload,JLabel btnExportExcel, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnReload = btnReload;
        this.btnExportExcel = btnExportExcel;
        this.jtfSearch = jtfSearch;

        this.tableModelUtilSupplier = new TableModelUtilSupplier();
        this.supplierService = new SupplierServiceImpl();
        this.excelExporter = new ExcelExporter();

    }

    public void setDataToTable() {
//        this.supplierService = new SupplierServiceImpl();
        List<Supplier> listItem = supplierService.getList();
        DefaultTableModel model = new TableModelUtilSupplier().setTableSupplier(listItem, listColumn);

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

                    Supplier supplier = new Supplier();
                    supplier.setSupplierCode(model.getValueAt(selectedRowIndex, 1).toString());
                    supplier.setSupplierName(model.getValueAt(selectedRowIndex, 2).toString());
                    supplier.setTradeName(model.getValueAt(selectedRowIndex, 3).toString());
                    supplier.setAddress(model.getValueAt(selectedRowIndex, 4).toString());
                    supplier.setPhone(model.getValueAt(selectedRowIndex, 5).toString() != null
                            ? model.getValueAt(selectedRowIndex, 5).toString() : "");
                    supplier.setEmail(model.getValueAt(selectedRowIndex, 6).toString() != null
                            ? model.getValueAt(selectedRowIndex, 6).toString() : "");

                    SupplierFrame frame = new SupplierFrame(supplier);
                    frame.setTitle("Thông tin nhà cung cấp");
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
                                supplierService.delete(code);
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

    public void setEvent() {
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SupplierFrame(new Supplier()).setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdd.setBackground(new Color(0, 200, 83));
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
                List<Supplier> supplierList = supplierService.getList();
                ExcelExporter.exportToExcel(supplierList,"D:/Files/suppliers.xlsx");
                JOptionPane.showMessageDialog(jpnView, "Xuất danh sách thành công");
            }
            
        });
    }
}
