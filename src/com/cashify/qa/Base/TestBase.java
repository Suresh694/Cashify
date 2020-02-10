package com.cashify.qa.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase() {

		try {
			prop=new Properties();
			//FileInputStream fis=new FileInputStream(prop.getProperty("configfilePath"));
			FileInputStream fis= new FileInputStream("C:\\Users\\Suresh Das\\eclipse-workspace\\CheckoutProcess\\src\\com\\cashify\\qa\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	public static void initialization() {

		String browser=prop.getProperty("browser");
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Suresh Das\\eclipse-workspace\\CheckoutProcess\\Driver\\chromedriverupdated.exe");
			
			ChromeOptions options=new ChromeOptions();
			options.addArguments("--start-maximized");			//For Disable Password

			HashMap<String, Boolean>prefs=new HashMap<>();		//For Disable Password
			prefs.put("credentials_enable_service", false);		//For Disable Password
			prefs.put("profile.password_manager_enabled", false);		//For Disable Password
			options.setExperimentalOption("prefs", prefs);			//For Disable Password
			
			options.setExperimentalOption("useAutomationExtension", false);				//Disable Automation Bar
			options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation")); 	//For Disable Automation Bar
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();		//For both Automation Bar and Password
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);			//For Both Automation Bar and Passwords
			driver=new ChromeDriver(options);
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
	
	}

}
