package com.selendriod.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class CommonUtils {
	private static Properties properties;
	
	static {
		properties = new Properties();
		InputStream in = CommonUtils.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getAndroidAppPath() {

		return properties.getProperty("AndroidAppPath");
	}
	
	public static String getAppName() {

		return properties.getProperty("AppName");
	}
	
	public static String getEmulatorName() {

		return properties.getProperty("emulatorName");
	}
	
	public static String getBasePath() {

		return properties.getProperty("BasePath");
	}
	
	public static String getExcelPath() {

		return properties.getProperty("ExcelPath");
	}
	
	public static Map getData() {
		HashMap<String, String> HMPObj = new HashMap();
		try {
			FileInputStream file = new FileInputStream(new File(getExcelPath()));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow FstRowObj = sheet.getRow(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int i=0;
				while (cellIterator.hasNext()) {
					String ColmName = FstRowObj.getCell(i).getStringCellValue();
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						HMPObj.put(ColmName, String.valueOf(cell.getNumericCellValue()));
						break;
					case Cell.CELL_TYPE_STRING:
						HMPObj.put(ColmName, cell.getStringCellValue());
						break;
					}
					i++;
				}
			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HMPObj;
	}
	
	public static void takeScreenshot(String ClsName, WebDriver driver, int status)
    {
			if ((status == 1)) {
			} else if ((status == 2)) {
				try {

					File scrFile3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					Calendar currentDate = Calendar.getInstance();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
					String dateN = formatter.format(currentDate.getTime()).replace("/", "_");
					String dateNow = dateN.replace(":", "_");
					FileUtils.copyFile(scrFile3, new File(ClsName + "_" + dateNow + ".png"));
				} catch (Exception e) {
					System.out.println("---------unable to take screenshot due to exception--------------");
				}
			} else {

			}
		}

    
}
