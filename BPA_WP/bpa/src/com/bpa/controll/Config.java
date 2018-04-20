package com.bpa.controll;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	private static Config projConfig;
	
	private Config() {
		config();
	}
	
	public void config(){
		Properties prop = new Properties();
		InputStream input = null;

		File file = new File("config/config.properties");
		if(file.exists()) 
			try {
				input = new FileInputStream(file);
				prop.load(input);
				System.setProperties(prop);
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/* ACCESS METHODS */
	public static Config getInstance(){
		if(projConfig == null)
			projConfig = new Config();
		return projConfig;
	}

	public String getPropertie(String _propertie) {
		return System.getProperty(_propertie);
	}
	
	

}
