package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;

import com.yoon.pms.domain.Menu;
import com.yoon.pms.domain.SellerMember;

public abstract class AbstractMenuHandler implements Command {
	protected List<Menu> menuList;

	public AbstractMenuHandler(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public static int menuIndex;

	public void countMenuIndex() {
		Iterator<Menu> menu = menuList.iterator();
		SellerMember s = AbstractMemberHandler.sellerMemberNumber;

		menuIndex = 1;
		while(menu.hasNext()) {
			Menu m = menu.next();
			if(s.getHash() == m.getId()) {
				menuIndex++;
			}
		}
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
