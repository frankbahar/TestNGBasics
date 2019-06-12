package com.class03;

import org.testng.annotations.Test;

public class TestGroup {
	
	@Test(groups={"test-group"})
	public void testMethodOne() {
		System.out.println("Test method one beloging to test-group");
	}
	
	@Test
	public void testMethodTwo() {
		System.out.println("Test method two NOT beloging to test-group");
	}
	
	@Test(groups= {"test-group"})
	public void testMethodThree() {
		System.out.println("Test method three belongin to test-group");
	}
}
