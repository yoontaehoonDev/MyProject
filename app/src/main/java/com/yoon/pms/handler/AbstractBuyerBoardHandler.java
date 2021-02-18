package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Comment;

public abstract class AbstractBuyerBoardHandler implements Command {

  protected List<Board> buyerBoardList;
  protected List<Comment> commentList;

  public AbstractBuyerBoardHandler(List<Board> buyerBoardList, List<Comment> commentList) {
    this.buyerBoardList = buyerBoardList;
    this.commentList = commentList;
  }


  public static boolean boardAuthorization = false;
  public static int boardIndex = 1;
  public static int commentCount = 0;
  public static int likeCount = 0;
  public static int buyerBoardWriterChangeCount = 0;
  public static int buyerBoardCommentWriterChangeCount = 0;



  public void commentList(Board b) {

    Iterator<Comment> iterator = commentList.iterator();

    System.out.println();
    System.out.println("■ 댓글 ■");
    while(iterator.hasNext()) {
      Comment c = iterator.next(); 
      if(c.getCommentId() == b.getNumber()) {
        if(c.getCommentNumber() == 0)
          System.out.printf("  └ %s : %s\n", c.getCommentWriter(), c.getComment());
        else 
          System.out.printf("%d. %s : %s\n", c.getCommentNumber(), c.getCommentWriter(), c.getComment());
      }
    }
    //  Comment[] list = commentList.toArray(new Comment[commentList.size()]);
    //    for(Comment c : list) {
    //      if(b.getNumber() == c.getCommentId()) {
    //        System.out.printf("%d. %s : %s\n", c.getCommentNumber(), c.getCommentWriter(), c.getComment());
    //      }
    //    }
    System.out.println();
  }

  public int findByCommentNum(int commentNum) {

    Iterator<Comment> iterator = commentList.iterator();
    int i = 0;

    while(iterator.hasNext()) {
      if(iterator.next().getCommentNumber() == commentNum) {
        return i+1;
      }
      i++;
    }
    return -1;
  }


  public Board findByNum(int boardNum) {

    Board[] list = buyerBoardList.toArray(new Board[buyerBoardList.size()]);
    for (Board b : list) {
      if (b.getNumber() == boardNum) {
        return b;
      }
    }
    return null;
  }

  public void changeWriter() {
    BuyerMember m = MemberHandler.buyerMemberNumber;
    Board[] list = buyerBoardList.toArray(new Board[buyerBoardList.size()]);
    for(Board b : list) {
      if(b.getId() == m.getHash()) {
        b.setWriter(m.getNickname());
      }
    }
    buyerBoardWriterChangeCount = 0;
  }

  //  private void changeCommentWriter() {
  //    
  //    Comment c = MemberHandler.buyerMemberNumber;
  //    BuyerMember m = MemberHandler.buyerMemberNumber;
  //    Board[] list = buyerBoardList.toArray(new Board[buyerBoardList.size()]);
  //    for(Board b : list) {
  //      if(b.getId() == m.getHash()) {
  //        
  //        b.setWriter(m.getNickname());
  //      }
  //    }
  //    buyerBoardCommentWriterChangeCount = 0;
  //  }

}
