package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Comment;
import com.yoon.pms.domain.SellerMember;

public class IntegratedBoardMyListHandler extends AbstractIntegratedBoardHandler {

  public IntegratedBoardMyListHandler(List<Board> integratedBoardList, List<Comment> commentList) {
    super(integratedBoardList, commentList);
  }

  @Override
  public void service() {
    if(IntegratedBoardWriterChangeCount == 1) {
      changeWriter();
    }

    Iterator<Board> iterator = integratedBoardList.iterator();

    System.out.println();

    if(AbstractMemberHandler.logStatus == 0) {
      BuyerMember buyer = AbstractMemberHandler.buyerMemberNumber;
      System.out.printf("%s 님의 게시글 목록\n", buyer.getNickname());
      while(iterator.hasNext()) {
        Board b = iterator.next();
        if(b.getId() == buyer.getHash()) {
          System.out.printf("게시글 번호 : [%d]   제목 : [%s]   내용 : [%s]\n", b.getNumber(), b.getTitle(), b.getContent());
        }
      }
    }
    else if (AbstractMemberHandler.logStatus == 1) {
      SellerMember seller = AbstractMemberHandler.sellerMemberNumber;
      System.out.printf("%s 님의 게시글 목록\n", seller.getBusinessName());
      while(iterator.hasNext()) {
        Board b = iterator.next();
        if(b.getId() == seller.getHash()) {
          System.out.printf("게시글 번호 : [%d]   제목 : [%s]   내용 : [%s]\n", b.getNumber(), b.getTitle(), b.getContent());
        }
      }
    }

    System.out.println();
  }

}
