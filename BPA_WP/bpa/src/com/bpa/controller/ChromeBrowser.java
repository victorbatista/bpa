package com.bpa.controller;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowser extends Browser{
	
	private String strChromeDriverPath = "C:\\temp\\Dsv\\Case\\java_libs\\chromedriver.exe";
	
	public void open() {
		// point webdriver directory
        System.setProperty("webdriver.chrome.driver", strChromeDriverPath);
        // activate logging
        System.setProperty("webdriver.chrome.logfile", "C:\\temp\\Dsv\\logging\\chromedriver.log");
        System.setProperty("webdriver.chrome.verboseLogging", "true");
        setDriver(new ChromeDriver());
	}
	
}
