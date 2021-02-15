package com.yoon.pms.handler;

import com.yoon.pms.domain.Board;
import com.yoon.util.Iterator;
import com.yoon.util.List;
import com.yoon.util.Prompt;

public class IntegratedBoardHandler {

  public static boolean boardAuthorization = false;
  private List integratedBoardList = new List();

  int boardIndex = 1;
  int commentCount = 0;
  public static int likeCount = 0;
  public static int changeCount = 0;

  public void service() throws CloneNotSupportedException {
    if(changeCount == 1) {
      changeWriter();
    }
    if(MemberHandler.logStatus == 0) {

    }
    else if(MemberHandler.logStatus == 1) {

    }
    else {

    }
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
      }
      else if (MemberHandler.logStatus == 1) {
        i.setWriter(MemberHandler.sellerMemberNumber.getBusinessName());
        i.setId(MemberHandler.sellerMemberNumber.getHash());
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
    System.out.println("■ 메뉴 - 통합게시판 - 게시글 목록 ■");
    Iterator iterator = integratedBoardList.iterator();
    while(iterator.hasNext()) {
      Board i = (Board)iterator.next();
      System.out.printf("번호 : [%d]  제목 : [%s]  작성자 : [%s]  추천 : [%d]  조회수 : [%d]  작성일 : [%s]\n",
          i.getNumber(), i.getTitle(), i.getWriter(), i.getLike(), i.getView(), i.getRegisteredDate());
    } // 회원 판별!!!!!!!!!!!!!!!!!!!!!!!!!!

    System.out.println();
  }

  public void detail() throws CloneNotSupportedException {
    if(integratedBoardList.size() == 0) {
      System.out.println("존재하는 게시글이 없습니다.");
      return;
    }
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

    // 댓글 반복문으로 출력

    if(MemberHandler.logStatus == 0) {
      // 구매 or 판매회원 해쉬값 수정 필요
      if(board.getId() == MemberHandler.buyerMemberNumber.getHash()) {
        Board i = board;
        while(true) {
          System.out.println("1. [수정]  2. [삭제]  3. [뒤로가기]");
          String choice = Prompt.inputString("선택 : ");
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
          String choice = Prompt.inputString("선택 : ");
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
    integratedBoardList.delete(i);
    System.out.println("게시글이 삭제되었습니다.");
  }

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
