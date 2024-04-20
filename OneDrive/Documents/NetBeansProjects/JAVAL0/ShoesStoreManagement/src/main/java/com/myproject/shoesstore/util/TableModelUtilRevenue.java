package com.myproject.shoesstore.util;

import com.myproject.shoesstore.model.Payment;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tadaboh;
 */
public class TableModelUtilRevenue {
    public DefaultTableModel setTableRevenue(List<Payment> listItem, String[] listColumn){
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
                Payment payment = listItem.get(i);
                obj = new Object[columns];
                
               obj[0] = (i+1);
               obj[1] = payment.getInvoiceCode();
               obj[2] = payment.getDatePay();
               obj[3] = payment.getTotal();
               
               dtm.addRow(obj);
            }
        }
        
        return dtm;
    }
}
