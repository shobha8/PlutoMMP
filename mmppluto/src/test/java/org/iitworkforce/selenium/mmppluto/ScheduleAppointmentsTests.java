package org.iitworkforce.selenium.mmppluto;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScheduleAppointmentsTests {
	WebDriver driver;
	HashMap<String,String> expectedHMap= new HashMap<String,String>();
	HashMap<String,String> actualHMap = new HashMap<String,String>();
	
	public void instantiateDriver()
	{
		 WebDriverManager.chromedriver().setup();
		 driver=new ChromeDriver();
	}
	public void launchBrowser(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
	}
	public void login (String uname, String pword)
	{
	    driver.findElement(By.id("username")).sendKeys(uname);
	    driver.findElement(By.id("password")).sendKeys(pword);
	    driver.findElement(By.name("submit")).click();
	}
	public void  navigate_module(String moduleName) {
		driver.findElement(By.xpath("//span[normalize-space()='"+moduleName+"']")).click();
	}
	
	public void bookAppointment(String doctorName,String time,String sym,int noofDays) throws InterruptedException
	{
		driver.findElement(By.xpath("//input[@value='Create new appointment']")).click();
		driver.findElement(By.xpath("//h4[normalize-space()='Dr."+doctorName+"']/ancestor::ul/following-sibling::button")).click();
		driver.switchTo().frame("myframe");
		driver.findElement(By.id("datepicker")).click();
		
		String expectedDate = getFutureDate(noofDays,"MMMM/d/yyyy");
		//expectedHMap.put("date",expectedDate);
		System.out.println(expectedDate);
		
		String dateArr[]=expectedDate.split("/");
		String expectedMonth = dateArr[0];//March
		String expectedDay = dateArr[1];//30
		String expectedYear = dateArr[2];//2023
		
		//System.out.println(dateArr[0]);
		//System.out.println(dateArr[1]);
        //System.out.println(dateArr[2]);
		
		String actualYear = driver.findElement(By.className("ui-datepicker-year")).getText();//2023
	    while(!(actualYear.equals(expectedYear)))
		{
		    driver.findElement(By.cssSelector(".ui-icon.ui-icon-circle-triangle-e")).click();
			actualYear = driver.findElement(By.className("ui-datepicker-year")).getText();
		}
		String actualMonth = driver.findElement(By.className("ui-datepicker-month")).getText();//March
		while(!(actualMonth.equals(expectedMonth)))
		{
			driver.findElement(By.cssSelector(".ui-icon.ui-icon-circle-triangle-e")).click();
			actualMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
		}
		driver.findElement(By.linkText(expectedDay)).click();
		expectedDate=getFutureDate(noofDays,"MM/dd/yyyy");
		expectedHMap.put("date",expectedDate);
		Select timeSelect = new Select(driver.findElement(By.id("time")));
		timeSelect.selectByVisibleText(time);
		
		driver.findElement(By.id("ChangeHeatName")).click();
		//Thread.sleep(2000);
		driver.findElement(By.id("sym")).sendKeys(sym);
		driver.findElement(By.xpath("//*[@value='Submit']")).click();
	}
	//Fetching the actual Values
	
	public HashMap<String, String> fetchPatientTabluarData()
	{
		HashMap<String,String> actualHMap = new HashMap<String,String>();
		String actualDate = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText();
		String actualTime = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[2]")).getText();
		String actualSym= driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText();
		String actualDoctor= driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[4]")).getText();
		actualHMap.put("doctor",actualDoctor);
		actualHMap.put("time",actualTime);
		actualHMap.put("sym",actualSym);
		actualHMap.put("date",actualDate);
		return actualHMap;
	}
	
	@Test(description="TC_003 Validation of the Schedule appointment Booking",groups= {"Sanity","Regression"})
	public void validateBooking_TC001() throws InterruptedException 
	{
		instantiateDriver();
		launchBrowser("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
		login("ria1","Ria12345");
		navigate_module("Schedule Appointment");
		String expectedDoctor="Beth";
		String expectedTime="12Pm";
		String expectedSym="Fever,Cold,Cough";
		expectedHMap.put("doctor",expectedDoctor);
		expectedHMap.put("time",expectedTime);
		//Thread.sleep(3000);
        expectedHMap.put("sym",expectedSym);
		bookAppointment(expectedDoctor,expectedTime,expectedSym,30);
		actualHMap = fetchPatientTabluarData();
		Assert.assertEquals(actualHMap, expectedHMap);			
	}
	public String getFutureDate(int noofdays,String pattern)
		{

			Calendar cal = 	Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, noofdays);
			
			Date d = cal.getTime();
			System.out.println("Current Date :" + d);
					
			SimpleDateFormat sdf = new SimpleDateFormat();
			String defaultformat = sdf.format(d);
			System.out.println(defaultformat);
			
			sdf = new SimpleDateFormat(pattern);
			String formatDate = sdf.format(d);
			System.out.println(formatDate);
			return formatDate;
		}
	
	
}
