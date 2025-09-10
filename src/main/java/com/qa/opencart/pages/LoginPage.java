package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	// private by locators : page objects
	private final By emailid = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgotPwdLink = By.linkText("Forgotten Password");
	private final By header = By.tagName("h2");
	private final By registerLink = By.linkText("Register");
	private final By loginErrorMessage = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	private static final Logger log = LogManager.getLogger(LoginPage.class);

	// public constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	// public page methods and actions

	@Step("Getting the login page title")
	public String getLoginPageTitle() {
		String title = eleutil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		// System.out.println("The page title is : " + title);
		log.info("The page title is : " + title);
		return title;
	}

	@Step("Getting the login page URL")
	public String getLoginPageUrl() {
		String url = eleutil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_WAIT);
		// System.out.println("The page URL: " + url);
		log.info("The page URL: " + url);
		return url;
	}

	@Step("Forgot password link exists")
	public boolean isForgotPwdLinkExists() {
		return eleutil.isElementDisplayed(forgotPwdLink);
	}

	@Step("Header exists")
	public boolean isheaderExist() {
		return eleutil.isElementDisplayed(header);
	}

	@Step("Login with the username : {0} and password {1}")
	public AccountsPage doLogin(String userName, String Pwd) {
		// System.out.println("Application credentials :" + userName + "and" + Pwd);
		log.info("Application credentials : " + userName + " and password " + "********");
		eleutil.waitForElementVisible(emailid, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(userName);
		eleutil.doSendKeys(password, Pwd);
		eleutil.doClick(loginBtn);
		return new AccountsPage(driver); // returning next page object - page chaining
	}

	public boolean doLoginWithInvalidCredentials(String invalidUN, String invalidPWD) {
		log.info("Invalid application credentials : " + invalidUN + " and pwssword : " + invalidPWD);
		WebElement element = eleutil.waitForElementVisible(emailid, AppConstants.DEFAULT_MEDIUM_WAIT);
		element.clear();
		element.sendKeys(invalidUN);
		eleutil.doSendKeys(password, invalidPWD);
		eleutil.doClick(loginBtn);
		String errorMessage = eleutil.doElementGetText(loginErrorMessage);
		log.info("Invalid error message is :" + errorMessage);
		if (errorMessage.contains(AppConstants.LOGIN_MAXIMUM_ATTEMPTS_MESSAGE)) {
			return true;
		} else if (errorMessage.contains(AppConstants.LOGIN_INVALID_CREDENTIALS_MESSAGE)) {
			return true;
		}
		return false;
	}

	@Step("navigating to the registration page")
	public RegisterPage navigateToRegisterLink() {
		log.info("Trying to navigate to the Registration page...");
		eleutil.waitForElementVisible(registerLink, AppConstants.DEFAULT_MEDIUM_WAIT).click();
		return new RegisterPage(driver);
	}
}
