package com.aim.test;
import static com.aim.test.DriverScript.APP_LOGS;

import static com.aim.test.DriverScript.CONFIG;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Keywords extends BusinessFunctions


{
	public BusinessFunctions bf;
	public Method[] bfunc;
	public static WebDriver dr;
	

			
	public String Start_TestCase(String object,String data,String locator)
	{
		String browser = CONFIG.getProperty("browserType");
		String URL=CONFIG.getProperty("Application_URL");
		try{
			System.out.println("StartingTestCase");
		openBrowser(browser);
		navigate(URL);
		}
		catch(Exception e)
		{
			
			
		}
		return Constants.KEYWORD_PASS;
	}
	
	public String Stop_TestCase(String object,String data,String locator)
	{
		try {
			Thread.sleep(10000);
			dr.close();
			System.out.println("ClosedBroswer");
		} catch (InterruptedException e)
		{
			
			e.printStackTrace();
		}
		
		
		return Constants.KEYWORD_CLOSED;
		
	}
	public String openBrowser(String data)
	{
		
			
		if(data.equals("Mozilla"))
		{
			dr=new FirefoxDriver();
		}
		else if (data.equals("IE"))
		{
			
		dr=new InternetExplorerDriver();
		}
		
		else if (data.equals("Chrome"))
		{
			
			
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "//BrowserDrivers//chrome//chromedriver.exe");
			dr=new ChromeDriver();
			
			
			
			
		}
	
		return Constants.KEYWORD_PASS;
	}
	
	public String navigate(String data)
	{
		
		    try
		    {
		dr.navigate().to(data);
		dr.manage().window().maximize();
	
		    }
		    catch(Exception e)
		    {
		    	return Constants.KEYWORD_FAIL+"---Not able to Navigate";
		    	
		    }
		return Constants.KEYWORD_PASS;
		
	}
	
	
	public String switchingFrame(String object,String data,String locator)
	{
			APP_LOGS.debug("Switching to Frame");
		    APP_LOGS.debug(data);
		    try
		    {
		//dr.navigate().to(data);
		    	
		    	
			dr.switchTo().frame(object);
		APP_LOGS.debug("Object="+object);
		    }
		    catch(Exception e)
		    {
		    	return Constants.KEYWORD_FAIL+"---Not able to Navigate";
		    	
		    }
		    System.out.println("SwitchingFrame"+object);
		return Constants.KEYWORD_PASS;
		
	}
	
	public String writeInput(String object,String data,String locator)
	{
		
		
		switch(locator)
		{
		case "id":
		{
			
		  dr.findElement(By.id(object)).sendKeys(data);
		
			break;
		}
		case "name":
		{
		
		  dr.findElement(By.name(object)).sendKeys(data);
			break;
		}
		case "xpath":
		{
			System.out.println("Entering Input:"+data);
		  dr.findElement(By.xpath(object)).sendKeys(data);
			break;
		}
		case "linkText":
		{
		
		  dr.findElement(By.linkText(object)).sendKeys(data);
			break;
		}
		case "partialLinkText":
		{
		
		  dr.findElement(By.partialLinkText(object)).sendKeys(data);
			break;
		}
		case "tagName":
		{
			
		  dr.findElement(By.linkText(object)).sendKeys(data);
			break;
		}
		case "className":
		{
			
		  dr.findElement(By.className(object)).sendKeys(data);
			break;
		}
		case "cssSelector":
		{
		
		  dr.findElement(By.cssSelector(object)).sendKeys(data);
		  break;
		}
			default:
				APP_LOGS.debug("No Locator Found");
		
		
		}
		  return Constants.KEYWORD_PASS;
		  
		  
		  
		  
		
	}
	
	public String passValueinDropDown(String object,String data,String locator)
	{
		
		
		switch(locator)
		{
		case "id":
		{
			
		  dr.findElement(By.id(object)).sendKeys(data);
		
			break;
		}
		case "name":
		{
		
		  dr.findElement(By.name(object)).sendKeys(data);
			break;
		}
		case "xpath":
		{
			System.out.println("Entering Input:"+data);
		  dr.findElement(By.xpath(object)).sendKeys(data);
			break;
		}
		case "linkText":
		{
		
		  dr.findElement(By.linkText(object)).sendKeys(data);
			break;
		}
		case "partialLinkText":
		{
		
		  dr.findElement(By.partialLinkText(object)).sendKeys(data);
			break;
		}
		case "tagName":
		{
			
		  dr.findElement(By.linkText(object)).sendKeys(data);
			break;
		}
		case "className":
		{
			
		  dr.findElement(By.className(object)).sendKeys(data);
			break;
		}
		case "cssSelector":
		{
		
		  dr.findElement(By.cssSelector(object)).sendKeys(data);
		  break;
		}
			default:
				APP_LOGS.debug("No Locator Found");
		
		
		}
		  return Constants.KEYWORD_PASS;
		  
		  
		  
		  
		
	}
	
	public String clickButton(String object,String data,String locator)
	{
	
		
			switch(locator)
			{
			case "id":
			{
			
			  dr.findElement(By.id(object)).click();
			
				break;
			}
			case "name":
			{
				
			
			
				break;
			}
			case "xpath":
			{
			
				System.out.println(object);
				dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				dr.findElement(By.xpath(object)).click();
					
				break;
			}
			case "linkText":
			{
				
				
				break;
			}
			case "partialLinkText":
			{
			
			  dr.findElement(By.partialLinkText(object)).click();
				break;
			}
			case "tagName":
			{
				  dr.findElement(By.linkText(object)).click();
				break;
			}
			case "className":
			{
				  dr.findElement(By.className(object)).click();
				break;
			}
			case "cssSelector":
			{
				 
			  dr.findElement(By.cssSelector(object)).click();
			  break;
			}
				default:
					APP_LOGS.debug("No Locator Found");
			
			
			}
			  return Constants.KEYWORD_PASS;
	
	}
			  public String clickLink(String object,String data,String locator)
				{
					
					APP_LOGS.debug("Locator="+locator);
					APP_LOGS.debug("Object="+object);
					APP_LOGS.debug("Data="+data);
					try{
						switch(locator)
						{
						case "id":
						{
						
						  dr.findElement(By.id(object)).click();
						
							break;
						}
						case "name":
						{
						
						  dr.findElement(By.name(object)).click();
							break;
						}
						case "xpath":
						{
						dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						dr.findElement(By.xpath(object)).click();
							break;
						}
						case "linkText":
						{
							dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							 dr.findElement(By.linkText(object)).click();
							break;
						}
						case "partialLinkText":
						{
						
						  dr.findElement(By.partialLinkText(object)).click();
							break;
						}
						case "tagName":
						{
							  dr.findElement(By.tagName(object)).click();
							break;
						}
						case "className":
						{
							  dr.findElement(By.className(object)).click();
							break;
						}
						case "cssSelector":
						{
							 
						  dr.findElement(By.cssSelector(object)).click();
						  break;
						}
							default:
								APP_LOGS.debug("No Locator Found");
						
						
						}
					}
					catch(Exception e)
					{
						return Constants.KEYWORD_FAIL+"--Element Not Found---";
						
					}
						  return Constants.KEYWORD_PASS;
				
				
		
	}
			  
			  
		
			  public String verifyLinkText(String object,String data,String locator)
				{
					String actual,expected;
					expected=data;
					String result=null;
					
											switch(locator)
											{
											case "id":
											
											{			
					actual= dr.findElement(By.id(object)).getText();
							try
							{
							if(actual.equals(expected))
							{
								result="Pass";
								
							}
							
							else
							{
								result="Fail";
								
							}
						}
							catch(Exception e)
							{
								
								result="Fail";
								
							}
						
											}	
											
											case "name":			
											{			
												actual= dr.findElement(By.name(object)).getText();
														try
														{
														if(actual.equals(expected))
														{
															result="Pass";
															
														}
														
														else
														{
															result="Fail";
															
														}
													}
														catch(Exception e)
														{
															
															result="Fail";
															
														}
													
											
											}
											
											
											case "xpath":			
											{			
												actual= dr.findElement(By.xpath(object)).getText();
														try
														{
														if(actual.equals(expected))
														{
															result="Pass";
															
														}
														
														else
														{
															result="Fail";
															
														}
													}
														catch(Exception e)
														{
															
															result="Fail";
															
														}
													
											
											}	
											
						
							
						
										}
					
						
											return result;
					
				}
				
		
			  public String pauseMethod(String object,String data,String locator) throws InterruptedException
			  {
				 
				  String time=CONFIG.getProperty("pause");
				  
				  int pausetime=Integer.parseInt(time);
				 
				  try
				  {
				  Thread.sleep(pausetime);
				  }
				  catch(Exception e)
				  {
					  
					  System.out.println(e.getMessage());
					  
				  }
				  
				  return Constants.KEYWORD_PASS;
				  
			  }
	
			  
				public void captureScreenshot(String filename, String keyword_execution_result) throws IOException{
					// take screen shots
					if(CONFIG.getProperty("screenshot_everystep").equals("Y")){
					
						if(!keyword_execution_result.startsWith(Constants.KEYWORD_CLOSED))
						{
						File scrFile = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
					    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") +"//screenshots//"+filename+".jpg"));
						}
						
					}else if (keyword_execution_result.startsWith(Constants.KEYWORD_FAIL) && CONFIG.getProperty("screenshot_error").equals("Y") ){
					// capture screenshot
						if(!keyword_execution_result.startsWith(Constants.KEYWORD_CLOSED))
						{
						File scrFile = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
						
					    FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") +"//screenshots//"+filename+".jpg"));
						}
						
					}
				}
	
	

}
