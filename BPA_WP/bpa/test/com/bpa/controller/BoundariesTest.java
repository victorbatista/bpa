package com.bpa.controller;

import org.junit.Test;

import com.bpa.controll.Config;

import static org.junit.Assert.*;

import java.io.IOException;

public class BoundariesTest extends com.bpa.controller.Test{
	@Test
	public void testConfigProject() throws IOException {
		String strUsr = Config.getInstance().getPropertie("usr");
		String strPSW = Config.getInstance().getPropertie("psw");
		assertEquals("victor.pbatista", strUsr);
		assertEquals("123456", strPSW);
	}
	
}
