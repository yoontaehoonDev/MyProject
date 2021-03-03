package com.yoon.pms.listener;

import java.util.Map;

import com.yoon.context.ApplicationContextListener;

public class AppListener implements ApplicationContextListener {

	@Override
	public void contextInitialized(Map<String,Object> context) {
		System.out.println("[Delivery System]");
	}

	@Override
	public void contextDestroyed(Map<String,Object> context) {
		System.out.println("System Done");
	}
}
