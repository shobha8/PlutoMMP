package org.iitworkforce.selenium.mmppluto.patientpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ViewHistoryPage {

	protected WebDriver driver;
	private By viewHistoryButton = By.xpath("//button[normalize-space()='View History']");
	
	//driver.findElement(By.xpath("//button[normalize-space()='View History']")).click(); 
	public ViewHistoryPage(WebDriver driver)
	{
		    this.driver = driver;
		   
    }
	
	public void clickViewHistory()
	{
		driver.findElement(viewHistoryButton).click();
	}
}
