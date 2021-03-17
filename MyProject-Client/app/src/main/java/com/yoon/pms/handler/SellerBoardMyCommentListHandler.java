package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.Comment;
import com.yoon.pms.domain.SellerMember;

public class SellerBoardMyCommentListHandler extends AbstractSellerBoardHandler {

  public SellerBoardMyCommentListHandler(List<Board> sellerBoardList, List<Comment> sellerCommentList) {
    super(sellerBoardList, sellerCommentList);
  }


  @Override
  public void service()  {
    System.out.println("■ 메뉴 - 판매자 - 내 댓글 ■\n");

    int flag = 0;
    SellerMember m = AbstractMemberHandler.sellerMemberNumber;
    Iterator<Comment> iterator = sellerCommentList.iterator();

    System.out.printf("[%s 님의 댓글 목록]\n", m.getBusinessName());

    while(iterator.hasNext()) {
      Comment c = iterator.next();
      if(c.getCommentWriter().equals(m.getBusinessName())) {
        if(c.getCommentNumber() != 0) {
          System.out.printf("게시글 번호 : [%d]    댓글 번호 : [%d]    내용 : [%s]\n", c.getCommentId(), c.getCommentNumber(), c.getComment());
          flag = 1;
        }
        else {
          System.out.printf("게시글 번호 : [%d]                       대댓글 내용 : [%s]\n", c.getCommentId(), c.getComment());
        }
      }
    }

    if(flag == 0) {
      System.out.println("작성한 댓글이 없습니다.");
    }
    System.out.println();
  }
}
