package com.yoon.pms.handler;

import java.util.LinkedList;
import com.yoon.pms.domain.Order;
import com.yoon.util.Prompt;

public class OrderHandler {

  private LinkedList<Order> orderList = new LinkedList<>();
  int orderNumber = 0;


  public void add() {
    System.out.println("■ 메뉴 - 주문 - 주문하기 ■");
    System.out.println("1. 배달주문 2. 포장주문");
    while(true) {
      int num = 0;
      String order = Prompt.inputString("선택 : ");
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

  public void list() {

  }
}
