package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.domain.Menu;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class MenuAddHandler extends AbstractMenuHandler {

	protected List<SellerMember> sellerMemberList;

	public MenuAddHandler(List<Menu> menuList) {
		super(menuList);
	}

	@Override
	public void service() {
		System.out.println("■ 메뉴 - 메뉴리스트 - 메뉴 추가 ■");
		while(true) {
			System.out.println("[메뉴 추가]\n");
			Menu m = new Menu();
			SellerMember s = AbstractMemberHandler.sellerMemberNumber;
			m.setCategoryId(s.getCategoryId());
			m.setId(s.getHash());
			m.setOwner(s.getBusinessName());
			countMenuIndex();
			m.setNumber(menuIndex);
			m.setName(Prompt.inputString("메뉴명 : "));
			m.setPrice(Prompt.inputInt("가격 : "));
			m.setExplain(Prompt.inputString("메뉴 설명 : "));
			System.out.printf("메뉴 업종 번호 : %d\n", m.getCategoryId());
			menuList.add(m);

			String repeat = Prompt.inputString("계속 추가하시겠습니까?[Y/N] : ");

			if(repeat.equalsIgnoreCase("n")) {
				break;
			}
		}
	}
}
