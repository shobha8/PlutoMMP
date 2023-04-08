package org.iitworkforce.selenium.mmppluto.patientpages;


import java.util.HashMap;

import org.iitworkforce.selenium.mmppluto.lib.AppLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ScheduleAppointmentPage {

	HashMap<String,String> expectedHMap= new HashMap<String,String>();
	HashMap<String,String> actualHMap= new HashMap<String,String>();
	
	private By createNewAppointmentButton = By.xpath("//input[@value='Create new appointment']");
	private By datePickerID = By.id("datepicker");
	
	
	protected WebDriver driver;
	public ScheduleAppointmentPage(WebDriver driver)
	{
		this.driver = driver;
	}
	public HashMap<String, String> bookAppointment(String doctorName,String time,String sym,int noofDays)
	{
		
		expectedHMap.put("doctor",doctorName);
		expectedHMap.put("time",time);
		expectedHMap.put("sym",sym);
		
		driver.findElement(createNewAppointmentButton).click();
		driver.findElement(By.xpath("//h4[normalize-space()='Dr."+doctorName+"']/ancestor::ul/following-sibling::button")).click();
		driver.switchTo().frame("myframe");
		driver.findElement(datePickerID).click();
		String expectedDate =  AppLibrary.getFutureDate(noofDays,"MMMM/d/yyyy");
		expectedHMap.put("date",expectedDate);
		System.out.println(expectedDate);
		String dateArr[]=expectedDate.split("/");
		String expectedMonth = dateArr[0];//March
		String expectedDay = dateArr[1];//24
		String expectedYear = dateArr[2];//2023
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
		expectedDate=AppLibrary.getFutureDate(noofDays,"MM/dd/yyyy");
		Select timeSelect = new Select(driver.findElement(By.id("time")));
		timeSelect.selectByVisibleText(time);
		
		driver.findElement(By.id("ChangeHeatName")).click();
		driver.findElement(By.id("sym")).sendKeys(sym);
		driver.findElement(By.xpath("//*[@value='Submit']")).click();
		//Fetching the actual Values
		
		return expectedHMap;
	}
}
