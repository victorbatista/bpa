package com.bpa.model;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser extends Browser{

	private String strDriverPath = "C:\\temp\\Dsv\\Case\\java_libs\\geckodriver.exe";
	
	@Override
	public void open() {
		// point webdriver directory
        System.setProperty("webdriver.gecko.driver", strDriverPath);
        setDriver(new FirefoxDriver());
	}

}
