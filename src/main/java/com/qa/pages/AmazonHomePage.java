package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class AmazonHomePage extends TestBase {
	public AmazonHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="twotabsearchtextbox")
	public static WebElement amazonSearchTextBox;
	
	@FindBy(xpath="//input[@value='Go']")
	public static WebElement amazonGoButton;
	
	@FindBy(xpath="//span[@class='a-badge-text' and text()='Best seller']/ancestor::*/div[@class='a-section a-spacing-medium']//a[@class='a-link-normal a-text-normal']")
	public static List<WebElement> bestSellerProducts;
		
}
