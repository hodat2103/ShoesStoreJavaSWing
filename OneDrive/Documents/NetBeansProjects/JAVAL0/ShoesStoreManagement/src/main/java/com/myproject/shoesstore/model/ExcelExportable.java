package com.myproject.shoesstore.model;

/**
 *
 * @author Tadaboh;
 */
public interface ExcelExportable {
    Object[] toExcelRow();
    String[] getColumnHeaders();

}
