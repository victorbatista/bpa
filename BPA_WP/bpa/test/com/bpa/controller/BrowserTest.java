package com.bpa.controller;

import java.awt.AWTException;

import org.junit.jupiter.api.Test;

import com.bpa.controll.BrowserController;

public class BrowserTest {
	
	@Test
	public void doLoginUsingFirefox() throws InterruptedException, AWTException{
		try {
			BrowserController.getInstance().doLogin();
		}catch (Exception _e) {
			BrowserController.getInstance().getBrowser().close();
		}
		
	}

	
}
