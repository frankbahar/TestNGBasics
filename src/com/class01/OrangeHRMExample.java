package com.class01;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class OrangeHRMExample extends CommonMethods {

	@BeforeMethod
	public void setUp() {
		setUpDriver("chrome", "https://opensource-demo.orangehrmlive.com");
	}

	@Test(enabled = false, priority = 1)
	public void titleVerification() {
		String expectedTitle = "OrangeHRM";
		String actualTitle = driver.getTitle();
		if (actualTitle.equals(expectedTitle)) {
			System.out.println("title match");
		} else {
			System.out.println("title do not match");
		}

	}

	@Test(priority = 1)
	public void login() {
		sendText(driver.findElement(By.cssSelector("#txtUsername")), "Admin");
		sendText(driver.findElement(By.cssSelector("#txtPassword")), "admin123");
		click(driver.findElement(By.cssSelector("#btnLogin")));
	}

	@Test(priority = 2)
	public void dashboardDisplayed() {
	 	sendText(driver.findElement(By.cssSelector("#txtUsername")), "Admin");
		sendText(driver.findElement(By.cssSelector("#txtPassword")), "admin123");
		click(driver.findElement(By.cssSelector("#btnLogin")));
		if (driver.findElement(By.xpath("//h1[text()='Dashboard']")).isDisplayed()) {
			System.out.println("Login Successful Test Passed");
		} else {
			System.out.println("Login was not successful Test Failed");
		}
	}

	@AfterMethod
	public void close() {
		quitDriver();
	}
}
