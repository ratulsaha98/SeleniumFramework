package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageNegativeTest extends BaseTest {

	@DataProvider
	public Object[][] getinvalidcredentials() {
		return new Object[][] { { "ratul.saha942@gmail.commmmm", "June@2025" },
				{ "ratul.saha942@gmail.com", "July@2025" }, { "ratul.saha942@gmail.commmmm", "July@2025" },
				{ "", "June@2025" }, { "ratul.saha942@gmail.commmmm", "" }, { "", "" } };
	}

	@Test(dataProvider = "getinvalidcredentials")
	public void negativeLoginTest(String invalidUserName, String invalidPassword) {

		Assert.assertTrue(loginPage.doLoginWithInvalidCredentials(invalidUserName, invalidPassword));
	}
}
