package org.iitworkforce.selenium.mmppluto.patienttests;

import org.iitworkforce.selenium.mmppluto.lib.BaseClass;
import org.iitworkforce.selenium.mmppluto.patientpages.HomePage;
import org.iitworkforce.selenium.mmppluto.patientpages.LoginPage;
import org.iitworkforce.selenium.mmppluto.patientpages.ViewHistoryPage;
import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ViewHistoryTests extends BaseClass {
	@Parameters({"username","password"})
	@Test(description="US_008 View History",groups={"US_008","regression","sanity","patientmodule"})
	public void validate_ViewHistory(String username, String password) throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = loginPage.login(username,password);
		homePage.navigate_module("Profile");	
		ViewHistoryPage viewHistory = new ViewHistoryPage(driver);
		viewHistory.clickViewHistory();
		
		
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
		
		
		
	}
}
