package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private final By firstName = By.id("input-firstname");
	private final By lastName = By.id("input-lastname");
	private final By email = By.id("input-email");
	private final By telephone = By.xpath("(//input[@class='form-control']) [4]");
	private final By password = By.id("input-password");
	private final By confirmPassword = By.id("input-confirm");
	private final By subscribeYes = By.xpath("(//input[@type='radio']) [2]");
	private final By subscribeNo = By.xpath("(//input[@type='radio']) [3]");
	private final By agree = By.name("agree");
	private final By continueButton = By.xpath("//input [@value='Continue']");
	private final By successMessage = By.tagName("h1");
	private final By logoutLink = By.linkText("Logout");
	private final By registerLink = By.linkText("Register");

	public boolean userRegistration(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {
		eleUtil.waitForElementVisible(this.firstName, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(confirmPassword, password);
		if (subscribe.equalsIgnoreCase("Yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agree);
		eleUtil.doClick(continueButton);

		String successText = eleUtil.waitForElementVisible(successMessage, AppConstants.DEFAULT_MEDIUM_WAIT).getText();
		System.out.println(successText);

		if (successText.contains(AppConstants.USER_REGISTER_SUCCESS_MESSAGE)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		} else {
			return false;

		}
	}
}
