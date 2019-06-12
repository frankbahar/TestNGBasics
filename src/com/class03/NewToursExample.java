package com.class03;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class NewToursExample extends CommonMethods {
	
	//@BeforeGroups({"Smoke", "Regression"})
//	@BeforeSuite
    @BeforeMethod(alwaysRun=true, groups="Smoke")
	public void setUp() {
		setUpDriver("chrome", "http://newtours.demoaut.com");
		click(driver.findElement(By.xpath("//a[text()='REGISTER']")));
	}
		
	@Test(groups="Smoke")
	public void auserInformation() {
		System.out.println("Title : " + driver.getTitle());
		sendText(driver.findElement(By.cssSelector("input[name='firstName']")), "firstnametest");
		sendText(driver.findElement(By.cssSelector("input[name='lastName']")), "lastnamestest");
		sendText(driver.findElement(By.cssSelector("input[name='phone']")), "phonetest");
		sendText(driver.findElement(By.cssSelector("#userName")), "usertest@gmail.com");
		//	click(driver.findElement(By.cssSelector("input[name='register']")));
	}
	
	@Test(groups="Regression")
	public void contactInformation() {
		sendText(driver.findElement(By.cssSelector("input[name='address1']")), "adresstest");
		sendText(driver.findElement(By.cssSelector("input[name='city']")), "citytest");
		sendText(driver.findElement(By.cssSelector("input[name='state']")), "statetest");
		sendText(driver.findElement(By.cssSelector("input[name='postalCode']")), "postalcodetest");
	
	}
	
	@Test(groups="Regression")
	public void mailingInformation() {
		sendText(driver.findElement(By.cssSelector("input[name='userName']")), "usertest");
		sendText(driver.findElement(By.cssSelector("#email")), "usertest@gmail.com");
		sendText(driver.findElement(By.cssSelector("input[name='password']")), "passtest");
		sendText(driver.findElement(By.cssSelector("input[name='confirmPassword']")), "passtest");
		click(driver.findElement(By.cssSelector("input[name='register']")));	
	}
	
//	@AfterGroups({"Smoke", "Regression"})
//	@AfterSuite
	@AfterMethod(alwaysRun=true, groups="Smoke")
	public void tearDown() {
		quitDriver();
	}

}
