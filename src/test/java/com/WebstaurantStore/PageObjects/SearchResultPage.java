package com.WebstaurantStore.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {
	private WebDriver driver;

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//h1[@class='page-header search--title']")
	private WebElement searchPageHeaderTitle;
	@FindBy(how = How.XPATH, using = "//div[@id='main']//div[@id='product_listing']//div//div//a//span[contains(text(), 'Table')]")
	private List<WebElement> productListing;
	@FindBy(how = How.XPATH, using = "//a[contains(@aria-label, 'page')]")
	private List<WebElement> pagesList;
	@FindBy(how = How.XPATH, using = "//li[@class='inline-block leading-4 align-top rounded-r-md']/a/*[name()='svg']")
	private WebElement nextPageButton;
	@FindBy(how = How.XPATH, using = "//h2[@class='notification__heading']")
	private WebElement notificationElement;

	public boolean validateSearchPage() {
		return searchPageHeaderTitle.isDisplayed();
	}

	public List<String> getAllProductsFromAllPages() throws InterruptedException {
		List<String> productList = new ArrayList<String>();

		int currentPage = 1;
		while (true) {
			// Find the active page element
			WebElement activePageElement = driver.findElement(By.xpath("//nav[@aria-label='pagination']/ul/li/a[contains(@aria-label, 'current')]"));
			System.out.println(activePageElement.getText());

			List<WebElement> allProductsFromPage = driver.findElements(By.xpath("//div[@id='product_listing']/div/div/a/span"));

			for (WebElement productElement : allProductsFromPage) {
				try {
					// Get the text of each product element and add it to the productList
					String productText = productElement.getText();
					productList.add(productText);
				} catch (StaleElementReferenceException e) {
					System.out.println("StaleElementReferenceException occurred while accessing an element. Skipping...");
				}
			}

			// Find all page elements
			List<WebElement> pageElements = driver.findElements(By.xpath("//nav[@aria-label='pagination']/ul/li/a"));

			// Check if there is a next page available
			boolean nextPageExists = false;
			for (WebElement pageElement : pageElements) {
				String pageText = pageElement.getText().trim();
				if (!pageText.isEmpty() && pageText.matches("\\d+") && Integer.parseInt(pageText) == currentPage + 1) {
					nextPageExists = true;
					pageElement.click();
					break;
				}
			}

			if (!nextPageExists) {
				System.out.println("No more pages found. Exiting loop...");
				break;
			}
			// Wait for the page to load before proceeding
			Thread.sleep(1000);
			currentPage++;
		}
		return productList;
	}

	public int validateProductTitlesContainKeyword(List<String> productList, String keyword) {
		int count= 0;
		for (String productTitle : productList) {
			if (!productTitle.contains(keyword)) {
				count++;
				System.out.println("Product title without the keyword: " + productTitle);
			}
		}
		return count; 
	}

	public void addLastProductToCart(List<String> productList) throws InterruptedException {
		try {
			if (!productList.isEmpty()) {
				String lastProduct = productList.get(productList.size() - 1);
				System.out.println("Adding the last product to the cart: " + lastProduct);

				WebElement addToCartButton = driver.findElement(By.xpath("//div[@id='product_listing']/div['" + lastProduct + "']//div[4]//form/div/div/input[2]"));

				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", addToCartButton);
				if (addToCartButton.isDisplayed() && addToCartButton.isEnabled()) {
					addToCartButton.click();
					Thread.sleep(1500);

					System.out.println("The last product has been added to the cart.");
				} else {
					System.out.println("The 'Add to Cart' button is not available for the last product.");
				}
			} else {
				System.out.println("No products found. Unable to add the last product to the cart.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getNumItemsAddedToCart() {
		if (notificationElement.isDisplayed()) {
			String notificationText = notificationElement.getText();
			System.out.println("Notification: " + notificationText);

			// Extract the number of items added from the notification text
			return extractNumItemsAdded(notificationText);
		}
		return 0; // Return 0 if the notification element is not displayed or not found
	}

	private int extractNumItemsAdded(String notificationText) {
		// Extract the number of items added from the notification text
		String[] parts = notificationText.split(" ");
		String numItemsStr = parts[0];
		return Integer.parseInt(numItemsStr);
	}

}