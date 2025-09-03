package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	private final By headers = By.cssSelector("div#content h2");
	private final By logout = By.linkText("Logout");
	private final By search = By.name("search");
	private final By searchButton = By.xpath("//button[@class='btn btn-default btn-lg']");

	public List<String> geAccountsPagetHeader() {
		List<WebElement> headerselement = eleutil.waitForElementsPresence(headers, AppConstants.DEFAULT_SHORT_WAIT);
		List<String> headersValueList = new ArrayList<String>();
		for (WebElement e : headerselement) {
			String text = e.getText();
			headersValueList.add(text);
		}
		return headersValueList;
	}

	public boolean isLogoutlinkExists() {
		WebElement logoutEle = eleutil.waitForElementPresence(logout, AppConstants.DEFAULT_MEDIUM_WAIT);
		return eleutil.isElementDisplayed(logoutEle);
	}

	public SearchResultsPage doserch(String searchKey) {
		System.out.println("Search key :" + searchKey);
		WebElement searchElement = eleutil.waitForElementVisible(search, AppConstants.DEFAULT_MEDIUM_WAIT);
		searchElement.clear();
		searchElement.sendKeys(searchKey);
		eleutil.doClick(searchButton);
		return new SearchResultsPage(driver);
	}

}
