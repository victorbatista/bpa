package com.bpa.controller;

import java.io.IOException;

import org.junit.Before;

import com.bpa.controll.Config;

public class Test {
	@Before
	public void init() throws IOException {
		Config.getInstance().config();
	}
}
