package org.iitworkforce.selenium.mmppluto.patienttests;



import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LaunchBrowser {

	public WebDriver driver;
	public String btype;
	public String url;

	public void browserLaunch(String btype, String url) throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumTraining\\Softwares\\driverexes\\chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "C:\\SeleniumTraining\\Softwares\\driverexes\\geckodriver.exe");

		if(btype.equalsIgnoreCase("Firefox") || btype.equalsIgnoreCase("ff")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.get(url);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(3));
		driver.manage().window().maximize();
	}

}
