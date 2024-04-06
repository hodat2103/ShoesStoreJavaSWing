package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.bean.BeanCategory;
import com.myproject.shoesstore.view.CustomerJPanel;
import com.myproject.shoesstore.view.DiscountJPanel;
import com.myproject.shoesstore.view.EmployeeJPanel;
import com.myproject.shoesstore.view.HomePageJPanel;
import com.myproject.shoesstore.view.InvoiceJPanel;
import com.myproject.shoesstore.view.Login;
import com.myproject.shoesstore.view.MainJFrame;
import com.myproject.shoesstore.view.OrderJPanel;
import com.myproject.shoesstore.view.PaymentJPanel;
import com.myproject.shoesstore.view.ProductJPanel;
import com.myproject.shoesstore.view.RevenueJPanel;
import com.myproject.shoesstore.view.SalaryJPanel;
import com.myproject.shoesstore.view.StatisticsJPanel;
import com.myproject.shoesstore.view.SupplierJPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Tadaboh;
 */
public class RedirectController {
    private JPanel root;
    private String categorySelected = "";
    
    List<BeanCategory> listItem = null;
    

    public RedirectController(JPanel jpnRoot) {
        this.root = jpnRoot;
    }
    
    public void setView(JPanel jpn, JLabel jlb){
        categorySelected = "HomePage";
        jpn.setBackground(new Color(205,255,205));
        jlb.setBackground(new Color(205,255,205));
        
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new HomePageJPanel());
        root.validate();
        root.repaint();
        
    }
    
    public void setEvent(List<BeanCategory> listItem){
        this.listItem = listItem;
        for (BeanCategory item : listItem) {
            item.getjLabel().addMouseListener(new LabelEvent(item.getCategory(),item.getjPanel(),item.getjLabel()));
        }
    }
    
    class LabelEvent implements MouseListener{
        private JPanel node;
        
        private String category;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String category, JPanel jpnItem, JLabel jlbItem) {
            this.category = category;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
        
        
        
        @Override
        public void mouseClicked(MouseEvent e) {
            switch (category) {
                case "HomePage":
                    node = new HomePageJPanel();
                    break;
                case "Product":
                    node = new ProductJPanel();
                    break;
                case "Customer":
                    node = new CustomerJPanel();
                    break;
                case "Supplier":
                    node = new SupplierJPanel();
                    break;
                case "Employee":
                    node = new EmployeeJPanel();
                    break;
                case "Discount":
                    node = new DiscountJPanel();
                    break;
                case "Salary":
                    node = new SalaryJPanel();
                    break;
                case "Invoice":
                    node = new InvoiceJPanel();
                    break;
                case "Payment":
                    node = new PaymentJPanel();
                    break;
                case "Revenue":
                    node = new RevenueJPanel();
                    break;
                case "Statistics":
                    node = new StatisticsJPanel();
                    break;
                case "Logout":
                    node.setVisible(false);

                    MainJFrame mainFrame = MainJFrame.getInstance();
                    mainFrame.setVisible(false);
                    new Login().setVisible(true);
                    break;
                default:
                    node = new HomePageJPanel();
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(category);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            categorySelected = category;
            jpnItem.setBackground(new Color(55,205,205));
            jlbItem.setBackground(new Color(55,205,205));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(55,205,205));
            jlbItem.setBackground(new Color(55,205,205));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(!categorySelected.equalsIgnoreCase(category)){
                jpnItem.setBackground(new Color(0,204,153));
                jlbItem.setBackground(new Color(0,204,153));
                
            }
        }
        
    }
    
    private void setChangeBackground(String category){
        for (BeanCategory item : listItem) {
            if(item.getCategory().equalsIgnoreCase(category)){
                item.getjPanel().setBackground(new Color(55,205,205));
                item.getjLabel().setBackground(new Color(55,205,205));
            }else{
                item.getjPanel().setBackground(new Color(0,204,153));
                item.getjLabel().setBackground(new Color(0,204,153));
            }
        }
    }
}
