package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Comment;

public class BuyerBoardChangeWriterHandler extends AbstractBuyerBoardHandler {

  public BuyerBoardChangeWriterHandler(List<Board> buyerBoardList, List<Comment> buyerCommentList) {
    super(buyerBoardList, buyerCommentList);
  }

  @Override
  public void service() {
    Iterator<Board> boardIterator = buyerBoardList.iterator();
    Iterator<Comment> commentIterator = buyerCommentList.iterator();
    BuyerMember buyer = AbstractMemberHandler.buyerMemberNumber;

    while(boardIterator.hasNext()) {
      Board b = boardIterator.next();
      if (b.getId() == buyer.getHash()) {
        b.setWriter(buyer.getNickname());
      }
    }

    while(commentIterator.hasNext()) {
      Comment c = commentIterator.next();
      if(c.getDivision() == buyer.getHash()) {
        c.setCommentWriter(buyer.getNickname());
      }
    }
  }

}
