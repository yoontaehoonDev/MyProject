package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.App;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.Comment;

public class BuyerBoardReturnHandler extends AbstractBuyerBoardHandler {

	public BuyerBoardReturnHandler(List<Board> buyerBoardList, List<Comment> buyerCommentList) {
		super(buyerBoardList, buyerCommentList);
	}

	@Override
	public void service() {
		App.location = 0;
	}

}
