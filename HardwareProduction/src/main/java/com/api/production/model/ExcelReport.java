package com.api.production.model;

import com.api.production.wrapper.HardwareWrapper;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class ExcelReport {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    List<HardwareWrapper> hardwareWrapperList;


    public ExcelReport(List<HardwareWrapper> hardwareWrappers) {
        this.hardwareWrapperList = hardwareWrappers;
        workbook = new XSSFWorkbook();
    }

    void writeHeaderLine() {
        sheet = workbook.createSheet("hardware");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "ID", style);
        createCell(row, 1, "Name", style);
        createCell(row, 2, "Unit cost", style);
        createCell(row, 3, "Type", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if(value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if(value instanceof Double) {
            cell.setCellValue((Double) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount  = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for(HardwareWrapper hardware: hardwareWrapperList) {
            Row row = sheet.createRow(rowCount++);
            int colCount = 0;
            createCell(row, colCount++, hardware.getId(), style);
            createCell(row, colCount++, hardware.getName(), style);
            createCell(row, colCount++, hardware.getUnit_cost(), style);
            createCell(row, colCount++, hardware.getCategory(), style);
        }
    }
    public void exoort(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
