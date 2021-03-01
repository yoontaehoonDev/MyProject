package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;

import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.Comment;
import com.yoon.pms.domain.SellerMember;

public class SellerBoardChangeWriterHandler extends AbstractSellerBoardHandler {

	public SellerBoardChangeWriterHandler(List<Board> sellerBoardList, List<Comment> sellerCommentList) {
		super(sellerBoardList, sellerCommentList);
	}

	@Override
	public void service() {
		Iterator<Board> boardIterator = sellerBoardList.iterator();
		Iterator<Comment> commentIterator = sellerCommentList.iterator();
		SellerMember seller = AbstractMemberHandler.sellerMemberNumber;

		while(boardIterator.hasNext()) {
			Board b = boardIterator.next();
			if (b.getId() == seller.getHash()) {
				b.setWriter(seller.getBusinessName());
			}
		}
		while(commentIterator.hasNext()) {
			Comment c = commentIterator.next();
			if(c.getDivision() == seller.getHash()) {
				c.setCommentWriter(seller.getBusinessName());
			}
		}
	}

}
