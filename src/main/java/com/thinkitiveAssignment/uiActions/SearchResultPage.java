package com.thinkitiveAssignment.uiActions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.thinkitiveAssignment.testBase.TestBase;

public class SearchResultPage extends TestBase{

	String productID="";

	public SearchResultPage() throws InterruptedException
	{
		getMaxPriceProduct();

	}


	public void getMaxPriceProduct() throws InterruptedException
	{
		Thread.sleep(30000);
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[contains(@data-pid,'')]"));
		float maxPrice=0;

		for(WebElement ele:elements)
		{
			if(ele.getAttribute("data-pid")!=null)
			{
				String price = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+ele.getAttribute("data-pid")+"']/descendant::span[@class='elements-offer-price-normal__price']")).getText();
				System.out.println("Fetching Price of all Products");
				float productPrice=0;
				if(price.contains("-"))
				{
					String[] productPrices = price.split("-");
					for(int i=0;i<productPrices.length;i++)
					{
						if(Float.valueOf(productPrices[i].replaceAll("\\$", ""))>productPrice)
							productPrice=Float.valueOf(productPrices[i].replaceAll("\\$", ""));
					}
				}else
				{
					productPrice=Float.valueOf(price.replaceAll("\\$", ""));
				}

				if(productPrice>maxPrice)
				{
					maxPrice=productPrice;
					productID=ele.getAttribute("data-pid");
				}

			}
		}
		System.out.println("MaxPrice Product ID: "+productID+"---- Max Price Product price"+maxPrice);
		System.out.println("Fetched Max Price Product");
	}


	public String getProductName()
	{
		System.out.println("Fetching: Product name");
		String productTitle = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer-section__title']/h4")).getAttribute("title");
		return productTitle;
	}

	public String getTitleLink()
	{
		System.out.println("Fetching: Title Link");
		String productTitleLink = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer-section__title']/h4/a")).getAttribute("href");
		return productTitleLink;
	}

	public String getTextBelowProductTitle()
	{
		System.out.println("Fetching: Text Below Product Title");
		String textBelowTitle = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer-section__title']/h4/div/p")).getText();
		return textBelowTitle;
	}

	public String getTagsBelowProductTitle()
	{
		System.out.println("Fetching: Tags Below Product Title");
		List<WebElement> tags = driver.findElements(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer-section__title']/div/span/span[@class='tags-below-title__chuc tag-style-orange']"));

		StringBuilder pTags = new StringBuilder();
		if(!tags.isEmpty())
		{
			for(WebElement ele:tags)
			{
				pTags.append(ele.getText()+", ");
			}

		}
		else
			pTags.append("N/A");
		return pTags.toString();
	}

	public String getImageCount()
	{
		System.out.println("Fetching: Image Count");
		String countString = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::a[@class='organic-gallery-offer__img-section']/div[@class='seb-img-switcher J-img-switcher']/div/div/span[@class='seb-img-switcher__icon-multi-img']")).getText();
		String imageCount = countString.substring(countString.lastIndexOf("/") + 1);
		return imageCount;
	}

	public Map<String, String> getImagesLinks() throws InterruptedException
	{
		System.out.println("Fetching: Images Links");
		int imageCount =Integer.valueOf(getImageCount()) ;
		Map<String, String> links = new HashMap<String, String>();
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::a[@class='organic-gallery-offer__img-section']"))).build().perform();
		Thread.sleep(30000);
		for(int i=0;i<imageCount;i++)
		{

			driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::a[@class='organic-gallery-offer__img-section']/div[@class='seb-img-switcher J-img-switcher']/span[@class='seb-img-switcher__arrow-right']")).click();
			Thread.sleep(3000);
			String link = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::a[@class='organic-gallery-offer__img-section']/div[@class='seb-img-switcher J-img-switcher']/div[@class='seb-img-switcher__imgs']")).getAttribute("data-image");

			links.put("Image Link "+(i+1), link);

		}

		System.out.println(links);
		return links;

	}

	public String getProductPrice()
	{
		System.out.println("Fetching: Product Price");
		String productPrice = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer-section__price']/p/span[@class='elements-offer-price-normal__price']")).getText();
		System.out.println(productPrice);
		return productPrice;
	}

	public String getSlashedProductPrice()
	{
		System.out.println("Fetching: Slashed Product Price");
		String slashedProductPrice;
		WebElement spp;

		try {
			spp = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer-section__price']/div/span[@class='elements-offer-slashprice-normal__price']"));
			slashedProductPrice = spp.getText();
		} catch (Exception e) {
			slashedProductPrice = "N/A";
		}

		return slashedProductPrice;
	}

	public String getProductPriceDiscount()
	{
		System.out.println("Fetching: Product Price Discount");
		String discount;
		WebElement priceDiscount; 

		try {
			priceDiscount = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer-section__price']/div/span[@class='elements-offer-slashprice-normal__discount']"));
			discount = priceDiscount.getText();
		} catch (Exception e) {
			discount = "N/A";
		}

		return discount;
	}

	public String getMinimumOrderQuantity()
	{
		System.out.println("Fetching: Minimum Order Quantity");
		String moq = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer-section__price']/p[@data-e2e-name='minOrder']/span[@class='element-offer-minorder-normal__value']")).getText();

		return moq;
	}

	public String getProductSellerCountryCode()
	{
		System.out.println("Fetching: Product Seller Country Code");
		String sellerCountry = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer_bottom-align-section']/div[@class='organic-gallery-offer__seller-section']/div[@class='organic-gallery-offer__minisite-container']/span")).getText();
		return sellerCountry;
	}


	public String getSellerCompanyName()
	{
		System.out.println("Fetching: Seller Company Name");
		String sellerCompanyName = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer_bottom-align-section']/div[@class='organic-gallery-offer__seller-section']/div[@class='organic-gallery-offer__minisite-container']/a")).getText();
		return sellerCompanyName;
	}

	public String getSellerCompanyLink()
	{
		System.out.println("Fetching: Seller Company Link");
		String sellerCompanyLink = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer_bottom-align-section']/div[@class='organic-gallery-offer__seller-section']/div[@class='organic-gallery-offer__minisite-container']/a")).getAttribute("href");
		return sellerCompanyLink;
	}

	public String getSellerYearOfAssociation()
	{
		System.out.println("Fetching: Seller Year Of Association");
		String sellerYOA = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer_bottom-align-section']/div[@class='organic-gallery-offer__seller-section']/div[@class='organic-gallery-offer-section__seller-tags']/span")).getText();
		return sellerYOA;
	}

	public String getSellerStatus()
	{
		System.out.println("Fetching: Seller Status");
		String sellerStatus=""; 
		WebElement status;

		try {
			status = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer_bottom-align-section']/div[@class='organic-gallery-offer__seller-section']/div[@class='organic-gallery-offer-section__seller-tags']/a/i[@class='ui2-icon-svg ui2-icon-svg-xs ui2-icon-svg-gold-supplier']"));
			if(status.isDisplayed())
				sellerStatus = "Is Gold Seller";
		} catch (Exception e) {
			sellerStatus = "N/A";
		}
		return sellerStatus;
	}

	public String getProductTradeAssuredStatus()
	{
		System.out.println("Fetching: Product Trade Assured Status");
		String productTradeAssuredStatus=""; 
		WebElement status;
		try {
			status = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer_bottom-align-section']/div[@class='organic-gallery-offer__seller-section']/div[@class='organic-gallery-offer-section__seller-tags']/a/i[@class='ui2-icon-svg ui2-icon-svg-xs ui2-icon-svg-trade-assurance']"));
			if(status.isDisplayed())
				productTradeAssuredStatus = "Is Trade Assured";
		} catch (Exception e) {
			productTradeAssuredStatus = "N/A";
		}

		return productTradeAssuredStatus;
	}

	public String getSellerStarLevel()
	{
		System.out.println("Fetching: Seller Star Level");
		List<WebElement> starLevel = driver.findElements(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer_bottom-align-section']/div[@class='organic-gallery-offer__seller-section']/div[@class='organic-gallery-offer-section__seller-tags']/a/i[@class='iconfont iconzuanshi seller-star-level__dm dm-orange']"));

		return String.valueOf(starLevel.size());
	}

	public String getSellerResopnseRate()
	{
		System.out.println("Fetching: Seller Resopnse Rate");
		String sellerResponseRate = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer_bottom-align-section']/div[@class='organic-gallery-offer__seller-section']/div[@class='organic-gallery-offer-section__reviews']/span")).getText();
		return sellerResponseRate;
	}

	public String getSellerReviewScore()
	{
		System.out.println("Fetching: Seller Review Score");
		String sellerReviewScore = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer_bottom-align-section']/div[@class='organic-gallery-offer__seller-section']/div[@class='organic-gallery-offer-section__reviews']/div/a/span[@class='seb-supplier-review__score']")).getText();
		return sellerReviewScore;
	}

	public String getSellerReviewCount()
	{
		System.out.println("Fetching: Seller Review Count");
		String sellerReviewCount = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer_bottom-align-section']/div[@class='organic-gallery-offer__seller-section']/div[@class='organic-gallery-offer-section__reviews']/div/a/span[@class='seb-supplier-review__review-count']")).getText();
		return sellerReviewCount;
	}


	public String getSellerReviewComments()
	{
		System.out.println("Fetching: Seller Review Comments");
		StringBuilder sellerReviews = new StringBuilder();
		List<WebElement> reviews = driver.findElements(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer_bottom-align-section']/div[@class='organic-gallery-offer__seller-section']/div[@class='organic-gallery-offer-section__reviews']/div/div/a"));
		System.out.println(reviews.size());
		for(int i=0;i<reviews.size();i++)
		{
			sellerReviews.append(driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer_bottom-align-section']/div[@class='organic-gallery-offer__seller-section']/div[@class='organic-gallery-offer-section__reviews']/div/div/a["+(i+1)+"]")).getText()+", ");
		}

		return sellerReviews.toString();
	}

	public String getSellerContactLink()
	{
		System.out.println("Fetching: Seller Contact Link");
		String sellerContactLink = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer_bottom-align-section']/div[@class='organic-gallery-offer__bottom-v2']/a[@class='contact-supplier-btn organic-gallery-offer__bottom-v2-item small']")).getAttribute("href");
		return sellerContactLink;
	}

	public String getSellerContactOption()
	{
		System.out.println("Fetching: Seller Contact Option");
		String contactoption = driver.findElement(By.xpath("//div[@class='organic-gallery-offer-outter J-offer-wrapper']/div[@data-pid='"+productID+"']/child::div[@class='organic-gallery-offer_bottom-align-section']/div[@class='organic-gallery-offer__bottom-v2']/a[@class='atm organic-gallery-offer__bottom-v2-item']/div")).getAttribute("title");
		return contactoption;
	}

	public Map<String, String> fetchData() throws InterruptedException
	{
		System.out.println("Fetching product details started.......");
		Map<String, String> productDetails = new HashMap<String, String>();

		productDetails.put("Product Title", getProductName());
		productDetails.put("Product Title Link", getTitleLink());
		productDetails.put("Text Below Product Title", getTextBelowProductTitle());
		productDetails.put("Tags Below Product Title", getTagsBelowProductTitle());
		productDetails.put("Product Image Count", getImageCount());
		productDetails.putAll(getImagesLinks());
		productDetails.put("Product Price", getProductPrice());
		productDetails.put("Slashed Product Price", getSlashedProductPrice());
		productDetails.put("Product Price Discount", getProductPriceDiscount());
		productDetails.put("Product Minimum order Quantity", getMinimumOrderQuantity());
		productDetails.put("Product Seller Country Code", getProductSellerCountryCode());
		productDetails.put("Seller Company Name", getSellerCompanyName());
		productDetails.put("Seller Company Link", getSellerCompanyLink());
		productDetails.put("Seller Years Of Association", getSellerYearOfAssociation());
		productDetails.put("Seller Status", getSellerStatus());
		productDetails.put("Product Trade Assured Status", getProductTradeAssuredStatus());
		productDetails.put("Seller Star Level", getSellerStarLevel());
		productDetails.put("Seller Response Rate", getSellerResopnseRate());
		productDetails.put("Seller Review Score", getSellerReviewScore());
		productDetails.put("Seller Review Count", getSellerReviewCount());
		productDetails.put("Seller Review Comments", getSellerReviewComments());
		productDetails.put("Seller Contact Link", getSellerContactLink());
		productDetails.put("Seller Contact Option", getSellerContactOption());

		return productDetails;


	}


}
