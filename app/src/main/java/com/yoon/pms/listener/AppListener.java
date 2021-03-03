package com.yoon.pms.listener;

import com.yoon.context.ApplicationContextListener;

public class AppListener implements ApplicationContextListener {

	@Override
	public void contextInitialized() {
		System.out.println("[Delivery System]");
	}

	@Override
	public void contextDestroyed() {
		System.out.println("System Done");
	}
}
