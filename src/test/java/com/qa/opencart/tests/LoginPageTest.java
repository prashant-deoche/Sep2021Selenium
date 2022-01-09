package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic100: Design open cart app- Login page")
@Story ("Story 101: Open cart login page user story")



public class LoginPageTest extends BaseTest {
	@Description ("login Page Title Test")
	@Severity (SeverityLevel.MINOR)
	@Test (priority=1)
	public void loginPageTitleTest()
	{
		String actTitle=loginPage.getLoginPageTitle();
		System.out.println("Actual title is "+actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE);
	}
	
	
	@Description ("login Page URL Test")
	@Severity (SeverityLevel.NORMAL)
	@Test (priority=2)
	public void loginPageUrlTest() {
	Assert.assertTrue(loginPage.getLoginPageURL());
		
	}
	
	@Description ("forgot pwd link Test")
	@Severity (SeverityLevel.CRITICAL)
	@Test (priority=3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExists());
	}
	
	@Description ("register link Test")
	@Severity (SeverityLevel.CRITICAL)
	@Test (priority=4)
	public void registerLinkTest() {
	Assert.assertTrue(loginPage.isRegisterLinkExists());
	}
	
	@Description ("Login Test with correct credentials")
	@Severity (SeverityLevel.BLOCKER)
	@Test (priority=5)
	public void loginTest() {
		accountsPage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertEquals(accountsPage.getAccountPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	
	

}
