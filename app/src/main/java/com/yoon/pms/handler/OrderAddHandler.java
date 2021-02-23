package com.yoon.pms.handler;

import java.util.List;
import com.yoon.pms.domain.Order;
import com.yoon.util.Prompt;

public class OrderAddHandler extends AbstractOrderHandler {

  public OrderAddHandler(List<Order> orderList) {
    super(orderList);
  }

  @Override
  public void service() {
    System.out.println("■ 메뉴 - 주문 - 주문하기 ■");
    int choice;
    String order;

    while(true) {
      System.out.println("[업종]");
      choice = Prompt.inputInt("선택 : ");
      Order o = findByNum(choice);


      System.out.println("1. 배달 주문   2. 포장 주문");
      order = Prompt.inputString("선택 : ");

      if(order.equals("1")) {
        System.out.println("[배달 주문]");


      }
      else if(order.equals("2")) {
        System.out.println("[포장 주문]");

      }
      else {
        System.out.println("잘못 입력하셨습니다.");
      }
    }


  }

}
