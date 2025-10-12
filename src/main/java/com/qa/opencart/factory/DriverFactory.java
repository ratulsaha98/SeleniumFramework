package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.exceptions.FrameworkExceptions;
import com.qu.opencart.errors.AppError;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlightEle;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	private static final Logger log = LogManager.getLogger(DriverFactory.class);
	private OptionsManager optionsManager;

	/**
	 * This method is initializing the browser on basic of browser selection
	 * 
	 * @param browserName
	 * @return this return driver instance
	 */
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		// System.out.println("Browser Name :" + browserName);
		log.info("Browser Name : " + browserName);
		highlightEle = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		Boolean remoteExecution = Boolean.parseBoolean(prop.getProperty("remote"));
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			// driver = new ChromeDriver();
			if (remoteExecution) {
				init_remoteDriver("chrome");
			} else {
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			break;
		case "firefox":
			// driver = new FirefoxDriver();
			if (remoteExecution) {
				init_remoteDriver("firefox");
			} else {
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			break;
		case "edge":
			// driver = new EdgeDriver();
			if (remoteExecution) {
				init_remoteDriver("edge");
			} else {
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			break;
		default:
			log.error(AppError.INVALID_BROWSER_MESSAGE + " : " + browserName);
			FrameworkExceptions fe = new FrameworkExceptions(AppError.INVALID_BROWSER_MESSAGE + " : " + browserName);
			log.error("Exception occurred while initializing driver :", fe);
			throw new FrameworkExceptions("===INVALID BROWSER====");

		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	/**
	 * This is used to initialize the remote web driver to Selenium grid
	 * 
	 * @param string
	 */
	private void init_remoteDriver(String browserName) {
		log.info("Running the test cases on " + browserName);
		try {
			switch (browserName) {
			case "chrome":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
				break;
			case "firefox":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
				break;
			case "edge":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));

				break;
			default:
				log.error("Please supply the right browser name for Selenium Grid");
				FrameworkExceptions fe = new FrameworkExceptions(
						AppError.INVALID_BROWSER_MESSAGE + " : " + browserName);
				log.error("Exception occurred while initializing driver :", fe);
				throw new FrameworkExceptions("===INVALID BROWSER====");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * this is used to get the local copy of the driver
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is responsible to initialize the properties file
	 * 
	 * @return the properties file
	 */
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		log.info("Envirement name is : " + envName);
		try {
			if (envName == null) {
				log.info("No enviorement is passed so running the test cases on QA enviorement");
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
			} else {
				switch (envName.trim().toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				default:
					log.error("Enviorement value is invalid please pass the correct enviorement value");
					throw new FrameworkExceptions("===INVALID EXCEPTION===");
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * Taking a screenshot
	 * 
	 * @return
	 */
	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		byte[] srcByte = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// OutputType is an enum
																							// which a collection of
																							// multiple constant
		return srcByte;
	}

	public static String getScreenshotBase64() {
		String srcBase64 = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
		return srcBase64;
	}

}
