package com.fastparking.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ParkingData {
	DataFormatter formatter = new DataFormatter();
	
	@DataProvider(name="parkingData")
	public Object[][] addParkingData() throws IOException {
		String filename = System.getProperty("user.dir")+ "\\Testdata\\TestData.xlsx";
		FileInputStream fis = new FileInputStream(filename);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet  = workbook.getSheetAt(2);
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colCount = row.getLastCellNum();
		
		Object[][]data = new Object[rowCount-1][colCount];
		for(int i=0; i<rowCount-1;i++) {
			row = sheet.getRow(i+1);
			for(int j=0; j<colCount;j++) {
				XSSFCell cell = row.getCell(j);
				data[i][j] = formatter.formatCellValue(cell);
			}
		}
		return data;
	}
	
	
	@DataProvider(name="editParkingData")
	public Object[][] editParkingData() throws IOException {
		String filename = System.getProperty("user.dir")+ "\\Testdata\\TestData.xlsx";
		FileInputStream fis = new FileInputStream(filename);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet  = workbook.getSheetAt(3);
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colCount = row.getLastCellNum();
		
		Object[][]data = new Object[rowCount-1][colCount];
		for(int i=0; i<rowCount-1;i++) {
			row = sheet.getRow(i+1);
			for(int j=0; j<colCount;j++) {
				XSSFCell cell = row.getCell(j);
				data[i][j] = formatter.formatCellValue(cell);
			}
		}
		return data;
	}
	
	
	
	
}
