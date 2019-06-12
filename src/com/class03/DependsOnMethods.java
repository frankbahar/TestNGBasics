package com.class03;

import org.testng.annotations.Test;

public class DependsOnMethods {
	
	@Test
	public void zone() {
		System.out.println("one test");
	}
	
	@Test(dependsOnMethods="zone")
	public void rtwo() {
		System.out.println("two test");
	}
	
	@Test(dependsOnMethods="rtwo")
	public void athree() {
		System.out.println("three test");
	}
	
}
