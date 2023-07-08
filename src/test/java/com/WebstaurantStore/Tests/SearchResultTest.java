package com.WebstaurantStore.Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.WebstaurantStore.PageObjects.SearchResultPage;

public class SearchResultTest extends TestBase{
	SearchResultPage searchResultPage;
	
	@Test
	public void verifySearchAndAddToCart() throws InterruptedException {
		searchResultPage = new SearchResultPage(driver);
		Assert.assertTrue(searchResultPage.validateSearchPage());
		
		searchResultPage.getAllProductsFromAllPages();
		 
		List<String> productList =searchResultPage.getAllProductsFromAllPages();
		String keyword = "Table";
		boolean allTitlesContainKeyword =searchResultPage.validateProductTitlesContainKeyword(productList, keyword);
		Assert.assertTrue(allTitlesContainKeyword, "All products don't contain the expected keyword");
		 
		searchResultPage.addLastProductToCart(productList);
		
//		Assert.assertTrue( searchResultPage.validateItemAddedToTheCart());
		
		
	}

}
