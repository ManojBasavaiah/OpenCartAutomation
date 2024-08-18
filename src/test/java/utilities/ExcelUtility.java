package utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {
    public static FileInputStream fi;// fileinputstream instance variable
    public static FileOutputStream fo;// fileoutputstream instance variable
    public static XSSFWorkbook wb;//    workbook instance variable
    public static XSSFSheet ws;// sheet instance variable
    public static XSSFRow row;// row instance variable
    public static XSSFCell cell;// cell instance variable
    public static XSSFCellStyle style;// cellstyle instance variable
    String path;
    public ExcelUtility(String path){
        this.path=path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        int rowcount = ws.getLastRowNum();
        wb.close();
        fi.close();
        return rowcount;
    }
    public int getCellCount(String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        row = ws.getRow(rownum);
        int cellcount = row.getLastCellNum();
        wb.close();
        fi.close();
        return cellcount;
    }
    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path); //fileinputstream object
        wb = new XSSFWorkbook(fi); //workbook object
        ws = wb.getSheet(sheetName);  //sheet object
        row = ws.getRow(rownum); //row object
        cell = row.getCell(colnum); //cell object
        String data; //data variable
        //try catch block is used to handle empty cells or null cells
        try {//to handle exception
           // data = cell.getStringCellValue();
            DataFormatter formatter = new DataFormatter();
            data= formatter.formatCellValue(cell); //to get the formatted value of the cell
        } catch (Exception e) {
            data = "";
        }
        wb.close();
        fi.close();
        return data;
    }

    // below method is used to set the data in the cell in the excel file using row and column numbers
    public static void setCellData(String path, String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlfile=new File(path);
        if(!xlfile.exists()){
            wb=new XSSFWorkbook();
            fo=new FileOutputStream(path);
            wb.write(fo);
        }
        fi = new FileInputStream(path);
        wb=new XSSFWorkbook(fi);
        if(wb.getSheetIndex(sheetName)==-1)//If sheet not exists then create new sheet
            wb.createSheet(sheetName);
        ws=wb.getSheet(sheetName);
        if(ws.getRow(rownum)==null)//if row not exists then create new row
            ws.createRow(rownum);
        row=ws.getRow(rownum);
        cell = row.createCell(colnum);
        cell.setCellValue(data);
        fo = new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }

    public static void fillGreenColor(String path, String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        row = ws.getRow(rownum);
        cell = row.getCell(colnum);
        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        fo = new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }
    public static void fillRedColor(String path, String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(sheetName);
        row = ws.getRow(rownum);
        cell = row.getCell(colnum);
        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        fo = new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }
}
