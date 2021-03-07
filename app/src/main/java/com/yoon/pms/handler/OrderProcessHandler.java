package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;

import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Menu;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class OrderProcessHandler extends AbstractMemberHandler {

	protected List<Menu> menuList;

	public OrderProcessHandler(List<BuyerMember> buyerMemberList, List<SellerMember> sellerMemberList, List<Menu> menuList) {
		super(buyerMemberList, sellerMemberList);
		this.menuList = menuList;
	}

	@Override
	public void service() {
		System.out.println("■ 메뉴 - 주문 - 가게 목록 ■");
		int num = Integer.parseInt(AbstractOrderHandler.categoryNumber);
		int count = 0;

		Iterator<SellerMember> iterator = sellerMemberList.iterator();
		while(iterator.hasNext()) {
			SellerMember s = iterator.next();
			if(s.getCategoryId() == num) {
				System.out.printf("가게 번호 : [%d]  업종 : [%s]  상호명 : [%s]  전화번호 : [%s]\n",
						s.getNumber(), s.getCategory(), s.getBusinessName(), s.getBusinessNumber());
				count++;
			}
		}
		if(count == 0) {
			System.out.println("등록된 가게가 없습니다.");
			return;
		}

		System.out.println();
		AbstractOrderHandler.categoryId = Prompt.inputInt("가게 선택 : ");

		int id = AbstractOrderHandler.categoryId;
		Iterator<Menu> menu = menuList.iterator();

		while(menu.hasNext()) {
			Menu m = menu.next();
			if(m.getId() == num) {
				System.out.printf("메뉴번호 : %d  메뉴명 : %s  메뉴가격 : %d  메뉴설명 : %s\n", 
						m.getNumber(), m.getName(), m.getPrice(), m.getExplain());
			}
		}

	}

}
