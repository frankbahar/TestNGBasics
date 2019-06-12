package com.class03;

import org.testng.annotations.Test;

public class GroupsExample {
	@Test(groups="Smoke")
	public void one() {
		System.out.println("testOne");
	}

	@Test(groups="Regression")
	public void two() {
		System.out.println("testTwo");
	}
	
	@Test(groups= {"Smoke"})
	public void three() {
		System.out.println("testThree");
	}
	
	@Test(groups= {"Smoke","Regression"})
	public void four() {
		System.out.println("testFour");
	}
}
