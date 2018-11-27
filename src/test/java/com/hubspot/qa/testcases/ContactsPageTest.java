package com.hubspot.qa.testcases;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hubspot.qa.base.BasePage;
import com.hubspot.qa.pages.ContactsPage;
import com.hubspot.qa.pages.HomePage;
import com.hubspot.qa.pages.LoginPage;
import com.hubspot.qa.util.TestUtil;

public class ContactsPageTest {
	
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
		contactsPage = homePage.goToContactsPage();
	}
	
	@DataProvider(name="getContactsTestData")
	public Object[][] getContactsTestData(){
		return TestUtil.getTestData("contacts");
//		Object data[][] = TestUtil.getTestData("contacts");
//		return data;
	}
	
	@Test(dataProvider = "getContactsTestData")
	public void createContactTest(String emailId, String firstName, String lastName, String jobTitle){
		contactsPage.createNewContact(emailId, firstName,  lastName, jobTitle );
		
		//contactsPage.createNewContact("abc@abc.ca", "Sandeep",  "Parihar", "Automation QA" );
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
