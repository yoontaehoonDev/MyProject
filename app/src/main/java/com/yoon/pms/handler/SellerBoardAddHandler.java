package com.yoon.pms.handler;

import java.util.List;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.Comment;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class SellerBoardAddHandler extends AbstractSellerBoardHandler {

  public SellerBoardAddHandler(List<Board> sellerBoardList, List<Comment> sellerCommentList) {
    super(sellerBoardList, sellerCommentList);
  }

  @Override
  public void service() {
    Board s = new Board();
    SellerMember m = AbstractMemberHandler.sellerMemberNumber;
    if(boardAuthorization == true) {
      System.out.println("■ 메뉴 - 판매회원 게시판 - 게시글 작성 ■");
      s.setId(m.getHash());
      s.setNumber(AbstractSellerBoardHandler.boardIndex++);
      s.setTitle(Prompt.inputString("제목 입력 : "));
      s.setContent(Prompt.inputString("내용 입력 : "));
      s.setWriter(m.getBusinessName()); // 체크
      s.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
      this.sellerBoardList.add(s);

      System.out.println("글 작성 완료\n");
    }
    else {
      System.out.println("판매회원만 작성 가능합니다.\n");
    }
  }


}
