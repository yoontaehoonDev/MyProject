package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class MemberUpdateHandler extends AbstractMemberHandler {

	private MemberValidatorHandler memberHandler;

	public MemberUpdateHandler(List<BuyerMember> buyerMemberList, List<SellerMember> sellerMemberList, MemberValidatorHandler memberHandler) {
		super(buyerMemberList, sellerMemberList);
		this.memberHandler = memberHandler;


	}

	@Override
	public void service() {

		if(logStatus == 1) {
			SellerMember s = sellerMemberNumber;
			s.setId(findByBuyerId("수정할 ID : "));
			s.setPassword(memberHandler.minimumLength("수정할 Password : "));
			s.setName(Prompt.inputString("수정할 이름 : "));
			s.setEmail(memberHandler.emailFormat("수정할 E-Mail : "));
			s.setPhone(memberHandler.phoneFormat("수정할 핸드폰 번호 : "));
			System.out.println("가게 관련 정보 변경은 고객센터(1542-1542)로 연락 바랍니다.");
			AbstractSellerBoardHandler.sellerBoardWriterChangeCount = 1;
			AbstractSellerBoardHandler.sellerBoardCommentWriterChangeCount = 1;
		}
		else if(logStatus == 0){
			BuyerMember b = buyerMemberNumber;
			b.setId(findByBuyerId("수정할 ID : "));
			b.setPassword(memberHandler.minimumLength("수정할 Password : "));
			b.setName(Prompt.inputString("수정할 이름 : "));
			b.setNickname(findByNickname("수정할 닉네임 : "));
			b.setEmail(memberHandler.emailFormat("수정할 E-Mail : "));
			b.setPhone(memberHandler.phoneFormat("수정할 핸드폰 번호 : "));
			AbstractBuyerBoardHandler.buyerBoardWriterChangeCount = 1;
			AbstractBuyerBoardHandler.buyerBoardCommentWriterChangeCount = 1;
		}
		AbstractIntegratedBoardHandler.integratedBoardWriterChangeCount = 1;
		System.out.println("[개인정보 수정 완료]\n");

	}



}
