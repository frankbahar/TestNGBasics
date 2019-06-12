package com.class02;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class Task extends CommonMethods{
	
	@BeforeMethod
	public void setup() {
		setUpDriver("chorome" , "https://opensource-demo.orangehrmlive.com");
		String expectedTitle = "OrangeHRM";
		String actualTitle = driver.getTitle();
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Title is matched");
		}else {
			System.out.println("Title do not match");
		}
	}
	
	@Test
	public void login() {
		sendText(driver.findElement(By.cssSelector("#txtUsername")), "Admin");
		sendText(driver.findElement(By.cssSelector("#txtPassword")), "admin123");
		click(driver.findElement(By.cssSelector("#btnLogin")));
		String expected="";
		String actualtext = driver.findElement(By.xpath("")).getText();
		if(actualtext.equals(expected)) {
			System.out.println("Text is matched test passed");
		}else {
			System.out.println("Text do not matched test failed");			
		}
		
	}
	
	@AfterMethod
	public void tearDown() {
		quitDriver();
	}

}
