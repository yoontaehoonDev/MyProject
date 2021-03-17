package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;

import com.yoon.pms.domain.Menu;

public class MenuListHandler extends AbstractMenuHandler {

	public MenuListHandler(List<Menu> menuList) {
		super(menuList);
	}

	@Override
	public void service() {
		System.out.println("■ 메뉴 - 주문 - 가게 - 메뉴리스트 ■");
		int num = AbstractOrderHandler.categoryId;
		Iterator<Menu> iterator = menuList.iterator();

		while(iterator.hasNext()) {
			Menu m = iterator.next();
			if(m.getId() == num) {
				System.out.printf("메뉴번호 : %d  메뉴명 : %s  메뉴가격 : %d  메뉴설명 : %s\n", 
						m.getNumber(), m.getName(), m.getPrice(), m.getExplain());
			}
		}

	}

}
