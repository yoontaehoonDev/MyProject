package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Comment;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public abstract class AbstractIntegratedBoardHandler implements Command {

  protected List<Board> integratedBoardList;
  protected List<Comment> commentList;

  public AbstractIntegratedBoardHandler(List<Board> integratedBoardList, List<Comment> commentList) {
    this.integratedBoardList = integratedBoardList;
    this.commentList = commentList;
  }

  public static boolean boardAuthorization = false;
  public static int boardIndex = 1;
  public static int commentCount = 0;
  public static int buyerNum = 0;
  public static int sellerNum = 1;
  public static int flag;
  public static int likeCount = 0;
  public static int integratedBoardWriterChangeCount = 0;
  public static int integratedBoardCommentWriterChangeCount = 0;
  public static int buyerIntegratedBoardCount;
  public static int sellerIntegratedBoardCount;


  public void update(Board i) {
    System.out.println("■ 메뉴 - 판매회원 게시판 - 게시글 수정 ■");

    i.setTitle(Prompt.inputString("수정할 제목 : "));
    i.setContent(Prompt.inputString("수정할 내용 : "));

    System.out.println("수정 완료");
  }

  public void delete(Board i) {
    System.out.println("■ 메뉴 - 판매회원 게시판 - 게시글 삭제 ■");
    integratedBoardList.remove(i);
    System.out.println("게시글이 삭제되었습니다.");
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

  public void commentAdd(Board b) {
    BuyerMember buyer = AbstractMemberHandler.buyerMemberNumber;
    SellerMember seller = AbstractMemberHandler.sellerMemberNumber;
    Comment c = new Comment();


    c.setCommentId(b.getNumber());
    //    c.setCommentWriter(m.getNickname());
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

    BuyerMember buyer = AbstractMemberHandler.buyerMemberNumber;
    SellerMember seller = AbstractMemberHandler.sellerMemberNumber;
    Comment c = new Comment();

    c.setCommentId(b.getNumber());
    //    c.setCommentWriter(m.getNickname());
    c.setComment(Prompt.inputString("대댓글 : "));

    //    c.setNestedCommentNumber(b.getCommentCount());

    this.commentList.add(insert, c); 

  }

  public int findByCommentNum(int commentNum) {

    Iterator<Comment> iterator = commentList.iterator();
    int i = 0;
    //    Iterator<Board> boardIterator = buyerBoardList.iterator();

    //      while(boardIterator.hasNext()) {
    //          if(boardIterator.next().getNumber() == iterator.next().getCommentId()) {
    //              j = 1;
    //          }
    while(iterator.hasNext()) {
      if(iterator.next().getCommentNumber() == commentNum) {
        return i+1;
      }
      i++;
    }
    return -1;
  }

  public Board findByNum(int boardNum) {

    Iterator<Board> iterator = integratedBoardList.iterator();

    while(iterator.hasNext()) {
      if(iterator.next().getNumber() == boardNum) {
        return iterator.next();
      }
    }
    return null;

    //    Object[] list = integratedBoardList.toArray();
    //    for (Object obj : list) {
    //      Board i = (Board) obj;
    //      if (i.getNumber() == boardNum) {
    //        return i;
    //      }
    //    }
    //    return null;
  }

  public void changeWriter() {
    BuyerMember buyer = AbstractMemberHandler.buyerMemberNumber;
    SellerMember seller = AbstractMemberHandler.sellerMemberNumber;

    Iterator<Board> iterator = integratedBoardList.iterator();

    while(iterator.hasNext()) {
      Board b = iterator.next();
      if(b.getId() == buyer.getHash()) {
        b.setWriter(buyer.getNickname());
      }
      else if (b.getId() == seller.getHash()) {
        b.setWriter(seller.getBusinessName());
      }
    }

    //    Object[] list = integratedBoardList.toArray();
    //    for(Object obj : list) {
    //      Board i = (Board)obj;
    //      if(i.getId() == s.getHash()) {
    //        i.setWriter(s.getBusinessName());
    //      }
    //      else if(i.getId() == b.getHash()) {
    //        i.setWriter(b.getNickname());
    //      }
    //    }
    integratedBoardWriterChangeCount = 0;
  }



}
