package com.thinkitiveAssignment.Assignment2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.thinkitiveAssignment.testBase.TestBase;
import com.thinkitiveAssignment.uiActions.HomePage;
import com.thinkitiveAssignment.uiActions.SearchResultPage;

public class TestProgram extends TestBase{
	
	@BeforeTest
	public void setup()
	{
		initialize();
	}

	@DataProvider(name="searchProduct")
	public Object getData()
	{
		Object[] product= {"iPhone"};

		return product;
	}

	@Test(dataProvider="searchProduct")
	public void testAlibabaSite(String product) throws InterruptedException
	{

		driver.get("https://www.alibaba.com");
		driver.findElement(By.xpath("(//input[@class='ui-searchbar-keyword'])[1]")).sendKeys(product);
		driver.findElement(By.xpath("(//input[@class='ui-searchbar-submit'])[1]")).submit();
		Thread.sleep(30000);
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='62582731941']/child::a[@class='organic-gallery-offer__img-section']"))).build().perform();
		Thread.sleep(30000);
		driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='62582731941']/child::a[@class='organic-gallery-offer__img-section']/div[@class='seb-img-switcher J-img-switcher']/span[@class='seb-img-switcher__arrow-right']")).click();
		Thread.sleep(3000);
	 	String link = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='62582731941']/child::a[@class='organic-gallery-offer__img-section']/div[@class='seb-img-switcher J-img-switcher']/div[@class='seb-img-switcher__imgs']")).getAttribute("data-image");
		System.out.println(link);
		

	}


	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
