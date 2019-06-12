package com.class02;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAndHardAssert {
	@Test
	public void soft() {
		SoftAssert soft = new SoftAssert();
		System.out.println("before soft assert");
		soft.assertTrue(false);
		System.out.println("after soft assert");
		soft.assertAll();
	}
	
	@Test 
	public void hard() {
		System.out.println("before hard assert");
		Assert.assertTrue(false);
		System.out.println("after hard assert");
	}

}
