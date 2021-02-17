package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.LinkedList;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.Comment;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class SellerBoardHandler {

  public static boolean boardAuthorization = false;
  private LinkedList<Board> sellerBoardList = new LinkedList<>();
  private LinkedList<Comment> commentList = new LinkedList<>();

  int boardIndex = 1;
  int commentCount = 0;
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

    SellerMember m = MemberHandler.sellerMemberNumber;
    while(true) {
      System.out.println("■ 메뉴 - 판매자 전용 게시판 ■");

      if(boardAuthorization == true && MemberHandler.sellerMemberNumber.isDivision() == true) {
        System.out.println("[판매회원 전용]");

        System.out.println("1. 주문 목록");
        System.out.println("2. 게시글 작성");
        System.out.println("3. 게시글 목록");
        System.out.println("4. 게시글 보기");
        System.out.println("5. 내가 쓴 글");
        System.out.println("6. 로그아웃");
      }

      else {
        System.out.println("판매회원만 이용 가능합니다.\n");
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
    Board s = new Board();
    if(boardAuthorization == true) {
      System.out.println("■ 메뉴 - 판매회원 게시판 - 게시글 작성 ■");
      s.setNumber(boardIndex++);
      s.setTitle(Prompt.inputString("제목 입력 : "));
      s.setContent(Prompt.inputString("내용 입력 : "));
      s.setWriter(MemberHandler.sellerMemberNumber.getBusinessName()); // 체크
      s.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
      s.setId(MemberHandler.sellerMemberNumber.getHash());
      this.sellerBoardList.add(s);

      System.out.println("글 작성 완료\n");
    }
    else {
      System.out.println("회원만 작성 가능합니다.\n");
    }
  }

  public void list() throws CloneNotSupportedException {
    if(sellerBoardList.size() == 0) {
      System.out.println("존재하는 게시글이 없습니다.\n");
      return;
    }
    System.out.println("■ 메뉴 - 판매회원 게시판 - 게시글 목록 ■");
    Iterator<Board> iterator = sellerBoardList.iterator();
    while(iterator.hasNext()) {
      Board s = iterator.next();
      System.out.printf("번호 : [%d]  제목 : [%s]  작성자 : [%s]  추천 : [%d]  조회수 : [%d]  작성일 : [%s]\n",
          s.getNumber(), s.getTitle(), s.getWriter(), s.getLike(), s.getView(), s.getRegisteredDate());
    }
    System.out.println();
  }

  public void commentAdd(Board b) {
    SellerMember m = MemberHandler.sellerMemberNumber;
    Comment c = new Comment();
    c.setCommentId(b.getNumber());
    c.setCommentWriter(m.getBusinessName());
    c.setComment(Prompt.inputString("댓글 : "));

    b.setCommentCount(b.getCommentCount() + 1);
    c.setCommentNumber(b.getCommentCount());
    this.commentList.add(c);

  }


  public void detail() throws CloneNotSupportedException {
    if(sellerBoardList.size() == 0) {
      System.out.println("존재하는 게시글이 없습니다.\n");
      return;
    }
    list();

    String choice;
    System.out.println("---------------------------------------");
    System.out.println("■ 메뉴 - 판매회원 게시판 - 게시글 보기 ■");
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
    commentList(board);

    while(true) {
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

    if(board.getId() == MemberHandler.sellerMemberNumber.getHash()) {
      Board s = board;
      while(true) {
        System.out.println("1. [수정]  2. [삭제]  3. [뒤로가기]");
        choice = Prompt.inputString("선택 : ");
        if(choice.equals("1")) {
          update(s);
          break;
        }
        else if(choice.equals("2")) {
          delete(s);
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

  public void update(Board s) {
    System.out.println("■ 메뉴 - 판매회원 게시판 - 게시글 수정 ■");

    s.setTitle(Prompt.inputString("수정할 제목 : "));
    s.setContent(Prompt.inputString("수정할 내용 : "));

    System.out.println("수정 완료\n");

  }

  public void delete(Board s) {
    sellerBoardList.remove(s);
    System.out.println("게시글이 삭제되었습니다.\n");
  }

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


  public void myList(SellerMember m) throws CloneNotSupportedException {
    Iterator<Board> iterator = sellerBoardList.iterator();

    System.out.println();
    System.out.printf("%s 님의 게시글 목록\n", m.getBusinessName());

    while(iterator.hasNext()) {
      Board b = iterator.next();
      if(b.getId() == m.getHash()) {
        System.out.printf("게시글 번호 : [%d]   제목 : [%s]   내용 : [%s]\n", b.getNumber(), b.getTitle(), b.getContent());
      }
    }
    System.out.println();
  }

  private Board findByNum(int boardNum) {
    Board[] list = sellerBoardList.toArray(new Board[sellerBoardList.size()]);
    for (Board s : list) {
      if (s.getNumber() == boardNum) {
        return s;
      }
    }
    return null;
  }

  // 상호명 변경 보류
  public void changeWriter() {
    Board[] list = sellerBoardList.toArray(new Board[sellerBoardList.size()]);
    for(Board s : list) {
      if(s.getId() == MemberHandler.sellerMemberNumber.getHash()) {
        s.setWriter(MemberHandler.sellerMemberNumber.getBusinessName());
      }
    }
    changeCount = 0;
  }

}
