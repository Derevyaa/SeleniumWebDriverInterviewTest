package ca.in.test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import junitx.util.PropertyManager;

public class MortgagePaymentCalculatorPage extends BasePage {

	

	String mortgagePaymentCalculatorPageTitle = "Mortgage Payment Calculator | iA Financial Group";

	String mainBackgroundLocation = "(//*[@id='main'])";
	// String calculateYourPaymentsButtonLocation = "//a[contains(.,'Calculate
	// your payments')]";
	String purchasePriceSliderLocation = "(//*[@class='slider-handle min-slider-handle custom'])[1]";
	// String purchasePriceFieldLocation = "//*[@id='PrixPropriete']";
	// String purchasePriceSliderPlusButtonLocation =
	// ".//*[@id='PrixProprietePlus']";
	String downPaymentSliderPlusButton = ".//*[@id='MiseDeFondPlus']";
	String amortissementDropdownLocation = "//*[@id='Amortissement']";
	String frequenceVersementDropdownLocation = "//*[@id='FrequenceVersement']";
	String tauxInteretLocation = "//*[@id='TauxInteret']";
	String btnCalculerLocation = "//button[contains(.,'Calculate')]";
	String paymentResultatsLocation = "//*[@id='paiement-resultats']";
	String purchasePriceFieldValueSlider = "500000";
	String monthlyPaymentsValue = "$ 836.75";
	int sliderPrixProprieteExpectedValue = 500000;

	// @FindBy(xpath = "(//*[@id='main'])") WebElement mainBackgroundLocation;
	@FindBy(xpath = "//a[contains(.,'Calculate your payments')]")
	private WebElement calculateYourPaymentsButtonLocation;
	
	@FindBy(xpath = "(//*[@class='slider-handle min-slider-handle custom'])[1]")
	private WebElement purchasePriceSliderLocation1;
	
	@FindBy(xpath = "//*[@id='PrixPropriete']")
	private WebElement purchasePriceTextField;

	@FindBy(xpath = "//*[@id='sliderPrixPropriete']")
	private WebElement purchasePriceGreenBar;

	@FindBy(xpath = ".//*[@id='PrixProprietePlus']")
	private WebElement purchasePriceSliderPlusButton;
	
	@FindBy(xpath = ".//*[@id='PrixProprieteMinus']")
	private WebElement purchasePriceSliderMinusButton;
	
	@FindBy(xpath = "(//*[@class='slider-tick-label'][1]/span)[1]")
	private WebElement purchasePriceSliderZeroPoint;
	
	@FindBy(xpath = "(//*[@class='slider-track-high'])[1]")
	private WebElement purchasePriceSliderLine;
	
//	@FindBy(xpath = ".//*[@id='MiseDeFondPlus']") 
//	private WebElement downPaymentSliderPlusButton;
	
	@FindBy(xpath = ".//*[@id='MiseDeFond']") 
	private WebElement downPaymentTextField;

	
	// @FindBy(xpath = "//*[@id='Amortissement']") 
	// private WebElement amortissementDropdownLocation;
	
	// @FindBy(xpath = "//*[@id='FrequenceVersement']") 
	// private WebElement frequenceVersementDropdownLocation;
	
	// @FindBy(xpath = "//*[@id='TauxInteret']") private WebElement  tauxInteretLocation;
	
	// @FindBy(xpath = "//button[contains(.,'Calculate')]") 
	// private WebElement btnCalculerLocation;
	
	// @FindBy(xpath = "//*[@id='paiement-resultats']") 
	// private WebElement paymentResultatsLocation;

	int mortgagePageSliderMoveElementPositionCoefficient = 100;

	// WebDriver driver;
	// WebDriverWait wait;

	public MortgagePaymentCalculatorPage(WebDriver driver) {
		super(driver);
	}

	public void clickCalculateYourPaymentsButton() {
		clickElementWait(calculateYourPaymentsButtonLocation);

		// try {
		// Assert.assertEquals(driver.getTitle(),
		// mortgagePaymentCalculatorPageTitle);
		// } catch (Exception e) {
		// System.out.println("Title is not " +
		// mortgagePaymentCalculatorPageTitle);
		// }
	}

	public int purchasePriceSliderInitialElementPosition() throws InterruptedException {
		int firstElementPosition = getElementPositionX(purchasePriceSliderLocation1);
		moveSlider(purchasePriceSliderLocation1, 8);
		return firstElementPosition;

	}

	public int nextElementPosition() {
		int nextElementPosition = getElementPositionX(purchasePriceSliderLocation1);
		return nextElementPosition;
	}

	public void clearSliderPosition() throws InterruptedException {
		clearElement(purchasePriceTextField);
		// Sleep for better look
		Thread.sleep(2000);
	}
	
	public int getPurchasePriceSliderPlusButtonStepValue() throws InterruptedException {
		clearSliderPosition();
		clickElement(purchasePriceSliderPlusButton);
		int PurchasePriceSliderPlusButtonStepValue = getPurchasePriceSliderValue();
		return PurchasePriceSliderPlusButtonStepValue;

	}

