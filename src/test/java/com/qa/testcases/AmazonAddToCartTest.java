package com.qa.testcases;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.AmazonHomePage;
import com.qa.pages.AmazonProductDetailPage;


public class AmazonAddToCartTest extends TestBase{
	AmazonHomePage amazonHomePage;
	AmazonProductDetailPage amazonProductDetailPage;
    private String amazonUrl="https://www.amazon.in/";
	private String productName="Headphones";
	List<WebElement> productWithBestSellerBadge;



	@BeforeTest
	public void setUp() throws FileNotFoundException {
		openBrowser();
		launchAut(amazonUrl);
		amazonHomePage= new AmazonHomePage();
		amazonProductDetailPage=new AmazonProductDetailPage();
	}

	@Test(priority=1,description=" Verifying user landed on Amazon Home Page")
	public void t001_verifyUserIsOnHomePage()
	{
		Assert.assertEquals(driver.getTitle(), "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
	}


	@Test(priority=2,dependsOnMethods="t001_verifyUserIsOnHomePage",description="Verifying if some Best seller product available in search result")
	public void t002_searchProduct()
	{
		AmazonHomePage.amazonSearchTextBox.sendKeys(productName);
		AmazonHomePage.amazonGoButton.click();
		/*Getting WebElements with BestSeller product*/
		productWithBestSellerBadge=AmazonHomePage.bestSellerProducts;
		Assert.assertNotEquals(productWithBestSellerBadge.size(), 0, "No Product found From Best seller.");

	}
	
	
	@Test(priority=3,dependsOnMethods="t002_searchProduct",description="Adding Product with Best seller badge to cart")
	public void t003_addProductToCart() throws InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		String firstWindowHandle=driver.getWindowHandle();
		
		/*looped to add all the items with Best Sellers to cart*/
		productWithBestSellerBadge.forEach(bestWithBestSeller ->{

			wait.until(ExpectedConditions.visibilityOf(bestWithBestSeller));
			bestWithBestSeller.click();
			List<String> tabs=new ArrayList<String>(driver.getWindowHandles());
			if(firstWindowHandle.equalsIgnoreCase(tabs.get(0))){
				driver.switchTo().window(tabs.get(1));	
			}	
			try{
				amazonProductDetailPage.regularBuyBox.isDisplayed();
				amazonProductDetailPage.regularPriceRadioButton.click();
							}
			catch(NoSuchElementException e){
				System.out.println("No such Element expection "+e.getAdditionalInformation());
			}
			wait.until(ExpectedConditions.visibilityOf(amazonProductDetailPage.addToCartButton));
			wait.until(ExpectedConditions.elementToBeClickable(amazonProductDetailPage.addToCartButton));
			/* Click on Add to card button */
			amazonProductDetailPage.addToCartButton.click();
			/* Waiting to see Added to card is displayed */
			wait.until(ExpectedConditions.visibilityOf(amazonProductDetailPage.addedToCart));
			/* closing the current tab */
			driver.close();
			/* Switched to Searh result tab */
			driver.switchTo().window(firstWindowHandle);
		});

	}



	@AfterTest
	public void closeSetUp()
	{
		driver.quit();
	}

}
