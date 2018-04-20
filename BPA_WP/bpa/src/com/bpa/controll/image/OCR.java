package com.bpa.controll.image;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.bpa.controll.Config;

public class OCR {
	
	public static String ocrImageToText(BufferedImage _image)
			throws IOException, InterruptedException{
		String currentPath = new File(".").getCanonicalPath();
		String strCaptchaImgFile = currentPath + "\\" + Config.getInstance().getPropertie("captcha_outputfile");
		String strTesseractBin = currentPath + "\\" + Config.getInstance().getPropertie("tesseract_bin");
		String tesractOutputTxt = currentPath + "\\" + Config.getInstance().getPropertie("tesseract_output");
		String cmd = (strTesseractBin + " " + strCaptchaImgFile + " " + tesractOutputTxt.replace(".txt", ""));
		
		ImageIO.write(_image, "png", new File(strCaptchaImgFile));
		Process tesseractProc = Runtime.getRuntime().exec(cmd);
		tesseractProc.waitFor();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(tesractOutputTxt)));
		String captcha = reader.readLine();
		reader.close();
		return captcha;
	}
}