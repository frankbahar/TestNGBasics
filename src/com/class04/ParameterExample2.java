package com.class04;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.CommonMethods;

public class ParameterExample2 extends CommonMethods{
	
	@Parameters({"browser", "url"})
	@Test
	public void testGoogle(String browser, String url) throws InterruptedException {
		setUpDriver(browser, url);
		Thread.sleep(2000);
		quitDriver();
	}

}
