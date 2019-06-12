package com.class01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonMethods;
/*TC 1: Swag Lab Title and Login Verification

@BeforeMethod should contain navigation to the URL and title verification
https://www.saucedemo.com/

@Test should contain steps to login and “Product” word verification

@AfterMethod should logOut from the application and close the browser*/

public class SauceDemoBeforeAfterTask extends CommonMethods{
	@BeforeMethod
	public void setUp() {
		setUpDriver("chrome", "https://www.saucedemo.com");
		String expectedTitle="Swag Labs";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Test
	public void login() {
		sendText(driver.findElement(By.cssSelector("#user-name")), "standard_user");
		sendText(driver.findElement(By.cssSelector("#password")), "secret_sauce");
		click(driver.findElement(By.cssSelector(".btn_action")));
		String expectedText = "Products";
		String actualText= driver.findElement(By.cssSelector(".product_label")).getText();
		Assert.assertEquals(actualText,expectedText);		
	}
	
	@AfterMethod
	public void quit() {
		click(driver.findElement(By.xpath("//button[text()='Open Menu']")));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		//WebElement element = driver.findElement(By.cssSelector("#logout_sidebar_link"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#logout_sidebar_link")));
		click(driver.findElement(By.cssSelector("#logout_sidebar_link")));
		quitDriver();
		
	}
	
}
