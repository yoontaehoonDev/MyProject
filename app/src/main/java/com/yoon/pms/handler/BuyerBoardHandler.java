package com.yoon.pms.handler;

import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.util.Iterator;
import com.yoon.util.List;
import com.yoon.util.Prompt;

public class BuyerBoardHandler {

  public static boolean boardAuthorization = false;
  protected List buyerBoardList = new List();

  int boardIndex = 1;
  int commentCount = 0;
  public static int likeCount = 0;
  public static int changeCount = 0;

  public void service() throws CloneNotSupportedException {
    if(changeCount == 1) {
      changeWriter();
    }

    while(true) {
      System.out.println("■ 메뉴 - 구매회원 전용 게시판 ■");
      BuyerMember m = MemberHandler.buyerMemberNumber;

      if(boardAuthorization == true && MemberHandler.buyerMemberNumber.isDivision() == true) {
        System.out.println("[구매회원 전용]");

        System.out.println("1. 주문하기");
        System.out.println("2. 주문현황");
        System.out.println("3. 게시글 작성");
        System.out.println("4. 게시글 목록");
        System.out.println("5. 게시글 보기");
        System.out.println("6. 내가 쓴 글");
        System.out.println("7. 로그아웃");
      }

      else {
        System.out.println("구매회원만 이용 가능합니다.\n");
        return;
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
        case "내글":
          this.myList(m);
          break;
        case "뒤로가기":
          System.out.println("초기 화면으로 전환합니다.\n");
          return;
      }
    }

  }

  public void add() {
    Board b = new Board();
    if(boardAuthorization == true && MemberHandler.buyerMemberNumber.isDivision() == true) {
      System.out.println("■ 메뉴 - 구매회원 게시판 - 게시글 작성 ■");

      b.setNumber(boardIndex++);
      b.setTitle(Prompt.inputString("제목 입력 : "));
      b.setContent(Prompt.inputString("내용 입력 : "));
      b.setWriter(MemberHandler.buyerMemberNumber.getNickname());
      b.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
      b.setId(MemberHandler.buyerMemberNumber.getHash());
      this.buyerBoardList.add(b);

      System.out.println("글 작성 완료\n");

    }
    else {
      System.out.println("회원만 작성 가능합니다.\n");
    }
  }

  public void list() throws CloneNotSupportedException {
    if(buyerBoardList.size() == 0) {
      System.out.println("존재하는 게시글이 없습니다.\n");
      return;
    }

    System.out.println("■ 메뉴 - 구매회원 게시판 - 게시글 목록 ■");

    Iterator iterator = buyerBoardList.iterator();
    while(iterator.hasNext()) {
      Board b = (Board)iterator.next();
      System.out.printf("번호 : [%d]  제목 : [%s]  작성자 : [%s]  추천 : [%d]  조회수 : [%d]  작성일 : [%s]\n",
          b.getNumber(), b.getTitle(), b.getWriter(), b.getLike(), b.getView(), b.getRegisteredDate());
    }
    System.out.println();
  }

  public void comment() {


  }

  public void detail() throws CloneNotSupportedException {
    if(buyerBoardList.size() == 0) {
      System.out.println("존재하는 게시글이 없습니다.\n");
      return;
    }
    list();
    System.out.println("---------------------------------------");
    System.out.println("■ 메뉴 - 구매회원 게시판 - 게시글 보기 ■");
    int num = Prompt.inputInt("게시글 번호 입력 : ");

    Board board = findByNum(num);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.\n");
      return;
    }

    board.setView(board.getView() + 1);

    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성자: %s\n", board.getWriter());
    System.out.printf("등록일: %s\n", board.getRegisteredDate());
    System.out.printf("추천수 : %d\n", board.getLike());
    System.out.printf("조회수: %d\n", board.getView());

    //		for(int i = 0; i < commentCount; i++) {
    //			System.out.printf("%d. 닉네임 : %s  댓글 : %s\n", i+1, board.getCommentWriter(), board.getComment());
    //		}

    if(board.getId() == MemberHandler.buyerMemberNumber.getHash()) {
      Board b = board;
      while(true) {
        System.out.println("1. [수정]  2. [삭제]  3. [뒤로가기]");
        String choice = Prompt.inputString("선택 : ");
        if(choice.equals("1")) {
          update(b);
          break;
        }
        else if(choice.equals("2")) {
          delete(b);
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

    /* 추천 기능 보류
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
     */
    System.out.println();
  }

  public void update(Board b) {
    System.out.println("■ 메뉴 - 구매회원 게시판 - 게시글 수정 ■");

    b.setTitle(Prompt.inputString("수정할 제목 : "));
    b.setContent(Prompt.inputString("수정할 내용 : "));

    System.out.println("수정 완료\n");

  }

  public void delete(Board b) {

    buyerBoardList.delete(b);
    System.out.println("게시글이 삭제되었습니다.\n");
  }

  public void myList(BuyerMember m) throws CloneNotSupportedException  {


    Iterator iterator = buyerBoardList.iterator();

    System.out.println();
    System.out.printf("%s 님의 게시글 목록\n", m.getNickname());

    while(iterator.hasNext()) {
      Board b = (Board)iterator.next();
      if(b.getId() == m.getHash()) {
        System.out.printf("제목 : [%s] 내용 : [%s]\n", b.getTitle(), b.getContent());
      }
    }
    System.out.println();

    //    Object[] list = buyerBoardList.toArray();
    //    System.out.println();
    //    System.out.printf("%s 님의 게시글 목록\n", m.getNickname());
    //    for(Object obj : list) {
    //      Board b = (Board)obj;
    //      if(b.getId() == m.getHash()) {
    //        System.out.printf("제목 : [%s] 내용 : [%s]\n", b.getTitle(), b.getContent());
    //      }
    //    }
    //    System.out.println();
  }

  private Board findByNum(int boardNum) {
    Object[] list = buyerBoardList.toArray();
    for (Object obj : list) {
      Board b = (Board) obj;
      if (b.getNumber() == boardNum) {
        return b;
      }
    }
    return null;
  }

  private void changeWriter() {
    Object[] list = buyerBoardList.toArray();
    for(Object obj : list) {
      Board b = (Board)obj;
      if(b.getId() == MemberHandler.buyerMemberNumber.getHash()) {
        b.setWriter(MemberHandler.buyerMemberNumber.getNickname());
      }
    }
    changeCount = 0;
  }

}
