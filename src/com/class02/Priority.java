package com.class02;

import org.testng.annotations.Test;

public class Priority {
	
	@Test()
	public void first() {
		System.out.println("First test annotation");
	}
	
	@Test(priority=1)
	public void second() {
		System.out.println("Second test annotation");
	}
	
	@Test()
	public void third() {
		System.out.println("Third test annotation");
	}
	
	@Test(priority=3)
	public void fourth() {
		System.out.println("Fourth test annotation");
	}
	
}
