package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

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

	// public constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	// public page methods and actions

	public String getLoginPageTitle() {

		String title = eleutil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("The page title is : " + title);
		return title;
	}

	public String getLoginPageUrl() {
		String url = eleutil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("The page URL: " + url);
		return url;
	}

	public boolean isForgotPwdLinkExists() {
		return eleutil.isElementDisplayed(forgotPwdLink);
	}

	public boolean isheaderExist() {
		return eleutil.isElementDisplayed(header);
	}

	public AccountsPage doLogin(String userName, String Pwd) {
		System.out.println("Application credentials :" + userName + "and" + Pwd);
		eleutil.waitForElementVisible(emailid, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(userName);
		eleutil.doSendKeys(password, Pwd);
		eleutil.doClick(loginBtn);
		return new AccountsPage(driver); // returning next page object - page chaining
	}

	public RegisterPage navigateToRegisterLink() {
		eleutil.waitForElementVisible(registerLink, AppConstants.DEFAULT_MEDIUM_WAIT).click();
		return new RegisterPage(driver);
	}
}
