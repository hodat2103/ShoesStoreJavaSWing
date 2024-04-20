package com.myproject.shoesstore.view;

import com.myproject.shoesstore.bean.BeanCategory;
import com.myproject.shoesstore.controller.RedirectController;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author Tadaboh;
 */
public class MainJFrame extends javax.swing.JFrame {
    private static MainJFrame instance;
    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
        setTitle("QUẢN LÝ CỬA HÀNG BÁN GIÀY");
        //setExtendedState(MainJFrame.MAXIMIZED_BOTH);
        RedirectController redirectController =  new RedirectController(jpnView);
        redirectController.setView(jpnHomePage, jlbHomePage);
        
        List<BeanCategory> listItem = new ArrayList<>();
        
        listItem.add(new BeanCategory("HomePage", jpnHomePage, jlbHomePage));
        listItem.add(new BeanCategory("Product", jpnProduct, jlbProduct));
        listItem.add(new BeanCategory("Customer", jpnCustomer, jlbCustomer));
        listItem.add(new BeanCategory("Supplier", jpnSupplier, jlbSupplier));
        listItem.add(new BeanCategory("Employee", jpnEmployee, jlbEmployee));
        listItem.add(new BeanCategory("Discount", jpnDiscount, jlbDiscount));
        listItem.add(new BeanCategory("Invoice", jpnInvoice, jlbInvoice));
        listItem.add(new BeanCategory("Salary", jpnSalary, jlbSalary));
        listItem.add(new BeanCategory("Payment", jpnPayment, jlbPayment));
        listItem.add(new BeanCategory("Revenue", jpnRevenue, jlbRevenue));
        listItem.add(new BeanCategory("Statistics", jpnStatistics, jlbStatistics));
        listItem.add(new BeanCategory("Logout", jpnLogOut, jlbLogOut));
         
        
        
