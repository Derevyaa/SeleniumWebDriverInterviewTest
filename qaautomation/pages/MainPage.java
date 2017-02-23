package ca.in.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ca.in.data.TestData;

public class MainPage {

	protected String field_SystemSetProperty1 = "webdriver.chrome.driver";
	protected String field_SystemSetProperty2 = "D:\\Java\\Drivers\\chromedriver.exe";
	protected WebDriver driver;
	protected WebDriverWait wait;
	// protected String url = "http://ia.ca/";
	// protected String field_MainPageTitle = "iA | Industrial Alliance |
	// Insurance";
	// protected String field_Loans = "LOANS";
	// protected String field_MortgagesPageTitle = "Mortgage - Mortage rates |
	// iA Financial Group";

	@BeforeClass(alwaysRun = true)
	public void setup() {
		System.setProperty(field_SystemSetProperty1, field_SystemSetProperty2);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 20);
	}

	@AfterClass(alwaysRun = true)
	public void teardown() {
		driver.close();
	}

	@Test(groups = { "p1", "firstPageLoads" }, dataProvider = "pages", dataProviderClass = TestData.class)
	public void loadFirstPage(String url, String title) throws InterruptedException {

		driver.get(url);
		wait.until(ExpectedConditions.titleIs(title));
		Assert.assertEquals(driver.getTitle(), title);
	}

	@Test(groups = { "p2",
			"individualPage" }, dependsOnMethods = "loadFirstPage", dataProvider = "pages", dataProviderClass = TestData.class)
	public void openIndividualsPage(String url, String title) {
		String individualsUrl = url + "individuals";
		driver.get(individualsUrl);
		wait.until(ExpectedConditions.titleContains(driver.getTitle()));
		Assert.assertEquals(driver.getCurrentUrl(), individualsUrl);
		Assert.assertEquals(driver.getTitle(), title);
	}

	@Test(groups = { "p2",
			"individualPage" }, dependsOnMethods = "openIndividualsPage", dataProvider = "loans", dataProviderClass = TestData.class)
	public void clickLoansLink(String field_Loans) {
		By loansButton = By.xpath("//span[contains(.,'Loans')]");
		wait.until(ExpectedConditions.presenceOfElementLocated(loansButton)).click();
		String loansButtonActualtext = driver.findElement(By.xpath("//span[contains(.,'Loans')]")).getText();
		Assert.assertEquals(loansButtonActualtext, field_Loans);
	}

	// @Test(dependsOnMethods = "clickLoansLink")
	// public void clickMortgagesLink() {
	// By mortgages = By.xpath("//a[contains(.,'Mortgages')]");
	// wait.until(ExpectedConditions.presenceOfElementLocated(mortgages)).click();
	// Assert.assertEquals(driver.getTitle(), field_MortgagesPageTitle);
	// }
}
