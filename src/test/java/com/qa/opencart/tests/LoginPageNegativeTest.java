package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest{
	
	@DataProvider
	public Object[][] loginWrongTestData() {
		return new Object [] [] {
			{"test@test.com","test@123"},
			{"FDSFDF", "ddgfgfdg"},
			{""	,""	}			
			
		};
		}
	
	
	@Test (dataProvider="loginWrongTestData")
	public void loginNegativeTest(String un, String pwd ) {
		Assert.assertFalse(loginPage.doLoginWithWrongCredentials(un, pwd));
	}
	
	

}
