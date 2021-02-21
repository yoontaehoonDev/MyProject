package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.App;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.Comment;

public class BuyerBoardServiceHandler extends AbstractBuyerBoardHandler {

	public BuyerBoardServiceHandler(List<Board> buyerBoardList, List<Comment> buyerCommentList) {
		super(buyerBoardList, buyerCommentList);
	}

	@Override
	public void service() {
		if (AbstractMemberHandler.logCount == true) {
			App.location = 2;
		}
		else {
			System.out.println("로그인 후 이용 가능합니다.\n");
		}
	}

}
