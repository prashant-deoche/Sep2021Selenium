package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	
	
	private ElementUtil eleUtil;
	
	//locators
	private By firstName=By.id("input-firstname");
	private By lastName=By.id("input-lastname");
	private By email =By.id("input-email");
	private By telephone=By.id("input-telephone");
	private By password=By.id("input-password");
	private By confirmpassword=By.id("input-confirm");
	private By subscribeYes=By.xpath("(//input[@type='radio'])[2]");
	private By subscribeNo=By.xpath("(//input[@type='radio'])[3]");
	private By agreeCheckBox=By.name("agree");
	private By continueButton=By.xpath("//input[@value='Continue']");
	private By successMessg=By.cssSelector("div#content h1");
	
	
	private By registerlink = By.linkText("Register");
	private By logoutlink = By.linkText("Logout");
	
	
	
	public RegistrationPage(WebDriver driver) {
	
		eleUtil=new ElementUtil(driver);
	}
	
	
	public boolean accountRegistration(String firstName, String lastName, String email, String telephone,
		String password, String subscribe) {
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);
		
		
		if(subscribe.equals("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else
		{
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		String mesg= eleUtil.waitForElementToBeVisible(successMessg, 5, 1000).getText();
		
		if (mesg.contains(Constants.REGISTER_SUCCESS_MESSG))
		{
			eleUtil.doClick(logoutlink);
			eleUtil.doClick(registerlink);
			return true;
		}
		return false;
	}

}
