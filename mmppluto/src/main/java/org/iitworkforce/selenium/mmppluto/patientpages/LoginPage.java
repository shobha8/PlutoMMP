package org.iitworkforce.selenium.mmppluto.patientpages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	protected WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		    this.driver = driver;
		   
    }
	/**
	    * Login as valid user
	    *
	    * @param userName
	    * @param password
	    * @return HomePage object
	    */
	public HomePage login(String userName,String password)
	{
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("submit")).click();
		return new HomePage(driver);
	}
	
	public String getLoginErrMessage() {
		Alert alrt =driver.switchTo().alert();
		String errMsg=alrt.getText();
		alrt.accept();
		return  errMsg;
	}
	
}
