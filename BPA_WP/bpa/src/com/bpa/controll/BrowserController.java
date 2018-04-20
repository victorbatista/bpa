package com.bpa.controll;

import javax.swing.JOptionPane;

import com.bpa.controll.routine.SisgfLoginRoutine;
import com.bpa.model.Browser;
import com.bpa.model.FirefoxBrowser;

public class BrowserController {

	/* ATRIBUTES */
	
	private static BrowserController browserController;
	Browser browser = null;
	
	/* CONSTRUCTORS */
	
	private BrowserController() {
		
	}
	
	/* METHODS */
	
	public void doLogin(){
		try {
			long time = 1000;
			browser = new FirefoxBrowser();
			// open chrome browser
			browser.open();
			Thread.sleep(time);
			// navigate to sisgf
			browser.navigate("http://www-sisgf/?");
			Thread.sleep(time);
			// execute login 
			browser.executeRoutine(new SisgfLoginRoutine("usuario", "senha"));		
			// close browser
			Thread.sleep(time);
			browser.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar logar");
		}
	}
	
	public static BrowserController getInstance() {
		if(browserController == null)
			browserController = new BrowserController();
		return browserController;
	}

	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

}
