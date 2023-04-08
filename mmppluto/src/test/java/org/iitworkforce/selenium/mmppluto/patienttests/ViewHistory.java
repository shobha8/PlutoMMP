package org.iitworkforce.selenium.mmppluto.patienttests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ViewHistory {
	static WebDriver driver;
	
	@Test(description="US_008 View History",groups={"US_008","regression","sanity","patientmodule"})
	public void validate_ViewHistory() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
        driver.manage().window().maximize();
		driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		
		driver.findElement(By.id("username")).sendKeys("ria1");
		driver.findElement(By.id("password")).sendKeys("Ria12345");
		driver.findElement(By.name("submit")).click();
		
		driver.findElement(By.xpath("//span[normalize-space()='Profile']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='View History']")).click(); 
		
		boolean pastApm=driver.findElement(By.xpath("//input[@value='Past Appointments']")).isDisplayed();
		boolean pastDia=driver.findElement(By.xpath("//input[@value='Past Diagnosis']")).isDisplayed();
		boolean pastpre=driver.findElement(By.xpath("//input[@value='Past Prescription']")).isDisplayed();
		boolean pastTrans=driver.findElement(By.xpath("//input[@value='Past Transaction']")).isDisplayed();
		
		
		SoftAssert sa = new SoftAssert();
		
		sa.assertTrue(pastpre,"Past Prescription Tab Present");
		sa.assertTrue(pastDia,"Past Diagnosis Tab Present");
		sa.assertTrue(pastApm,"Past Appontments  Tab Present");
		sa.assertTrue(pastTrans,"Past Transaction  Tab Present");
		
		sa.assertAll();
		
		if(driver.findElement(By.xpath("//input[@value='Past Appointments']")).isDisplayed()) {
			
		         System.out.println("TestCase Passed");
	        }
	      else
	        {
		         System.out.println("TestCase Failed");
	        }
		
		
		
		
	}
		
	
	
	
	
}
