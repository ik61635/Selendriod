package com.selendriod.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.selendriod.demo.driver_setup.MobileEvent;
import com.selendriod.demo.logger.Log;
import com.selendriod.demo.objectrespository.Locators;

public class Home {
	private WebDriver driver;
	private MobileEvent event;

	public Home(MobileEvent b, WebDriver d) {
		this.event = b;
		this.driver = d;
	}
	public void verifyDisplayTextView(String expectedText) throws Exception {
		event.clickSimple(Locators.Home.visibleBtn);
		String actualText = event
				.getTextOfWebElement(Locators.Home.visibleTxt);
		Assert.assertEquals(actualText,expectedText);
		Log.info("Passed");
	}
	
	public void verifyDisplayfocusedText(String expectedText) throws Exception {
		event.clickSimple(Locators.Home.topLevelElementBtn);
		String actualText = event
				.getTextOfWebElement(Locators.Home.focusedTxt);
		Assert.assertEquals(actualText,expectedText);
		Log.info("Passed");
	}
}
