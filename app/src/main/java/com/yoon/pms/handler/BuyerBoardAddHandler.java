package com.yoon.pms.handler;

import java.util.List;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.util.Prompt;

public class BuyerBoardAddHandler extends AbstractBuyerBoardHandler {


  public BuyerBoardAddHandler(List<Board> buyerBoardList, List<Command> commentList) {
    super(buyerBoardList, commentList);
  }

  @Override
  public void service() {
    Board b = new Board();
    BuyerMember m = MemberHandler.buyerMemberNumber;
    if(boardAuthorization == true && m.isDivision() == true) {
      System.out.println("■ 메뉴 - 구매회원 게시판 - 게시글 작성 ■");

      b.setNumber(boardIndex++);
      b.setTitle(Prompt.inputString("제목 입력 : "));
      b.setContent(Prompt.inputString("내용 입력 : "));
      b.setWriter(m.getNickname());
      b.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
      b.setId(m.getHash());
      this.buyerBoardList.add(b);

      System.out.println("글 작성 완료\n");

    }
    else {
      System.out.println("회원만 작성 가능합니다.\n");
    }

  }

}
