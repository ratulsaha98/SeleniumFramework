package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test
	public void isLogoutlinkExistsTest() {
		Assert.assertTrue(accountsPage.isLogoutlinkExists());
	}

	@Test
	public void accountPageHeaderTest() {
		List<String> actualHeader = accountsPage.geAccountsPagetHeader();
		Assert.assertEquals(actualHeader.size(), AppConstants.ACC_PAGE_HEADERS_COUNT);
		Assert.assertEquals(actualHeader, AppConstants.expectedAccPageHeadersList);

	}
}
