package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	
	private ElementUtil eleUtil;
	
	private By productHeader= By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By qty = By.id("input-quantity");
	private By addToCartBtn=By.id("button-cart");
	
	private Map<String,String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver) {
		
		eleUtil=new ElementUtil(driver);
		
	}
	
	public String getProductHeader() {
		String header=eleUtil.doGetText(productHeader);
		return header;
	}
	
	public int getProductImagesCount() {
		List<WebElement> productimages=eleUtil.waitForElementsToBeVisible(productImages, 10);
		int imagescount =productimages.size();
		System.out.println("Images count is : " +imagescount);
		return imagescount;
	}
	
	public Map<String, String> getProductInfo() {
		
		productInfoMap= new LinkedHashMap<String,String>();
		productInfoMap.put("name", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
		
	}
	
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		
		for (WebElement e: metaDataList) {
			 String text =e.getText();
			 String meta[]=text.split(":");
			 String metaKey=meta[0].trim();
			 String metaValue =meta[1].trim();
			 productInfoMap.put(metaKey, metaValue);
		}
	}
	
private void getProductPriceData() {
		List<WebElement> metaPriceList= eleUtil.getElements(productPriceData);
		String price=metaPriceList.get(0).getText();
		String exPrice =metaPriceList.get(1).getText();
		productInfoMap.put("price", price);
		productInfoMap.put("ExtaxPrice", exPrice);
		
		
	}
	



}
