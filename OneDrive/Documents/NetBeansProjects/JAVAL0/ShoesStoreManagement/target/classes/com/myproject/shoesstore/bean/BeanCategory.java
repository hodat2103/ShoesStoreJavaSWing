package com.myproject.shoesstore.bean;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Tadaboh;
 */
public class BeanCategory {
    private String category;
    private JPanel jpn;
    private JLabel jlb;

    public BeanCategory() {
    }

    public BeanCategory(String category, JPanel jpn, JLabel jlb) {
        this.category = category;
        this.jpn = jpn;
        this.jlb = jlb;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public JPanel getjPanel() {
        return jpn;
    }

    public void setjPanel(JPanel jpn) {
        this.jpn = jpn;
    }

    public JLabel getjLabel() {
        return jlb;
    }

    public void setjLabel(JLabel jlb) {
        this.jlb = jlb;
    }
    
}
