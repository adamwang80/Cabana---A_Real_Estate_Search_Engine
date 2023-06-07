package com.aic.cabana.spring.files.excel.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.aic.cabana.spring.files.excel.model.Cabana;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelHelper {
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = { "Id", "ScrapperOrder", "Link", "ApartmentName", "Address", "RentStudio", "Rent1BD", "Rent2BDS", "Rent3BDS", "Rent4BDS", "Rent5BDS", "Images"};
  static String SHEET = "Cabana";

  public static boolean hasExcelFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }
    return true;
  }

  public static ByteArrayInputStream tutorialsToExcel(List<Cabana> cabanas) {

    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
      Sheet sheet = workbook.createSheet(SHEET);

      // Header
      Row headerRow = sheet.createRow(0);

      for (int col = 0; col < HEADERs.length; col++) {
        Cell cell = headerRow.createCell(col);
        cell.setCellValue(HEADERs[col]);
      }

      int rowIdx = 1;
      for (Cabana cabana : cabanas) {
        Row row = sheet.createRow(rowIdx++);
        row.createCell(0).setCellValue(cabana.getId());
        row.createCell(1).setCellValue(cabana.getScrapperOrder());
        row.createCell(2).setCellValue(cabana.getLink());
        row.createCell(3).setCellValue(cabana.getApartmentName());
        row.createCell(4).setCellValue(cabana.getAddress());
        row.createCell(5).setCellValue(cabana.getRentStudio());
        row.createCell(6).setCellValue(cabana.getRent1bd());
        row.createCell(7).setCellValue(cabana.getRent2bds());
        row.createCell(8).setCellValue(cabana.getRent3bds());
        row.createCell(9).setCellValue(cabana.getRent4bds());
        row.createCell(10).setCellValue(cabana.getRent5bds());
        row.createCell(11).setCellValue(cabana.getImages());
      }
      workbook.write(out);
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
    }
  }

  public static List<Cabana> excelToTutorials(InputStream is) {
    try {
      /*Workbook workbook = new XSSFWorkbook(is);
      Sheet sheet = workbook.getSheet(SHEET);*/

      Workbook workbook = WorkbookFactory.create(is);
      Sheet sheet = workbook.getSheetAt(0);
      Iterator<Row> rows = sheet.iterator();
      List<Cabana> cabanas = new ArrayList<Cabana>();

      int rowNumber = 0;
      while (rows.hasNext()) {
        Row currentRow = rows.next();

        // skip header
        if (rowNumber == 0) {
          rowNumber++;
          continue;
        }

        Iterator<Cell> cellsInRow = currentRow.iterator();
        Cabana cabana = new Cabana();

        int cellIdx = 0;
        while (cellsInRow.hasNext()) {
          Cell currentCell = cellsInRow.next();

          switch (cellIdx) {
          case 0:
            cabana.setId((long) currentCell.getNumericCellValue());
            break;

          case 1:
            cabana.setScrapperOrder(currentCell.getStringCellValue());
            break;

          case 2:
            cabana.setLink(currentCell.getStringCellValue());
            break;

          case 3:
            cabana.setApartmentName(currentCell.getStringCellValue());
            break;

          case 4:
            cabana.setAddress(currentCell.getStringCellValue());
            break;

          case 5:
            cabana.setRentStudio((long)currentCell.getNumericCellValue());
            break;

          case 6:
            cabana.setRent1bd((long)currentCell.getNumericCellValue());
            break;

          case 7:
            cabana.setRent2bds((long)currentCell.getNumericCellValue());
            break;

          case 8:
            cabana.setRent3bds((long)currentCell.getNumericCellValue());
            break;

          case 9:
            cabana.setRent4bds((long)currentCell.getNumericCellValue());
            break;

          case 10:
            cabana.setRent5bds((long)currentCell.getNumericCellValue());
            break;

          case 11:
            cabana.setImages(currentCell.getStringCellValue());
            break;

          default:
            break;
          }
          cellIdx++;
        }
        cabanas.add(cabana);
      }
      workbook.close();
      return cabanas;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
    }
  }
}
