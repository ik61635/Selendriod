package com.selendriod.demo.driver_setup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import com.selendriod.demo.logger.Log;
import com.selendriod.demo.util.CommonUtils;

import io.selendroid.standalone.SelendroidConfiguration;
import io.selendroid.standalone.SelendroidLauncher;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

/**
 * contains all the methods to create a new session and destroy the session
 * after the test(s) execution is over. Each test extends this class.
 */
public class CreateSession {
	private int Status;
	public WebDriver driver = null;
	Properties configFile;
	protected File file = new File("");
	Properties configProp = new Properties();
	String OS;
	public MobileEvent event;
	public SelendroidLauncher selendroidServer;


	 @BeforeSuite
	public void invokeServer() throws Exception {
		try {
			startSelendriodServer();
			Log.info("Selendroid server started successfully");
		} catch (Exception e) {
			Log.logError(getClass().getName(), "startSelendroid", "Unable to start Selendroid server");
			throw new Exception(e.getMessage());
		}
	}


	 @AfterSuite
	public void stopAppium() throws Exception {
		try {
			stopSelendriodServer();
			Log.info("Selendriod server stopped successfully");

		} catch (Exception e) {
			Log.logError(getClass().getName(), "StopSelendriod", "Unable to stop Selendriod server");
			throw new Exception(e.getMessage());
		}
	}
	
	 
	@BeforeMethod
	public void createDriver(String os, Method methodName) throws Exception {
		File propertiesFile = new File(file.getAbsoluteFile() + "//src//main//resources//log4j.properties");
		PropertyConfigurator.configure(propertiesFile.toString());
		Log.info("--------------------------------------");
			event = new MobileEvent();
			driver = event.getDriver();
			Log.info("Android driver created");
	}

	
	@AfterMethod
	public void teardown(ITestResult result) {
		Status = result.getStatus();
		String className = this.getClass().getName();
		CommonUtils.takeScreenshot(className, driver, Status);
		Log.info("Shutting down driver");
		driver.quit();
	}

	public void startSelendriodServer() throws ExecuteException, IOException, InterruptedException {
		String emulatorName = CommonUtils.getEmulatorName();
		String path = CommonUtils.getBasePath();
		Runtime.getRuntime().exec(
				"cmd /c start cmd.exe /K \"cd " + path + " && emulator @"+emulatorName+"\"");
		SelendroidConfiguration config = new SelendroidConfiguration();
		config.addSupportedApp(CommonUtils.getAndroidAppPath());
		SelendroidLauncher selendroidServer = new SelendroidLauncher(config);
		selendroidServer.launchSelendroid();
	}


	public void stopSelendriodServer() throws ExecuteException, IOException {
		selendroidServer.stopSelendroid();
		

	}

}