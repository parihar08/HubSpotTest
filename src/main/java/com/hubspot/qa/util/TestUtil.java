package com.hubspot.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.hubspot.qa.base.BasePage;

public class TestUtil extends BasePage{
	
	public static Workbook book;
	public static Sheet sheet;
	public static String fileDirectory;
	public static String fileName;

	public static String TESTDATA_SHEET_PATH = "/Users/Parihar08/Documents/workspace/HubspotTest"
			+ "/src/main/java/com/hubspot/qa/testdata/HubSpotAppTestData.xlsx";

	public static void shortWait() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void mediumWait() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void longWait() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static Object[][] getTestData(String sheetName) {

		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} 
		catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);

		Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		System.out.println("Total Row Count::: "+sheet.getLastRowNum());
		System.out.println("Total Column Count::: "+sheet.getRow(0).getLastCellNum());
	
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}

		return data;
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		//File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
		Date dateobj = new Date();
		//System.out.println(df.format(dateobj));
		//FileUtils.copyFile(srcFile,new File(currentDir + "/screenshots/"+System.currentTimeMillis()+".png"));
		fileDirectory = currentDir + "/screenshots/"+df.format(dateobj)+".png";
		FileUtils.copyFile(srcFile,new File(fileDirectory));
		fileName = df.format(dateobj)+".png";
	}
}
