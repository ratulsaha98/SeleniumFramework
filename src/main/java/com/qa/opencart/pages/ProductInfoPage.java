package com.qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	private Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	private final By header = By.tagName("h1");
	private final By produceImages = By.cssSelector("div.col-sm-8 img");
	private final By productQuantity = By.name("quantity");
	private final By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private final By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

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

	public Map<String, String> getProductData() {
		// productMap = new HashMap<String, String>();// it does not maintain the order
		//productMap = new LinkedHashMap<String, String>();// Based on the insertion order data will occur
		productMap = new TreeMap<String, String>();// To get the sorted o/p in sorted order
		productMap.put("ProductName", getProductHeader());
		productMap.put("ProductImages", String.valueOf(getImageCount()));
		getProductMetaData();
		getPriceMetaData();
		System.out.println("============Product full data============== \n" + productMap);
		return productMap;
	}

	private void getProductMetaData() {
		List<WebElement> metaList = eleutil.waitForElementsVisible(productMetaData, AppConstants.DEFAULT_MEDIUM_WAIT);
		System.out.println("Total metadata is" + metaList.size());
		for (WebElement e : metaList) {
			String metaData = e.getText();
			String meta[] = metaData.split(":");
			String key = meta[0].trim();
			String value = meta[1].trim();
			productMap.put(key, value);
		}
	}

	private void getPriceMetaData() {
		List<WebElement> priceList = eleutil.waitForElementsVisible(productPriceData, AppConstants.DEFAULT_MEDIUM_WAIT);
		System.out.println("Total pricelist is" + priceList.size());
		String price = priceList.get(0).getText();
		String pricewithTax = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("ProductPrice", price);
		productMap.put("ProductPriceWithTax", pricewithTax);
	}

}
