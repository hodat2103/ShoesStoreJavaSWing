package com.myproject.shoesstore.util;

import com.myproject.shoesstore.model.Product;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tadaboh;
 */
public class TableModelUtilProduct {
    public DefaultTableModel setTableProduct(List<Product> listItem, String[] listColumn){
        DefaultTableModel dtm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;        
            }

            
        };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        if(rows > 0){
            for (int i = 0; i < rows; i++) {
                Product product = listItem.get(i);
                obj = new Object[columns];
                
                obj[0] = (i + 1);
                obj[1] = product.getProductCode();
                obj[2] = product.getProductName();
                ImageIcon imageIcon = new ImageIcon(product.getImage());

                obj[3] = setSizeImage(imageIcon);

                obj[4] = product.getPrice();
                obj[5] = product.getQuantity();
                obj[6] = product.getSupplierCode();
                obj[7] = product.getDescript();

                dtm.addRow(obj);
            }
        }
        
        return dtm;
    }

    public ImageIcon setSizeImage(ImageIcon imageIcon) {
        ImageIcon icon = imageIcon;

        Image image = icon.getImage();

        Image newImage = image.getScaledInstance(90, 90, Image.SCALE_SMOOTH);

        ImageIcon newIcon = new ImageIcon(newImage);
        
        return newIcon;
    }
}
