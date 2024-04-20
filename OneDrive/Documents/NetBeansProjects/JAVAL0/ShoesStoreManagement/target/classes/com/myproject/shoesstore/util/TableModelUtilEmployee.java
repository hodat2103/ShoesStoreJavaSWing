package com.myproject.shoesstore.util;

import com.myproject.shoesstore.model.Employee;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tadaboh;
 */
public class TableModelUtilEmployee {
    public DefaultTableModel setTableEmployee(List<Employee> listItem, String[] listColumn){
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
                Employee employee = listItem.get(i);
                obj = new Object[columns];
                
                obj[0] = (i+1);
                obj[1] = employee.getEmployeeCode();
                obj[2] = employee.getName();
                obj[3] = employee.getDate();
                obj[4] = employee.isGender() == true ? "Nam" : "Nữ";
                obj[5] = employee.getPosition();
                obj[6] = employee.getPhone();
                obj[7] = employee.getAddress();
               
               dtm.addRow(obj);
            }
        }
        
        return dtm;
    }
}
