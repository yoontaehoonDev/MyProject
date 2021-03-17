package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.Menu;
import com.yoon.pms.domain.SellerMember;

public class MenuMyListHandler extends AbstractMenuHandler {

  public MenuMyListHandler(List<Menu> menuList) {
    super(menuList);
  }

  @Override
  public void service() {
    System.out.println("■ 메뉴 - 메뉴리스트 - 내 메뉴 목록 ■");
    int flag = 0;
    Iterator<Menu> iterator = menuList.iterator();
    SellerMember s = AbstractMemberHandler.sellerMemberNumber;
    System.out.printf("[%s의 메뉴 목록]\n", s.getBusinessName());
    while(iterator.hasNext()) {
      Menu m = iterator.next();
      if(m.getId() == s.getHash()) {
        System.out.printf("메뉴번호 : [%d]  메뉴명 : [%s]  메뉴가격 : [%d]  메뉴설명 : [%s]\n", 
            m.getNumber(), m.getName(), m.getPrice(), m.getExplain());
        flag = 1;
      }
    }
    System.out.println();
    if(flag == 0) {
      System.out.println("등록하신 메뉴가 없습니다.\n");
    }
  }
}
