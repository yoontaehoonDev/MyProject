package com.yoon.pms.domain;

import java.sql.Date;

public class Order {

	// 영수증, 영수증 ID, 금액 등

	private int firstId;
	private int secondId;
	private int number;
	private String name;
	private String address;
	private String phone;
	private Date orderTime;
	private Date servingTime;
	private int time;
	private int sum;


	public Date getServingTime() {
		return servingTime;
	}

	public void setServingTime(Date servingTime) {
		this.servingTime = servingTime;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getFirstId() {
		return firstId;
	}
	public void setFirstId(int firstId) {
		this.firstId = firstId;
	}
	public int getSecondId() {
		return secondId;
	}
	public void setSecondId(int secondId) {
		this.secondId = secondId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

	// 재주문율 시스템

}
