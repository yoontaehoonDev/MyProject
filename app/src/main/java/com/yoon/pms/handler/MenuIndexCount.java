package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;

import com.yoon.pms.domain.Menu;
import com.yoon.pms.domain.SellerMember;

public class MenuIndexCount extends AbstractMenuHandler {


	public MenuIndexCount(List<Menu> menuList) {
		super(menuList);
	}

	@Override
	public void service() {
		Iterator<Menu> menu = menuList.iterator();
		SellerMember s = AbstractMemberHandler.sellerMemberNumber;

		AbstractMenuHandler.menuIndex = 1;
		while(menu.hasNext()) {
			Menu m = menu.next();
			if(s.getHash() == m.getId()) {
				AbstractMenuHandler.menuIndex++;
			}
		}
	}
}
