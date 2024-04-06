package com.myproject.shoesstore.util;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Tadaboh;
 */
public class ImageRenderer extends DefaultTableCellRenderer {
   @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Kiểm tra xem giá trị của ô có phải là JLabel không
        if (value instanceof JLabel) {
            return (JLabel) value;
        } else {
            // Kiểm tra xem giá trị của ô có phải là ImageIcon không
            if (value instanceof ImageIcon) {
                label.setIcon((ImageIcon) value);
                label.setText("");
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
            }
        }
        return label;
    }
}
