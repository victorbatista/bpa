package com.bpa.controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser extends Browser{

	@Override
	public void open() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");


	}

}
