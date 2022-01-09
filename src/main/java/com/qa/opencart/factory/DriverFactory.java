package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	public WebDriver init_driver (Properties prop) {
		 String BrowserName= prop.getProperty("browser").trim();
		System.out.println("Browser name is " +BrowserName);
		
		highlight = prop.getProperty("highlight");
		optionsManager=new OptionsManager(prop);
		
		if (BrowserName.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			//driver= new ChromeDriver(optionsManager.getChromeOptions());;
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
					}
		
		else if (BrowserName.equals("fifrefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//driver= new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
					}
		else if (BrowserName.equals("safari"))
		{
			WebDriverManager.safaridriver().setup();
			//driver= new SafariDriver();
			tlDriver.set(new SafariDriver() );
					}
		else {
			System.out.println("Please pass the correct browser");
		}
		
		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	/**
	 * getDriver(): This will return thread local copy of driver
	 * @return 
	 * @return
	 */
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties init_prop()  {
		
		prop=new Properties();
		FileInputStream fp=null;
		
		String envName=System.getProperty("env");
		
		if (envName==null) {
			try {
				fp=new FileInputStream (".\\src\\test\\resources\\config\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else {
			switch (envName) {
			case "qa":
				try {
					fp=new FileInputStream (".\\src\\test\\resources\\config\\qa.config.properties");
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				break;
			case "dev":
				try {
					fp=new FileInputStream (".\\src\\test\\resources\\config\\dev.config.properties");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "stage":
				try {
					fp=new FileInputStream (".\\src\\test\\resources\\config\\stage.config.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Please pass correct environmet");
				break;
			}
		
		}

		try {
			prop.load(fp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
		
	}
	
	/**
	 * take screenshot
	 * @return 
	 */
	
	public String getScreenshot() {
		File src=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path= System.getProperty("user.dir")+("/screenshots/")+System.currentTimeMillis()+(".png");
		File destination=new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}

}
