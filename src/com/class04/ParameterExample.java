package com.class04;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterExample {
	
	@Parameters({"userName1", "userName2", "userName3"})
	@Test
	public void userNames(String username2, String username3, String username1) {
		System.out.println(username1 + username2 + username3);
	}

}
