package com.okr.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider 
{
	static Properties pro;

	public ConfigDataProvider() {
		File src = new File("./config/config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Not able to load the config file >>" + e.getMessage());
		}
	}


	public String URL()
	{
		return pro.getProperty("qaURL");
	}
	public  String getbrowser()
	{
		return pro.getProperty("Browser");
	}

}





