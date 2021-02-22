package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.Comment;
import com.yoon.pms.domain.SellerMember;

public class SellerBoardMyListHandler extends AbstractSellerBoardHandler {

  public SellerBoardMyListHandler(List<Board> sellerBoardList, List<Comment> sellerCommentList) {
    super(sellerBoardList, sellerCommentList);
  }


  @Override
  public void service()  {
    System.out.println("■ 메뉴 - 판매자 - 내가 쓴 글 ■\n");
    SellerMember m = AbstractMemberHandler.sellerMemberNumber;
    Iterator<Board> iterator = sellerBoardList.iterator();

    System.out.println();
    System.out.printf("[%s 님의 게시글 목록]", m.getBusinessName());

    while(iterator.hasNext()) {
      Board b = iterator.next();
      if(b.getId() == m.getHash()) {
        System.out.printf("게시글 번호 : [%d]   제목 : [%s]   내용 : [%s]\n", b.getNumber(), b.getTitle(), b.getContent());
      }
    }
    System.out.println();
  }

}
