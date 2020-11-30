package com.thinkitiveAssignment.Assignment2;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.thinkitiveAssignment.testBase.TestBase;
import com.thinkitiveAssignment.uiActions.HomePage;
import com.thinkitiveAssignment.uiActions.SearchResultPage;

public class Assignment2 extends TestBase{


	@BeforeTest
	public void setup()
	{
		initialize();//To setup webdriver
	}

	//Data Provider to provide data to testAlibabaSite method. 
	@DataProvider(name="searchProduct")
	public Object getData()
	{
		Object[] product= {"iPhone"};

		return product;
	}

	/*
	 * testAlibabaSite method is used to:
	 * 1. open Alibaba site
	 * 2. search for product on site
	 * 3. Find Max price product
	 * 4. Get details of product
	 * 5. write product details in text file
	 */
	@Test(dataProvider="searchProduct") 
	public void testAlibabaSite(String product) throws InterruptedException, IOException
	{
		driver.get("https://www.alibaba.com");
		HomePage home = new HomePage();
		SearchResultPage searchPage = home.searchProduct(product);
		Map<String, String> productDetails = searchPage.fetchData();
		writeProductDetailsInFile("product_details.txt", productDetails);
		
	}


	@AfterTest
	public void tearDown()
	{
		driver.quit();//closing any open browser window
	}

	


}
