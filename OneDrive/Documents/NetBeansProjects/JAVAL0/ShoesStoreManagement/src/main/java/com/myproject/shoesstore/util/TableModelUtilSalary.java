package com.myproject.shoesstore.util;

import com.myproject.shoesstore.model.Salary;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tadaboh;
 */
public class TableModelUtilSalary {
     public DefaultTableModel setTableSalary(List<Salary> listItem, String[] listColumn){
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
                Salary salary = listItem.get(i);
                obj = new Object[columns];
                
               obj[0] = (i+1);
               obj[1] = salary.getSalaryCode();
               obj[2] = salary.getEmplpoyeeCode();
               obj[3] = salary.getEmployeeName();
               obj[4] = salary.getEffectiveDate();
               obj[5] = salary.getBasicSalary();
               obj[6] = salary.getSubsidy();
               obj[7] = salary.getBonuses();
               obj[8] = salary.getForfeit();
               obj[9] = salary.getNetSalary();
               obj[10] = salary.getDescript();
               
               dtm.addRow(obj);
            }
        }
        
        return dtm;
    }
}
