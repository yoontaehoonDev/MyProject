package com.yoon.pms.handler;

public class Temp implements Command {

	@Override
	public void service() {
		System.out.println("준비중입니다.\n");
	}
}
