package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class AmazonProductDetailPage extends TestBase {
	public AmazonProductDetailPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="add-to-cart-button")
	public static WebElement addToCartButton;
	
	@FindBy(xpath="//i[@class='a-icon a-accordion-radio a-icon-radio-inactive']")
	public static WebElement regularPriceRadioButton;
	
	@FindBy(id="regularBuybox")
	public static WebElement regularBuyBox;
	
	@FindBy(xpath="//h1[@class='a-size-medium a-text-bold'][contains(text(),'Added to Cart')]")
	public static WebElement addedToCart;
			
}
