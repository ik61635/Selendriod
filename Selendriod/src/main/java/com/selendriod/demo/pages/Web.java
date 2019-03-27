package com.selendriod.demo.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.selendriod.demo.driver_setup.MobileEvent;
import com.selendriod.demo.logger.Log;
import com.selendriod.demo.objectrespository.Locators;
import com.selendriod.demo.util.CommonUtils;

public class Web {
	private WebDriver driver;
	private MobileEvent event;

	public Web(MobileEvent b, WebDriver d) {
		this.event = b;
		this.driver = d;
	}

	public void verifyFormSubmission(String expectedText) throws Exception {
		event.clickSimple(Locators.Home.buttonStartWebviewBtn);
		event.switchToAnotherWindow("WEBVIEW");
		event.setByJS(Locators.Web.name_inputEdt, CommonUtils.getData().get("NAME").toString());
		event.clickSimple(Locators.Web.carSelect);
		event.switchToAnotherWindow("NATIVE_APP");
		event.findElements(Locators.Web.text1Radio).get(3).click();
		event.switchToAnotherWindow("WEBVIEW");
		event.submit(Locators.Web.submitBtn);
		String actualText = event.getTextOfWebElement(Locators.Web.titleLable);
		Assert.assertEquals(actualText, expectedText);
		Log.info("Passed");
	}
}
