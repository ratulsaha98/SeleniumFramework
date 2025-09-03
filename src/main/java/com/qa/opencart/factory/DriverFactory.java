package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.exceptions.FrameworkExceptions;
import com.qu.opencart.errors.AppError;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlightEle;

	/**
	 * This method is initializing the browser on basic of browser selection
	 * 
	 * @param browserName
	 * @return this return driver instance
	 */
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("Browser Name :" + browserName);
		highlightEle = prop.getProperty("highlight");
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println(AppError.INVALID_BROWSER_MESSAGE + " : " + browserName);
			throw new FrameworkExceptions("===INVALID BROWSER====");

		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));

		return driver;
	}

	/**
	 * This method is responsible to initialize the properties file
	 * 
	 * @return the properties file
	 */
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
