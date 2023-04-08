package org.iitworkforce.selenium.mmppluto;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ViewHistory2 {
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
		//ValidateButton("Past Appointments");
	//ValidateButton("Past Diagnosis");
	//ValidateButton("Past Prescription");
		//String PastAppointments=driver.findElement(By.xpath("//input[@value='Past Appointments']")).getText();
				//System.out.println(PastAppointments);
			//String ExpectedText="PAST APPOINTMENTS";
				//Assert.assertEquals(true,PastAppointments.getText());
	}
	
    public static void ValidateButton(String Button) {
	
	
    driver.findElement(By.xpath("//input[@value='"+Button+"']")).click(); 
	//String Tab=driver.findElement(By.xpath("//input[@value='"+Button+"']")).getText();
    //System.out.println(Tab);
}

	public void panelHistory() {
		WebElement panel = driver.findElement(By.id("wrapper"));
		System.out.println(panel.findElements(By.tagName("a")).size());

		for (int i=1;i<panel.findElements(By.tagName("a")).size();i++)
		{

			String clickonLinkTab = Keys.chord(Keys.CONTROL,Keys.ENTER);
			panel.findElements(By.tagName("a")).get(i).sendKeys(clickonLinkTab);      
		}
	}
}
