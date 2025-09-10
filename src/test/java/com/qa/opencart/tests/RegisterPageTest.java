package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CsvUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void launchRegisterPage() {
		registerPage = loginPage.navigateToRegisterLink();
	}

	@DataProvider
	public Object[][] getRegistrationData() {
		return new Object[][] { { "Ratul", "Saha", "9885227496", "Abc@123", "yes" },
				{ "Santanu", "Saha", "7009856741", "Axy@123", "No" },
				{ "Luna", "Saha", "6290074596", "ABCD@345", "Yes" } };
	}

	@DataProvider
	public Object[][] getExcelSheetData() {
		return ExcelUtil.getData("Registration");
	}

	@DataProvider
	public Object[][] getCSVData() {
		return CsvUtil.csvData("Registration");
	}

	@Test(dataProvider = "getCSVData")
	public void userRegisterTest(String firstName, String lastName, String telephone, String password,
			String subscribe) {
		Assert.assertTrue(registerPage.userRegistration(firstName, lastName, StringUtils.getRandomEmail(), telephone,
				password, subscribe));
	}

}
