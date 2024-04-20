package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.model.Invoice;
import com.myproject.shoesstore.model.InvoiceDetail;
import com.myproject.shoesstore.service.CustomerService;
import com.myproject.shoesstore.service.DiscountService;
import com.myproject.shoesstore.service.EmployeeService;
import com.myproject.shoesstore.service.InvoiceService;
import com.myproject.shoesstore.service.ProductService;
import com.myproject.shoesstore.service.impl.CustomerServiceImpl;
import com.myproject.shoesstore.service.impl.DiscountServiceImpl;
import com.myproject.shoesstore.service.impl.EmployeeServiceImpl;
import com.myproject.shoesstore.service.impl.InvoiceServiceImpl;
import com.myproject.shoesstore.service.impl.ProductServiceImpl;
import com.myproject.shoesstore.util.TableModelUtilInvoice;
import com.myproject.shoesstore.view.JFrame.InvoiceDetailJFrame;
import com.myproject.shoesstore.view.JFrame.InvoiceJFrame;
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
public class InvoiceManagementController {

    private final JPanel jpnView;
    private final JButton btnAdd;
    private final JLabel btnReload;
    private final JLabel btnExportExcel;
    private final JTextField jtfSearch;

    TableModelUtilInvoice tableModelUtilInvoice = null;
    private final InvoiceService invoiceService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final EmployeeService employeeService;
    private final DiscountService discountService;

    private final String[] listColumn;
    private TableRowSorter<TableModel> rowSorter = null;

    public InvoiceManagementController(JPanel jpnView, JButton btnAdd, JLabel btnReload,JLabel btnExportExcel, JTextField jtfSearch) {
        this.listColumn = new String[]{"STT", "Mã hóa đơn", "Mã khách hàng", "Mã sản phẩm", "Giá", "Số lượng", "Mã giảm giá", "Mã nhân viên", "Ngày mua"};
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnReload = btnReload;
        this.btnExportExcel = btnExportExcel;
        this.jtfSearch = jtfSearch;

        this.tableModelUtilInvoice = new TableModelUtilInvoice();
        this.invoiceService = new InvoiceServiceImpl();
        this.customerService = new CustomerServiceImpl();
        this.productService = new ProductServiceImpl();
        this.employeeService = new EmployeeServiceImpl();
        this.discountService = new DiscountServiceImpl();

    }

    public void setDataToTable() {
        List<Invoice> listItem = invoiceService.getList();
        DefaultTableModel model = new TableModelUtilInvoice().setTableInvoice(listItem, listColumn);

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

                    Invoice invoice = new Invoice();
                    invoice.setInvoiceCode(model.getValueAt(selectedRowIndex, 1).toString());
                    invoice.setCustomerCode(model.getValueAt(selectedRowIndex, 3).toString());
                    invoice.setProductCode(model.getValueAt(selectedRowIndex, 2).toString());
                    invoice.setEmployeeCode(model.getValueAt(selectedRowIndex, 7).toString());
                    invoice.setDateBuy((Date) model.getValueAt(selectedRowIndex, 8));

                    invoice.setPrice(Double.parseDouble(model.getValueAt(selectedRowIndex, 4).toString()));
                    invoice.setQuantity(Integer.parseInt(model.getValueAt(selectedRowIndex, 5).toString()));

                    invoice.setDiscountCode(model.getValueAt(selectedRowIndex, 6).toString());

                    InvoiceJFrame frame = new InvoiceJFrame(invoice);
                    frame.setTitle("Thông tin hóa đơn");
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                }

                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = jTable.rowAtPoint(e.getPoint());
                    int column = jTable.columnAtPoint(e.getPoint());
                    if (row >= 0 && column >= 0) {
                        Object codeObject = jTable.getValueAt(row, 1);
                        if (codeObject != null) {
                            String code = codeObject.toString();
                            System.out.println(code);
                            int option = JOptionPane.showConfirmDialog(jTable, "Bạn có muốn xóa hóa đơn này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                invoiceService.delete(code);
                                setDataToTable();
                            }
                        }
                    }
                }
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (e.getClickCount() == 1 && jTable.getSelectedRow() != -1) {
                        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                        int selectedRowIndex = jTable.getSelectedRow();
                        selectedRowIndex = jTable.convertRowIndexToModel(selectedRowIndex);
                        InvoiceDetail invoiceDetail = new InvoiceDetail();

                        String customerCode = model.getValueAt(selectedRowIndex, 2).toString();
                        double price = Double.parseDouble(model.getValueAt(selectedRowIndex, 4).toString());
                        int quantity = Integer.parseInt(model.getValueAt(selectedRowIndex, 5).toString());
                        String discountCode = model.getValueAt(selectedRowIndex, 6).toString();
                        Date dateBuy = (Date) model.getValueAt(selectedRowIndex, 8);
                        float discountAmount = discountService.getDiscountAmountByCode(discountCode);
                        double total = calculateTotalInvoice(price, quantity, discountAmount);

                        invoiceDetail.setInvoiceCode(model.getValueAt(selectedRowIndex, 1).toString());
                        invoiceDetail.setCustomerCode(customerCode);
                        invoiceDetail.setCutomerName(customerService.getCustomerNameByCode(customerCode));
                        invoiceDetail.setProductName(productService.getProductNameByCode(model.getValueAt(selectedRowIndex, 3).toString()));
                        invoiceDetail.setEmployeeName(employeeService.getEmployeeNameByCode(model.getValueAt(selectedRowIndex, 7).toString()));
                        invoiceDetail.setPrice(price);
                        invoiceDetail.setQuantity(quantity);
                        invoiceDetail.setDateBuy(dateBuy);
                        invoiceDetail.setDiscountCode(discountCode);
                        invoiceDetail.setTotal(total);
                        new InvoiceDetailJFrame(invoiceDetail).setVisible(true);

                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

        });

        jTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        jTable.getTableHeader().setPreferredSize(new Dimension(70, 40));
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
                new InvoiceJFrame(new Invoice()).setVisible(true);
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
        btnReload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setDataToTable();
            }

        });
        btnExportExcel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                List<Invoice> invoiceList = invoiceService.getList();
                ExcelExporter.exportToExcel(invoiceList, "D:/Files/invoices.xlsx");

            }
            
        });
    }

    public double calculateTotalInvoice(double price, int quantity, float discountAmount) {

        double totalBeforeDiscount = price * quantity;
        double discount = totalBeforeDiscount * (discountAmount / 100.0);
        double totalAfterDiscount = totalBeforeDiscount - discount;
        return Math.round(totalAfterDiscount * 100.0) / 100.0;

    }
}
