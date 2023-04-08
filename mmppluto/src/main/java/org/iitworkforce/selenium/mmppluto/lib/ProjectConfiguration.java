package org.iitworkforce.selenium.mmppluto.lib;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProjectConfiguration {
	Properties pro =new Properties();
	public Properties loadProperties() throws IOException 
	{
		String filePath = System.getProperty("user.dir")+"//config//mmp.properties";
		FileInputStream fis = new FileInputStream(filePath);
		pro.load(fis);
		return pro;
	}

}
