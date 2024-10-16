package commonFunctions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class FunctionLibrary 
{
	public static WebDriver driver;
	public static Properties conpro;
	public static WebDriver startBrowser() throws Throwable 
	{
		conpro = new Properties();
		conpro.load(new FileInputStream("PropertyFiles\\Environment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome")) 
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox")) 
		{
			driver = new FirefoxDriver();
		}
		else 
		{
			Reporter.log("Browser value is Not Matching",true);
		}
		return driver;
	}
		public static void openUrl() 
		{
			driver.get(conpro.getProperty("url"));
		}
		public static void waitForElement(String LocatorType, String LocatorValue,String TestData) 
		{
			WebDriverWait mywait = new WebDriverWait(driver,Duration.ofSeconds(Integer.parseInt(TestData)));
			if(LocatorType.equalsIgnoreCase("xpath")) 
			{
				mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
			}
			if(LocatorType.equalsIgnoreCase("name")) 
			{
				mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
			}
			if(LocatorType.equalsIgnoreCase("id")) 
			{
				mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
			}
		}

		public static void typeAction(String LocatorType, String LocatorValue,String TestData) 
		{
		if(LocatorType.equalsIgnoreCase("xpath")) 
		{
			driver.findElement(By.xpath(LocatorValue)).clear();
			driver.findElement(By.xpath(LocatorValue)).sendKeys(TestData);
		}
		if(LocatorType.equalsIgnoreCase("id")) 
		{
			driver.findElement(By.id(LocatorValue)).clear();
			driver.findElement(By.id(LocatorValue)).sendKeys(TestData);
		}
		if(LocatorType.equalsIgnoreCase("name")) 
		{
			driver.findElement(By.name(LocatorValue)).clear();
			driver.findElement(By.name(LocatorValue)).sendKeys(TestData);
		}
	}
		public static void clickAction(String LocatorType, String LocatorValue) 
		{
			if(LocatorType.equalsIgnoreCase("xpath")) 
			{
				driver.findElement(By.xpath(LocatorValue)).click();
			}
			if(LocatorType.equalsIgnoreCase("name")) 
			{
				driver.findElement(By.name(LocatorValue)).click();
			}
			if(LocatorType.equalsIgnoreCase("id")) 
			{
				driver.findElement(By.id(LocatorValue)).sendKeys(Keys.ENTER);
			}
		}
		public static void validateTitle(String Expected_Title) 
		{
			String Actual_title = driver.getTitle();
			try {
			Assert.assertEquals(Actual_title, Expected_Title,"Title is Not Matching");
			} catch(AssertionError a) 
			{
				System.out.println(a.getMessage());
			}
		}
		public static void closeBrowser() 
		{
			driver.quit();
		}
			
			
		
		
		
	}


