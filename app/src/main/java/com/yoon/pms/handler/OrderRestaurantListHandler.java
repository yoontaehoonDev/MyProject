package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Menu;
import com.yoon.pms.domain.SellerMember;

public class OrderRestaurantListHandler extends AbstractMemberHandler {

  protected List<Menu> menuList;

  public OrderRestaurantListHandler(List<BuyerMember> buyerMemberList, List<SellerMember> sellerMemberList) {
    super(buyerMemberList, sellerMemberList);

  }

  @Override
  public void service() {
    System.out.println("■ 메뉴 - 주문 - 가게 목록 ■");
    int num = Integer.parseInt(AbstractOrderHandler.categoryNumber);

    Iterator<SellerMember> iterator = sellerMemberList.iterator();
    while(iterator.hasNext()) {
      SellerMember s = iterator.next();
      if(s.getCategoryId() == num) {
        System.out.printf("가게 번호 : [%d]  업종 : [%s]  상호명 : [%s]  전화번호 : [%s]\n",
            s.getNumber(), s.getCategory(), s.getBusinessName(), s.getBusinessNumber());
      }
    }

  }

}
