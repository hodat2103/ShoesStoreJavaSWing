package com.myproject.shoesstore.util;

import com.myproject.shoesstore.model.Discount;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tadaboh;
 */
public class TableModelUtilDiscount {
    public DefaultTableModel setTableDiscount(List<Discount> listItem, String[] listColumn){
        DefaultTableModel dtm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;        
            }
//            @Override
//            public Class<?> getColumnClass(int columnIndex) {
//                return columnIndex == 6 ? Boolean.class : String.class;
//            }
            
        };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        if(rows > 0){
            for (int i = 0; i < rows; i++) {
                Discount discount = listItem.get(i);
                obj = new Object[columns];
                
               obj[0] = (i+1);
               obj[1] = discount.getDiscountCode();
               obj[2] = discount.getTitle();
               obj[3] = discount.getDateStart();
               obj[4] = discount.getDateEnd();
               obj[5] = discount.getDiscountAmount();
               obj[6] = discount.getDescript();
               
               dtm.addRow(obj);
            }
        }
        
        return dtm;
    }
}
