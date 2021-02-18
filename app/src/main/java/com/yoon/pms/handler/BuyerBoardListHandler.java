package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;

import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.Comment;

public class BuyerBoardListHandler extends AbstractBuyerBoardHandler {

	public BuyerBoardListHandler(List<Board> buyerBoardList, List<Comment> commentList) {
		super(buyerBoardList, commentList);
	}

	@Override
	public void service() {
		if(buyerBoardWriterChangeCount == 1) {
			changeWriter();
		}
		if(buyerBoardList.size() == 0) {
			System.out.println("존재하는 게시글이 없습니다.\n");
			return;
		}

		System.out.println("■ 메뉴 - 구매회원 게시판 - 게시글 목록 ■");

		Iterator<Board> iterator = buyerBoardList.iterator();
		while(iterator.hasNext()) {
			Board b = iterator.next();
			System.out.printf("번호 : [%d]  제목 : [%s]  작성자 : [%s]  추천 : [%d]  조회수 : [%d]  작성일 : [%s]\n",
					b.getNumber(), b.getTitle(), b.getWriter(), b.getLike(), b.getView(), b.getRegisteredDate());
		}
		System.out.println();
	}


}
