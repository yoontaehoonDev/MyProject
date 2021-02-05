package com.yoon.pms.handler;

import com.yoon.pms.domain.Board;
import com.yoon.util.List;
import com.yoon.util.Prompt;

public class BoardHandler {

	public static boolean boardAuthorization = false;
	private List boardList = new List();
	private List commentList = new List();

	int boardIndex = 1;
	int boardCount = 0;
	int commentCount = 0;
	Board currentIndex;
	public static int likeCount = 0;

	public void service() {
		while(true) {
			int i = 1;
			System.out.println("■ 메뉴 / 게시판 ■\n");


			if(boardAuthorization == true) {
				System.out.println("[회원 전용]");
				System.out.printf("%d. 글 목록\n", i++);
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
			System.out.println("■ 메뉴 / 게시판 / 게시글 작성 ■");
			Board b = new Board();
			b.setNumber(boardIndex++);
			b.setTitle(Prompt.inputString("제목 입력 : "));
			b.setContent(Prompt.inputString("내용 입력 : "));
			b.setWriter(MemberHandler.memberNumber.getNickname());
			b.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
			this.boardList.add(b);
			this.boardCount++;
			System.out.println("글 작성 완료");
		}
		else {
			System.out.println("글쓰기 권한이 없습니다.");
		}
	}

	public void list() {
		if(this.boardCount == 0) {
			System.out.println("존재하는 게시글이 없습니다.");
			return;
		}
		System.out.println("■ 메뉴 / 게시판 / 게시글 목록 ■");
		Object[] list = boardList.toArray();
		for(Object obj : list) {
			Board b = (Board)obj;
			System.out.printf("번호 : [%d]  제목 : [%s]  작성자 : [%s]  추천 : [%d]  조회수 : [%d]  작성일 : [%s]\n",
					b.getNumber(), b.getTitle(), b.getWriter(), b.getLike(), b.getView(), b.getRegisteredDate());
		}
	}
	//	public void search() {
	//		list();
	//		System.out.println("■ 메뉴 / 게시판 / 검색 ■\n");
	//		int num = Prompt.inputInt(")
	//	}

	public void comment() {

	}


	public void detail() {
		if(this.boardCount == 0) {
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

		for(int i = 0; i < commentCount; i++) {
			System.out.printf("%d. 닉네임 : %s  댓글 : %s\n", i+1, board.getCommentWriter(), board.getComment());
		}


		if(board.getWriter().equals(MemberHandler.memberNumber.getNickname())) {
			Board b = board;
			while(true) {
				System.out.println("1. [수정]  2. [삭제]");
				String choice = Prompt.inputString("선택 : ");
				if(choice.equals("1")) {
					update(b);
					break;
				}
				else if(choice.equals("2")) {
					delete(b);
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

	}

	public void update(Board b) {
		System.out.println("■ 메뉴 / 게시판 / 게시글 수정 ■");

		b.setTitle(Prompt.inputString("수정할 제목 : "));
		b.setContent(Prompt.inputString("수정할 내용 : "));

		System.out.println("수정 완료");

	}

	public void delete(Board b) {
		System.out.println("■ 메뉴 / 게시판 / 게시글 삭제 ■");
		boardList.delete(b);
		boardCount--;
		System.out.println("게시글이 삭제되었습니다.");
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
