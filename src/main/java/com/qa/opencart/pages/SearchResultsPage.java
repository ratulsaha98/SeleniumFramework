package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	private final By SearchResults = By.cssSelector("div.product-thumb");
	private final By headerText = By.cssSelector("div#content h1");

	public int getSearchResultsCount() {
		int resultsCount = eleutil.waitForElementsPresence(SearchResults, AppConstants.DEFAULT_MEDIUM_WAIT).size();
		System.out.println("The results count " + resultsCount);
		return resultsCount;
	}

	public String getResultsHeaderValue() {
		String header = eleutil.doElementGetText(headerText);
		System.out.println("The header value" + header);
		return header;
	}

	public ProductInfoPage selectProduct(String productName) {
		System.out.println("The product name is" + productName);
		eleutil.doClick(By.linkText(productName)); // Dynamic by locator it changes according to the user input
		return new ProductInfoPage(driver);
	}

}
