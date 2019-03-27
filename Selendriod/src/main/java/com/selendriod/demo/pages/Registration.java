package com.selendriod.demo.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.selendriod.demo.driver_setup.MobileEvent;
import com.selendriod.demo.logger.Log;
import com.selendriod.demo.objectrespository.Locators;
import com.selendriod.demo.util.CommonUtils;

public class Registration {

	private WebDriver driver;
	private MobileEvent event;

	public Registration(MobileEvent b, WebDriver d) {
		this.event = b;
		this.driver = d;
	}

	public void verifyRegistration() throws Exception {
		event.clickSimple(Locators.Home.startUserRegistrationBtn);
		event.inputText(Locators.Registration.usernameEdt, CommonUtils.getData().get("USERNAME").toString());
		event.inputText(Locators.Registration.inputEmailEdt, CommonUtils.getData().get("EMAIL").toString());
		event.inputText(Locators.Registration.inputPasswordEdt, CommonUtils.getData().get("PASSWORD").toString());
		event.inputTextClear(Locators.Registration.inputNameEdt, CommonUtils.getData().get("NAME").toString());
		event.clickSimple(Locators.Registration.input_preferedProgrammingLanguageSelectBx);
		event.findElements(Locators.Registration.text1Radio).get(3).click();
		event.clickSimple(Locators.Registration.input_addsChckBx);
		event.clickSimple(Locators.Registration.btnRegisterUserBtn);
		String actualText = event.getTextOfWebElement(Locators.Registration.label_name_dataLbl);
		Assert.assertEquals(actualText, CommonUtils.getData().get("NAME").toString());
		Log.info("Passed");
	}
}
