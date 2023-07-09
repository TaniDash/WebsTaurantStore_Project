package com.WebstaurantStore.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CartFuntionalityPage {

	private WebDriver driver;

	public CartFuntionalityPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[contains(text(), 'View Cart')]")
	private WebElement viewCart;
	@FindBy(how = How.XPATH, using = "//h1[contains(text(), 'Cart')]")
	private WebElement cartPageHeader;
	@FindBy(how = How.XPATH, using = "//button[contains(text(), 'Empty Cart')]")
	private WebElement emptyCartButton;
	@FindBy(how = How.XPATH, using = "//p[@id='empty-cart-body']")
	private WebElement emptyCardBody;
	@FindBy(how = How.XPATH, using = "//footer/button[@type='button' and contains(text(), 'Empty')]")
	private WebElement emptyCartButtonInPopUp;
	@FindBy(how = How.XPATH, using = "//div[@role='alertdialog']//div")
	private WebElement alertDialogBox;
	@FindBy(how = How.XPATH, using = "//p[@class='header-1']")
	private WebElement cartEmptyHeaderEle;
	


	public boolean validateCartPage() throws InterruptedException {
		viewCart.click();
		Thread.sleep(1500);
		return cartPageHeader.isDisplayed();
	}

	public void emptyCartFunctionality() throws InterruptedException {
	    if (emptyCartButton.isDisplayed() && emptyCartButton.isEnabled()) {
	        emptyCartButton.click();
	        Thread.sleep(1000);
	        
	        // Check if the pop-up box is displayed
	        if (alertDialogBox.isDisplayed()) {
	            String alertText = alertDialogBox.getText();
	            System.out.println("Pop-up Box Text: " + alertText);
	            
	            // Perform necessary actions to close the pop-up box
	            emptyCartButtonInPopUp.click();
	            Thread.sleep(1000);
	        }
	    }
	}

	public boolean validateCartIsEmpty() {
		return cartEmptyHeaderEle.isDisplayed();
	}

}
