package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.domain.Order;

public abstract class AbstractOrderHandler implements Command {

	List<Order> orderList;

	public AbstractOrderHandler(List<Order> orderList) {
		this.orderList = orderList;
	}


}
