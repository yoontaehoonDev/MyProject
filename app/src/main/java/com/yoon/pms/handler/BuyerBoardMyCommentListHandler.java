package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;

import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Comment;

public class BuyerBoardMyCommentListHandler extends AbstractBuyerBoardHandler {

	public BuyerBoardMyCommentListHandler(List<Board> buyerBoardList, List<Comment> buyerCommentList) {
		super(buyerBoardList, buyerCommentList);
	}

	@Override
	public void service() {
		System.out.println("■ 메뉴 - 구매자 - 내 댓글 ■\n");

		int flag = 0;
		BuyerMember m = AbstractMemberHandler.buyerMemberNumber;
		Iterator<Comment> iterator = buyerCommentList.iterator();

		System.out.printf("[%s 님의 댓글 목록]\n", m.getNickname());
		while(iterator.hasNext()) {
			Comment c = iterator.next();
			if(c.getCommentWriter().equals(m.getNickname())) {
				if(c.getCommentNumber() != 0) {
					System.out.printf("게시글 번호 : [%d]    댓글 번호 : [%d]    내용 : [%s]\n", c.getCommentId(), c.getCommentNumber(), c.getComment());
					flag = 1;
				}
				else {
					System.out.printf("게시글 번호 : [%d]                       대댓글 내용 : [%s]\n", c.getCommentId(), c.getComment());
				}
			}
		}

		if(flag == 0) {
			System.out.println("작성한 댓글이 없습니다.");
		}
		System.out.println();
	}

}
