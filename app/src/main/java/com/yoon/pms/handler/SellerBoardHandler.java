package com.yoon.pms.handler;

import com.yoon.pms.domain.SellerBoard;
import com.yoon.util.List;
import com.yoon.util.ListIterator;
import com.yoon.util.Prompt;

public class SellerBoardHandler {

	public static boolean boardAuthorization = false;
	private List sellerBoardList = new List();

	int boardIndex = 1;
	int commentCount = 0;
	public static int likeCount = 0;
	public static int changeCount = 0;

	public void service() {
		while(true) {
			System.out.println("■ 메뉴 - 판매자 전용 게시판 ■");

			if(boardAuthorization == true && MemberHandler.sellerMemberNumber.isDivision() == true) {
				System.out.println("[판매회원 전용]");
				System.out.println("1. 주문 목록");
				System.out.println("2. 판매자 게시판");
				System.out.println("3. 신고 게시판");
				System.out.println("4. 고객센터");
				System.out.println("5. 설정");
				System.out.println("6. 로그아웃");
			}

			else {
				System.out.println("판매회원 전용 게시판입니다.\n");
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
			case "뒤로가기":
				System.out.println("초기 화면으로 전환합니다.\n");
				return;
			}
		}

	}

	public void add() {
		SellerBoard s = new SellerBoard();
		if(boardAuthorization == true) {
			System.out.println("■ 메뉴 - 판매회원 게시판 - 게시글 작성 ■");
			s.setNumber(boardIndex++);
			s.setTitle(Prompt.inputString("제목 입력 : "));
			s.setContent(Prompt.inputString("내용 입력 : "));
			s.setWriter(MemberHandler.sellerMemberNumber.getBusinessName()); // 체크
			s.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
			s.setId(MemberHandler.sellerMemberNumber.getHash());
			this.sellerBoardList.add(s);

			System.out.println("글 작성 완료");
		}
		else {
			System.out.println("회원만 작성 가능합니다.");
		}
	}

	public void list() {
		if(sellerBoardList.size() == 0) {
			System.out.println("존재하는 게시글이 없습니다.");
			return;
		}
		System.out.println("■ 메뉴 - 판매회원 게시판 - 게시글 목록 ■");
		ListIterator iterator = new ListIterator(this.sellerBoardList);
		while(iterator.hasNext()) {
			SellerBoard s = (SellerBoard)iterator.next();
			System.out.printf("번호 : [%d]  제목 : [%s]  작성자 : [%s]  추천 : [%d]  조회수 : [%d]  작성일 : [%s]\n",
					s.getNumber(), s.getTitle(), s.getWriter(), s.getLike(), s.getView(), s.getRegisteredDate());
		}
	}

	public void comment() {

	}


	public void detail() {
		if(sellerBoardList.size() == 0) {
			System.out.println("존재하는 게시글이 없습니다.");
			return;
		}
		list();
		System.out.println("---------------------------------------");
		System.out.println("■ 메뉴 - 판매회원 게시판 - 게시글 보기 ■");
		int num = Prompt.inputInt("게시글 번호 입력 : ");

		SellerBoard board = findByNum(num);

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


		if(board.getId() == MemberHandler.sellerMemberNumber.getHash()) {
			SellerBoard s = board;
			while(true) {
				System.out.println("1. [수정]  2. [삭제]");
				String choice = Prompt.inputString("선택 : ");
				if(choice.equals("1")) {
					update(s);
					break;
				}
				else if(choice.equals("2")) {
					delete(s);
					break;
				}
				else {
					System.out.println("잘못 누르셨습니다.\n");
					break;
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

	public void update(SellerBoard s) {
		System.out.println("■ 메뉴 - 판매회원 게시판 - 게시글 수정 ■");

		s.setTitle(Prompt.inputString("수정할 제목 : "));
		s.setContent(Prompt.inputString("수정할 내용 : "));

		System.out.println("수정 완료");

	}

	public void delete(SellerBoard s) {
		System.out.println("■ 메뉴 - 판매회원 게시판 - 게시글 삭제 ■");
		sellerBoardList.delete(s);
		System.out.println("게시글이 삭제되었습니다.");
	}

	private SellerBoard findByNum(int boardNum) {
		Object[] list = sellerBoardList.toArray();
		for (Object obj : list) {
			SellerBoard s = (SellerBoard) obj;
			if (s.getNumber() == boardNum) {
				return s;
			}
		}
		return null;
	}

	public void changeWriter() {
		Object[] list = sellerBoardList.toArray();
		for(Object obj : list) {
			SellerBoard s = (SellerBoard)obj;
			if(s.getId() == MemberHandler.sellerMemberNumber.getHash()) {
				s.setWriter(MemberHandler.sellerMemberNumber.getBusinessName());
			}
		}
		changeCount = 0;
	}

}
