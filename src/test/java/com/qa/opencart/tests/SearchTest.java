package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchTest extends BaseTest {

	@BeforeClass
	public void searchSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][] { { "Macbook", "MacBook Pro", "MacBook Pro" },
				{ "samsung", "Samsung SyncMaster 941BW", "Samsung SyncMaster 941BW" } };
	}

	@Test(dataProvider = "getSearchKey")
	public void searchTest(String searchKey, String produceName, String expectedProduct) {
		searchResultsPage = accountsPage.doserch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(produceName);
		String productHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(productHeader, expectedProduct);
	}

}
