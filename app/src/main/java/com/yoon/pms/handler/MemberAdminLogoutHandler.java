package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.App;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.SellerMember;

public class MemberAdminLogoutHandler extends AbstractMemberHandler {

	public MemberAdminLogoutHandler(List<BuyerMember> buyerMemberList, List<SellerMember> sellerMemberList) {
		super(buyerMemberList, sellerMemberList);
	}

	@Override
	public void service() {
		if(adminNumber == 1) {
			adminNumber = 0;
			App.location = -1;
			System.out.println("관리자 로그아웃\n");
		}
		else {
			System.out.println("접근 권한이 없습니다.\n");
		}
	}

}
