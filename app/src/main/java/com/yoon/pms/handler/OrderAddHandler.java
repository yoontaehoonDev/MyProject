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
    String choice, order;

    while(true) {
      System.out.println("[가게 종류]");
      System.out.println("1. 한식 2. 양식 3. 일식 4. 중식 5. 분식");
      System.out.println("6. 치킨 7. 피자 8. 디저트 9. 야식\n");
      choice = Prompt.inputString("선택 : ");
      listRestaurants(choice);




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
