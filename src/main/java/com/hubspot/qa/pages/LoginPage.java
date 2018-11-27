package com.hubspot.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hubspot.qa.base.BasePage;

public class LoginPage extends BasePage {
	
	//1. Define PageFactory (page elements -- OR)
		//1.a: create a constructor of page class and initialize page elements with driver
	//2. actions -- methods
	
	@FindBy(id = "username")
	WebElement loginName;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(id = "loginBtn")
	WebElement loginBtn;
	
	@FindBy(xpath = "//a/i18n-string[contains(text(),'Forgot my password')]")
	WebElement forgotMyPwdLink;
	
	@FindBy(xpath = "//a/i18n-string[contains(text(),'Sign up')]")
	WebElement signUpLink;
	
	public LoginPage(WebDriver driver){  //Constructor of the class
		BasePage.driver = driver;
		//this.driver = driver;
		PageFactory.initElements(driver, this); //this means pointing to current class object LoginPage.class
	}
	
	public String getLoginPageTitle(){
		return driver.getTitle();
	}
	
	public boolean verifyForgotMyPasswordLink(){
		return forgotMyPwdLink.isDisplayed();
	}
	
	public boolean verifySignUpLink(){
		return signUpLink.isDisplayed();
	}
	
	public HomePage doLogin(String username, String pwd){
		loginName.sendKeys(username);
		password.sendKeys(pwd);
		loginBtn.click();
		
		return new HomePage(driver); //Successful login takes to the HomePage,
									// So return type of LoginPage is HomePage class object
	}
	

}
