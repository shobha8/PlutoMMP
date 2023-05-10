package org.iitworkforce.selenium.mmppluto.patienttests;

import java.util.HashMap;

import org.iitworkforce.selenium.mmppluto.patientpages.HomePage;
import org.iitworkforce.selenium.mmppluto.patientpages.LoginPage;
import org.iitworkforce.selenium.mmppluto.patientpages.ScheduleAppointmentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class ScheduleAppoitmenstTests1 {

	WebDriver driver;
	HashMap<String,String> expectedHMap= new HashMap<String,String>();
	HashMap<String,String> actualHMap= new HashMap<String,String>();
	 
	
	@BeforeClass
	public void instantiateDriver()
	{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			
			launchBrowser("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
			
		 
	}
	public void launchBrowser(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	@Test(description="TC_003 Validation of the Schedule appointment Booking",groups= {"Sanity","Regression"})
	public void validateBooking_TC001()
	{
		
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = loginPage.login("ria1","Ria12345");
		homePage.navigate_module("Schedule Appointment");
		
		String expectedDoctor="Beth";
		String expectedTime="12Pm";
		String expectedSym="Fever,Cold,Cough";
	 
		ScheduleAppointmentPage sPage = new ScheduleAppointmentPage(driver);
		expectedHMap = sPage.bookAppointment(expectedDoctor,expectedTime,expectedSym,30);
		
		actualHMap = homePage.fetchPatientTabluarData();
		Assert.assertEquals(actualHMap, expectedHMap);			
	}
}
