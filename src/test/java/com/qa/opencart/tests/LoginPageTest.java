package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100 : Design the Open cart App login page")
@Feature("F 01 : Design the open cart login feature")
@Story("US - 50 : Develop login core features - Title, URL user able to login")
public class LoginPageTest extends BaseTest {

	@Description("Login page title test")
	@Owner("Ratul Saha")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void aloginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		ChainTestListener.log("Login page title :" + title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Description("Login page URL test")
	@Owner("Ratul Saha")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void bloginPageURLTest() {
		String actURL = loginPage.getLoginPageUrl();
		ChainTestListener.log("Login page rul :" + actURL);
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}

	@Description("Forgot link exist test")
	@Owner("Ratul Saha")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void cisForgotPwdLinkExistsTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExists());
	}

	@Description("Login page header test")
	@Owner("Ratul Saha")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void disheaderExistTest() {
		Assert.assertTrue(loginPage.isheaderExist());
	}

	@Description("User is able to login test with corrct credentials")
	@Owner("Ratul Saha")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void eLoginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password")); // storing in the
																										// accounts page
																										// reference
																										// variable
		Assert.assertTrue(accountsPage.isLogoutlinkExists());
		;
	}
}
