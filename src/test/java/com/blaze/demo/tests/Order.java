package com.blaze.demo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.blaze.demo.base.BaseTest;
import com.blaze.demo.pages.CartPage;
import com.blaze.demo.pages.HomePage;

public class Order extends BaseTest{

	
	@Test(dataProvider = "OrderDetails")
	public void placeOrder(String name, String country, String city, 
			String creditCard, String month, String year){
		
		HomePage home = new HomePage(driver);
		CartPage cart = new CartPage(driver);
			
		//Click on Nexus 6 phone link
		home.selectPhone();
		WAIT_FOR_SEC(WAIT_2_SECS);
		
		//Checking the selected product description
		Assert.assertTrue(home.verifyProdDes(), "Product description is not matching");
		
		//Click on add to cart button to make an order
		home.clickOnAddToCartBtn();
		WAIT_FOR_SEC(WAIT_2_SECS);
		
		//Checking whether product is added to cart or not
		Assert.assertTrue(home.verifyAlertText(), "Alert message is not matching");
		
		//Click on cart menu
		home.gotoCartPage();
		WAIT_FOR_SEC(WAIT_2_SECS);
		
		Assert.assertTrue(cart.verifyNameAndPrice(), "Product name and price are not matching");
		
		//Purchase the product
		cart.purchaseOrder(name, country, city, creditCard, month, year);
		
		Assert.assertTrue(cart.verifySuccessMsg(), "Parchase was not successfuly");
		WAIT_FOR_SEC(WAIT_2_SECS);
		
		cart.completeOrder();
		WAIT_FOR_SEC(WAIT_2_SECS);
	}
	
	
}
