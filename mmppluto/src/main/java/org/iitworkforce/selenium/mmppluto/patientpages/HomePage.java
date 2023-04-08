package org.iitworkforce.selenium.mmppluto.patientpages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	protected WebDriver driver;
	//private By createNewAppointmentButton = By.xpath("//input[@value='Create new appointment']");
	//private By datePickerID = By.id("datepicker");
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	
	}
	
	public void navigate_module(String moduleName)
	{
		driver.findElement(By.xpath("//span[normalize-space()='"+moduleName+"']")).click();

	}
	public String getcurrentTitle()
	{
		return driver.getTitle();
	}
	
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
	
	
}
