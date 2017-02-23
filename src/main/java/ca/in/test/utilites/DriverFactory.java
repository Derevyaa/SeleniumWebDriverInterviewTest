package ca.in.test.utilites;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import junitx.util.PropertyManager;

public class DriverFactory {
	public enum BrowserType {
		CHROME("chrome"), FIREFOX("firefox");
		// SAFARI,
		// IE

		private String value;

		BrowserType(String value) {
			this.value = value;
		}

		public String getBrowserName() {
			return this.value;
		}
	}

	public static WebDriver getDriver(BrowserType type) {
		WebDriver driver = null;
		switch (type) {
		case CHROME:
			ChromeDriverManager.getInstance().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			return driver;
		case FIREFOX:
			FirefoxDriverManager.getInstance().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			return driver;
		default:
			throw new RuntimeException("Couldn't initialize driver from " + type);
		}
	}

	public static BrowserType getBrowserTypeByPropertyFile() {
		String browsername = PropertyManager.getProperty("BROWSER");
		for (BrowserType bType : BrowserType.values()) {
			if (bType.getBrowserName().equalsIgnoreCase(browsername)) {
				System.out.println("Browser =" + bType.getBrowserName());
				return bType;
			}
		}
		throw new RuntimeException("Unknown or Unsupported browser: " + browsername);
	}

}
