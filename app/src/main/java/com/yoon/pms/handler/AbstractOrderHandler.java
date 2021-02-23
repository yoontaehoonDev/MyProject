package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.Order;
import com.yoon.pms.domain.SellerMember;

public abstract class AbstractOrderHandler implements Command {

  List<Order> orderList;
  List<SellerMember> menuList;

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

  public void listRestaurants() {



  }

}
