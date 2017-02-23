package ca.in.test;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {
	public String PAGE_URL;
	public String PAGE_TITLE;
	public WebDriver driver;
	public WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void loadPage() {

	}

	public void clickElement(WebElement element) {
		element.click();
		// element = driver.findElement(By.xpath(PAGE_TITLE));
	}
	
//	public void clickUntilActualValueGreaterExpected(WebElement element, WebElement element1, Integer actualValue,
//			Integer expectedValue) {
//
//		if (actualValue > expectedValue) {
//			element.click();
//		}
//	}
//
//	public void clickUntilActualValueLessExpected(WebElement element, WebElement element1, Integer actualValue,
//			Integer expectedValue) {
//
//		if (actualValue < expectedValue) {
//			element.click();
//		}
//	}
	
public void clickPlusUntil(WebElement element, /*WebElement element1,*/ Integer actualValue, Integer expectedValue) {
		
		if (actualValue < expectedValue) {
			element.click();
//		} else
//		if (actualValue < expectedValue) {
//			element1.click();
		}
	}

public void clickMinusUntil(WebElement element, /*WebElement element1,*/ Integer actualValue, Integer expectedValue) {
	
	if (actualValue > expectedValue) {
		element.click();
//	} else
//	if (actualValue < expectedValue) {
//		element1.click();
	}
}

	public void setElementText(WebElement element,String text) {
		
		element.sendKeys();
		Assert.assertEquals(element.getAttribute("value"), text);
	}
	
	public void clearElement(WebElement element) {
		element.clear();
	}

	public void clickElementWait(WebElement element) {
		wait = new WebDriverWait(driver, 20);
		clickElement(wait.until(ExpectedConditions.visibilityOf(element)));
	}

	public void moveSlider(WebElement element, Integer mortgagePageSliderMoveElementPositionCoefficient) {
		// WebElement slider =
		// driver.findElement(By.xpath(purchasePriceSliderLocation));
		int width = element.getSize().getWidth();
		// Using Action Class
		Actions move = new Actions(driver);
		// How to know max slider value ???
		Action action = move.dragAndDropBy(element, (width * mortgagePageSliderMoveElementPositionCoefficient), 0)
				.build();
		// move.dragAndDrop(slider, target)
		action.perform();

	}

	public int getElementPositionX(WebElement element) {
		//
		Point point = element.getLocation();
		int xcord = point.getX();
		//System.out.println("Element's Position from left side Is " + xcord + " pixels.");
		return xcord;
		
	}
	public int getElementPositionY(WebElement element) {
		//
		Point point = element.getLocation();
		int ycord = point.getY();
		//System.out.println("Element's Position from top side Is " + ycord + " pixels.");
		return ycord;
		
	}
	
	public String getElementAttribute(WebElement element, String name) {
		return element.getAttribute(name);
	}
	
	// j variable Number of steps for slider. Could have values (1-4) 
	public void moveSliderPlusButton(WebElement element, Integer j) {
		for (int i = 0; i < j; i++) {
			element.click();
		}
	}

	public void checkLanguageElement(WebElement element, String expectedResultText) {
		wait = new WebDriverWait(driver, 5);
		String actualResultText = element.getText();
		//System.out.println("CHECK_Actual=" + actualResultText);
		//System.out.println("CHECK_Expected=" + expectedResultText);
		if (actualResultText.equals(expectedResultText)) {
			clickElement(wait.until(ExpectedConditions.visibilityOf(element)));
		}
	}

	public void printElementText(WebElement element) {
		String actualResultText = element.getText();
		System.out.println("CHECK_Actual=" + actualResultText);
	}

	public String getCurrentUrl() {
		return this.driver.getCurrentUrl();
	}

	public String getCurrentTitle() {
		return this.driver.getTitle();
	}
	


}
