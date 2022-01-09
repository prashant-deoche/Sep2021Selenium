package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
		
			}
	//By locators
	private By emailID= By.id("input-email");
	private By password= By.id("input-password");
	private By loginBtn= By.xpath("//input[@value='Login']");
	private By registerlink = By.linkText("Register");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginErrorMesg =By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	
	@Step ("getting login page title...")
	public String getLoginPageTitle() {
		return eleUtil.doGetTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step ("getting login page URL...")
	public boolean getLoginPageURL() {
		return eleUtil.waitForURLToContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step ("checking forgot pwd link exist...")
	public boolean isForgotPwdLinkExists() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	
	@Step ("checking register link exist...")
	public boolean isRegisterLinkExists() {
		return eleUtil.doIsDisplayed(registerlink);
	}
	
	@Step ("do login with username:{0} and password:{1}")
	public AccountsPage doLogin (String username, String pwd) {
		
	eleUtil.doSendKeys(emailID, username);
	eleUtil.doSendKeys(password, pwd);
	eleUtil.doClick(loginBtn);
	return new AccountsPage(driver);
	}

	@Step ("do login with wrong username:{0} and wrong password:{1}")
	public boolean doLoginWithWrongCredentials(String un, String pwd) {
		
		System.out.println("try login with wrong credentials :"+un +":"+pwd);
		eleUtil.doSendKeys(emailID, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		String errorMesg=eleUtil.doGetText(loginErrorMesg);
		System.out.println(errorMesg);
		
		if (errorMesg.contains(Constants.LOGIN_ERROR_MESSG)) {
			System.out.println("login is not done....");
			return false;
		}
		return true;
			
	}
	
	@Step("navigating to registration page...")
	public RegistrationPage goToRegisterPage() {
		eleUtil.doClick(registerlink);
		return new RegistrationPage(driver);
	}
	
	
	
}
