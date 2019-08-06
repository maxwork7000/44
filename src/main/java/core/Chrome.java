package core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Chrome {
	static Properties p = new Properties();
	static WebDriver driver;

	public static void main(String[] args) throws Exception {
		p.load(new FileInputStream("./input.properties"));
		Logger.getLogger("").setLevel(Level.OFF);

		System.out.println("Browser: Chrome");
		String driverPath = "";

		if (System.getProperty("os.name").toUpperCase().contains("MAC"))
			driverPath = "./resources/webdrivers/mac/chromedriver";
		else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
			driverPath = "./resources/webdrivers/pc/chromedriver.exe";
		else throw new IllegalArgumentException("Unknown OS");

		System.setProperty("webdriver.chrome.driver", driverPath);
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("disable-infobars");
		option.addArguments("--disable-notifications");

		if (System.getProperty("os.name").toUpperCase().contains("MAC"))
			option.addArguments("-start-fullscreen");
		else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
			option.addArguments("--start-maximized");
		else throw new IllegalArgumentException("Unknown OS");

		driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(p.getProperty("url"));
		
		System.out.println("Page URI: " + driver.getCurrentUrl());
		System.out.println("Page Title: " + driver.getTitle());

		driver.quit();
	}
}