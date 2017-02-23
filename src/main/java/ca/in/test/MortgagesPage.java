package ca.in.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MortgagesPage extends BasePage {

	// String linkMortgages = "//a[contains(.,'Mortgages')]";
	String title = "Mortgage - Mortage rates | iA Financial Group";
	String linkCalculateYourPaymentsButton = "//a[contains(.,'Calculate your payments')]";

	@FindBy(xpath = "//a[contains(.,'Mortgages')]")	WebElement linkMortgages;

	// WebDriver driver;
	// WebDriverWait wait;

	public MortgagesPage(WebDriver driver) {
		super(driver);
	}

	public MortgagesPage clickMortgagesLink() {
		clickElementWait(linkMortgages);
		return this;

	}

}

