package com.yoon.pms.handler;

import java.util.List;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.Comment;
import com.yoon.util.Prompt;

public class BuyerBoardUpdateHandler extends AbstractBuyerBoardHandler {

  public BuyerBoardUpdateHandler(List<Board> buyerBoardList, List<Comment> commentList) {
    super(buyerBoardList, commentList);
  }

  public void service(Board b) {
    System.out.println("■ 메뉴 - 구매회원 게시판 - 게시글 수정 ■");

    b.setTitle(Prompt.inputString("수정할 제목 : "));
    b.setContent(Prompt.inputString("수정할 내용 : "));

    System.out.println("수정 완료\n");

  }

}
