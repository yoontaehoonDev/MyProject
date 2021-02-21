package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.domain.Order;

public class OrderAddHandler extends AbstractOrderHandler {

	public OrderAddHandler(List<Order> orderList) {
		super(orderList);
	}

	@Override
	public void service() {
		System.out.println("■ 메뉴 - 주문 - 주문하기 ■");
		
		
	}

}
