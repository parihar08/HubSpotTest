package com.hubspot.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hubspot.qa.base.BasePage;
import com.hubspot.qa.util.Constants;
import com.hubspot.qa.util.TestUtil;
import com.hubspot.qa.pages.ContactsPage;

public class HomePage extends BasePage{
	
	Logger log = Logger.getLogger(HomePage.class);
	
	@FindBy(xpath = "//h1[text()='Sales Dashboard']")
	WebElement homePageHeader;
	
	@FindBy(xpath = "//span[@class='account-name ']")
	WebElement accountName;
	
	@FindBy(id = "nav-primary-contacts-branch")
	WebElement contactsTab;
	
	@FindBy(id = "nav-secondary-contactss")
	WebElement contactsLink;
	
	public HomePage(WebDriver driver){
		BasePage.driver = driver;
	   //this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getHomePageTitle(){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleIs(Constants.HOME_PAGE_TITLE));
		return driver.getTitle().trim();
	}
	
	public String getHomePageHeader(){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(homePageHeader));
		return homePageHeader.getText();
	}
	
	public String getLoggedInAccountName(){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(accountName));
		return accountName.getText();
	}
	
	public ContactsPage goToContactsPage(){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(contactsTab));
		contactsTab.click();
		//wait.until(ExpectedConditions.visibilityOf(contactsLink));
		TestUtil.shortWait();
		contactsLink.click();
		return new ContactsPage(driver);
	}
}
