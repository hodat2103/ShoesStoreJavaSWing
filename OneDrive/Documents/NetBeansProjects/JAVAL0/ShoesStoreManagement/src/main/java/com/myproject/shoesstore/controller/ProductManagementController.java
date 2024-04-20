package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.model.Product;
import com.myproject.shoesstore.service.ProductService;
import com.myproject.shoesstore.service.impl.ProductServiceImpl;
import com.myproject.shoesstore.util.ImageRenderer;
import com.myproject.shoesstore.util.TableModelUtilProduct;
import com.myproject.shoesstore.view.JFrame.ProductJFrame;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Tadaboh;
 */
public class ProductManagementController {

    private final JPanel jpnView;
    private final JButton btnAdd;
    private final JLabel btnReload;
    private final JLabel btnExportExcel;
    private final JTextField jtfSearch;

    TableModelUtilProduct tableModelUtilProduct = null;
    private final ProductService productSerivce;
    private final  ExcelExporter excelExporter;

    private final String[] listColumn;
    private TableRowSorter<TableModel> rowSorter = null;

    public ProductManagementController(JPanel jpnView, JButton btnAdd, JLabel btnReload,JLabel btnExportExcel, JTextField jtfSearch) {
        this.listColumn = new String[]{"STT", "Mã sản phẩm", "Tên sản phẩm", "Ảnh", "Giá", "Số lượng", "Mã cung cấp", "Mô tả"};
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnReload = btnReload;
        this.btnExportExcel = btnExportExcel;
        this.jtfSearch = jtfSearch;

        this.tableModelUtilProduct = new TableModelUtilProduct();
        this.productSerivce = new ProductServiceImpl();
        this.excelExporter = new ExcelExporter();

    }

    public void setDataToTable() {
        List<Product> listItem = productSerivce.getList();
        DefaultTableModel model = new TableModelUtilProduct().setTableProduct(listItem, listColumn);

        JTable jTable = new JTable(model);
        TableColumnModel columnModel = jTable.getColumnModel();
        columnModel.getColumn(3).setCellRenderer(new ImageRenderer());

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

                    Product product = new Product();
                    product.setProductCode(model.getValueAt(selectedRowIndex, 1).toString());
                    product.setProductName(model.getValueAt(selectedRowIndex, 2).toString());
                    
//                    ImageIcon icon = (ImageIcon) model.getValueAt(selectedRowIndex, 3);
                    byte[] imageData = convertImageIconToByteArray((ImageIcon) model.getValueAt(selectedRowIndex, 3));
                    product.setImage(imageData);
                    product.setPrice(Double.parseDouble(model.getValueAt(selectedRowIndex, 4).toString()));
                    product.setQuantity(Integer.parseInt(model.getValueAt(selectedRowIndex, 5).toString()));
                    product.setSupplierCode(model.getValueAt(selectedRowIndex, 6).toString());
                    product.setDescript(model.getValueAt(selectedRowIndex, 7).toString());

                    ProductJFrame frame = new ProductJFrame(product);
                    frame.setTitle("Thông tin sản phẩm");
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
                            int option = JOptionPane.showConfirmDialog(jTable, "Bạn có muốn xóa sản phẩm này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION) {
                                productSerivce.delete(code);
                                setDataToTable();
                            }
                        }
                    }
                }
            }

        });
        jTable.setRowHeight(100);
        jTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        jTable.getTableHeader().setPreferredSize(new Dimension(60, 40));
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
                new ProductJFrame(new Product()).setVisible(true);
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
                List<Product> productList = productSerivce.getList();
                ExcelExporter.exportToExcel(productList, "D:/Files/products.xlsx");

            }
            
        });
    }
    public static byte[] convertImageIconToByteArray(ImageIcon icon) {
        if (icon == null) {
            return null;
        }

        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", baos);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return baos.toByteArray();
    } 
}
