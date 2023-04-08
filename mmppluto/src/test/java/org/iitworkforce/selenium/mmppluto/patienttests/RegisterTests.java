package org.iitworkforce.selenium.mmppluto.patienttests;

import org.iitworkforce.selenium.mmppluto.lib.BaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class RegisterTests extends BaseClass{
	
	@Test(description="TC_003 Validation of the Schedule appointment Booking",groups= {"Sanity","Regression"})
	public void validateBooking_TC001()
	{
		 
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@value='Register']")).click();
		driver.findElement(By.id("firstname")).sendKeys("AutomationQA123");
		String fName = driver.findElement(By.id("firstname")).getAttribute("value");
		System.out.println(fName);
		
		
		
	}

}
