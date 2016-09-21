package com.aim.test;

import static com.aim.test.DriverScript.APP_LOGS;
import static com.aim.test.Keywords.dr;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;


public class BusinessFunctions {
	
	
	public WebElement wprice;
	
	
	public String validateText(String object,String data)
	{
		
		APP_LOGS.debug("Business Function Invoked");
		APP_LOGS.debug(object);
		APP_LOGS.debug(data);
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String matchText(String object,String data)
	{
		
		APP_LOGS.debug("Test String is Matched");
		APP_LOGS.debug(object);
		APP_LOGS.debug(data);
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public static void transactionNavigation(String object,String data,String locator) throws InterruptedException
	{
	
		
		dr.switchTo().frame("company-info");
			
		Thread.sleep(10000);
		dr.findElement(By.xpath("//div[@class='right']//a[.='Sign in']")).click();
		
		Thread.sleep(10000);
		dr.switchTo().frame("company-info");
		
		dr.findElement(By.xpath("//div/input[1]")).sendKeys("swamy.ch07+2@gmail.com");
		dr.findElement(By.xpath("//div/input[2]")).sendKeys("swamy123$");
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		dr.findElement(By.linkText("Sign In")).click();
       
		Thread.sleep(10000);
		dr.switchTo().frame("company-info");
		dr.findElement(By.linkText("StockList")).click();
		Thread.sleep(10000);
		dr.switchTo().frame("company-info");
		dr.findElement(By.linkText("Valuation")).click();
		Thread.sleep(10000);
		dr.switchTo().frame("company-info");
		dr.findElement(By.linkText("TRANSACTIONS")).click();
    		//dr.close();
		Thread.sleep(10000);
		addTransaction();
		
	}

	
	public static void addTransaction() throws InterruptedException
	{
		
		String company="S68";
		String transaction="BUY";
		String tdate="2016/08/17";
		int shares=10;
		double Price=10;
		int iterations=2;
		
		for(int i=0;i<=iterations;i++)
		{
		Select drop=new Select(dr.findElement(By.id("watchlistCompaniesSelect")));
	drop.selectByValue(company);
	Actions act=new Actions(dr);
	act.moveToElement(dr.findElement(By.id("watchlistCompaniesSelect"))).click().perform();
	
	Select type=new Select(dr.findElement(By.className("select-type")));
	type.selectByValue(transaction);
	
	dr.findElement(By.id("tradeDate")).sendKeys(tdate);	
	
	dr.findElement(By.id("initialNumberOfShares")).sendKeys(String.valueOf(shares));
	
	dr.findElement(By.id("initialCostAtPurchase")).sendKeys(String.valueOf(Price));
	dr.findElement(By.linkText("Add")).click();
	
	
		System.out.println("Clicked"+i);
		Thread.sleep(10000);
		}
		
	}
	
	
	public String verifyPrice(String object,String data)
	{
	
		wprice=dr.findElement(By.xpath(object));
		
		if(wprice.getText().equals("-") || wprice.getText().equals("") )
		{
			return Constants.KEYWORD_FAIL;
		
		}
		else
		{
			return Constants.KEYWORD_PASS;
			
			
		}
		
	
		
	}
	

}
