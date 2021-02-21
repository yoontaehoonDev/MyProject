package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.App;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.Comment;

public class IntegratedBoardServiceHandler extends AbstractIntegratedBoardHandler {

	public IntegratedBoardServiceHandler(List<Board> integratedBoardList, List<Comment> commentList) {
		super(integratedBoardList, commentList);
	}

	@Override
	public void service() {
		if (AbstractMemberHandler.logCount == true) {
			App.location = 4;
		}
		else {
			System.out.println("로그인 후 이용 가능합니다.\n");
		}
	}

}
