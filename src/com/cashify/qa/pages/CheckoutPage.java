package com.cashify.qa.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.cashify.qa.Base.TestBase;
import com.cashify.qa.util.TestUtil;

public class CheckoutPage extends TestBase {
	//Prepared By Suresh
	
	CheckoutPage checkout;
	TestUtil util=new TestUtil();
	static double sum=0;
	static double discount=0;
	static double totalPrice=0;
	String obj;
	String total;
	String datawithout$;
	//Object Repository
	
	@FindBy(xpath="//input[contains(@id,'firstName')]")
	WebElement firstName;
	
	@FindBy(xpath="//input[contains(@id,'lastName')]")
	WebElement lastName;
	
	@FindBy(xpath="//input[contains(@id,'username')]")
	WebElement userName;
	
	@FindBy(xpath="//input[contains(@id,'email')]")
	WebElement email;
	
	@FindBy(xpath="//*[@id,'address']")
	WebElement address1;
	
	@FindBy(xpath="//*[@id,'address2']")
	WebElement address2;
	
	@FindBy(xpath="//select[contains(@id,'country')]")
	WebElement country;
	
	@FindBy(xpath="//select[contains(@id,'state')]")
	WebElement state;
	
	@FindBy(xpath="//input[contains(@id,'zip')]")
	WebElement zipCode;
	
	@FindBy(xpath="//input[contains(@id,'same-address')]")
	WebElement sameAddressCheckbx;
	
	@FindBy(xpath="//input[contains(@id,'save-info')]")
	WebElement saveInfoCheckBox;
	
	@FindBy(xpath="//input[contains(@id,'credit')]")
	WebElement creditCardOption;
	
	@FindBy(xpath="//input[contains(@id,'debit')]")
	WebElement debitCardOption;
	
	@FindBy(xpath="//input[contains(@id,'paypal')]")
	WebElement paypalOption;
	
	@FindBy(xpath="//input[contains(@id,'cc-name')]")
	WebElement NameonCard;
	
	@FindBy(xpath="//input[contains(@id,'cc-number')]")
	WebElement creditCardNumber;
	
	@FindBy(xpath="//input[contains(@id,'cc-expiration')]")
	WebElement expiryDate;
	
	@FindBy(xpath="//input[contains(@id,'cc-cvv')]")
	WebElement cvvCode;
	
	@FindBy(xpath="//button[contains(text(),'Continue to checkout')]")
	WebElement checkoutButton;
	
	@FindBy(xpath="//input[contains(@placeholder,'Promo code')]")
	WebElement promoCode;
	
	@FindBy(xpath="//button[contains(text(),'Redeem')]")
	WebElement redeemButton;
	
	@FindBy(xpath="//strong")
	WebElement totalCartValue;
	
	
	
	public CheckoutPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void fillFirstName(int row,int col) {				
		firstName.sendKeys(util.readExcel(row,col));
	}
	
	public void fillLastName(int row,int column) {				
		lastName.sendKeys(util.readExcel(row, column));
	}
	public void fillUsername(int row,int column) {
		userName.sendKeys(util.readExcel(row, column));
	}
	public void fillEmail(int row,int col) {
		email.sendKeys(util.readExcel(row, col));
	}
	public void fillAddress1(int row, int col) {
		address1.sendKeys(util.readExcel(row, col));
	}
	public void fillAddress2(int row,int col) {
		address2.sendKeys(util.readExcel(row, col));
	}
	public void fillCountry() {
		Select obj=new Select(country);
		obj.selectByIndex(1);
	}
	public void fillState() {
		Select obj=new Select(state);
		obj.selectByIndex(1);
	}
	public void seeTotalValueFunction() {
		 total=totalCartValue.getText();
		//System.out.println("Total DollarValue:-"+total);
		datawithout$=total.substring(1);
		//System.out.println("String without dollar:-"+datawithout$);
	}
	
	
	public void findthesumofallPriceFunction() {
		
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		List<WebElement> allprice= driver.findElements(By.xpath("//span"));
		for (WebElement price : allprice) {
			String stringPrice=price.getText();
			//System.out.println("Price is:- "+stringPrice);
			if (stringPrice.charAt(0)=='$') {
				obj=stringPrice.substring(1);
			double aDouble = Double.parseDouble(obj);   
		        int i = (int)aDouble;
		                                  // System.out.println(i);
				sum+=i;
				
			}
			
		}
		//System.out.println(sum);
	}
	
	public void applyPromocode(String promo) {
		checkout=new CheckoutPage();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		checkout.findthesumofallPriceFunction();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		promoCode.sendKeys(promo);
		redeemButton.click();
		checkout.seeTotalValueFunction();	
		System.out.println("Redeem Button Pressed");
		
		if (promo.equalsIgnoreCase("CASHIFY")) {
			System.out.println("Total Price Before Discount:"+sum);
			discount=sum*10/100;
			System.out.println("Given Discount for Cashify is:"+discount);
			totalPrice = sum-discount;
			System.out.println("Total Price after Given Discount is:"+totalPrice);
		Assert.assertEquals(datawithout$, totalPrice,"Total Cart Price is not same after Cashify Promocode");
		}
		
	}
	
	
	
	
	
}
