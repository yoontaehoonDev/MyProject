package com.yoon.pms.domain;

public class Bill {
	private int number;
	private int id;
	private String menus;
	private String buyer;
	private String time;




	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

}
