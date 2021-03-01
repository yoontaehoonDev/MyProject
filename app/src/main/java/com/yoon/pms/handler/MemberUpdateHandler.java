package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Comment;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class MemberUpdateHandler extends AbstractMemberHandler {

	private MemberValidatorHandler memberHandler;
	protected List<Board> buyerBoardList;
	protected List<Comment> buyerCommentList;
	protected List<Board> sellerBoardList;
	protected List<Comment> sellerCommentList;

	public MemberUpdateHandler(List<BuyerMember> buyerMemberList, List<SellerMember> sellerMemberList, List<Board> buyerBoardList, 
			List<Comment> buyerCommentList, List<Board> sellerBoardList, 
			List<Comment> sellerCommentList, MemberValidatorHandler memberHandler) {
		super(buyerMemberList, sellerMemberList);
		this.buyerBoardList = buyerBoardList;
		this.buyerCommentList = buyerCommentList;
		this.sellerBoardList = sellerBoardList;
		this.sellerCommentList = sellerCommentList;
		this.memberHandler = memberHandler;

	}

	@Override
	public void service() {
		System.out.println("업데이트 접근");
		if(logStatus == 0){
			BuyerMember b = buyerMemberNumber;
			b.setId(findByBuyerId("수정할 ID : "));
			b.setPassword(memberHandler.minimumLength("수정할 Password : "));
			b.setName(Prompt.inputString("수정할 이름 : "));
			b.setNickname(findByNickname("수정할 닉네임 : "));
			b.setEmail(memberHandler.emailFormat("수정할 E-Mail : "));
			b.setPhone(memberHandler.phoneFormat("수정할 핸드폰 번호 : "));
			System.out.println("메소드 접근");

			BuyerBoardChangeWriterHandler execute = new BuyerBoardChangeWriterHandler(buyerBoardList, buyerCommentList);
			execute.service();
		}
		else if(logStatus == 1) {
			SellerMember s = sellerMemberNumber;
			s.setId(findByBuyerId("수정할 ID : "));
			s.setPassword(memberHandler.minimumLength("수정할 Password : "));
			s.setName(Prompt.inputString("수정할 이름 : "));
			s.setEmail(memberHandler.emailFormat("수정할 E-Mail : "));
			s.setPhone(memberHandler.phoneFormat("수정할 핸드폰 번호 : "));
			System.out.println("가게 관련 정보 변경은 고객센터(1542-1542)로 연락 바랍니다.");

			SellerBoardChangeWriterHandler execute = new SellerBoardChangeWriterHandler(sellerBoardList, sellerCommentList);
			execute.service();

		}
		AbstractIntegratedBoardHandler.integratedBoardWriterChangeCount = 1;
		System.out.println("[개인정보 수정 완료]\n");

	}



}
