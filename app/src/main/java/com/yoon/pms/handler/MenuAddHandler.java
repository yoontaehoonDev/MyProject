package com.yoon.pms.handler;

import java.util.List;
import com.yoon.pms.domain.Menu;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class MenuAddHandler extends AbstractMenuHandler {

  public MenuAddHandler(List<Menu> menuList) {
    super(menuList);
  }

  @Override
  public void service() {
    System.out.println("■ 메뉴 - 메뉴리스트 - 메뉴 추가 ■");
    while(true) {
      System.out.println("[메뉴 추가]\n");
      Menu m = new Menu();
      SellerMember s = AbstractMemberHandler.sellerMemberNumber;

      m.setId(s.getHash());
      m.setOwner(s.getBusinessName());
      s.setCount(s.getCount() + 1);
      m.setNumber(s.getCount());
      m.setName(Prompt.inputString("메뉴명 : "));
      m.setPrice(Prompt.inputInt("가격 : "));
      m.setExplain(Prompt.inputString("메뉴 설명 : "));

      menuList.add(m);

      String repeat = Prompt.inputString("계속 추가하시겠습니까?[Y/N] : ");
      while(true) {

        if(repeat.equalsIgnoreCase("y")) {
          break;
        }
        else {System.out.println("메뉴로 돌아갑니다.");
        return;
        }
      }
    }
  }
}
