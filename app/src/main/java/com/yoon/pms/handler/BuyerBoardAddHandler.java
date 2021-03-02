package com.yoon.pms.handler;

import java.util.List;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Comment;
import com.yoon.util.Prompt;

public class BuyerBoardAddHandler extends AbstractBuyerBoardHandler {


  public BuyerBoardAddHandler(List<Board> buyerBoardList, List<Comment> buyerCommentList) {
    super(buyerBoardList, buyerCommentList);
  }

  @Override
  public void service() {
    Board b = new Board();
    BuyerMember m = AbstractMemberHandler.buyerMemberNumber;
    if(boardAuthorization == true && m.isDivision() == true) {
      System.out.println("■ 메뉴 - 구매회원 게시판 - 게시글 작성 ■");

      b.setId(m.getHash());
      b.setNumber(AbstractBuyerBoardHandler.boardIndex++);
      b.setTitle(Prompt.inputString("제목 입력 : "));
      b.setContent(Prompt.inputString("내용 입력 : "));
      b.setWriter(m.getNickname());
      b.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
      this.buyerBoardList.add(b);

      System.out.println("글 작성 완료\n");

    }
    else {
      System.out.println("구매회원만 작성 가능합니다.\n");
    }

  }

}
