package com.test.shop.SampleTest.testscript;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.test.shop.SampleTest.pageobjects.HomePageObjects;

import org.testng.annotations.BeforeMethod;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class SampleProgram {
	public  static WebDriver driver;
	
  @Test
  public void f() throws Exception{

		HomePageObjects homeObjects= new  HomePageObjects(driver);
	  homeObjects.clickProduct();
	  homeObjects.clickVewAllLinks();
	  int  productsCount  = driver.findElements(By.xpath("//div[@class='product-item']")).size();
	  System.out.println(productsCount);
	  
	  Random ran = new  Random();
	  
	  for(int i=1; i<=5; i++)
	  {
		  int reqRan = ran.nextInt(productsCount);
		  System.out.println(reqRan);
		  WebElement prices = driver.findElement(By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div[3]/div/div["+reqRan+"]/div/p"));
		  String priceData = prices.getText();
		  System.out.println(priceData);
		  WebElement reqObject  = driver.findElement(By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div[3]/div/div["+reqRan+"]/div/a"));
		  reqObject.click();
		  String  onlyNumaric = priceData.substring(1, 6);
		  
	  
		   System.out.println("specific positions  " + onlyNumaric);
		   Double  pricesDouble  = Double.parseDouble(onlyNumaric);

		   System.out.println( pricesDouble* reqRan);
		   Double estimatedPrices = pricesDouble * reqRan;
		  Thread.sleep(10000);
		  WebElement  displayValue = driver.findElement(By.id("displayValue"));
		  displayValue.clear();
		  displayValue.sendKeys(Integer.toString(reqRan));
		  Thread.sleep(5000);
		   ///click add cart  button  
		  driver.findElement(By.xpath("(//button[@id='add-to-cart-button'])[2]")).click();
		  Thread.sleep(5000);
		  ///sub total prices  
		   WebElement  subTotalPrice = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[3]/div/div[4]/div[3]/div/div[1]/div[2]/div[1]/p/span[2]/span"));
		   System.out.println("from application  " + subTotalPrice.getText());
		    String priceFromApp  =subTotalPrice.getText();
		    Double priceApp = Double.parseDouble(priceFromApp);
		    if(priceApp==estimatedPrices)
		    {
		    	System.out.println("price is same of  from app  " + priceApp + "  and  estimated  " + estimatedPrices);
		    }
		    else {
				System.out.println("price is diff from estimated and app    " +  priceApp + "  and  estimated  " + estimatedPrices);
			}
		    Thread.sleep(5000);
		    /////delete
		    WebElement  deleteLink  = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[3]/div/div[4]/div[3]/div/div[1]/div[2]/div[3]/div/div/div/div[2]/div[5]/a[1]/span"));

		  deleteLink.click();
		  driver.navigate().back();
		  Thread.sleep(15000);
		  
		  
	  }

	
			  
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + 
			  "/Driver/chromedriver");
	  driver = new ChromeDriver();
	   driver.get("https://naturesway.com");
  }

  @AfterMethod
  public void afterMethod() {
	  driver.close();
  }

  @BeforeTest
  public void beforeTest() {
	  
	   System.out.println("test start");
	  
  }

  @AfterTest
  public void afterTest() {
	   System.out.println("end test");
  }

}

