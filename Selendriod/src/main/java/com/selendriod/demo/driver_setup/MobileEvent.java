package com.selendriod.demo.driver_setup;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.selendriod.demo.logger.Log;
import com.selendriod.demo.util.CommonUtils;

import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;

public class MobileEvent {
	private WebDriver driverObject = null;
	private int explicitWait = 40;
	private Actions actions = null;
	private WebDriverWait ExplicitwaitObject;
	private JavascriptExecutor ScriptExecuterObject = null;
	private int implicitWait = 60;
	private int fluentWait = 60;

	public void setImplicitWait(int ImplicitWaitByUser) {
		this.driverObject.manage().timeouts().implicitlyWait(ImplicitWaitByUser, TimeUnit.SECONDS);
	}

	public MobileEvent() throws Exception {
			this.driverObject = initializeAndReturnDriver();
			getScriptExecuterObject();
			getActionsInstance(this.driverObject);
			getExplicitWaitInstance();
	}

	public WebDriver getDriver() throws Exception {

		return driverObject;
	}

	private WebDriver initializeAndReturnDriver() throws Exception {
		SelendroidCapabilities caps = new SelendroidCapabilities(CommonUtils.getAppName());
		driverObject = new SelendroidDriver(caps);
		driverObject.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
		return this.driverObject;
	}

	private Actions getActionsInstance(WebDriver driver) throws Exception {
		if (actions == null) {
			actions = new Actions(this.driverObject);
		}
		return this.actions;
	}

	public Actions getActionsObject() throws Exception {
		return this.actions;
	}

	private WebDriverWait getExplicitWaitInstance() throws Exception {
		if (ExplicitwaitObject == null) {
			ExplicitwaitObject = new WebDriverWait(this.driverObject, this.explicitWait);
		}
		return ExplicitwaitObject;
	}

	private JavascriptExecutor getScriptExecuterObject() throws Exception {
		if (ScriptExecuterObject == null) {
			ScriptExecuterObject = (JavascriptExecutor) this.driverObject;
		}
		return ScriptExecuterObject;
	}

	public JavascriptExecutor getJSExecuter() throws Exception {
		return this.ScriptExecuterObject;
	}

	public String getElement(String LogicalIdentifierOfElement) throws Exception {

		String[] ElementIdentifier = LogicalIdentifierOfElement.split("__");
		return ElementIdentifier[1];

	}

	public String getElementIdentifier(String LogicalIdentifierOfElement) throws Exception {

		String[] ElementIdentifier = LogicalIdentifierOfElement.split("__");
		return ElementIdentifier[0];

	}

