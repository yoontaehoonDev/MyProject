package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.App;
import com.yoon.pms.domain.Menu;

public class MenuReturnHandler extends AbstractMenuHandler {

	public MenuReturnHandler(List<Menu> menuList) {
		super(menuList);
	}

	@Override
	public void service() {
		App.location = 1;
	}
}
