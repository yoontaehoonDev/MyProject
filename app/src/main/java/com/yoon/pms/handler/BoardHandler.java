package com.yoon.pms.handler;

import com.yoon.pms.domain.Board;
import com.yoon.util.List;
import com.yoon.util.Prompt;

public class BoardHandler {

  public static boolean boardAuthorization = false;
  private List boardList = new List();

  int boardIndex = 1;
  int boardCount = 0;
  public static int likeCount = 0;

  public void service() {
    while(true) {
      int i = 1;
      System.out.println("■ 메뉴 / 게시판 ■\n");


      if(boardAuthorization == true) {
        System.out.println("[회원 전용]");
        System.out.printf("%d. 게시글 보기\n", i++);
        System.out.printf("%d. 글 작성\n", i++);

      }
      else {
        System.out.println("[비회원 전용]");
        System.out.printf("%d. 게시글 보기\n", i++);

      }

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
    if(boardAuthorization == true) {
      System.out.println("■ 메뉴 / 게시판 / 게시글 작성 \n■");
      Board b = new Board();

      b.setNumber(boardIndex++);
      b.setTitle(Prompt.inputString("제목 입력 : "));
      b.setContent(Prompt.inputString("내용 입력 : "));
      b.setWriter(MemberHandler.memberNumber.getNickname());
      b.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));

      boardList.add(b);
      System.out.println("글 작성 완료");
      this.boardCount++;
    }
    else {
      System.out.println("글쓰기 권한이 없습니다.");
    }
  }

  public void list() {
    if(boardCount == 0) {
      System.out.println("존재하는 게시글이 없습니다.");
      return;
    }
    System.out.println("■ 메뉴 / 게시판 / 게시글 목록 ■");
    Object[] list = boardList.toArray();
    for(Object obj : list) {
      Board b = (Board)obj;
      System.out.printf("번호 : [%d]  제목 : [%s]  작성자 : [%s]  추천 : [%d]  조회수 : [%d]\n",
          b.getNumber(), b.getTitle(), b.getWriter(), b.getLike(), b.getView());
    }
  }
  public void detail() {
    if(boardCount == 0) {
      System.out.println("존재하는 게시글이 없습니다.");
      return;
    }
    list();
    System.out.println("---------------------------------------");
    System.out.println("■ 메뉴 / 게시판 / 게시글 보기 ■\n");
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

    String message = Prompt.inputString("이 게시글을 추천하시겠습니까? [Y/N] : ");
    if(likeCount == 0) {
      if(message.equalsIgnoreCase("y")) {
        board.setLike(board.getLike() + 1);
        System.out.println("추천했습니다.");
        likeCount = 1;
      }
      else {
        System.out.println("메뉴로 돌아갑니다.");
      }
    }
    else {
      System.out.println("이미 추천하셨습니다.");
    }

  }

  public void update() {
    System.out.println("■ 메뉴 / 게시판 / 게시글 수정 ■\n");

  }

  public void delete() {

  }

  private Board findByNum(int boardNum) {
    Object[] list = boardList.toArray();
    for (Object obj : list) {
      Board b = (Board) obj;
      if (b.getNumber() == boardNum) {
        return b;
      }
    }
    return null;
  }

}
