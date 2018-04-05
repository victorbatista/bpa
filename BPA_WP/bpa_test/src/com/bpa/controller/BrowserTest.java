package com.bpa.controller;

import org.junit.jupiter.api.Test;

public class BrowserTest {
	
	@Test
	public void startChromeBrowser() throws InterruptedException {
		Browser browser = new ChromeBrowser();
		// open chrome browser
		browser.open();
		// navigate to google
		browser.navigate("https://www.google.com");
		Thread thread = new Thread();
		thread.wait(5000);
		browser.close();
	}
	
	@Test
	public void openGoogleOnFirefox() {
		Browser browser = new FirefoxBrowser();
		// open chrome browser
		browser.open();
		// navigate to google
		browser.navigate("http://www.google.com");
	}
}
