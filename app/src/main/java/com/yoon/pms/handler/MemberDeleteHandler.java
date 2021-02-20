package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class MemberDeleteHandler extends AbstractMemberHandler {

	public MemberDeleteHandler(List<BuyerMember> buyerMemberList, List<SellerMember> sellerMemberList) {
		super(buyerMemberList, sellerMemberList);
	}

	@Override
	public void service() {
		if(logCount == true) {
			if(logStatus == 1) {
				SellerMember seller = sellerMemberNumber;
				sellerMemberList.remove(seller);
			}
			else {
				BuyerMember buyer = buyerMemberNumber;
				buyerMemberList.remove(buyer);
			}
			logCount = false;
			logStatus = -1;
			System.out.println("계정 탈퇴 처리가 완료되었습니다. 그동안 이용해주셔서 감사합니다.\n");
		}
		else {
			int num;
			if(point == 0) {
				num = Prompt.inputInt("탈퇴시킬 구매회원 번호를 입력하세요 : ");
				BuyerMember buyer = findByBuyerNum(num);

				if(buyer == null) {
					System.out.println("회원 번호가 존재하지 않습니다.\n");
					return;
				}
				else {
					buyerMemberList.remove(buyer);
					System.out.println("회원 탈퇴 처리가 완료되었습니다.\n");
				}
			}

			else if(point == 1){
				num = Prompt.inputInt("탈퇴시킬 판매회원 번호를 입력하세요 : ");

				SellerMember seller = findBySellerNum(num);

				if(seller == null) {
					System.out.println("회원 번호가 존재하지 않습니다.\n");
					return;
				}
				else {
					sellerMemberList.remove(seller);
					System.out.println("회원 탈퇴 처리가 완료되었습니다.\n");
				}
			}
		}
	}

}
