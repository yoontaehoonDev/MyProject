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
		int flag = 0;

		Iterator<SellerMember> iterator = sellerMemberList.iterator();
		while(iterator.hasNext()) {
			SellerMember s = iterator.next();
			if(s.getCategoryId() == num) {
				System.out.printf("가게 번호 : [%d]  업종 : [%s]  상호명 : [%s]  전화번호 : [%s]\n",
						s.getNumber(), s.getCategory(), s.getBusinessName(), s.getBusinessNumber());
				flag = 1;
			}
		}
		if(flag == 0) {
			System.out.println("등록된 가게가 없습니다.");
			return;
		}

		System.out.println();
		int id =  AbstractOrderHandler.categoryId = Prompt.inputInt("가게 선택 : ");

		Iterator<Menu> menu = menuList.iterator();
		while(menu.hasNext()) {
			Menu m = menu.next();
			if(num == m.getCategoryId()) {
				if(id == m.getId()) {
					System.out.printf("메뉴번호 : %d  메뉴명 : %s  메뉴가격 : %d  메뉴설명 : %s\n", 
							m.getNumber(), m.getName(), m.getPrice(), m.getExplain());
				}
			}
		}

		//		if(flag == 1) {
		//			System.out.println("등록된 메뉴가 없습니다.");
		//			return;
		//		}
		int sum = 0;
		while(true) {
			// 메뉴 리스트 출력
			int choice = Prompt.inputInt("메뉴 선택 : ");

			Iterator<Menu> list = menuList.iterator();
			while(list.hasNext()) {
				Menu m = list.next();
				if(num == m.getCategoryId() && id == m.getId()) {
					// 위 두 조건문 분리시켜서 실행하기
					if(choice == m.getNumber()) {
						sum += m.getPrice();
					}
				}
				System.out.printf("금액 : %d\n", sum);

				// 계산 코드 구현 (취소 코드 포함)
				// 메뉴 개수 세기
				// 

				String repeat = Prompt.inputString("계속 추가하시겠습니까?[Y/N] : ");
				if(repeat.equalsIgnoreCase("n")) {
					break;
				}
			}

		}
	}
}
