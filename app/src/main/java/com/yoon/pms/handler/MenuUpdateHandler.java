package com.yoon.pms.handler;

import java.util.List;
import com.yoon.pms.domain.Menu;
import com.yoon.util.Prompt;

public class MenuUpdateHandler extends AbstractMenuHandler {



  public MenuUpdateHandler(List<Menu> menuList) {
    super(menuList);
  }

  @Override
  public void service() {
    System.out.println("■ 메뉴 - 메뉴리스트 - 메뉴 변경 ■\n");

    MenuMyListHandler list = new MenuMyListHandler(menuList);
    list.service();
    System.out.println("---------------------------------------");
    int num = Prompt.inputInt("메뉴 번호 선택 : ");
    Menu m = findByNum(num);

    if(m == null) {
      System.out.println("해당 메뉴 번호가 존재하지 않습니다.");
      return;
    }

    m.setName(Prompt.inputString("변경한 메뉴명 : "));
    m.setPrice(Prompt.inputInt("변경할 가격 : "));
    m.setExplain(Prompt.inputString("변경할 메뉴 설명 : "));

    System.out.println("선택하신 메뉴 정보가 변경되었습니다.\n");
  }

}
