package com.yoon.pms.handler;

import java.util.List;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Comment;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class IntegratedBoardAddHandler extends AbstractIntegratedBoardHandler {

  public IntegratedBoardAddHandler(List<Board> integratedBoardList, List<Comment> commentList) {
    super(integratedBoardList, commentList);
  }

  @Override
  public void service() {
    Board i = new Board();
    BuyerMember b = AbstractMemberHandler.buyerMemberNumber;
    SellerMember s = AbstractMemberHandler.sellerMemberNumber;
    if(boardAuthorization == true) {
      System.out.println("■ 메뉴 - 통합게시판 - 게시글 작성 ■");
      i.setNumber(boardIndex++);
      i.setTitle(Prompt.inputString("제목 입력 : "));
      i.setContent(Prompt.inputString("내용 입력 : "));
      if(AbstractMemberHandler.logStatus == 0) {
        i.setWriter(b.getNickname());
        i.setId(b.getHash());
        i.setOwner(buyerNum);
      }
      else if (AbstractMemberHandler.logStatus == 1) {
        i.setWriter(s.getBusinessName());
        i.setId(s.getHash());
        i.setOwner(sellerNum);
      }
      i.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));

      this.integratedBoardList.add(i);

      System.out.println("글 작성 완료");
    }
    else {
      System.out.println("회원만 작성 가능합니다.");
    }
  }

}
