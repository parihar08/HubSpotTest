package com.hubspot.qa.testcases;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hubspot.qa.base.BasePage;
import com.hubspot.qa.pages.ContactsPage;
import com.hubspot.qa.pages.HomePage;
import com.hubspot.qa.pages.LoginPage;
import com.hubspot.qa.util.Constants;
import com.hubspot.qa.util.TestUtil;

public class HomePageTest {
	
	Logger log = Logger.getLogger(HomePageTest.class);
	
	public BasePage basePage;
	public WebDriver driver;
	public Properties prop;
	public LoginPage loginPage;
	public HomePage homePage;
	public ContactsPage contactsPage;
	
	@Parameters("browser") 
	@BeforeMethod
	public void setUp(String browser){
		
		basePage = new BasePage();
		prop = basePage.init_properties();
		driver = basePage.init_driver(browser);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest(){
		String title = homePage.getHomePageTitle();
		System.out.println("home page title is: "+ title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE,"Home Page Tiltle not matched");
	}
	
	@Test(priority=2)
	public void verifyHomePageHeaderTest(){
		String header = homePage.getHomePageHeader();
		System.out.println("home page header is: "+ header);
		Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3)
	public void verifyCorrectLoggedInAccountNameTest(){
		String accountName = homePage.getLoggedInAccountName();
		System.out.println("Logged in Account Name is: "+ accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname"));
	}
	
	@Test(priority=4)
	public void goToContactsPageTest(){
		contactsPage = homePage.goToContactsPage();
	}
	
	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {
		if(testResult.getStatus() == ITestResult.FAILURE){
			TestUtil.takeScreenshotAtEndOfTest();
			System.out.println("Method Name: "+testResult.getName());
		}
		driver.quit();
	}

}
