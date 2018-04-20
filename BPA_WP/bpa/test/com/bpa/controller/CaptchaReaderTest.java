package com.bpa.controller;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.bpa.controll.image.ImageHelper;
import com.bpa.controll.image.OCR;

public class CaptchaReaderTest {
	
	@Test
	public void treatImageTest() throws Exception {
		// read image
		String in = "C:\\temp\\Dsv\\Proj\\BPA_WP\\bpa\\ocr\\captcha-out.png";
		String out = "C:\\temp\\Dsv\\Proj\\BPA_WP\\bpa\\ocr\\out.png";
		BufferedImage image = ImageIO.read(new FileInputStream(in));
		// read captcha
		image= ImageHelper.doImageTreatment(image);
		ImageIO.write(image, "png", new File(out));
	}
	
	@Test
	public void readCaptchaTest() throws Exception {
		String file;
		file = "C:\\temp\\Dsv\\Proj\\BPA_WP\\bpa\\ocr\\captcha-out.png";
		BufferedImage image = ImageIO.read(new FileInputStream(file));
		String  strCaptcha = OCR.ocrImageToText(image);
		assertEquals("4449", strCaptcha);
	}
	
}
