package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Comment;
import com.yoon.util.Prompt;

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


  public void update(Board b) {
    System.out.println("■ 메뉴 - 구매회원 게시판 - 게시글 수정 ■");

    b.setTitle(Prompt.inputString("수정할 제목 : "));
    b.setContent(Prompt.inputString("수정할 내용 : "));

    System.out.println("수정 완료\n");

  }

  public void delete(Board b) {

    buyerBoardList.remove(b);
    System.out.println("게시글이 삭제되었습니다.\n");
  }

  public void commentAdd(Board b) {
    BuyerMember m = AbstractMemberHandler.buyerMemberNumber;
    Comment c = new Comment();
    c.setCommentId(b.getNumber());
    c.setCommentWriter(m.getNickname());
    c.setComment(Prompt.inputString("댓글 : "));

    b.setCommentCount(b.getCommentCount() + 1);
    c.setCommentNumber(b.getCommentCount());
    this.commentList.add(c);

  }

  public void nestedCommentAdd(Board b, int index) {

    int insert = findByCommentNum(index);

    if(insert == -1) {
      System.out.println("선택하신 댓글이 존재하지 않습니다.\n");
      return;
    }

    BuyerMember m = AbstractMemberHandler.buyerMemberNumber;
    Comment c = new Comment();

    c.setCommentId(b.getNumber());
    c.setCommentWriter(m.getNickname());
    c.setComment(Prompt.inputString("대댓글 : "));

    //    c.setNestedCommentNumber(b.getCommentCount());

    this.commentList.add(insert, c); 

  }

  public int findByCommentNum(int commentNum) {

    Iterator<Comment> iterator = commentList.iterator();
    int i = 0;
    //    Iterator<Board> boardIterator = buyerBoardList.iterator();

    //		while(boardIterator.hasNext()) {
    //			if(boardIterator.next().getNumber() == iterator.next().getCommentId()) {
    //				j = 1;
    //			}
    while(iterator.hasNext()) {
      if(iterator.next().getCommentNumber() == commentNum) {
        return i+1;
      }
      i++;
    }
    return -1;
  }

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
    System.out.println();
  }

  public Board findByNum(int boardNum) {

    Iterator<Board> iterator = buyerBoardList.iterator();

    while(iterator.hasNext()) {
      Board b = iterator.next();
      if (b.getNumber() == boardNum) {
        return b;
      }
    }
    return null;
  }

  public void changeWriter() {
    BuyerMember m = AbstractMemberHandler.buyerMemberNumber;

    Iterator<Board> iterator = buyerBoardList.iterator();

    while(iterator.hasNext()) {
      Board b = iterator.next();
      if (b.getId() == m.getHash()) {
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
