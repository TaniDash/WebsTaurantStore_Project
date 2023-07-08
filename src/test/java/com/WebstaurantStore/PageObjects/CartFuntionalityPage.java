package com.WebstaurantStore.PageObjects;

import org.openqa.selenium.Alert;
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

	@FindBy(how = How.XPATH, using = "//span[@id='cartItemCountSpan']")
	private WebElement cartItemCount;
	@FindBy(how = How.XPATH, using = "//h1[contains(text(), 'Cart')]")
	private WebElement cartPageHeader;
	@FindBy(how = How.XPATH, using = "//button[contains(text(), 'Empty Cart')]")
	private WebElement emptyCartButton;
	@FindBy(how = How.XPATH, using = "//*[@id='td']/div[11]/div/div/div/footer/button[1]")
	private WebElement emptyButtonInPopUp;


	public boolean validateCartPage() {
		cartItemCount.click();
		return cartPageHeader.isDisplayed();
	}

	public void emptyCartFuntionality() {
		if (getCartItemCount()>0) {
			emptyCartButton.click();
			confirmEmptyCart();
		}
	}

	public int getCartItemCount() {
		return Integer.parseInt(cartItemCount.getText());
	}

	private void confirmEmptyCart() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		if (alertText.equals("Are you sure you want to empty your cart?")) {
			alert.accept();
		} else {
			System.out.println("Unexpected alert message: " + alertText);
			alert.dismiss();
		}
	}

}
