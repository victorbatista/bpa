package com.bpa.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bpa.controll.routine.Routine;
import com.bpa.model.exception.RoutineException;

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
			// driver.navigate().to(_strUrl);
			driver.get(_strUrl);
		}
	}

	/* GETTERS AND SETTERS*/
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getWebElement(String _xPath) {
		return driver.findElement(By.xpath(_xPath));
	}
	
	public void setWebElementValue(String _xPath, String value) {
		WebElement element = getWebElement(_xPath);
		if(element != null)
			element.sendKeys(value);
	}
	
	public void executeRoutine(Routine _routine) throws RoutineException {
		_routine.execute(this);
	}
	
	
}
