package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.Comment;

public class SellerBoardListHandler extends AbstractSellerBoardHandler {

  public SellerBoardListHandler(List<Board> sellerBoardList, List<Comment> sellerCommentList) {
    super(sellerBoardList, sellerCommentList);
  }

  @Override
  public void service() {
    if(sellerBoardList.size() == 0) {
      System.out.println("존재하는 게시글이 없습니다.\n");
      return;
    }
    System.out.println("■ 메뉴 - 판매자 게시판 - 게시글 목록 ■");
    Iterator<Board> iterator = sellerBoardList.iterator();
    while(iterator.hasNext()) {
      Board s = iterator.next();
      System.out.printf("번호 : [%d]  제목 : [%s]  작성자 : [%s]  추천 : [%d]  조회수 : [%d]  작성일 : [%s]\n",
          s.getNumber(), s.getTitle(), s.getWriter(), s.getLike(), s.getView(), s.getRegisteredDate());
    }
    System.out.println();
  }

}
