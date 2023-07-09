package com.WebstaurantStore.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.WebstaurantStore.PageObjects.HomePage;

public class HomePageTest extends TestBase {
	HomePage homepage;
	SoftAssert softAssert;

	@Test
	public void verifyHomePageAndSearchProduct() {
		homepage = new HomePage(driver);
		softAssert = new SoftAssert();
		
		//Verify if the URL landed onto the homepage
		softAssert.assertEquals(homepage.getCurrentURL(), "https://www.webstaurantstore.com/");
		//Search item using given keyword
		homepage.searchItem("stainless work table");
		softAssert.assertAll();
	}
	
	
	
}