	public WebElement findElement(String ElementIdentifier) throws Exception {
		WebElement ElementToReturn = null;
		if (ElementIdentifier.isEmpty()) {
			System.out.println("WARNING||" + driverObject.getCurrentUrl() + "||"
					+ "ELEMENT IDENTIFIER IS NOT DEFINED ON" + driverObject.getTitle());
			return ElementToReturn;
		}
		else {
			ElementIdentifier = ElementIdentifier.trim();
			String Identifier;
			String[] ElementIdentiferAfterSplit = ElementIdentifier.split("__");
			String FindElementWith = ElementIdentiferAfterSplit[0];
			int identifierSize = ElementIdentiferAfterSplit.length;
			if (identifierSize == 2) {
				Identifier = ElementIdentiferAfterSplit[1];
			} else {
				String Buffer = ElementIdentiferAfterSplit[0];
				Identifier = Buffer;
				FindElementWith = "xpath";
			}

			if (FindElementWith.equalsIgnoreCase("xpath")) {
				this.findWebElementFluently(By.xpath(Identifier));
				this.ExplicitwaitObject.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Identifier)));
				return this.driverObject.findElement(By.xpath(Identifier));

			}

			if (FindElementWith.equalsIgnoreCase("id")) {
				this.findWebElementFluently(By.id(Identifier));
				this.ExplicitwaitObject.until(ExpectedConditions.presenceOfElementLocated(By.id(Identifier)));
				return this.driverObject.findElement(By.id(Identifier));

			}

			if (FindElementWith.equalsIgnoreCase("name")) {
				this.findWebElementFluently(By.name(Identifier));
				// explicit wait to
				this.ExplicitwaitObject.until(ExpectedConditions.presenceOfElementLocated(By.name(Identifier)));
				return this.driverObject.findElement(By.name(Identifier));

			}
			if (FindElementWith.equalsIgnoreCase("css")) {
				this.findWebElementFluently(By.cssSelector(Identifier));
				this.ExplicitwaitObject.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(Identifier)));
				return this.driverObject.findElement(By.cssSelector(Identifier));
			}

			if (FindElementWith.equalsIgnoreCase("classname")) {
				this.findWebElementFluently(By.className(Identifier));
				this.ExplicitwaitObject.until(ExpectedConditions.presenceOfElementLocated(By.className(Identifier)));
				return this.driverObject.findElement(By.className(Identifier));
			}
			if (FindElementWith.equalsIgnoreCase("linktext")) {
				this.findWebElementFluently(By.linkText(Identifier));
				this.ExplicitwaitObject.until(ExpectedConditions.presenceOfElementLocated(By.linkText(Identifier)));
				return this.driverObject.findElement(By.linkText(Identifier));

			}

			if (FindElementWith.equalsIgnoreCase("partiallinktext")) {
				this.findWebElementFluently(By.partialLinkText(Identifier));
				this.ExplicitwaitObject
						.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(Identifier)));
				return this.driverObject.findElement(By.partialLinkText(Identifier));
			}

		}
		return ElementToReturn;

	}


	public void clickSimple(String ElementIdentifier) throws Exception {
		findElement(ElementIdentifier).click();
	}

	public void submit(String ElementIdentifier) throws Exception {
		findElement(ElementIdentifier).submit();
	}

	public void waitForElementToBeClickAble(String ElementIdentifier) throws Exception {
		WebDriverWait wait = new WebDriverWait(this.driverObject, 120);
		wait.until(ExpectedConditions.elementToBeClickable(findElement(ElementIdentifier)));

	}

	
	public void clickByActionClass(String ElementIdentifier) throws Exception {
		getActionsInstance(this.driverObject).moveToElement(findElement(ElementIdentifier)).click().perform();
	}

	
	public String getTextOfWebElement(String ElementIdentifier) throws Exception {
		WebElement elementToGetTextFrom = this.findElement(ElementIdentifier);
		String elementText = elementToGetTextFrom.getText();
		return elementText;
	}

	public boolean checkElementPresentOrNot(String ElementIdentifier) throws Exception {
		try {
			findElement(ElementIdentifier);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public void setByJS(String ElementIdentifier, String inputText) throws Exception {
		this.ScriptExecuterObject.executeScript("arguments[0].setAttribute('value', arguments[1])",
				findElement(ElementIdentifier), inputText);

	}

	public List<WebElement> findElements(String ElementIdentifier) throws Exception {
		String expression = this.getElementIdentifier(ElementIdentifier);
		List<WebElement> Elements = null;
		switch (expression) {
		case "xpath":
			Elements = this.driverObject.findElements(By.xpath(this.getElement(ElementIdentifier)));
			break;
		case "id":
			Elements = this.driverObject.findElements(By.id(this.getElement(ElementIdentifier)));
			break;

		}
		return Elements;

	}

	public void clickDoupbleClick(String ElementIdentifier) throws Exception {
		// highlightElement(findElement(ElementIdentifier,"click"));
		getActionsInstance(this.driverObject).doubleClick(findElement(ElementIdentifier)).perform();

	}

	public void inputText(String ElementIdentifier, String inputText) throws Exception {
		findElement(ElementIdentifier).sendKeys(inputText);
	}

	public void inputTextClear(String ElementIdentifier, String inputText) throws Exception {
		findElement(ElementIdentifier).clear();
		findElement(ElementIdentifier).sendKeys(inputText);
	}

	public boolean verifyDisplayedElement(String ElementIdentifier) throws Exception {
		return findElement(ElementIdentifier).isDisplayed();
	}

	public void executeJS(String JavaScript) throws Exception {
		this.ScriptExecuterObject.executeScript(JavaScript);
	}

	public void setExplicitWait(int i) throws Exception {
		if (i >= 60) {

		}
		this.explicitWait = i;
	}

	public int getExplicitWait() throws Exception {
		return this.explicitWait;
	}

	public void selectdropdown(String ElementIdentifier, String dropdownVisisbleText) throws Exception {
		Select selectObject = new Select(findElement(ElementIdentifier));
		selectObject.selectByVisibleText(dropdownVisisbleText);
	}

	public void selectdropdownByValue(String ElementIdentifier, String dropdownValue) throws Exception {
		Select selectObject = new Select(findElement(ElementIdentifier));
		selectObject.selectByValue(dropdownValue);
	}

	public void selectdropdownByIndex(String ElementIdentifier, int index) throws Exception {
		Select selectObject = new Select(findElement(ElementIdentifier));
		selectObject.selectByIndex(index);
	}

	public void mouseHover(String ElementIdentifier) throws Exception {
		this.getActionsObject().moveToElement(this.findElement(ElementIdentifier)).perform();

	}

	public void switchToWindow_By_PageContent(String contentOfThePage) throws Exception {
		Set<String> allWindows = this.driverObject.getWindowHandles();
		if (!allWindows.isEmpty()) {
			for (String windowId : allWindows) {
				this.driverObject.switchTo().window(windowId);
				if (this.driverObject.getPageSource().contains(contentOfThePage)) {
					System.out.println("Moved to child window");
					break;

				}
			}
		}

	}

	public WebElement findWebElementFluently(final By selector) throws Exception {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(this.driverObject)
				.withTimeout(fluentWait, TimeUnit.SECONDS).pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(Exception.class);
		WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(selector);
			}
		});
		return webElement;
	}

	public void switchToAnotherWindow(String windowID) throws InterruptedException {
		Log.info("Driver control switching to another window.");
		driverObject.switchTo().window(windowID);
		Thread.sleep(5000);
	}

	public void scrollIntoView(String ObjectLogicalName) {
		String[] ArrObjLogicalName = ObjectLogicalName.split("__");
		String locator = ArrObjLogicalName[0];
		String identifier = ArrObjLogicalName[1];
		if (locator.toLowerCase().trim().contains("xpath")) {
			WebElement getCoverageAmtList = this.driverObject.findElement(By.xpath(identifier));
			((JavascriptExecutor) this.driverObject).executeScript("arguments[0].scrollIntoView()", getCoverageAmtList);
		} else {
			WebElement getCoverageAmtList = this.driverObject.findElement(By.id(identifier));
			((JavascriptExecutor) this.driverObject).executeScript("arguments[0].scrollIntoView()", getCoverageAmtList);
		}

	}
}
