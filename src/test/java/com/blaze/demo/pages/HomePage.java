package com.blaze.demo.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.blaze.demo.base.BaseTest;

public class HomePage extends BaseTest{
	
	WebDriver driver; 
	
	private static final String prodDesc = "The Motorola Google Nexus 6 is powered by 2.7GHz quad-core Qualcomm Snapdragon 805 processor and it comes with 3GB of RAM.";
	
	private static final String addAlertMsg = "Product added";
	
	@FindBy(css="li>a[href='index.html']")
	private WebElement index;
	
	@FindBy(css="li>a[href='cart.html']")
	private WebElement cart;
	
	@FindBy(css="div.list-group>a:nth-of-type(2)")
	private WebElement phonesXpath;
	
	@FindBy(linkText="Phones")
	private WebElement phones;
	
	@FindBy(linkText="Laptops")
	private WebElement laptops;
	
	@FindBy(linkText="Monitors")
	private WebElement monitors;
	
	@FindBy(xpath="//div/h4/a[text()='Nexus 6']")
	WebElement phoneNexus;
	
	@FindBy(css="div#more-information>p")
	private WebElement productDescription;
	
	@FindBy(linkText="Add to cart")
	private WebElement addToCartBtn;
	
	@FindBy(css="h3#totalp")
	private WebElement totalPrice;
	
	@FindBy(css="div>button.btn.btn-success")
	private WebElement placeOrderBtn;
	
	@FindBy(id="name")
	private WebElement name;
	
	@FindBy(id="country")
	private WebElement country;
	
	@FindBy(id="city")
	private WebElement city;
	
	@FindBy(id="card")
	private WebElement card;
	
	@FindBy(id="month")
	private WebElement month;
	
	@FindBy(id="year")
	private WebElement year;
	
	@FindBy(xpath="//button[text()='Purchase']")
	WebElement purchaseBtn;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void gotoDemoBlaze(){
		driver.get("https://www.demoblaze.com/");
		WAIT_FOR_SEC(WAIT_2_SECS);
		
	}
	
	public void gotoHomePage(){
		index.click();
		
	}
	
	public void selectCategory(String catName){
		
		if(catName.equals("Phones")){
			phones.click();
		}
		else if(catName.equals("Laptops")){
			laptops.click();
		}
		else if(catName.equals("Monitors")){
			monitors.click();
		}
		else{
			System.err.println("Invalid catergory name");
		}
	}
	
	public void selectPhone(){
		
		//Click on home menu
		gotoHomePage();
		WAIT_FOR_SEC(WAIT_2_SECS);
		
		//Click on Phones category
		selectCategory("Phones");
		WAIT_FOR_SEC(WAIT_2_SECS);
		
		phoneNexus.click();
	}
	
	public boolean verifyProdDes(){
		String phoneDesc = productDescription.getText();
		
		if(phoneDesc.contains(prodDesc)){
			return true;
		}
		
		return false;
	}
	
	public void clickOnAddToCartBtn(){
		addToCartBtn.click();
	}
	
	public void switchToAlert(){
		
		Alert alert = driver.switchTo().alert();
		
	}
	
	public boolean verifyAlertText(){
		switchToAlert();
		
		String alertMsg = driver.switchTo().alert().getText();
		
		if(alertMsg.contains(addAlertMsg)){
			
			//accept the alert
			acceptAlert();
			WAIT_FOR_SEC(WAIT_1_SEC);
			
			return true;
		}
		
		return false;
	}
	
	public void acceptAlert(){
		
		driver.switchTo().alert().accept();
		
	}
	
	public void gotoCartPage(){
		cart.click();
	}

}
