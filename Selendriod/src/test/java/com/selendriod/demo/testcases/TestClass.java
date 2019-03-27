package com.selendriod.demo.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selendriod.demo.driver_setup.CreateSession;
import com.selendriod.demo.driver_setup.MobileEvent;
import com.selendriod.demo.pages.Home;
import com.selendriod.demo.pages.Registration;
import com.selendriod.demo.pages.Web;


public class TestClass extends CreateSession {
	

	@Test
	public void TestDisplayTextViewFunctionality() throws Exception {
		Home HomePageObj = new Home(event, driver);
		HomePageObj.verifyDisplayTextView("Text is sometimes displayed");
		Reporter.log("Test Case Passed");

	}

	@Test
	public void TestDisplayAndFocusLayoutFunctionality() throws Exception {

		Home HomePageObj = new Home(event, driver);
		HomePageObj.verifyDisplayTextView("Should only be found once");
		Reporter.log("Test Case Passed");


	}

	@Test
	public void TestWebViewFunctionality() throws Exception {

		Web WebPageObj = new Web(event, driver);
		WebPageObj.verifyFormSubmission("John Henry");
		Reporter.log("Test Case Passed");

	}

	@Test
	public void TestRegistrationFunctionality() throws Exception {
		Registration RegistrationPageObj = new Registration(event, driver);
		RegistrationPageObj.verifyRegistration();
		Reporter.log("Test Case Passed");

	}


}
