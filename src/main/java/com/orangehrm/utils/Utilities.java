package com.orangehrm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	
	public static final int IMPLICITLY_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=10;
	static FileInputStream fin;
	static XSSFWorkbook workbook;
	
	
	public static Object[][] readDataFromExcelFile(String filePath,String sheetName)
	{
		File file=new File(filePath);
		try {
			fin=new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			workbook=new XSSFWorkbook(fin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XSSFSheet sheet=workbook.getSheet(sheetName);
		
		int rowNum=sheet.getLastRowNum();
		int colNum=sheet.getRow(0).getLastCellNum();
		
		System.out.println("rowNum: " + rowNum + " colNum: " + colNum);
		
		Object[][] data=new Object[rowNum][colNum];
		
		for(int i = 0; i < rowNum; i++)
		{
			
			
			XSSFRow currentRow=sheet.getRow(i+1);
			
			for(int j = 0; j < colNum; j++)
			{

				System.out.println("i: " + i + " j: " + j);
				
				XSSFCell cell = currentRow.getCell(j);
				
				if(cell.getCellType() == CellType.STRING)
				{
					data[i][j]=cell.getStringCellValue();
//					System.out.println(data[i-1][j]);
				}
				
				if(cell.getCellType() == CellType.NUMERIC)
				{
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
//					System.out.println(data[i-1][j]);
				}
			}
			
		}
		
		return data;
		
	}
	
	public static String takeScreenshot(WebDriver driver,String testName)
	{
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotFile=System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + testName + "screenshot.png";
		try { 
			FileHandler.copy(file, new File(destinationScreenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationScreenshotFile;
	}
}
