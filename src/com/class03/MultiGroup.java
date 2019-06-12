package com.class03;

import org.testng.annotations.Test;

public class MultiGroup {
	
	@Test(groups={"group-one"})
	public void testMethodOne() {
		System.out.println("Test method one beloging to group-one");
	}
	
	@Test(groups= {"group-one", "group-two"})
	public void testMethodTwo() {
		System.out.println("Test method two beloging to both group");
	}
	
	@Test(groups= {"group-two"})
	public void testMethodThree() {
		System.out.println("Test method three belongin to group-two");
	}
}
