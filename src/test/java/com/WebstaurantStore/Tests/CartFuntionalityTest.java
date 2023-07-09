package com.WebstaurantStore.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.WebstaurantStore.PageObjects.CartFuntionalityPage;

public class CartFuntionalityTest extends TestBase{
	CartFuntionalityPage cartFuntionalityPage;
	SoftAssert softAssert;
	
	@Test
	public void verifyCartFunctionality() throws InterruptedException {		
		cartFuntionalityPage = new CartFuntionalityPage(driver);
		softAssert = new SoftAssert();
		
		//verify if the view cart is working
	    softAssert.assertEquals(cartFuntionalityPage.validateCartPage(), true);
	    //verify empty cart functionality
	    cartFuntionalityPage.emptyCartFunctionality();	   	      
	   softAssert.assertEquals(cartFuntionalityPage.validateCartIsEmpty(), true);
			
		softAssert.assertAll();
	}
	
	
	
	
}
