package com.class02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utils.CommonMethods;

public class SoftAndHardAssertExample extends CommonMethods{
	@BeforeClass
	public void setUp() {
		setUpDriver("chrome", "https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}
	
	@Test(priority= 1)
	public void orangehrmLoginScreen() {
		String title = driver.getTitle();
		String exptitle = "OrangeHRM";
//		SoftAssert soft = new SoftAssert();
//		soft.assertEquals(title, exptitle);
//		System.out.println("soft assert ");
		Assert.assertEquals(title, exptitle);
		//System.out.println("after hard assert ");
	}
	
	@Test(priority =2)
	public void logo() {
		SoftAssert soft2 = new SoftAssert();
		boolean flag=false;
		  try{
		  flag = driver.findElement(By.xpath("//img[contains(@src,'logo.pngwwr')]")).isDisplayed();
		 
		 
		 } catch (Exception e) { // TODO: handle exception }
			 flag=false;
		 }
		 soft2.assertTrue(flag);
		System.out.println("after soft assert ");
		soft2.assertAll();
	}
	
	@AfterClass
	public void tearDown() {

		quitDriver();
	}
}
