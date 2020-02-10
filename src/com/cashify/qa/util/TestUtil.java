package com.cashify.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cashify.qa.Base.TestBase;

public class TestUtil extends TestBase {
	public XSSFWorkbook wb;
	public XSSFSheet sheet1;
	public FileInputStream fis;
	String data;
	
	
	public String readExcel(int rownum, int column) {
		try {
		File src=new File("C:\\Users\\Suresh Das\\eclipse-workspace\\CheckoutProcess\\Input\\InputData.xlsx");
		fis= new FileInputStream(src);
		wb=new XSSFWorkbook(fis);
		sheet1=wb.getSheet("Customer");
		data= sheet1.getRow(rownum).getCell(column-1).getStringCellValue();
		
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
		
	}
	
}
