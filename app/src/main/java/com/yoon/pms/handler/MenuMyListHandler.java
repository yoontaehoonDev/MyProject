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
		System.out.println("■ 메뉴 - 내 메뉴 목록 ■");

		Iterator<Menu> iterator = menuList.iterator();
		SellerMember s = AbstractMemberHandler.sellerMemberNumber;
		System.out.println();
		System.out.printf("[%s의 메뉴 목록]\n", s.getBusinessName());
		while(iterator.hasNext()) {
			Menu m = iterator.next();
			if(m.getId() == s.getHash()) {
				System.out.printf("메뉴번호 : %d  메뉴명 : %s  메뉴가격 : %d  메뉴설명 : %s\n", 
						m.getNumber(), m.getName(), m.getPrice(), m.getExplain());
			}
		}
	}
}
