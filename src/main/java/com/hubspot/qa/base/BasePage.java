package com.hubspot.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.hubspot.qa.util.Constants;
import com.hubspot.qa.eventlistener.WebEventListener;

public class BasePage {
	
	public static WebDriver driver;
	public Properties prop;
	public WebEventListener webEventListener;
	public EventFiringWebDriver e_driver;

		
	public Properties init_properties(){
		prop = new Properties();
		try{
			FileInputStream fis = new FileInputStream("/Users/Parihar08/Documents/workspace/HubspotTest/src/"
					+ "main/java/com/hubspot/qa/config/config.properties");
			prop.load(fis);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		}
	
	public WebDriver init_driver(String browser){
		//String browser = prop.getProperty("browser");
		if(browser.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
			driver = new ChromeDriver();
			driver.manage().window().fullscreen();
		}
		else if(browser.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();;
		}
		
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		//WebDriver Fire Events is used to generate Selenium Action Logs
		//Create an object of EventListenerHandler to register it with EventFiringWebDriver
		webEventListener = new WebEventListener();
		e_driver = new EventFiringWebDriver(driver);
		e_driver.register(webEventListener);
		driver = e_driver;
		return driver;
		}
}
