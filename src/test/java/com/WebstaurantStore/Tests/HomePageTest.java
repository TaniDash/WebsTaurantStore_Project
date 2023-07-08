package com.WebstaurantStore.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.WebstaurantStore.PageObjects.HomePage;

public class HomePageTest extends TestBase {
	HomePage homepage;

	@Test
	public void verifyHomePageAndSearchProduct() {
		homepage = new HomePage(driver);
		Assert.assertEquals(homepage.getCurrentURL(), "https://www.webstaurantstore.com/");
		homepage.searchItem("stainless work table'");
		
	}
	
	
	
}
