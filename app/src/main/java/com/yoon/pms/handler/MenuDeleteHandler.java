package com.yoon.pms.handler;

import java.util.List;
import com.yoon.pms.domain.Menu;
import com.yoon.util.Prompt;

public class MenuDeleteHandler extends AbstractMenuHandler {



  public MenuDeleteHandler(List<Menu> menuList) {
    super(menuList);
  }

  @Override
  public void service() {
    System.out.println("■ 메뉴 - 메뉴리스트 - 메뉴 삭제 ■\n");

    MenuMyListHandler list = new MenuMyListHandler(menuList);
    list.service();
    System.out.println("---------------------------------------");
    int num = Prompt.inputInt("삭제할 메뉴 번호 선택 : ");
    Menu m = findByNum(num);

    if(m == null) {
      System.out.println("해당 메뉴 번호가 존재하지 않습니다.");
      return;
    }

    System.out.printf("%s 가 메뉴에서 삭제되었습니다.\n", m.getName());
    menuList.remove(m);
  }

}
