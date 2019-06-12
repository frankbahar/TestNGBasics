package com.class04;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.CommonMethods;

/*Identify Priority of Test Cases

http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2fTestComplete11%2fWebOrders%2fDefault.aspx

TC 1: WebOrder Verify Successful Login ( )
Step 1: Open browser and navigate to WebOrder
Step 2: Enter valid username, enter valid password and click on the  login button
Step 3: Verify user successfully logged in

TC 2: WebOrder Creating and verifying Users( )
Step 1: Create Two users and fill out all the required fields
 Step 2: Verify that user one name appears within the table 
 Step 3: Edit user one  and update user one’s State, assert the new updated State is displayed and save the changes.
 Step 4: Verify that user two name appears within the table 
 Step 5: Edit user two and update user two’s City and assert the new updated City is displayed and save the changes.

TC 3: WebOrder verifying Users( )
        Step 1 : Assert Both User one and User Two are displayed

Note: Create BeforeClass and AfterClass annotations to open browser and logout from the application. 
The creation of users two should depend on the creation of users one.
The validation both users should depend on the creation of both users. 
Create xml file and share a screenshot of the test.

*/
public class DependencyTask extends CommonMethods {
	String expectedLoginText = "Web Orders";
	// Order Information
	String product = "FamilyAlbum";
	String quantity = "3";
	String discount = "0";
	String user1name = "Enes Kanter";
	String user2name = "Michael Jordan";
	String street = "12 Main St.";
	String city = "Portland";
	String state = "OR";
	String zip = "89776";
	String cardType = "MasterCard";
	String cardNum = "12345";
	String expDate = "02/22";
	String updatedUser2City = "Chicago";
	String updatedUser1State = "NJ";

