package com.test.shop.SampleTest.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePageObjects {
	public  static WebDriver driver;
	
	
	///identify the   products object  
	
	@FindBy(how =How.ID, using = "dropdown-products")
	private  WebElement productsHomePage;
	
	@FindBy(how = How.XPATH, using =  "//*[@id='button-view-all-products']")
	private  WebElement viewAllProducts;
	
	public  HomePageObjects (WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	////products links  
	public  void clickProduct()
	{
		Actions acts  = new  Actions(driver);
		acts.moveToElement(productsHomePage).click().build().perform();
		//productsHomePage.click();
	}
	//view all links  
	 public void  clickVewAllLinks()
	 {
		 viewAllProducts.click();
	 }
	

}
