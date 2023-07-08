package com.WebstaurantStore.Tests;

import org.testng.Assert;

import com.WebstaurantStore.PageObjects.CartFuntionalityPage;

public class CartFuntionalityTest extends TestBase{
	CartFuntionalityPage cartFuntionalityPage;
	
	public void verifyCartFunctionality() {
		
		cartFuntionalityPage = new CartFuntionalityPage(driver);
		
		Assert.assertEquals(cartFuntionalityPage.validateCartPage(), "Cart");
		
		cartFuntionalityPage.emptyCartFuntionality();
		
		
	}
	
	
	
	
}
