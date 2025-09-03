package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	private final By header = By.tagName("h1");
	private final By produceImages = By.cssSelector("div.col-sm-8 img");
	private final By productQuantity = By.name("quantity");

	public String getProductHeader() {
		String headerValue = eleutil.waitForElementVisible(header, AppConstants.DEFAULT_SHORT_WAIT).getText();
		System.out.println("Product header is : " + headerValue);
		return headerValue;
	}

	public String selectNoOfProducts(String setProductValue) {
		WebElement productCount = eleutil.waitForElementVisible(productQuantity, AppConstants.DEFAULT_SHORT_WAIT);
		productCount.clear();
		productCount.sendKeys(setProductValue);
		String noOfProducts = eleutil.waitForElementVisible(productQuantity, AppConstants.DEFAULT_SHORT_WAIT).getText();
		System.out.println("The product count is :" + noOfProducts);
		return noOfProducts;
	}

	public int getImageCount() {
		int imageCount = eleutil.waitForElementsVisible(produceImages, AppConstants.DEFAULT_MEDIUM_WAIT).size();
		System.out.println("The no of images " + imageCount);
		return imageCount;
	}

}
