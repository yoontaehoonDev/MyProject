package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.LinkedList;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Comment;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class IntegratedBoardHandler {

  public static boolean boardAuthorization = false;
  private LinkedList<Board> integratedBoardList = new LinkedList<>();
  private LinkedList<Comment> commentList = new LinkedList<>();

  int boardIndex = 1;
  int commentCount = 0;
  int buyerNum = 0;
  int sellerNum = 1;
  int flag;

  public static int likeCount = 0;
  public static int changeCount = 0;

  public void service() throws CloneNotSupportedException {

    if(MemberHandler.logStatus == -1) {
      System.out.println("로그인 후 이용 가능합니다.\n");
      return;
    }

    if(changeCount == 1) {
      changeWriter();
    }

    /*
    if(MemberHandler.logStatus == 0) {

    }
    else if(MemberHandler.logStatus == 1) {

    }
    else {

    }
     */
    while(true) {
      System.out.println("■ 메뉴 - 통합 게시판 ■");

      System.out.println("[회원 전용]");
      System.out.println("1. 글 쓰기");
      System.out.println("2. 글 목록");
      System.out.println("3. 글 상세보기");
      System.out.println("4. 신고하기");

      System.out.println();
      String menu = Prompt.inputString("메뉴 입력 : ");

      switch(menu) {
        case "글쓰기":
          this.add();
          break;
        case "글목록":
          this.list();
          break;
        case "글보기":
          this.detail();
          break;
        case "뒤로가기":
          System.out.println("초기 화면으로 전환합니다.\n");
          return;
      }
    }
  }

  public void add() {
    Board i = new Board();
    if(boardAuthorization == true) {
      System.out.println("■ 메뉴 - 통합게시판 - 게시글 작성 ■");
      i.setNumber(boardIndex++);
      i.setTitle(Prompt.inputString("제목 입력 : "));
      i.setContent(Prompt.inputString("내용 입력 : "));
      if(MemberHandler.logStatus == 0) {
        i.setWriter(MemberHandler.buyerMemberNumber.getNickname());
        i.setId(MemberHandler.buyerMemberNumber.getHash());
        i.setOwner(buyerNum);
      }
      else if (MemberHandler.logStatus == 1) {
        i.setWriter(MemberHandler.sellerMemberNumber.getBusinessName());
        i.setId(MemberHandler.sellerMemberNumber.getHash());
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

  public void list() throws CloneNotSupportedException {
    if(integratedBoardList.size() == 0) {
      System.out.println("존재하는 게시글이 없습니다.");
      return;
    }

    int count;

    System.out.println("■ 메뉴 - 통합게시판 - 게시글 목록 ■\n");

    Iterator<Board> iterator = integratedBoardList.iterator();

    while(true) {
      System.out.println("1. 모든 게시글  2. 구매회원 게시글  3. 판매회원 게시글");

      String choice = Prompt.inputString("선택 : ");

      if(choice.equals("1")) {
        System.out.println("■ 메뉴 - 통합게시판 - 게시글 목록 - 통합 게시글 ■\n");
        while(iterator.hasNext()) {
          count = 0;
          Board i = iterator.next();
          if(i.getOwner() == buyerNum) {
            System.out.print("[구매회원] ");
          }
          else {
            System.out.print("[판매회원] ");
          }
          System.out.printf("번호 : [%d]  제목 : [%s]  작성자 : [%s]  추천 : [%d]  조회수 : [%d]  작성일 : [%s]\n",
              i.getNumber(), i.getTitle(), i.getWriter(), i.getLike(), i.getView(), i.getRegisteredDate());
        }
        break;
      }
      else if(choice.equals("2")) {
        count = 0;
        System.out.println("■ 메뉴 - 통합게시판 - 게시글 목록 - 구매회원 게시글 ■\n");
        while(iterator.hasNext()) {
          Board i = iterator.next();
          if(i.getOwner() == buyerNum) {
            System.out.printf("번호 : [%d]  제목 : [%s]  작성자 : [%s]  추천 : [%d]  조회수 : [%d]  작성일 : [%s]\n",
                i.getNumber(), i.getTitle(), i.getWriter(), i.getLike(), i.getView(), i.getRegisteredDate());
            count = 1;
          }
        }
        if(count == 0) {
          System.out.println("구매회원 게시글이 존재하지 않습니다.");
        }
        break;
      }
      else if(choice.equals("3")) {
        count = 0;
        System.out.println("■ 메뉴 - 통합게시판 - 게시글 목록 - 판매회원 게시글 ■\n");
        while(iterator.hasNext()) {
          Board i = iterator.next();
          if(i.getOwner() == sellerNum) {
            System.out.printf("번호 : [%d]  제목 : [%s]  작성자 : [%s]  추천 : [%d]  조회수 : [%d]  작성일 : [%s]\n",
                i.getNumber(), i.getTitle(), i.getWriter(), i.getLike(), i.getView(), i.getRegisteredDate());
            count = 1;
          }  
        }
        if(count == 0) {
          System.out.println("판매회원 게시글이 존재하지 않습니다.");
        }
        break;
      }
      else {
        System.out.println("잘못 입력하셨습니다.");
      }
    }
    System.out.println();
  }

  public void commentAdd(Board b) {
    SellerMember seller = MemberHandler.sellerMemberNumber;
    BuyerMember buyer = MemberHandler.buyerMemberNumber;
    Comment c = new Comment();
    if(flag == 0) { 
      c.setCommentId(b.getNumber());
      c.setCommentWriter(buyer.getNickname());
      c.setComment(Prompt.inputString("댓글 : "));
    }
    else if (flag == 1) {
      c.setCommentId(b.getNumber());
      c.setCommentWriter(seller.getBusinessName());
      c.setComment(Prompt.inputString("댓글 : "));
    }

    b.setCommentCount(b.getCommentCount() + 1);
    c.setCommentNumber(b.getCommentCount());
    this.commentList.add(c);
    flag = -1;
  }

  public void detail() throws CloneNotSupportedException {
    if(integratedBoardList.size() == 0) {
      System.out.println("존재하는 게시글이 없습니다.");
      return;
    }

    String choice;
    list();

    System.out.println("---------------------------------------");
    System.out.println("■ 메뉴 - 통합게시판 - 게시글 보기 ■");
    int num = Prompt.inputInt("게시글 번호 입력 : ");

    Board board = findByNum(num);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    board.setView(board.getView() + 1);

    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성자: %s\n", board.getWriter());
    System.out.printf("등록일: %s\n", board.getRegisteredDate());
    System.out.printf("추천수 : %d\n", board.getLike());
    System.out.printf("조회수: %d\n", board.getView());

    commentList(board);

    while(true) {
      if(MemberHandler.logStatus == 0) {
        flag = 0;
      }
      else if(MemberHandler.logStatus == 1) {
        flag = 1;
      }
      System.out.println("1. 댓글 작성  2. 나가기");
      choice = Prompt.inputString("선택 : ");
      if(choice.equals("1")) {
        commentAdd(board);
        break;
      }
      else if(choice.equals("2")) {
        break;
      }

      else {
        System.out.println("잘못 입력하셨습니다.");
      }
    }


    if(MemberHandler.logStatus == 0) {
      // 구매 or 판매회원 해쉬값 수정 필요
      if(board.getId() == MemberHandler.buyerMemberNumber.getHash()) {
        Board i = board;
        while(true) {
          System.out.println("1. [수정]  2. [삭제]  3. [뒤로가기]");
          choice = Prompt.inputString("선택 : ");
          if(choice.equals("1")) {
            update(i);
            break;
          }
          else if(choice.equals("2")) {
            delete(i);
            break;
          }
          else if(choice.equals("3")) {
            break;
          }
          else {
            System.out.println("잘못 누르셨습니다.\n");
          }
        }
      }
    }
    else if (MemberHandler.logStatus == 1) {
      if(board.getId() == MemberHandler.sellerMemberNumber.getHash()) {
        Board i = board;
        while(true) {
          System.out.println("1. [수정]  2. [삭제]");
          choice = Prompt.inputString("선택 : ");
          if(choice.equals("1")) {
            update(i);
            break;
          }
          else if(choice.equals("2")) {
            delete(i);
            break;
          }
          else {
            System.out.println("잘못 누르셨습니다.\n");
            break;
          }
        }
      }
    }
    System.out.println();
  }


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

  private void commentList(Board b) {
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

  private Board findByNum(int boardNum) {
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
    Object[] list = integratedBoardList.toArray();
    for(Object obj : list) {
      Board i = (Board)obj;
      if(i.getId() == MemberHandler.sellerMemberNumber.getHash()) {
        i.setWriter(MemberHandler.sellerMemberNumber.getBusinessName());
      }
      else if(i.getId() == MemberHandler.buyerMemberNumber.getHash()) {
        i.setWriter(MemberHandler.buyerMemberNumber.getNickname());
      }
    }
    changeCount = 0;
  }

}
