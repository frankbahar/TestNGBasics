package com.class04;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvideTest {

	@DataProvider
	public Object[][] setUpData() {
		Object[][] data = new Object[3][2];

		data[0][0] = "Tim";
		data[0][1] = "Jones";

		data[1][0] = "Billy";
		data[1][1] = "Jones";

		data[2][0] = "Will";
		data[2][1] = "Smith";
		return data;
	}

	@Test(dataProvider = "setUpData")
	public void getData(String first, String last) {
		System.out.println(first + " " + last);
	}
}
