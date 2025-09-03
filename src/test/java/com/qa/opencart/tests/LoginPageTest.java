package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test
	public void aloginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test
	public void bloginPageURLTest() {
		String actURL = loginPage.getLoginPageUrl();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}

	@Test
	public void cisForgotPwdLinkExistsTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExists());
	}

	@Test
	public void disheaderExistTest() {
		Assert.assertTrue(loginPage.isheaderExist());
	}

	@Test
	public void eLoginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password")); //storing in the accounts page reference variable
		Assert.assertTrue(accountsPage.isLogoutlinkExists());
		;
	}
}
