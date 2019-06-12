package com.class03;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonMethods;

/*Identify Priority of Test Cases
https://www.saucedemo.com/
​
TC 1: Saucedemo Username1(tag the groups - Smoke)
Step 1: Open browser and navigate to Saucedemo
Step 2: Enter username standard_user and enter password secret_sauce and click login button
Step 3: Verify user successfully logged in
​
TC 2: Saucedemo Username2(tag the groups - Regression)
Step 1: Open browser and navigate to Saucedemo
Step 2: Enter username problem_user and enter password secret_sauce and click login button
Step 3: Verify user successfully logged in
​
​
Note: Create BeforeMethod and AfterMethod annotations to open browser and logout from the application. Create a xml file and include smoke.*/
public class TaskOne extends CommonMethods{
	public static String expected = "Products";
	
	
	//@BeforeSuite(alwaysRun=true, groups= {"Smoke", "Regression"})
	//@BeforeGroups({"Smoke1","Regression1", "Smoke2","Regression2"})
	@BeforeMethod(alwaysRun=true, groups= {"Smoke", "Regression"})
	public void setUp() {
		setUpDriver("chrome", "https://www.saucedemo.com");
		
	}
	
	@Test(groups="Smoke")
	public void userOne() {
		sendText(driver.findElement(By.cssSelector("#user-name")), "standard_user");
		sendText(driver.findElement(By.cssSelector("#password")), "secret_sauce");
		click(driver.findElement(By.cssSelector(".btn_action")));
		String actual = driver.findElement(By.xpath("//div[text()='Products']")).getText();
		Assert.assertEquals(actual, expected);
	}
	
	@Test(groups="Regression")
	public void userTwo() {
		sendText(driver.findElement(By.cssSelector("#user-name")), "problem_user");
		sendText(driver.findElement(By.cssSelector("#password")), "secret_sauce");
		click(driver.findElement(By.cssSelector(".btn_action")));
		String actual = driver.findElement(By.xpath("//div[text()='Products']")).getText();
		Assert.assertEquals(actual, expected);

	}

	/*
	 * @Test(groups="Regression2") public void userThree() {
	 * sendText(driver.findElement(By.cssSelector("#user-name")),
	 * "performance_glitch_user");
	 * sendText(driver.findElement(By.cssSelector("password")), "secret_sauce");
	 * click(driver.findElement(By.cssSelector(".btn_action"))); }
	 */
	
	//@AfterSuite(alwaysRun=true, groups= {"Smoke", "Regression"})
	//@AfterGroups({"Smoke1","Regression1", "Smoke2","Regression2"})
	@AfterMethod(alwaysRun=true, groups= {"Smoke", "Regression"})
	public void tearDown(){
		quitDriver();
		
	}
}
