package ca.in.test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ca.in.test.utilites.DriverFactory;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;


@Features("Selenium WebDriver Interview Test")
@Stories("First story")
@Listeners(TestListener.class)

public class FullTest {

	private MainPage mainPage;
	private MortgagesPage morPage;
	private MortgagePaymentCalculatorPage morCalcPage;
	private  WebDriver driver;
	

	@BeforeClass(alwaysRun = true)
	public void setup() {
		
		initDriver();
		mainPage = PageFactory.initElements(driver, MainPage.class);
		morPage = new MortgagesPage(driver);
		morCalcPage = new MortgagePaymentCalculatorPage(driver);
	}
	
	@Step ("Initialise web driver")
	private void initDriver() {
		this.driver = DriverFactory.getDriver(DriverFactory.getBrowserTypeByPropertyFile());
	}

	@Test(priority = 10, groups = { "p1" })
	public void loadFirstPage() throws InterruptedException {
		mainPage.load();

		Assert.assertEquals(mainPage.getCurrentTitle(), mainPage.title);
	}

	@Test(priority = 20,  groups = { "p2", "individualPage" })
	public void openIndividualsPage() {
		String expectedUrl = mainPage.url + "individuals";
		String expectedTitle = mainPage.title;
		
		mainPage.openIndividualsPage();
		
		String actualUrl = mainPage.getCurrentUrl();
		String actualTitle = mainPage.getCurrentTitle();

		Assert.assertEquals(actualUrl, expectedUrl, "Incorrect URL:");
		Assert.assertEquals(actualTitle,expectedTitle, "Incorrect Title:");
	}
	
	@Step ("Set the site language")
	public void setLanguage() throws InterruptedException {
		mainPage.setLanguage();
	}

	/*
	 *  Check that "LOANS" is clicked
	 */
	@Test(priority = 40, groups = { "p2", "individualPage" })

	public void checkLoansLinkClicked() {
		mainPage.clickLoansLink();
		String actualLinkName = mainPage.getText();
		String expectedLinkName = mainPage.field_Loans;
		Assert.assertEquals(actualLinkName, expectedLinkName, "Incorrect Link:");
	}

	/*
	 *  Check that " Mortgages" link is clicked
	 */
	@Test(priority = 50, groups = { "p3", "mortgagePage" }, dependsOnMethods = "checkLoansLinkClicked")

	public void checkMortgagesLinkClicked() throws InterruptedException {
		morPage.clickMortgagesLink();
		String expectedTitle = morPage.title;
		String actualTitle = morPage.getCurrentTitle();

		Assert.assertEquals(actualTitle, expectedTitle, "Incorrect Title:");
	}

	/*
	 *  Check that "Calculate Your Payments" button is clicked
	 */
	
	@Test(priority = 60, groups = { "p4", "mortgagePageLoad" }, dependsOnMethods = "checkMortgagesLinkClicked")

	public void checkCalculateYourPaymentsButtonClicked() {
		morCalcPage.clickCalculateYourPaymentsButton();
		String expectedTitle = morCalcPage.mortgagePaymentCalculatorPageTitle;
		String actualTitle = morCalcPage.getCurrentTitle();
		
		Assert.assertEquals(actualTitle, expectedTitle, "Incorrect Title:");
	}

	/*
	 * Verify that Purchase Price Slider moved to the right
	 * Validate that the Purchase Price Slider movement works
	 */
	
	@Test(priority = 70, groups = { "p4", "mortgagePageLoad" }, dependsOnMethods = "checkCalculateYourPaymentsButtonClicked")
	public void checkPurchasePriceSliderWork() throws InterruptedException {
		int firstElementPosition = morCalcPage.purchasePriceSliderInitialElementPosition();
		int nextElementPosition = morCalcPage.nextElementPosition();
		boolean compareElementPosition = (firstElementPosition <= nextElementPosition);

		Assert.assertTrue(compareElementPosition, "Slider is not moved to the right:");
		Assert.assertNotEquals(firstElementPosition, nextElementPosition, "Slider do not work:");
		
	}
	
