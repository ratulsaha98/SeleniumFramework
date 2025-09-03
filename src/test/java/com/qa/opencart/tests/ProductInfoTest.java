package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoTest extends BaseTest {

	@BeforeTest
	public void productInforPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProducts() {
		return new Object[][] { { "Macbook", "MacBook Pro" }, { "iMac", "iMac" },
				{ "samsung", "Samsung SyncMaster 941BW" }, { "Canon", "Canon EOS 5D" } };
	}

	@Test(dataProvider = "getProducts")
	public void productHeaderTest(String searchkey, String productName) {
		searchResultsPage = accountsPage.doserch(searchkey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		String productHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(productHeader, productName);
	}

	@DataProvider
	public Object[][] getProductImages() {
		return new Object[][] { { "Macbook", "MacBook Pro", 4 }, { "samsung", "Samsung SyncMaster 941BW", 1 },
				{ "Canon", "Canon EOS 5D", 3 }, { "iMac", "iMac", 3 } };

	}

	@DataProvider
	public Object[][] getProductFromExcel() {
		return ExcelUtil.getData("ProductName");
	}

	@Test(dataProvider = "getProductFromExcel")
	public void getImageCountTest(String searchKey, String productName, String productCount) {
		searchResultsPage = accountsPage.doserch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		int actualImageCount = productInfoPage.getImageCount();
		Assert.assertEquals(Integer.toString(actualImageCount), productCount);
	}

	/*
	 * @Test public void getProductCount() throws InterruptedException {
	 * searchResultsPage = accountsPage.doserch("Macbook"); productInfoPage =
	 * searchResultsPage.selectProduct("MacBook Pro"); String productCount =
	 * productInfoPage.selectNoOfProducts("4"); Thread.sleep(4000);
	 * Assert.assertEquals(productCount, "4"); }
	 */

}
