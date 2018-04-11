package com.bpa.controller;

import java.awt.AWTException;

import org.junit.jupiter.api.Test;

import com.bpa.pojo.Browser;
import com.bpa.pojo.ChromeBrowser;

public class BrowserTest {
	
	@Test
	public void doLoginUsingChrome() throws InterruptedException {
		Browser browser = new ChromeBrowser();
		// open chrome browser
		browser.open();
		// navigate to google
		browser.navigate("https://www.google.com");
		super.wait(5000);
		browser.close();
	}
	
	@Test
	public void doLoginUsingFirefox() throws InterruptedException, AWTException{
		try {
			BrowserController.getInstance().doLogin();
		}catch (Exception _e) {
			BrowserController.getInstance().getBrowser().close();
		}
		
	}

	
}
