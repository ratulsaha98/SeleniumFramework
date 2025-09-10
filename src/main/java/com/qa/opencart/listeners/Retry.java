package com.qa.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	private int count = 0;
	private static int maxTry = 3;

	@Override
	public boolean retry(ITestResult iTestResult) {
		if (!iTestResult.isSuccess()) { // check if the test not succeed
			if (count < maxTry) { // check if the max count is reached
				count++; // increasing the count
				iTestResult.setStatus(ITestResult.FAILURE); // Mark as failed
				return true; // tells TestNG to re run the test
			} else {
				iTestResult.setStatus(ITestResult.FAILURE);
			}
		} else {
			iTestResult.setStatus(ITestResult.SUCCESS);
		}
		return false;
	}

}
