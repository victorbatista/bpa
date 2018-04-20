package com.bpa.controll.routine;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import com.bpa.controll.Config;
import com.bpa.controll.image.ImageHelper;
import com.bpa.controll.image.OCR;
import com.bpa.model.Browser;
import com.bpa.model.exception.RoutineException;

public class SisgfLoginRoutine implements Routine{
	
	/* ATRIBUTES */
	private String usuario = null;
	private String senha = null;
	
	/* CONSTRUCTOS */ 
	public SisgfLoginRoutine(String _usuario, String _senha) {
		this.usuario = _usuario;
		this.senha = _senha;
	}

	@Override
	public void execute(Browser _browser) throws RoutineException {
		try {
			// read captcha
			String captcha = "";
			BufferedImage img = printImageFromScreen(_browser);
			img = ImageHelper.doImageTreatment(img);
			captcha = OCR.ocrImageToText(img);
			// set field values
			_browser.setWebElementValue("//input[@id='j_username']", Config.getInstance().getPropertie("usr"));
			_browser.setWebElementValue("//input[@id='j_password']", Config.getInstance().getPropertie("psw"));
			_browser.setWebElementValue("//input[@id='captchaId']", captcha);
			Thread.sleep(3000);
			// call login
			_browser.getWebElement("//button[@id='btnRegistrar']").click();	
		}catch (Exception e) {
			RoutineException exception = new RoutineException("Erro ao ler captcha");
			exception.setStackTrace(e.getStackTrace());
			throw exception;
		} 
	}	
	
	private BufferedImage printImageFromScreen(Browser _browser) {
		try {
			Point point = new Point(521, 444);
			Dimension dimension = new Dimension(283, 92);
			Rectangle rectangle = new Rectangle(point, dimension);
			Robot robot = new  Robot();
			return robot.createScreenCapture(rectangle);
		} catch (AWTException e) {
			e.printStackTrace();
			return null;
		}
	}

	/* GETTERS AND SETTERS*/
		
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
