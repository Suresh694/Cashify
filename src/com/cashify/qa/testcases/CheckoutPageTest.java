package com.cashify.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cashify.qa.Base.TestBase;
import com.cashify.qa.pages.CheckoutPage;

public class CheckoutPageTest extends TestBase{
	
	CheckoutPage checkout;
	
	public CheckoutPageTest() {
		super();
	}
	
	@Test(enabled=false)
	public void setup() {
		initialization();
		checkout=new CheckoutPage();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		checkout.fillFirstName(1, 1);
		checkout.fillLastName(1, 2);
		checkout.fillUsername(1, 3);
		checkout.fillEmail(1, 4);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		/*checkout.fillAddress1(1, 5);
		checkout.fillAddress2(1, 6);
		checkout.fillCountry();
		checkout.fillState();*/
	}
	
	@Test(enabled=false)
	public void totalcartvalueTest() {
		initialization();
		checkout=new CheckoutPage();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		checkout.seeTotalValueFunction();	
	}
	
	@Test(enabled=true)
	public void allpricesTest() {
		initialization();
		checkout=new CheckoutPage();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//checkout.findthesumofallPriceFunction();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		checkout.applyPromocode(prop.getProperty("promocode"));
	}

}
