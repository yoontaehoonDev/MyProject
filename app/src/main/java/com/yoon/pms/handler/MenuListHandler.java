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
		System.out.println("■ 메뉴 - 메뉴리스트 ■");

		Iterator<Menu> iterator = menuList.iterator();

		while(iterator.hasNext()) {
			Menu m = iterator.next();
			System.out.printf("메뉴ID : %d  메뉴 소유 : %s  메뉴번호 : %d  메뉴명 : %s  메뉴가격 : %d  메뉴설명 : %s\n", 
					m.getId(), m.getOwner(), m.getNumber(), m.getName(), m.getPrice(), m.getExplain());
		}

	}

}
