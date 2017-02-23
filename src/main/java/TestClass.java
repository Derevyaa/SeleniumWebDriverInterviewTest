import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class TestClass {
	
	
	private static String url = "http://ia.ca/";

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Java\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		
		String CurrentUrl = driver.getCurrentUrl();
		System.out.println(CurrentUrl);
		Assert.assertEquals(CurrentUrl, "http://ia.ca/");

		String individualsUrl = url + "individuals";
		driver.get(individualsUrl);

		// Wait loans button
		WebDriverWait wait = new WebDriverWait(driver, 20);
		By loansButton = By.xpath("//span[contains(.,'Loans')]");
		wait.until(ExpectedConditions.presenceOfElementLocated(loansButton)).click();

//		Thread.sleep(5000);

		WebElement Mortgages = driver.findElement(By.xpath("//a[contains(.,'Mortgages')]"));
		Mortgages.click();
		
		// Wait CalculateYourPaymentsButton button
		By CalculateYourPaymentsButton = By.xpath("//a[contains(.,'Calculate your payments')]");
		wait.until(ExpectedConditions.presenceOfElementLocated(CalculateYourPaymentsButton)).click();
		
//		Thread.sleep(5000);

		WebElement slider = driver.findElement(By.xpath("(//*[@class='slider-handle min-slider-handle custom'])[1]"));
		int width = slider.getSize().getWidth();
		
		// Using Action Class
		Actions move = new Actions(driver);
		
		// How to know max slider value ???
		Action action = move.dragAndDropBy(slider, (width * 100), 0).build();

		// move.dragAndDrop(slider, target)
		action.perform();

		// Validation of slider working
		
		String StyleValue = slider.getAttribute("style");
		System.out.println(StyleValue);
		Assert.assertEquals(StyleValue, "left: 100%;");
		

		WebElement PurchasePrice = driver.findElement(By.xpath("//*[@id='PrixPropriete']"));
		PurchasePrice.clear();
		// PurchasePrice.sendKeys("\b");
		// PurchasePrice.sendKeys("55");

		for (int i = 0; i < 2; i++) {
			driver.findElement(By.xpath(".//*[@id='PrixProprietePlus']")).click();
		}
		for (int i = 0; i < 1; i++) {
			driver.findElement(By.xpath(".//*[@id='MiseDeFondPlus']")).click();
		}

		Select AmortissementDropdown = new Select(driver.findElement(By.xpath("//*[@id='Amortissement']")));
		AmortissementDropdown.selectByVisibleText("15 years");

		Select FrequenceVersementDropdown = new Select(driver.findElement(By.xpath("//*[@id='FrequenceVersement']")));
		FrequenceVersementDropdown.selectByVisibleText("weekly");

		WebElement TauxInteret = driver.findElement(By.xpath("//*[@id='TauxInteret']"));
		TauxInteret.clear();
		// TauxInteret.sendKeys("\b");
		TauxInteret.sendKeys("5.0");

		WebElement BtnCalculer = driver.findElement(By.xpath("//button[contains(.,'Calculate')]"));
		// Do not work with one click
		BtnCalculer.click();
		BtnCalculer.click();

		//Thread.sleep(5000);
		 //Wait PaymentResultats result
				By PaymentResultats = By.xpath("//*[@id='paiement-resultats']");
				wait.until(ExpectedConditions.visibilityOfElementLocated(PaymentResultats));

		WebElement PaymentResultats1 = driver.findElement(By.xpath("//*[@id='paiement-resultats']"));
		String PaymentResultatsValue1 = PaymentResultats1.getText();
		System.out.println(PaymentResultats1.getText());
		Assert.assertEquals(PaymentResultatsValue1, "$ 836.75");
		
		driver.close();
	}

}