package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;

import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Comment;

public class BuyerBoardMyListHandler extends AbstractBuyerBoardHandler {

	public BuyerBoardMyListHandler(List<Board> buyerBoardList, List<Comment> buyerCommentList) {
		super(buyerBoardList, buyerCommentList);
	}

	@Override
	public void service() {
		if(buyerBoardWriterChangeCount == 1) {
			changeWriter();
		}
		if(buyerBoardCommentWriterChangeCount == 1) {
			changeCommentWriter();
		}
		BuyerMember m = AbstractMemberHandler.buyerMemberNumber;
		Iterator<Board> iterator = buyerBoardList.iterator();

		System.out.println();
		System.out.printf("[%s 님의 게시글 목록]\n", m.getNickname());

		while(iterator.hasNext()) {
			Board b = iterator.next();
			if(b.getId() == m.getHash()) {
				System.out.printf("게시글 번호 : [%d]   제목 : [%s]   내용 : [%s]\n", b.getNumber(), b.getTitle(), b.getContent());
			}
		}
		System.out.println();
	}

}
