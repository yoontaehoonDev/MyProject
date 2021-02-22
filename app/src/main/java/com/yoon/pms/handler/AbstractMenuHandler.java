package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.Menu;

public abstract class AbstractMenuHandler implements Command {
  protected List<Menu> menuList;

  public AbstractMenuHandler(List<Menu> menuList) {
    this.menuList = menuList;
  }


  public Menu findByNum(int menuNum) {

    Iterator<Menu> iterator = menuList.iterator();

    while(iterator.hasNext()) {
      Menu m = iterator.next();
      if (m.getNumber() == menuNum) {
        return m;
      }
    }
    return null;
  }
}
