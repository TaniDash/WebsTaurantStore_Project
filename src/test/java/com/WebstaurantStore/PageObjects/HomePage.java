package com.WebstaurantStore.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.WebstaurantStore.Tests.TestBase;

public class HomePage {
private WebDriver driver;

public HomePage (WebDriver driver) {
	this.driver =driver;
	PageFactory.initElements(driver, this);
}

//@FindBy (how= How.XPATH, using = "/html/head/link[7]")  private WebElement webstaurantStoreTitle; 
@FindBy (how= How.XPATH, using = "//div[3]/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/input[1]")  private WebElement searchBox; 
@FindBy (how= How.XPATH, using = "//div[3]/div[1]/div[1]/form[1]/div[1]/button[1]")  private WebElement searchButton; 



//public boolean validateHomePage() {
//	return webstaurantStoreTitle.isDisplayed();
//}

public String getCurrentURL() {
	return driver.getCurrentUrl();
}


public void searchItem(String searchItem) {
	searchBox.sendKeys(searchItem);
	searchButton.click();
}


}
