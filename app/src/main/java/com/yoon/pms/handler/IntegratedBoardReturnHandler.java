package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.App;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.Comment;

public class IntegratedBoardReturnHandler extends AbstractIntegratedBoardHandler {

	public IntegratedBoardReturnHandler(List<Board> integratedBoardList, List<Comment> commentList) {
		super(integratedBoardList, commentList);
	}

	@Override
	public void service() {
		App.location = -1;
	}

}
