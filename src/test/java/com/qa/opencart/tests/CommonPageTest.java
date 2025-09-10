package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class CommonPageTest extends BaseTest {

	@Test
	public void checkCommonElementsOnLoginPage() {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(commonPage.isSearchFieldExists());
		softAssert.assertTrue(commonPage.isLogoDisplayed());
		List<String> footerlist = commonPage.footerLinkExists();
		softAssert.assertEquals(footerlist.size(), AppConstants.DEFAULT_FOOTER_LINKS_COUNT);
		softAssert.assertAll();
	}
	
	@Test
	public void checkCommonElementsOnAccountPage() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(commonPage.isSearchFieldExists());
		softAssert.assertTrue(commonPage.isLogoDisplayed());
		List<String> footerlist = commonPage.footerLinkExists();
		softAssert.assertEquals(footerlist.size(), AppConstants.DEFAULT_FOOTER_LINKS_COUNT);
		softAssert.assertAll();
	}
}
