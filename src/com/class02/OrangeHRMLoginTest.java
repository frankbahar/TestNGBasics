package com.class02;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class OrangeHRMLoginTest extends CommonMethods{
	public static String firstName= "Enes";
	public static String lastName="Kanter";
	public static String Id = "12";
	@BeforeClass
	public void setup() {
		setUpDriver("chrome", "https://opensource-demo.orangehrmlive.com");
	}
	
	@BeforeMethod
	public void login() {
		sendText(driver.findElement(By.cssSelector("#txtUsername")), "Admin");
		sendText(driver.findElement(By.cssSelector("#txtPassword")), "admin123");
		click(driver.findElement(By.cssSelector("#btnLogin")));
		boolean flag=false;
		try {
			flag = driver.findElement(By.xpath("//h1[text()='Dashboard']")).isDisplayed();
		}catch (Exception e) {
			flag = false;
		}
		Assert.assertTrue(flag);
	}
	
	@Test
	public void addEmployee() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//b[text()='PIM']"))).perform();;
		click(driver.findElement(By.xpath("//a[text()='Add Employee']")));
		sendText(driver.findElement(By.cssSelector("#firstName")), firstName);
		sendText(driver.findElement(By.cssSelector("#lastName")), lastName);
		sendText(driver.findElement(By.cssSelector("#employeeId")), Id);
		click(driver.findElement(By.cssSelector("#btnSave")));
		
		String expected ="Personal Details";
		String actual = driver.findElement(By.xpath("//h1[text()='Personal Details']")).getText();
		Assert.assertEquals(actual, expected);	
		 
	}
	
	@AfterMethod
	public void verifyEmployee() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//b[text()='PIM']"))).perform();
		click(driver.findElement(By.xpath("//a[text()='Employee List']")));
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
		boolean employeeFound=false;
		SoftAssert soft = new SoftAssert();
		for(int i=1; i<=rows.size(); i++) {
			String actualId = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr[" + i + "]/td[2]")).getText();
			if(actualId.equals(Id)) {
				employeeFound=true;
				String actualFirstName= driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr[" + i + "]/td[3]")).getText();
				soft.assertEquals(actualFirstName, firstName);
				String actualLastName=driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr[" + i + "]/td[4]")).getText();
				soft.assertEquals(actualLastName, lastName);
			}
		}
		Assert.assertTrue(employeeFound);
		soft.assertAll();
			 
	}
	
	@AfterClass
	public void tearDown() {
		quitDriver();
	}
}
