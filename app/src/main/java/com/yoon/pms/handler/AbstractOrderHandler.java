package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.Menu;
import com.yoon.pms.domain.Order;
import com.yoon.pms.domain.SellerMember;

public abstract class AbstractOrderHandler implements Command {

  List<Order> orderList;
  List<SellerMember> sellerMember;
  List<Menu> menuList;

  public AbstractOrderHandler(List<Order> orderList) {
    this.orderList = orderList;
  }

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

  public void listRestaurants(String number) {

    switch(number) {
      case "1": list(number); break;
      case "2": list(number); break;
      case "3": list(number); break;
      case "4": list(number); break;
      case "5": list(number); break;
      case "6": list(number); break;
      case "7": list(number); break;
      case "8": list(number); break;
      case "9": list(number); break;
      default: System.out.println("다시 선택하세요.\n");
    }


  }
  public void list(String number) {
    int num = Integer.parseInt(number);

    Iterator<SellerMember> iterator = sellerMember.iterator();


  }


}
