package core;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Edge {
	static Properties p = new Properties();
	static WebDriver driver;

	public static void main(String[] args) throws Exception {
		p.load(new FileInputStream("./input.properties"));
		Logger.getLogger("").setLevel(Level.OFF);

		if (!System.getProperty("os.name").contains("Windows"))
			throw new IllegalArgumentException("MS Edge is available only on Windows");
		System.setProperty("webdriver.edge.driver", "./resources/webdrivers/pc/MicrosoftWebDriver.exe");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get(p.getProperty("url"));
		
		System.out.println("Page URI: " + driver.getCurrentUrl());
		System.out.println("Page Title: " + driver.getTitle());

		driver.quit();
	}
}