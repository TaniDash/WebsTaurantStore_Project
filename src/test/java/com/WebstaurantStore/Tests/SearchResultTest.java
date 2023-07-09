package com.WebstaurantStore.Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.WebstaurantStore.PageObjects.SearchResultPage;

public class SearchResultTest extends TestBase{
	SearchResultPage searchResultPage;
	SoftAssert softAssert;
	
	@Test
	public void verifySearchAndProductTitle() throws InterruptedException {
		searchResultPage = new SearchResultPage(driver);
		softAssert = new SoftAssert();
		
//Verify if the search page is diplayed as expected		
		softAssert.assertTrue(searchResultPage.validateSearchPage());
		
//verify if all the products contain the keyword "Table"		
		List<String> fullProductList =searchResultPage.getAllProductsFromAllPages();
		String keyword = "Table";
		boolean allTitlesContainKeyword =searchResultPage.validateProductTitlesContainKeyword(fullProductList, keyword);
		softAssert.assertTrue(allTitlesContainKeyword, "All products don't contain the expected keyword. Unmatched title is printed in the console.");		
		softAssert.assertAll();
	}

	@Test
	public void verifyProductAddedToCart() throws InterruptedException {
		searchResultPage = new SearchResultPage(driver);
		softAssert = new SoftAssert();
		
		//add the last found item to the cart
		List<String>allProducts= searchResultPage.getAllProductsFromAllPages();
		searchResultPage.addLastProductToCart(allProducts);
		
		//verify if the item has been added
		int numItemsAdded= searchResultPage.getNumItemsAddedToCart();
		softAssert.assertEquals(numItemsAdded, 1, "Number of items added to the cart should be 1." );
		softAssert.assertAll();
		
	}
	
	
}
