package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;

import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Comment;

public class BuyerBoardChangeWriterHandler implements Command {

	protected List<Board> buyerBoardList;
	protected List<Comment> buyerCommentList;

	public BuyerBoardChangeWriterHandler(List<Board> buyerBoardList, List<Comment> buyerCommentList) {
		this.buyerBoardList = buyerBoardList;
		this.buyerCommentList = buyerCommentList;
	}

	@Override
	public void service() {
		System.out.println("체인지 접근");
		Iterator<Board> boardIterator = buyerBoardList.iterator();
		System.out.println("게시판 접근 후");
		Iterator<Comment> commentIterator = buyerCommentList.iterator();
		System.out.println("댓글 접근 후");

		BuyerMember buyer = AbstractMemberHandler.buyerMemberNumber;
		System.out.println("바이어 접근 후");

		while(boardIterator.hasNext()) {
			Board b = boardIterator.next();
			if (b.getId() == buyer.getHash()) {
				b.setWriter(buyer.getNickname());
			}
		}
		while(commentIterator.hasNext()) {
			Comment c = commentIterator.next();
			if(c.getDivision() == buyer.getHash()) {
				c.setCommentWriter(buyer.getNickname());
			}
		}
	}

}
