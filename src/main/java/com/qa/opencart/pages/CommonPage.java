package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class CommonPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public CommonPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private final By logo = By.cssSelector("img.img-responsive");
	private final By searchField = By.cssSelector("input.form-control.input-lg");
	private final By footerLinks = By.cssSelector("footer li a");

	public boolean isLogoDisplayed() {
		return eleUtil.isElementCheck(logo);
	}

	public boolean isSearchFieldExists() {
		return eleUtil.isElementCheck(searchField);
	}

	public List<String> footerLinkExists() {
		List<WebElement> footerList = eleUtil.waitForElementsVisible(footerLinks, AppConstants.DEFAULT_MEDIUM_WAIT);
		List<String> footerValue = new ArrayList<String>();
		for (WebElement e : footerList) {
			String text = e.getText();
			footerValue.add(text);
		}
		return footerValue;
	}
}
