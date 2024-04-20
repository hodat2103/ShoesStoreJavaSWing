package com.myproject.shoesstore.controller;

import static com.myproject.shoesstore.controller.ExcelExporter.exportToExcel;
import com.myproject.shoesstore.model.Customer;
import com.myproject.shoesstore.model.Discount;
import com.myproject.shoesstore.service.DiscountService;
import com.myproject.shoesstore.service.impl.DiscountServiceImpl;
import com.myproject.shoesstore.util.TableModelUtilDiscount;
import com.myproject.shoesstore.view.JFrame.DiscountJFrame;
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
public class DiscountManagementController {
    private final JPanel jpnView;
    private final JButton btnAdd;
    private final JLabel btnReload;
    private final JLabel btnExportExcel;
    private final JTextField jtfSearch;
    private TableModelUtilDiscount tableModelUtil = null;
    private final DiscountService discountService;
    
   
    private final String[] listColumn = {"STT","Mã khuyến mãi","Tiêu đề","Ngày bắt đầu","Ngày kết thúc","Mức giảm giá (%)","Ghi chú"};
    
    private TableRowSorter<TableModel> rowSorter = null;
    public DiscountManagementController(JPanel jpnView, JButton btnAdd, JLabel btnReload, JLabel btnExportExcel, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnReload = btnReload;
        this.btnExportExcel = btnExportExcel;
        this.jtfSearch = jtfSearch;
        
        this.tableModelUtil = new TableModelUtilDiscount();
        this.discountService = new DiscountServiceImpl();
    }
    
    public void setDataToTable(){
        List<Discount> listItem = discountService.getList();
        DefaultTableModel model = new TableModelUtilDiscount().setTableDiscount(listItem, listColumn);
        
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
                    
                    Discount discount = new Discount();
                    discount.setDiscountCode(model.getValueAt(selectedRowIndex, 1).toString());
                    discount.setTitle(model.getValueAt(selectedRowIndex, 2).toString());
                    discount.setDateStart((Date) model.getValueAt(selectedRowIndex, 3));
                    discount.setDateEnd((Date) model.getValueAt(selectedRowIndex, 4));
                    discount.setDiscountAmount(Float.parseFloat(model.getValueAt(selectedRowIndex, 5).toString()));
                    discount.setDescript(model.getValueAt(selectedRowIndex, 6).toString());
                 
                    
                    DiscountJFrame frame = new DiscountJFrame(discount);
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
                                discountService.delete(code);
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
        btnAdd.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                new DiscountJFrame (new Discount()).setVisible(true);
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
                List<Discount> discountList = discountService.getList();
                ExcelExporter.exportToExcel(discountList, "D:/Files/discounts.xlsx");

            }
            
        });
    }
}
