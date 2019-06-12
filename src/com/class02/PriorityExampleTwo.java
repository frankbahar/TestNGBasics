package com.class02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class PriorityExampleTwo  extends CommonMethods{
	@BeforeClass
	public void setup() throws InterruptedException {
		setUpDriver("chrome", "https://www.saucedemo.com");
		Thread.sleep(3000);
		String expectedTitle = "Swag Labs";
	//	String actualTitle = driver.findElement(By.xpath("//title[text()='Swag Labs']")).getText();
		WebElement element = driver.findElement(By.tagName("input"));
		String actualTitle=element.getAttribute("title");
		System.out.println("actual " + actualTitle);
		System.out.println("expected " + expectedTitle);
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("This is the right title");
		}else {
			System.out.println("This is wrong title");
		}
	}
	
	@Test(priority=1)
	public void login() {
		sendText(driver.findElement(By.cssSelector("#user-name")), "standard_user");
		sendText(driver.findElement(By.cssSelector("#password")), "secret_sauce");
		click(driver.findElement(By.cssSelector(".btn_action")));
	}
	
	@Test(priority=2)
	public void mainPage() {
		String expected="Products";
		String  actual = driver.findElement(By.xpath("//div[text()='Products']")).getText();
		if(expected.equals(actual)) {
			System.out.println("Expected mathces actual text");
		}else {
			System.out.println("Expected does not mathc actual text");
		}
	}
	
	@AfterClass
	public void tearDown() {
		quitDriver();
	}
}
