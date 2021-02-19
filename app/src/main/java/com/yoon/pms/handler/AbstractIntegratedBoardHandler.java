package com.yoon.pms.handler;

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
  public static int changeCount = 0;


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

  //  public void myList()  {
  //    if()
  //    Object[] list = sellerBoardList.toArray();
  //    System.out.println();
  //    System.out.printf("%s 님의 게시글 목록\n", m.getBusinessName());
  //    for(Object obj : list) {
  //      Board b = (Board)obj;
  //      if(b.getId() == m.getHash()) {
  //        System.out.printf("제목 : [%s] 내용 : [%s]\n", b.getTitle(), b.getContent());
  //      }
  //    }
  //    System.out.println();
  //  }

  public void commentList(Board b) {
    Comment[] list = commentList.toArray(new Comment[commentList.size()]);
    System.out.println();
    System.out.println("■ 댓글 ■");
    for(Comment c : list) {
      if(b.getNumber() == c.getCommentId()) {
        System.out.printf("%d. %s : %s\n", c.getCommentNumber(), c.getCommentWriter(), c.getComment());
      }
    }
    System.out.println();
  }

  //  public void myList(SellerMember m) throws CloneNotSupportedException {
  //    //    Iterator<Board> buyerIterator = sellerBoardList.iterator();
  //    //    Iterator<Board> sellerIterator = sellerBoardList.
  //
  //    System.out.println();
  //    System.out.printf("%s 님의 게시글 목록\n", m.getBusinessName());
  //
  //    while(iterator.hasNext()) {
  //      Board b = iterator.next();
  //      if(b.getId() == m.getHash()) {
  //        System.out.printf("게시글 번호 : [%d]   제목 : [%s]   내용 : [%s]\n", b.getNumber(), b.getTitle(), b.getContent());
  //      }
  //    }
  //    System.out.println();
  //  }

  public Board findByNum(int boardNum) {
    Object[] list = integratedBoardList.toArray();
    for (Object obj : list) {
      Board i = (Board) obj;
      if (i.getNumber() == boardNum) {
        return i;
      }
    }
    return null;
  }

  public void changeWriter() {
    BuyerMember b = AbstractMemberHandler.buyerMemberNumber;
    SellerMember s = AbstractMemberHandler.sellerMemberNumber;
    Object[] list = integratedBoardList.toArray();
    for(Object obj : list) {
      Board i = (Board)obj;
      if(i.getId() == s.getHash()) {
        i.setWriter(s.getBusinessName());
      }
      else if(i.getId() == b.getHash()) {
        i.setWriter(b.getNickname());
      }
    }
    changeCount = 0;
  }



}