	// Position of '0' element
	public int SliderZeroPosition() {
		int SliderZeroPoint = getElementPositionX(purchasePriceSliderZeroPoint);
		return SliderZeroPoint;
	}

	public int SliderDefaultPosition() {
		int SliderZeroPoint = getElementPositionX(purchasePriceSliderLocation1);
		return SliderZeroPoint;
	}

	public String sliderTrackHigh() throws IOException{
		String sliderTrackHighElementAttribute = getElementAttribute(purchasePriceSliderLine, "style");
		// System.out.println("sliderTrackHighElementAttribute="+sliderTrackHighElementAttribute);
		return sliderTrackHighElementAttribute;
	}
	
	public int setPurchasePriceSliderValueTo(int input) throws InterruptedException {
		int actualValue = getPurchasePriceSliderValue();
	//	int stepValue = getPurchasePriceSliderPlusButtonStepValue();

		// if (input % stepValue != 0)
		// throw new RuntimeException("Purchase price value is not multiple to
		// step value");

		if (input == actualValue) {
			System.out.println("input=actual" + actualValue);
			return input;
		} else 
			if (input < actualValue) {
				if (input == actualValue) {
					return actualValue;
				}
				else
				while (input < actualValue) {
					clickMinusUntil(purchasePriceSliderMinusButton, actualValue, input);
					System.out.println("input<actual" + actualValue);
					actualValue = getPurchasePriceSliderValue();
					
					if (input > actualValue)
						throw new RuntimeException("Purchase price value couldn`t be set to"+input+ "with - button");
				}
			}	
			if (input > actualValue) {
				if (input == actualValue) {
					return actualValue;
				}
				else
				while (input > actualValue) {
					clickPlusUntil(purchasePriceSliderPlusButton, actualValue, input);
					System.out.println("input>actual" + actualValue);
					actualValue = getPurchasePriceSliderValue();
					
					
					if (input < actualValue)
						throw new RuntimeException("Purchase price value couldn`t be set to"+input+ "with + button");
				}
			}
		

		return input;

	}
	

	public int getPurchasePriceSliderValue() {

		String initValue = purchasePriceTextField.getAttribute("value");
		// System.out.println("initValue= " + initValue);
		int actualValue = Integer.parseInt(initValue);
		return actualValue;
	}

	public static int expectedPurchasePriceFieldValueByPropertyFile() {
		
		String purchasePriceFieldExpectedValueString = PropertyManager.getProperty("purchasePriceFieldExpectedValue");
		int expectedValue = Integer.parseInt(purchasePriceFieldExpectedValueString);
			//	System.out.println("purchasePriceFieldExpectedValue =" + actualValue);
				return expectedValue;
	}
	
public static String expectedPurchasePriceSliderElementEndPositionAttributeValueByPropertyFile() {
		
		String expectedValue = PropertyManager.getProperty("purchasePriceSliderElementEndPositionAttributeValue");
		//int expectedValue = Integer.parseInt(expectedPurchasePriceSliderElementEndPositionAttributeValueByPropertyFile);
			//	System.out.println("purchasePriceFieldExpectedValue =" + actualValue);
				return expectedValue;
	}
	
	public int getDownPaymentrValue() {

		String initValue = downPaymentTextField.getAttribute("value");
		// System.out.println("initValue= " + initValue);
		int actualValue = Integer.parseInt(initValue);
		return actualValue;
	}
	

	public void downPaymentSliderChangePosition() throws InterruptedException {
		for (int i = 0; i < 1; i++) {
			driver.findElement(By.xpath(downPaymentSliderPlusButton)).click();
			Thread.sleep(2000);
		}

	}

	public void checkAmortissementDropdown() throws InterruptedException {
		Select amortissementDropdown = new Select(driver.findElement(By.xpath(amortissementDropdownLocation)));
		amortissementDropdown.selectByVisibleText("15 years");
	}

	public void checkFrequenceVersementDropdown() {
		Select frequenceVersementDropdown = new Select(
				driver.findElement(By.xpath(frequenceVersementDropdownLocation)));
		frequenceVersementDropdown.selectByVisibleText("weekly");
	}

	public void checkTauxInteretField() {
		WebElement tauxInteret = driver.findElement(By.xpath(tauxInteretLocation));
		tauxInteret.clear();
		// TauxInteret.sendKeys("\b");
		tauxInteret.sendKeys("5.0");
	}

	public void checkbtnCalculerButton() {
		WebElement btnCalculer = driver.findElement(By.xpath(btnCalculerLocation));
		// Do not work with one click
		btnCalculer.click();
		btnCalculer.click();
	}

	public String checkPaymentResultats() {
		By paymentResultats = By.xpath(paymentResultatsLocation);
		WebElement paymentResultats1 = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentResultats));
		String paymentResultatsValue = paymentResultats1.getText();
		try {
			Assert.assertEquals(paymentResultatsValue, monthlyPaymentsValue);
		} catch (Exception e) {
			System.out.println("Monthly payments is not " + monthlyPaymentsValue);
		}
		return paymentResultatsValue;
	}

}

//