	@Test(priority = 80, groups = { "p4", "mortgagePageLoad" }, dependsOnMethods = "checkPurchasePriceSliderWork")
	public void checkClearSliderPositionUsingPurchasePriceFieldVariantFirst() throws InterruptedException {
		morCalcPage.clearSliderPosition();
		int SliderZeroPosition = morCalcPage.SliderZeroPosition();
		int SliderDefaultPosition = morCalcPage.SliderDefaultPosition();
		int dimensionSize = (SliderZeroPosition-SliderDefaultPosition);
//		System.out.println("zeroElementPosition=" + SliderZeroPosition);
//		System.out.println("sliderDefaultElementPosition=" + SliderDefaultPosition);
//		System.out.println("dimensionSize=" + dimensionSize);
		Assert.assertEquals((SliderZeroPosition-dimensionSize), SliderDefaultPosition, "Slider position do not cleared:");
		
	}
	
	
	@Test(priority = 81, groups = { "p4", "mortgagePageLoad" }, dependsOnMethods = "checkClearSliderPositionUsingPurchasePriceFieldVariantFirst")
	public void	checkClearSliderPositionUsingPurchasePriceFieldVariantSecond() throws IOException {
		morCalcPage.sliderTrackHigh();
		String actualSliderTrackHighElementAttribute = morCalcPage.sliderTrackHigh();
		String expectedSliderTrackHighElementAttribute = MortgagePaymentCalculatorPage.expectedPurchasePriceSliderElementEndPositionAttributeValueByPropertyFile();
		//System.out.println("actualSliderTrackHighElementAttribute =" + actualSliderTrackHighElementAttribute);
		Assert.assertEquals(actualSliderTrackHighElementAttribute, expectedSliderTrackHighElementAttribute, "Incorrect slider position:");
	}
	
	
	/*
	 * Verify that Purchase Price can be changed to 500 000 using the + button of the slider
	 */
	
	@Test(priority = 82, groups = { "p4",
			"mortgagePageLoad" }, dependsOnMethods = "checkClearSliderPositionUsingPurchasePriceFieldVariantSecond")

	public void checkPurchasePriceSliderChangePositionWithSliderPlusButton() throws Exception {
		int expectedPriceValue = MortgagePaymentCalculatorPage.expectedPurchasePriceFieldValueByPropertyFile();
		int input = MortgagePaymentCalculatorPage.expectedPurchasePriceFieldValueByPropertyFile();
		morCalcPage.setPurchasePriceSliderValueTo(input);
		int actualPriceValue = morCalcPage.getPurchasePriceSliderValue();

		Assert.assertEquals(actualPriceValue, expectedPriceValue,
				"Price is not "+ expectedPriceValue +" when + button of the slider is used");
	}
	

	@Test(priority = 90, groups = { "p4", "mortgagePageLoad" }, dependsOnMethods = "checkPurchasePriceSliderChangePositionWithSliderPlusButton")
	public void checkDownPaymentSliderChangePosition() throws InterruptedException {
		morCalcPage.downPaymentSliderChangePosition();
	}

	@Test(priority = 100, groups = { "p4", "mortgagePageLoad" }, dependsOnMethods = "checkDownPaymentSliderChangePosition")
	public void checkAmortissementDropdown() throws InterruptedException {
		morCalcPage.checkAmortissementDropdown();
	}

	@Test(priority = 110, groups = { "p4", "mortgagePageLoad" }, dependsOnMethods = "checkAmortissementDropdown")
	public void checkFrequenceVersementDropdown() throws InterruptedException {
		morCalcPage.checkFrequenceVersementDropdown();
	}

	@Test(priority = 120, groups = { "p4", "mortgagePageLoad" }, dependsOnMethods = "checkFrequenceVersementDropdown")
	public void checkTauxInteretField() throws InterruptedException {
		morCalcPage.checkTauxInteretField();
	}

	@Test(priority = 130, groups = { "p4", "mortgagePageLoad" }, dependsOnMethods = "checkTauxInteretField")
	public void checkbtnCalculerButton() throws InterruptedException {
		morCalcPage.checkbtnCalculerButton();
	}

	@Test(priority = 140, groups = { "p4", "mortgagePageLoad" }, dependsOnMethods = "checkbtnCalculerButton")
	public void checkPaymentResultats() throws InterruptedException {
		morCalcPage.checkPaymentResultats();
	}
	
	@AfterClass(alwaysRun = true)
	public void teardown() {
		//mainPage.driver.close();
		if (driver != null) {
		      driver.quit();
	}
	}
	

}
