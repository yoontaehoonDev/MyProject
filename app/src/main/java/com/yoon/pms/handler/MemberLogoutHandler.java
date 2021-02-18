package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.SellerMember;

public class MemberLogoutHandler extends AbstractMemberHandler{

	public MemberLogoutHandler(List<BuyerMember> buyerMemberList, List<SellerMember> sellerMemberList) {
		super(buyerMemberList, sellerMemberList);
	}

	@Override
	public void service() {
		logCount = false;
		if(logStatus == 1) {
			sellerMemberNumber.setDivision(false);
			SellerBoardHandler.boardAuthorization = false;
		}
		else if (logStatus == 0){
			buyerMemberNumber.setDivision(false);
			AbstractBuyerBoardHandler.boardAuthorization = false;
		}

		logStatus = -1;
		System.out.println("로그아웃\n");
	}



}
