package com.thinkitiveAssignment.testBase;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.thinkitiveAssignment.utils.TextFileWriter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	
	
	public void initialize() //This method initialize webDriver
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
	/*
	 *writeProductDetailsInFile method take two arguments
	 *1. File Name
	 *2. Product details as key value pairs
	 *It is used to write product details in text file
	 *It uses TextFileWriter class methods to write content in text file 
	 */
	public void writeProductDetailsInFile(String fileName, Map<String,String> productDetails) throws IOException
	{
		TextFileWriter write = new TextFileWriter(fileName);
		
		for(String parameters:productDetails.keySet())
		{
			write.writeFile(parameters+" = "+productDetails.get(parameters)+"\n");
		}
		
		write.closeFileWriting();
		
		
		
	}
}
