package com.myproject.shoesstore.controller;

import com.myproject.shoesstore.model.ExcelExportable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Tadaboh;
 */
public class ExcelExporter {

    

        public static void exportToExcel(List<? extends ExcelExportable> dataList, String filePath) {
            try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            //format row
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            headerStyle.setAlignment(HorizontalAlignment.CENTER); 

            // format data
            CellStyle dataCellStyle = workbook.createCellStyle();
            dataCellStyle.setAlignment(HorizontalAlignment.CENTER); 

            // format date
            DataFormat format = workbook.createDataFormat();
            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(format.getFormat("dd/MM/yyyy")); 
            dateCellStyle.setAlignment(HorizontalAlignment.CENTER); 

            // export list from object
            int rowNum = 0;
            String[] columnHeaders = dataList.get(0).getColumnHeaders();
            Row headerRow = sheet.createRow(rowNum++);
            for (int i = 0; i < columnHeaders.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnHeaders[i]);
                cell.setCellStyle(headerStyle); 
            }

            for (ExcelExportable obj : dataList) {
                Row row = sheet.createRow(rowNum++);
                Object[] rowData = obj.toExcelRow();
                for (int i = 0; i < rowData.length; i++) {
                    Cell cell = row.createCell(i);
                    if (rowData[i] instanceof String) {
                        cell.setCellValue((String) rowData[i]);
                        cell.setCellStyle(dataCellStyle); 
                    } else if (rowData[i] instanceof Double) {
                        cell.setCellValue((Double) rowData[i]);
                        cell.setCellStyle(dataCellStyle);
                    } else if (rowData[i] instanceof Date) {
                        cell.setCellValue((Date) rowData[i]);
                        cell.setCellStyle(dateCellStyle); 
                    }  else if (rowData[i] instanceof Float) {
                        cell.setCellValue((Float) rowData[i]);
                        cell.setCellStyle(dataCellStyle); 
                    }else if (rowData[i] instanceof Integer) {
                        cell.setCellValue((Integer) rowData[i]);
                        cell.setCellStyle(dataCellStyle); 
                    }else if (rowData[i] instanceof Boolean) {
                        cell.setCellValue((Boolean) rowData[i] ? "Nam" : "Nữ");
                        cell.setCellStyle(dataCellStyle); 
                    } else if (rowData[i] instanceof byte[]){
                        String value = new String((byte[]) rowData[i]);
                        cell.setCellValue(value);
                    }
                }
            }

            // adjust width row 
            for (int i = 0; i < columnHeaders.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // export excel to file
            try (FileOutputStream outputStream = new FileOutputStream(new File(filePath))) {
                workbook.write(outputStream);
            }
            JOptionPane.showMessageDialog(null, "Xuất excel thành công ! Và mở tại đường dẫn " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Xuất excel thất bại");
        }
    }


    }

