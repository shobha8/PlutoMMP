package org.iitworkforce.selenium.mmppluto.admintests;

import java.util.HashMap;

import org.iitworkforce.selenium.mmppluto.patientpages.HomePage;


import org.iitworkforce.selenium.mmppluto.patientpages.LoginPage;
import org.iitworkforce.selenium.mmppluto.patientpages.ScheduleAppointmentPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ScheduleAppointmentAdminTests extends org.iitworkforce.selenium.mmppluto.lib.BaseClass {
	
	
	HashMap<String,String> expectedHMap= new HashMap<String,String>();
	HashMap<String,String> actualHMap= new HashMap<String,String>();
	
	@Parameters({"username","password","doctor","time","sym"})
	@Test(description="TC_003 Validation of the Schedule appointment Booking",groups= {"Sanity","Regression"})
	public void validateBooking_TC001(String username,String password,
											String doctor,String time,String sym)
	{
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = loginPage.login(username,password);
		homePage.navigate_module("Schedule Appointment");	
		ScheduleAppointmentPage sPage = new ScheduleAppointmentPage(driver);
		expectedHMap = sPage.bookAppointment(doctor,time,sym,30);
		actualHMap = homePage.fetchPatientTabluarData();
		Assert.assertEquals(actualHMap, expectedHMap);			
	}
	 
}

	