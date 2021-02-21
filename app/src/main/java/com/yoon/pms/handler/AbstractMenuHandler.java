package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.domain.Menu;

public abstract class AbstractMenuHandler implements Command {
	protected List<Menu> menuList;

	public AbstractMenuHandler(List<Menu> menuList) {
		this.menuList = menuList;
	}

}
