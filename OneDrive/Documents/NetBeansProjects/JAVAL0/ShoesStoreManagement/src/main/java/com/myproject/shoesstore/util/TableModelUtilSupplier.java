package com.myproject.shoesstore.util;

import com.myproject.shoesstore.model.Customer;
import com.myproject.shoesstore.model.Supplier;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tadaboh;
 */
public class TableModelUtilSupplier {
    public DefaultTableModel setTableSupplier(List<Supplier> listItem, String[] listColumn){
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
                Supplier supplier = listItem.get(i);
                obj = new Object[columns];
                
               obj[0] = (i+1);
               obj[1] = supplier.getSupplierCode();
               obj[2] = supplier.getSupplierName();
               obj[3] = supplier.getTradeName();
               obj[4] = supplier.getAddress();
               obj[5] = supplier.getPhone();
               obj[6] = supplier.getEmail();
               
               dtm.addRow(obj);
            }
        }
        
        return dtm;
    }
}
