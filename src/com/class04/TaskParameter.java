package com.class04;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class TaskParameter extends CommonMethods {
	public static String expected = "Products";
	
	@Parameters({ "browser", "url" })
	@BeforeMethod(alwaysRun = true)
	public void setUp(String browser, String url) {
		setUpDriver(browser, url);
	}

	@Parameters({ "username", "password" })
	@Test
	public void userone(String username, String password) {
		sendText(driver.findElement(By.cssSelector("#user-name")), username);
		sendText(driver.findElement(By.cssSelector("#password")), password);
		click(driver.findElement(By.cssSelector(".btn_action")));
		String actual = driver.findElement(By.xpath("//div[text()='Products']")).getText();
		Assert.assertEquals(actual, expected);
	}
	
	@Parameters({ "username1", "password1" })
	@Test(groups="Regression")
	public void userTwo(String username, String password) {
		sendText(driver.findElement(By.cssSelector("#user-name")), "problem_user");
		sendText(driver.findElement(By.cssSelector("#password")), "secret_sauce");
		click(driver.findElement(By.cssSelector(".btn_action")));
		String actual = driver.findElement(By.xpath("//div[text()='Products']")).getText();
		Assert.assertEquals(actual, expected);

	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		quitDriver();
	}
}
