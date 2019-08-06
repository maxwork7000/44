package core;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Firefox_P1 {
	static Properties p = new Properties();
	static WebDriver driver;
	
	public static boolean isElementPresent(By by) {
	     driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	     // if (driver.findElements(by).size() == 1) return true;
	     if (!driver.findElements(by).isEmpty()) return true;
	     else return false;
	}
	
	public static boolean isAttributePresent(By by, String attribute) {
	     driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	     if (!driver.findElements(by).isEmpty() && 
	    	  driver.findElement(by).getAttribute(attribute) != null) return true;
	     else return false;
	}
	
	public static boolean isAttributeValueMatch(By by, String attribute, String attribute_value) {
	     driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	     if (!driver.findElements(by).isEmpty() &&
	    	  driver.findElement(by).getAttribute(attribute) != null &&
	    	 !driver.findElement(by).getAttribute(attribute).isEmpty() &&
	    	  driver.findElement(by).getAttribute(attribute).equals(attribute_value)
	    	) return true; else return false;
	}

	public static boolean isElementVisible(By by) {
	     driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	     if (!driver.findElements(by).isEmpty() && 
	    	  driver.findElement(by).isDisplayed()) return true; else return false;
	}
	
	public static boolean isElementEnabled(By by) {
	     driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	     if (!driver.findElements(by).isEmpty() && driver.findElement(by).isEnabled()) return true;
	     else return false;
	}
	
	public static boolean isElementReadOnly(By by) {
	     driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	     if (!driver.findElements(by).isEmpty() && driver.findElement(by).getAttribute("readonly") != null) return true;
	     else return false;
	}
	
	public static String getSize(By by) {
	     driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	     if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
	    	 return driver.findElement(by).getRect().getDimension().toString(); else return "null";
		}

	public static String getLocation(By by) {
	     driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	     if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
	    	 return driver.findElement(by).getRect().getPoint().toString(); else return "null";
		}
	
	public static void setValue(By by, String value) {
	     driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	     if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
	    	  driver.findElement(by).getRect().getPoint().toString();
	     driver.findElement(by).sendKeys(p.getProperty(value));
		}
	
	public static String getValue(By by) {
	     driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	     if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
	    	 return driver.findElement(by).getText(); else return "null";
		}
	
	public static void main(String[] args) throws Exception {
		p.load(new FileInputStream("./input.properties"));
		Logger.getLogger("").setLevel(Level.OFF);
		String driverPath = "";

		if (System.getProperty("os.name").toUpperCase().contains("MAC"))
			driverPath = "./resources/webdrivers/mac/geckodriver.sh";
		else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
			driverPath = "./resources/webdrivers/pc/geckodriver.exe";
		else throw new IllegalArgumentException("Unknown OS");

		System.setProperty("webdriver.gecko.driver", driverPath);
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get(p.getProperty("url"));
		
		System.out.println("Page URI: " + driver.getCurrentUrl());
		System.out.println("Page Title: " + driver.getTitle());

		driver.quit();
	}
}
