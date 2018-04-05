package com.bpa.controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class Browser {
	
	private WebDriver driver = null;
	
	public abstract void open(); 
	
	public void close(){
		if(driver != null){
			driver.close();		
		}
	}
	
	public void navigate(String _strUrl) {
		if(driver != null) {
//			driver.navigate().to(_strUrl);
			driver.get(_strUrl);
		}
	}

	/* GETTERS AND SETTERS*/
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(ChromeDriver driver) {
		this.driver = driver;
	}
	
	
}
