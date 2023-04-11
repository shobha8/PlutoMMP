package org.iitworkforce.selenium.mmppluto.lib;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	protected WebDriver driver;
	protected Properties pro; 
	@BeforeClass
	public void instantiateDriver() throws IOException
	{

		ProjectConfiguration projectConfig = new ProjectConfiguration();
		pro = projectConfig.loadProperties();

		if(pro.getProperty("browserType").equals("chrome")) 
		{

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		}
		else if(pro.getProperty("browserType").equals("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
	//launchBrowser(pro.getProperty("url"));

	}
	
	public void launchBrowser(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
	}
}
