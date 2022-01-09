package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void setupRegistration() {
		registrationPage=loginPage.goToRegisterPage();
	}
	
	public String getRandomEmail() {
		Random randomemailgenerator =new Random();
		String randomemail="sepautomation"+randomemailgenerator.nextInt(1000)+"@gmail.com";
		return randomemail;
	}
	
	@Test (dataProvider="getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, String telephone,
			String password, String subscribe) {
		Assert.assertTrue(registrationPage.accountRegistration
				(firstName, lastName, getRandomEmail(), telephone,
						 password, subscribe));
	}
	

	@DataProvider
	public Object[][] getRegisterData() {
		
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME); 
	}
	
	
	

}
