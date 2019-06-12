package com.class05;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;

import utils.CommonMethods;

public class SmartBearDataProviderParallel {
	/*
	 * Identify Priority of Test Cases ​
	 * http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Process.
	 * aspx TC 1: Saucedemo Username1(Data Provider - Quantity:, Customer
	 * name:,Street: City:, State: ,Zip:,Card:,Card Nr:, Expire date ) Step 1: Open
	 * browser and navigate to smartbearsoftware Step 2: Enter valid
	 * username,password and click login button Step 3: Verify user successfully
	 * logged in Step 4: Click on Order Step 5: Make an order for two user,enter all
	 * the information for both users(Quantity:, Customer name:,Street: City:,
	 * State: ,Zip:,Card:,Card Nr:, Expire date) Step 6: Take ScreenShot before
	 * submitting each user
	 */

	WebDriver driver=null;

	@Parameters({ "browser", "url", "username", "password" })
	@BeforeTest(alwaysRun=true)
	public void setUp(String browser, String url, String username, String password) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}else {
			System.out.println("browser given is wrong");
		}
		driver.get(url);

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		CommonMethods.sendText(driver.findElement(By.cssSelector("input[id$='username']")), username);
		CommonMethods.sendText(driver.findElement(By.cssSelector("input[id*='password']")), password);
		CommonMethods.click(driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_login_button\"]")));
		
	}

	@Test(dataProvider = "orderData")
	public void validateOrder(String qty, String name, String street, String city, String state, String zip,
			String cardType, String cardNum, String expDate) throws WebDriverException, IOException {

		CommonMethods.click(driver.findElement(By.xpath("//a[text()='Order']")));

		CommonMethods.sendText(driver.findElement(By.cssSelector("input[id*='txtQuantity']")), qty);
		CommonMethods.sendText(driver.findElement(By.cssSelector("input[id*='txtName']")), name);
		CommonMethods.sendText(driver.findElement(By.cssSelector("input[id*='TextBox2']")), street);
		CommonMethods.sendText(driver.findElement(By.cssSelector("input[id*='TextBox3']")), city);
		CommonMethods.sendText(driver.findElement(By.cssSelector("input[id*='TextBox4']")), state);
		CommonMethods.sendText(driver.findElement(By.cssSelector("input[id*='TextBox5']")), zip);
		CommonMethods.selectValueFromRadioButton(driver.findElements(By.cssSelector("input[type='radio']")), cardType);
		CommonMethods.sendText(driver.findElement(By.cssSelector("input[id*='TextBox6']")), cardNum);
		CommonMethods.sendText(driver.findElement(By.cssSelector("input[id$='TextBox1']")), expDate);
		FileUtils.copyFile(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE), new File("Passed/FileUpload/" + name));
		CommonMethods.click(driver.findElement(By.cssSelector("a[id$='InsertButton']")));

	}

	@DataProvider(name = "orderData")
	public Object[][] getData() {
		Object[][] data = new Object[2][9];
		data[0][0] = "1";
		data[0][1] = "Enes Kanter";
		data[0][2] = "12 Main St.";
		data[0][3] = "Portland";
		data[0][4] = "OR";
		data[0][5] = "89776";
		data[0][6] = "MasterCard";
		data[0][7] = "12345";
		data[0][8] = "02/22";

		data[1][0] = "3";
		data[1][1] = "Michael Jordan";
		data[1][2] = "12 Main St.";
		data[1][3] = "Chicago";
		data[1][4] = "IL";
		data[1][5] = "89776";
		data[1][6] = "Visa";
		data[1][7] = "12345";
		data[1][8] = "09/24";

		return data;
	}

	@AfterTest(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
}