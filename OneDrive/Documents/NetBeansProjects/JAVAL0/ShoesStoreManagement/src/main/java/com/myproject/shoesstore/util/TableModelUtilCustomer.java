package com.myproject.shoesstore.util;

import com.myproject.shoesstore.model.Customer;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tadaboh;
 */
public class TableModelUtilCustomer {
    public DefaultTableModel setTableCustomer(List<Customer> listItem, String[] listColumn){
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
                Customer customer = listItem.get(i);
                obj = new Object[columns];
                
               obj[0] = (i+1);
               obj[1] = customer.getCustomerCode();
               obj[2] = customer.getName();
               obj[3] = customer.getDate();
               obj[4] = customer.isGender() == true ? "Nam" : "Nữ";
               obj[5] = customer.getPhone();
               obj[6] = customer.getAddress();
               
               dtm.addRow(obj);
            }
        }
        
        return dtm;
    }
}