        redirectController.setEvent(listItem);
    }
    public static MainJFrame getInstance() {
        if (instance == null) {
            instance = new MainJFrame();
        }
        return instance;
    }
    public static void closeFrame() {
        MainJFrame mainFrame = getInstance();
        mainFrame.dispose();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnRoot = new javax.swing.JPanel();
        jpnMenu = new javax.swing.JPanel();
        jpnHomePage = new javax.swing.JPanel();
        jlbHomePage = new javax.swing.JLabel();
        jpnCustomer = new javax.swing.JPanel();
        jlbCustomer = new javax.swing.JLabel();
        jpnSupplier = new javax.swing.JPanel();
        jlbSupplier = new javax.swing.JLabel();
        jpnProduct = new javax.swing.JPanel();
        jlbProduct = new javax.swing.JLabel();
        jpnInvoice = new javax.swing.JPanel();
        jlbInvoice = new javax.swing.JLabel();
        jpnSalary = new javax.swing.JPanel();
        jlbSalary = new javax.swing.JLabel();
        jpnPayment = new javax.swing.JPanel();
        jlbPayment = new javax.swing.JLabel();
        jpnRevenue = new javax.swing.JPanel();
        jlbRevenue = new javax.swing.JLabel();
        jpnStatistics = new javax.swing.JPanel();
        jlbStatistics = new javax.swing.JLabel();
        jpnEmployee = new javax.swing.JPanel();
        jlbEmployee = new javax.swing.JLabel();
        jpnDiscount = new javax.swing.JPanel();
        jlbDiscount = new javax.swing.JLabel();
        jpnLogOut = new javax.swing.JPanel();
        jlbLogOut = new javax.swing.JLabel();
        jpnView = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(0, 0));

        jpnRoot.setPreferredSize(new java.awt.Dimension(1400, 755));

        jpnMenu.setBackground(new java.awt.Color(153, 153, 153));

        jpnHomePage.setBackground(new java.awt.Color(0, 204, 102));

        jlbHomePage.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jlbHomePage.setForeground(new java.awt.Color(255, 255, 255));
        jlbHomePage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/myproject/shoesstore/view/icon/Sneakers.png"))); // NOI18N
        jlbHomePage.setText("     SHOES STORE");
        jlbHomePage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jpnHomePageLayout = new javax.swing.GroupLayout(jpnHomePage);
        jpnHomePage.setLayout(jpnHomePageLayout);
        jpnHomePageLayout.setHorizontalGroup(
            jpnHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHomePageLayout.createSequentialGroup()
                .addComponent(jlbHomePage, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnHomePageLayout.setVerticalGroup(
            jpnHomePageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHomePageLayout.createSequentialGroup()
                .addComponent(jlbHomePage, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jpnCustomer.setBackground(new java.awt.Color(0, 204, 153));
        jpnCustomer.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpnCustomer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlbCustomer.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jlbCustomer.setForeground(new java.awt.Color(255, 255, 255));
        jlbCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/myproject/shoesstore/view/icon/Customer.png"))); // NOI18N
        jlbCustomer.setText("Quản lý khách hàng");

        javax.swing.GroupLayout jpnCustomerLayout = new javax.swing.GroupLayout(jpnCustomer);
        jpnCustomer.setLayout(jpnCustomerLayout);
        jpnCustomerLayout.setHorizontalGroup(
            jpnCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCustomerLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jlbCustomer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnCustomerLayout.setVerticalGroup(
            jpnCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        jpnSupplier.setBackground(new java.awt.Color(0, 204, 153));
        jpnSupplier.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpnSupplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlbSupplier.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jlbSupplier.setForeground(new java.awt.Color(255, 255, 255));
        jlbSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/myproject/shoesstore/view/icon/Supplier.png"))); // NOI18N
        jlbSupplier.setText("Quản lý nhà cung cấp");

        javax.swing.GroupLayout jpnSupplierLayout = new javax.swing.GroupLayout(jpnSupplier);
        jpnSupplier.setLayout(jpnSupplierLayout);
        jpnSupplierLayout.setHorizontalGroup(
            jpnSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSupplierLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jlbSupplier)
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jpnSupplierLayout.setVerticalGroup(
            jpnSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        jpnProduct.setBackground(new java.awt.Color(0, 204, 153));
        jpnProduct.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpnProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlbProduct.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jlbProduct.setForeground(new java.awt.Color(255, 255, 255));
        jlbProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/myproject/shoesstore/view/icon/Trainers.png"))); // NOI18N
        jlbProduct.setText("Quản lý sản phẩm");

        javax.swing.GroupLayout jpnProductLayout = new javax.swing.GroupLayout(jpnProduct);
        jpnProduct.setLayout(jpnProductLayout);
        jpnProductLayout.setHorizontalGroup(
            jpnProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnProductLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jlbProduct)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnProductLayout.setVerticalGroup(
            jpnProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        jpnInvoice.setBackground(new java.awt.Color(0, 204, 153));
        jpnInvoice.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpnInvoice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlbInvoice.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jlbInvoice.setForeground(new java.awt.Color(255, 255, 255));
        jlbInvoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/myproject/shoesstore/view/icon/Invoice.png"))); // NOI18N
        jlbInvoice.setText("Hóa đơn");

        javax.swing.GroupLayout jpnInvoiceLayout = new javax.swing.GroupLayout(jpnInvoice);
        jpnInvoice.setLayout(jpnInvoiceLayout);
        jpnInvoiceLayout.setHorizontalGroup(
            jpnInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnInvoiceLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jlbInvoice)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnInvoiceLayout.setVerticalGroup(
            jpnInvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbInvoice, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );

        jpnSalary.setBackground(new java.awt.Color(0, 204, 153));
        jpnSalary.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpnSalary.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlbSalary.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jlbSalary.setForeground(new java.awt.Color(255, 255, 255));
        jlbSalary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/myproject/shoesstore/view/icon/Salary.png"))); // NOI18N
        jlbSalary.setText("Quản lý lương NV");

        javax.swing.GroupLayout jpnSalaryLayout = new javax.swing.GroupLayout(jpnSalary);
        jpnSalary.setLayout(jpnSalaryLayout);
        jpnSalaryLayout.setHorizontalGroup(
            jpnSalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSalaryLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jlbSalary)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnSalaryLayout.setVerticalGroup(
            jpnSalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbSalary, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        jpnPayment.setBackground(new java.awt.Color(0, 204, 153));
        jpnPayment.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpnPayment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlbPayment.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jlbPayment.setForeground(new java.awt.Color(255, 255, 255));
        jlbPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/myproject/shoesstore/view/icon/Card Payment.png"))); // NOI18N
        jlbPayment.setText("Thanh toán");

        javax.swing.GroupLayout jpnPaymentLayout = new javax.swing.GroupLayout(jpnPayment);
        jpnPayment.setLayout(jpnPaymentLayout);
        jpnPaymentLayout.setHorizontalGroup(
            jpnPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPaymentLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jlbPayment)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jpnPaymentLayout.setVerticalGroup(
            jpnPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbPayment, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jpnRevenue.setBackground(new java.awt.Color(0, 204, 153));
        jpnRevenue.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpnRevenue.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlbRevenue.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jlbRevenue.setForeground(new java.awt.Color(255, 255, 255));
        jlbRevenue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/myproject/shoesstore/view/icon/Sales Performance.png"))); // NOI18N
        jlbRevenue.setText("Doanh thu");

        javax.swing.GroupLayout jpnRevenueLayout = new javax.swing.GroupLayout(jpnRevenue);
        jpnRevenue.setLayout(jpnRevenueLayout);
        jpnRevenueLayout.setHorizontalGroup(
            jpnRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRevenueLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jlbRevenue)
                .addContainerGap(160, Short.MAX_VALUE))
        );
        jpnRevenueLayout.setVerticalGroup(
            jpnRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbRevenue, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jpnStatistics.setBackground(new java.awt.Color(0, 204, 153));
        jpnStatistics.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpnStatistics.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlbStatistics.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jlbStatistics.setForeground(new java.awt.Color(255, 255, 255));
        jlbStatistics.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/myproject/shoesstore/view/icon/Combo Chart.png"))); // NOI18N
        jlbStatistics.setText("Thống kê");

        javax.swing.GroupLayout jpnStatisticsLayout = new javax.swing.GroupLayout(jpnStatistics);
        jpnStatistics.setLayout(jpnStatisticsLayout);
        jpnStatisticsLayout.setHorizontalGroup(
            jpnStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnStatisticsLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jlbStatistics)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        jpnStatisticsLayout.setVerticalGroup(
            jpnStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbStatistics, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );

        jpnEmployee.setBackground(new java.awt.Color(0, 204, 153));
        jpnEmployee.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpnEmployee.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlbEmployee.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jlbEmployee.setForeground(new java.awt.Color(255, 255, 255));
        jlbEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/myproject/shoesstore/view/icon/Employee.png"))); // NOI18N
        jlbEmployee.setText("Quản lý nhân viên");

        javax.swing.GroupLayout jpnEmployeeLayout = new javax.swing.GroupLayout(jpnEmployee);
        jpnEmployee.setLayout(jpnEmployeeLayout);
        jpnEmployeeLayout.setHorizontalGroup(
            jpnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnEmployeeLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jlbEmployee)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnEmployeeLayout.setVerticalGroup(
            jpnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
        );

        jpnDiscount.setBackground(new java.awt.Color(0, 204, 153));
        jpnDiscount.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpnDiscount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlbDiscount.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jlbDiscount.setForeground(new java.awt.Color(255, 255, 255));
        jlbDiscount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/myproject/shoesstore/view/icon/Discount.png"))); // NOI18N
        jlbDiscount.setText("Khuyến mãi");

        javax.swing.GroupLayout jpnDiscountLayout = new javax.swing.GroupLayout(jpnDiscount);
        jpnDiscount.setLayout(jpnDiscountLayout);
        jpnDiscountLayout.setHorizontalGroup(
            jpnDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDiscountLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jlbDiscount)
                .addContainerGap(152, Short.MAX_VALUE))
        );
        jpnDiscountLayout.setVerticalGroup(
            jpnDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jpnLogOut.setBackground(new java.awt.Color(255, 51, 51));
        jpnLogOut.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jpnLogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlbLogOut.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jlbLogOut.setForeground(new java.awt.Color(255, 255, 255));
        jlbLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/myproject/shoesstore/view/icon/Logout.png"))); // NOI18N
        jlbLogOut.setText("Đăng xuất");

        javax.swing.GroupLayout jpnLogOutLayout = new javax.swing.GroupLayout(jpnLogOut);
        jpnLogOut.setLayout(jpnLogOutLayout);
        jpnLogOutLayout.setHorizontalGroup(
            jpnLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnLogOutLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jlbLogOut)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnLogOutLayout.setVerticalGroup(
            jpnLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnMenuLayout = new javax.swing.GroupLayout(jpnMenu);
        jpnMenu.setLayout(jpnMenuLayout);
        jpnMenuLayout.setHorizontalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addGroup(jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnMenuLayout.createSequentialGroup()
                        .addGroup(jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpnHomePage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpnMenuLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jpnCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jpnSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jpnInvoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jpnPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jpnRevenue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jpnEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jpnProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jpnStatistics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jpnSalary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpnMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpnLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnMenuLayout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addComponent(jpnDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpnMenuLayout.setVerticalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addComponent(jpnHomePage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnPayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jpnView.setPreferredSize(new java.awt.Dimension(1050, 0));

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1050, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 743, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnRootLayout = new javax.swing.GroupLayout(jpnRoot);
        jpnRoot.setLayout(jpnRootLayout);
        jpnRootLayout.setHorizontalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpnView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 44, Short.MAX_VALUE))
        );
        jpnRootLayout.setVerticalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jpnRoot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jpnRoot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlbCustomer;
    private javax.swing.JLabel jlbDiscount;
    private javax.swing.JLabel jlbEmployee;
    private javax.swing.JLabel jlbHomePage;
    private javax.swing.JLabel jlbInvoice;
    private javax.swing.JLabel jlbLogOut;
    private javax.swing.JLabel jlbPayment;
    private javax.swing.JLabel jlbProduct;
    private javax.swing.JLabel jlbRevenue;
    private javax.swing.JLabel jlbSalary;
    private javax.swing.JLabel jlbStatistics;
    private javax.swing.JLabel jlbStatistics1;
    private javax.swing.JLabel jlbSupplier;
    private javax.swing.JPanel jpnCustomer;
    private javax.swing.JPanel jpnDiscount;
    private javax.swing.JPanel jpnEmployee;
    private javax.swing.JPanel jpnHomePage;
    private javax.swing.JPanel jpnInvoice;
    private javax.swing.JPanel jpnLogOut;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnPayment;
    private javax.swing.JPanel jpnProduct;
    private javax.swing.JPanel jpnRevenue;
    private javax.swing.JPanel jpnRoot;
    private javax.swing.JPanel jpnSalary;
    private javax.swing.JPanel jpnStatistics;
    private javax.swing.JPanel jpnStatistics1;
    private javax.swing.JPanel jpnSupplier;
    private javax.swing.JPanel jpnView;
    // End of variables declaration//GEN-END:variables
}
