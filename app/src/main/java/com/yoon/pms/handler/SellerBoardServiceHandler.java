package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.App;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.Comment;

public class SellerBoardServiceHandler extends AbstractSellerBoardHandler {

	public SellerBoardServiceHandler(List<Board> sellerBoardList, List<Comment> sellerCommentList) {
		super(sellerBoardList, sellerCommentList);
	}

	@Override
	public void service() {
		if (AbstractMemberHandler.logCount == true) {
			App.location = 3;
		}
		else {
			System.out.println("로그인 후 이용 가능합니다.\n");
		}
	}


}
