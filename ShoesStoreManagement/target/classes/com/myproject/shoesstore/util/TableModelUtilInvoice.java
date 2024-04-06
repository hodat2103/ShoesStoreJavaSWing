package com.myproject.shoesstore.util;

import com.myproject.shoesstore.model.Invoice;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tadaboh;
 */
public class TableModelUtilInvoice {
    public DefaultTableModel setTableInvoice(List<Invoice> listItem, String[] listColumn){
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
                Invoice invoice = listItem.get(i);
                obj = new Object[columns];
                
                obj[0] = (i+1);
                obj[1] = invoice.getInvoiceCode();
                obj[2] = invoice.getCustomerCode();
                obj[3] = invoice.getProductCode();
                obj[4] = invoice.getPrice();
                obj[5] = invoice.getQuantity();
                obj[6] = invoice.getDiscountCode();
                obj[7] = invoice.getEmployeeCode();
                obj[8] = invoice.getDateBuy();
               
               dtm.addRow(obj);
            }
        }
        
        return dtm;
    }
}
