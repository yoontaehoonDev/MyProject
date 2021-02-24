package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;

import com.yoon.pms.domain.Order;

public abstract class AbstractOrderHandler implements Command {

	List<Order> orderList;

	public AbstractOrderHandler(List<Order> orderList) {
		this.orderList = orderList;
	}

	public static String categoryNumber;

	public Order findByNum(int orderNum) {

		Iterator<Order> iterator = orderList.iterator();

		while(iterator.hasNext()) {
			Order o = iterator.next();
			if (o.getNumber() == orderNum) {
				return o;
			}
		}
		return null;
	}

	public void list(String number) {
		categoryNumber = number;
		//		OrderRestaurantListHandler list = new OrderRestaurantListHandler(orderList);

		System.out.println("식당 목록 접근");
		//		list.service();
		//		while(true) {
		//			System.out.println("반복문 접근");
		//			switch(number) {
		//			case "1": list.service(); break;
		//			case "2": list.service(); break;
		//			case "3": list.service(); break;
		//			case "4": list.service(); break;
		//			case "5": list.service(); break;
		//			case "6": list.service(); break;
		//			case "7": list.service(); break;
		//			case "8": list.service(); break;
		//			case "9": list.service(); break;
		//			default: System.out.println("다시 선택하세요.\n");
		//			}
		//		}

	}
}
