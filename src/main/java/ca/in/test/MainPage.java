package ca.in.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.allure.annotations.Step;

public class MainPage extends BasePage {

	protected String url = "http://ia.ca/";
	protected String title = "iA | Industrial Alliance | Insurance";
	protected String field_Loans = "LOANS";
	// protected String linkMortgages = "//a[contains(.,'Mortgages')]";
	// protected String changeLanguageButton = "#topLangMenuItem";
	// protected String loansLinkLocation = "//span[contains(.,'Loans')]";

	@FindBy(css = "#topLangMenuItem")
	protected WebElement changeLanguageButton;
	@FindBy(xpath = "//span[contains(.,'Loans')]")
	protected WebElement loansLinkLocation;

	// WebDriver driver;
	// WebDriverWait wait;

	public MainPage(WebDriver driver) {
		super(driver);
	}

	@Step("Load Main Page")
	public void load() throws InterruptedException {
		driver.get(url);
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleIs(title));

	}

	@Step
	public MainPage openIndividualsPage() {
		String individualsUrl = url + "individuals";
		driver.get(individualsUrl);
		wait.until(ExpectedConditions.titleContains(driver.getTitle()));
		return this;
	}

	@Step
	public void setLanguage() throws InterruptedException {
		checkLanguageElement(changeLanguageButton, "EN");

	}

	public void clickLoansLink() {
		clickElementWait(loansLinkLocation);
	}

	public String getText() {
		String loansButtonActualtext = loansLinkLocation.getText();
		return loansButtonActualtext;

	}

}