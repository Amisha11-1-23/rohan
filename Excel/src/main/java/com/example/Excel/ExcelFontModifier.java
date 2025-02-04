package com.example.Excel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFontModifier {

    public static void main(String[] args) {
        String inputFilePath = "C:\\Spring_casestudy\\rohan\\input.xlsx"; // Path to input Excel file
        String outputFilePath = "C:\\Spring_casestudy\\rohan\\output.xlsx"; // Path to save modified Excel file
        
        System.out.println("Starting...");
        try (FileInputStream fis = new FileInputStream(new File(inputFilePath));
                Workbook workbook = new XSSFWorkbook(fis)) {

               Sheet sheet = workbook.getSheetAt(0); // Work on the first sheet

               // Create CellStyle for Arial Bold, size 8
               CellStyle boldStyle = workbook.createCellStyle();
               Font boldFont = workbook.createFont();
               boldFont.setFontName("Arial");
               boldFont.setBold(true);
               boldFont.setFontHeightInPoints((short) 8);
               boldStyle.setFont(boldFont);

               // Create CellStyle for Arial, size 8
               CellStyle normalStyle = workbook.createCellStyle();
               Font normalFont = workbook.createFont();
               normalFont.setFontName("Arial");
               normalFont.setFontHeightInPoints((short) 8);
               normalStyle.setFont(normalFont);

               // Iterate through rows
               for (Row row : sheet) {
                   for (Cell cell : row) {
                       if (row.getRowNum() == 0) { // First row
                           cell.setCellStyle(boldStyle);
                       } else { // Other rows
                           cell.setCellStyle(normalStyle);
                       }
                   }
               }

               // Save the modified Excel file
               try (FileOutputStream fos = new FileOutputStream(new File(outputFilePath))) {
                   workbook.write(fos);
               }

               System.out.println("Excel file modified successfully: " + outputFilePath);

           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
 