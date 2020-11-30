package com.thinkitiveAssignment.uiActions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thinkitiveAssignment.testBase.TestBase;

public class HomePage extends TestBase{

	@FindBy(xpath="(//input[@class='ui-searchbar-keyword'])[1]")
	private WebElement searchTextBox;
	@FindBy(xpath="(//input[@class='ui-searchbar-submit'])[1]")
	private WebElement searchBtn;
	
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public SearchResultPage searchProduct(String product) throws InterruptedException
	{
		searchTextBox.sendKeys(product);
		searchBtn.submit();
		return new SearchResultPage();
	}
	
}