	@BeforeClass(alwaysRun = true)
	public void setUp() {
		setUpDriver("chrome", "http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
	}

	@Test
	public void login() {
		sendText(driver.findElement(By.cssSelector("input[id$='username']")), "Tester");
		sendText(driver.findElement(By.cssSelector("input[id*='password']")), "test");
		click(driver.findElement(By.cssSelector(".button")));
		String actualLoginText = driver.findElement(By.xpath("//h1[text()='Web Orders']")).getText();
		Assert.assertEquals(actualLoginText, expectedLoginText);
	}

	@Test(dependsOnMethods = "login", groups = "addUser")
	public void addUser1() {
		click(driver.findElement(By.xpath("//a[text()='Order']")));
		// entering the order
		selectValueFromDD(driver.findElement(By.xpath("//select[contains(@id,'ddlProduct')]")), product);
		// String unitPrice =
		// driver.findElement(By.cssSelector("input[id$='txtUnitPrice']")).getAttribute("value");
		sendText(driver.findElement(By.cssSelector("input[id*='txtQuantity']")), quantity);
		sendText(driver.findElement(By.cssSelector("input[id*='txtDiscount']")), discount);
		click(driver.findElement(By.cssSelector("input[type='submit']")));
//		String total = driver.findElement(By.cssSelector("input[id*='txtTotal']")).getAttribute("value");
		sendText(driver.findElement(By.cssSelector("input[id*='txtName']")), user1name);
		sendText(driver.findElement(By.cssSelector("input[id*='TextBox2']")), street);
		sendText(driver.findElement(By.cssSelector("input[id*='TextBox3']")), city);
		sendText(driver.findElement(By.cssSelector("input[id*='TextBox4']")), state);
		sendText(driver.findElement(By.cssSelector("input[id*='TextBox5']")), zip);
		selectValueFromRadioButton(driver.findElements(By.cssSelector("input[type='radio']")), cardType);
		sendText(driver.findElement(By.cssSelector("input[id*='TextBox6']")), cardNum);
		sendText(driver.findElement(By.cssSelector("input[id$='TextBox1']")), expDate);
		click(driver.findElement(By.cssSelector("a[id$='InsertButton']")));
		// check user1 added and updated state
		click(driver.findElement(By.xpath("//a[text()='View all orders']")));
		List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr"));
		List<WebElement> cols = driver.findElements(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr[1]/th"));
		boolean orderFound = false;
		boolean orderUpdated = false;
		for (int i = 2; i <= rows.size(); i++) {
			String rowText = driver
					.findElement(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr[" + i + "]/td[2]")).getText();
			if (rowText.contains(user1name)) {
				orderFound = true;
				driver.findElement(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr[" + i + "]/td[13]")).click();
				sendText(driver.findElement(By.cssSelector("input[id*='TextBox4']")), updatedUser1State);
				driver.findElement(By.xpath("//a[contains(@id,'UpdateButton')]")).click();
				List<WebElement> updatedRows = driver
						.findElements(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr"));
				for (int j = 2; i <= rows.size(); i++) {
					String updatedRowText = driver
							.findElement(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr[" + i + "]/td[8]"))
							.getText();
					if (updatedRowText.contains(updatedUser1State)) {
						orderUpdated = true;
						break;
					}
				}
				break;
			}
		}
		Assert.assertTrue(orderFound, user1name + " could not found");
		Assert.assertTrue(orderUpdated, user1name + " state could not updated");
	}

	@Test(dependsOnMethods = { "login", "addUser1" }, groups = "addUser")
	public void addUser2() {
		click(driver.findElement(By.xpath("//a[text()='Order']")));
		// entering the order
		selectValueFromDD(driver.findElement(By.xpath("//select[contains(@id,'ddlProduct')]")), product);
		// String unitPrice =
		// driver.findElement(By.cssSelector("input[id$='txtUnitPrice']")).getAttribute("value");
		sendText(driver.findElement(By.cssSelector("input[id*='txtQuantity']")), quantity);
		sendText(driver.findElement(By.cssSelector("input[id*='txtDiscount']")), discount);
		click(driver.findElement(By.cssSelector("input[type='submit']")));
//		String total = driver.findElement(By.cssSelector("input[id*='txtTotal']")).getAttribute("value");
		sendText(driver.findElement(By.cssSelector("input[id*='txtName']")), user2name);
		sendText(driver.findElement(By.cssSelector("input[id*='TextBox2']")), street);
		sendText(driver.findElement(By.cssSelector("input[id*='TextBox3']")), city);
		sendText(driver.findElement(By.cssSelector("input[id*='TextBox4']")), state);
		sendText(driver.findElement(By.cssSelector("input[id*='TextBox5']")), zip);
		selectValueFromRadioButton(driver.findElements(By.cssSelector("input[type='radio']")), cardType);
		sendText(driver.findElement(By.cssSelector("input[id*='TextBox6']")), cardNum);
		sendText(driver.findElement(By.cssSelector("input[id$='TextBox1']")), expDate);
		click(driver.findElement(By.cssSelector("a[id$='InsertButton']")));

		// check user2 added and updated city
		click(driver.findElement(By.xpath("//a[text()='View all orders']")));
		List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr"));
		List<WebElement> cols = driver.findElements(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr[1]/th"));
		boolean orderFound = false;
		boolean orderUpdated = false;
		for (int i = 2; i <= rows.size(); i++) {
			String rowText = driver
					.findElement(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr[" + i + "]/td[2]")).getText();
			if (rowText.contains(user2name)) {
				orderFound = true;
				driver.findElement(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr[" + i + "]/td[13]")).click();
				sendText(driver.findElement(By.cssSelector("input[id*='TextBox3']")), updatedUser2City);
				driver.findElement(By.xpath("//a[contains(@id,'UpdateButton')]")).click();
				List<WebElement> updatedRows = driver
						.findElements(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr"));
				for (int j = 2; i <= rows.size(); i++) {
					String updatedRowText = driver
							.findElement(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr[" + i + "]/td[7]"))
							.getText();
					if (updatedRowText.contains(updatedUser2City)) {
						orderUpdated = true;
						break;
					}
				}
				break;
			}
		}
		Assert.assertTrue(orderFound, user2name + " could not found");
		Assert.assertTrue(orderUpdated, user2name + " city could not updated");

	}

	@Test(dependsOnGroups="addUser")
	public void validateUsers() {
		click(driver.findElement(By.xpath("//a[text()='View all orders']")));
		List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr"));
		List<WebElement> cols = driver.findElements(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr[1]/th"));
		boolean user1Found = false;
		boolean user2Found = false;
		for (int i = 2; i <= rows.size(); i++) {
			String rowText = driver
					.findElement(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr[" + i + "]/td[2]")).getText();
			if (rowText.contains(user1name)) {
				user1Found = true;
				if(user2Found) {
					break;
				}
			}	
			if (rowText.contains(user2name)) {
				user2Found = true;
				if(user1Found) {
					break;
				}
			}
		}
		Assert.assertTrue(user1Found, user1name + " could not found");
		Assert.assertTrue(user2Found, user2name + " could not found");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		quitDriver();
	}

}
