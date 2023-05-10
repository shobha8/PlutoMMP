package org.iitworkforce.selenium.mmppluto.patienttests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
 
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
 

public class ConnectionManager1
{
	/*
	 * Load the Driver Instance->class.forName("pass the driver class")
		Create a connection- username,password,hostip,database
		Create a statement
		Execute Queries: select * from mmp.patient_data=> result set
		Fetch teh data store in Array
		Pass the Array to the DP
		Use the DP for executing the testcases
		https://www.w3schools.com/sql/sql_where.asp
		https://total-qa.com/mysql-database-testing-using-selenium-webdriver-step-step-guide/
	 */

	@Test(dataProvider="DP")
	public void validateLogin(String patientID,String patientName)
	{
		//String dateOfAppointment
		System.out.println("DB Values: " + patientID);
		System.out.println("DB Values:"  + patientName);
		//System.out.println("DB Values:"  + dateOfAppointment);
		
//		WebDriverManager.chromeDriver.setup();
//		WebDriver driver = new ChromeDriver();
//		driver.get(url);
//		driver.findElement(By.id("")).sendKeys(uname);
//		driver.findElement(By.id("")).sendKeys(pword);
//		driver.findElement(By.id("")).click();
		
	}
	
	@DataProvider(name="DP")
	public String[][] feedDP() throws ClassNotFoundException, SQLException
	{
		String data[][] =getDBValues("root","rootroot","testdb","localhost");
		return data;
	}
	public static String[][] getDBValues(String uname,String pword,String dbname,String hostip) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		/*
		 * url a database url of the form jdbc:subprotocol:subnameuser 
		 * the database user on whose behalf the connection is being madepassword 
		 * the user's password
		 */
		String url="jdbc:mysql://localhost:3306/testdb";
		String username="root";
		String password="rootroot";
		
		Connection con = DriverManager.getConnection(url, username, password);
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
											 ResultSet.CONCUR_READ_ONLY);
		//int  value = stmt.executeUpdate("INSERT INTO `mmp`.`patient_data` VALUES (11,'James','22/11/2021');");
		//System.out.println("The rows are updated "+ value);
		
		ResultSet rs =  stmt.executeQuery("Select * from patient_data");
		rs.last();
		
		int rows = rs.getRow();
		System.out.println("Number of rows " + rows);
		
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		System.out.println("Number of cols: "+ cols);
		
		String data[][]= new String[rows][cols];
		
		int i=0;
		rs.beforeFirst();
		while(rs.next())
		{
			for(int j=0;j<cols;j++)
			{
				data[i][j]=rs.getString(j+1);
				System.out.println(data[i][j]);
			}
			i++;
		}	
		return data;
	}

}
