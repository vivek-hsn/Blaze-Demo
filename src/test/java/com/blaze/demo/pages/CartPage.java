package com.blaze.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.blaze.demo.base.BaseTest;

public class CartPage extends BaseTest{
	
	WebDriver driver; 
	
	private static final String phoneName = "Nexus 6";
	private static final int cost = 650;
	private static final String message = "Thank you for your purchase!";
	
	
	@FindBy(xpath="//tr/td[text()='Nexus 6']")
	WebElement prodName;
	
	@FindBy(css="h3#totalp")
	private WebElement totalPrice;
	
	@FindBy(css="div>button.btn.btn-success")
	private WebElement placeOrderBtn;
	
	@FindBy(id="name")
	private WebElement orderName;
	
	@FindBy(id="country")
	private WebElement orderCountry;
	
	@FindBy(id="city")
	private WebElement orderCity;
	
	@FindBy(id="card")
	private WebElement orderCard;
	
	@FindBy(id="month")
	private WebElement orderMonth;
	
	@FindBy(id="year")
	private WebElement orderYear;
	
	@FindBy(xpath="//button[text()='Purchase']")
	private WebElement purchaseBtn;
	
	@FindBy(xpath="//div[contains(@class,'sweet-alert')]/h2")
	private WebElement successMsg;
	
	@FindBy(xpath="//div[@class='sa-confirm-button-container']/button[text()='OK']")
	private WebElement okBtn;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public testMethod(){
		return "test";
	}
	
	public boolean verifyNameAndPrice(){
		
		int price = Integer.parseInt(totalPrice.getText());
		
		String name = prodName.getText();
		
		if(price == cost && name.contains(phoneName)){
			return true;
		}
		
		return false;
	}
	
	public void clickOnPlaceOrder(){
		placeOrderBtn.click();
		WAIT_FOR_SEC(WAIT_2_SECS);
	}
	
	public void enterOrderInfo(String name, String country, String city, 
			String creditCard, String month, String year){
		
		orderName.sendKeys(name);
		WAIT_FOR_SEC(WAIT_1_SEC);
		
		orderCountry.sendKeys(country);
		WAIT_FOR_SEC(WAIT_1_SEC);
		
		orderCity.sendKeys(city);
		WAIT_FOR_SEC(WAIT_1_SEC);
		
		orderCard.sendKeys(creditCard);
		WAIT_FOR_SEC(WAIT_1_SEC);
		
		orderMonth.sendKeys(month);
		WAIT_FOR_SEC(WAIT_1_SEC);
		
		orderYear.sendKeys(year);
		WAIT_FOR_SEC(WAIT_1_SEC);
	}
	
	public void clickOnPurchaseBtn(){
		
		purchaseBtn.click();
		WAIT_FOR_SEC(WAIT_2_SECS);
	}
	
	public void purchaseOrder(String name, String country, String city, 
			String creditCard, String month, String year){
		
		clickOnPlaceOrder();
		
		enterOrderInfo(name, country, city, creditCard, month, year);
		WAIT_FOR_SEC(WAIT_2_SECS);
		
		clickOnPurchaseBtn();
		
	}
	
	public boolean verifySuccessMsg(){
		
		String finalMsg = successMsg.getText();
		if(finalMsg.contains(message)){
			return true;
		}
		
		return false;
	}
	
	public void completeOrder(){
		
		okBtn.click();
		WAIT_FOR_SEC(WAIT_3_SECS);
	}

}
