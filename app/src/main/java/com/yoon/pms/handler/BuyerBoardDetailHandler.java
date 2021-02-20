package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Comment;
import com.yoon.util.Prompt;

public class BuyerBoardDetailHandler extends AbstractBuyerBoardHandler {

	public BuyerBoardDetailHandler(List<Board> buyerBoardList, List<Comment> commentList) {
		super(buyerBoardList, commentList);
	}


	@Override
	public void service() {
		if(buyerBoardList.size() == 0) {
			System.out.println("존재하는 게시글이 없습니다.\n");
			return;
		}
		String choice;
		BuyerBoardListHandler list = new BuyerBoardListHandler(buyerBoardList, commentList);
		list.service();
		System.out.println("---------------------------------------");
		System.out.println("■ 메뉴 - 구매회원 게시판 - 게시글 보기 ■");
		int num = Prompt.inputInt("게시글 번호 입력 : ");

		BuyerMember m = AbstractMemberHandler.buyerMemberNumber;
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
			System.out.println("1. 댓글 작성  2. 대댓글 작성  3. 나가기");
			choice = Prompt.inputString("선택 : ");
			if(choice.equals("1")) {
				commentAdd(board);
				break;
			}
			else if(choice.equals("2")) {
				commentList(board);
				int index = Prompt.inputInt("댓글 번호 선택 : ");
				if(index != 0) {
					nestedCommentAdd(board, index);
				}
				else {
					System.out.println("댓글이 존재하지 않습니다.\n");
				}
				break;
			}
			else if(choice.equals("3")) {
				break;
			}
			else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}

		if(board.getId() == m.getHash()) {
			Board b = board;
			while(true) {
				System.out.println("1. [수정]  2. [삭제]  3. [뒤로가기]");
				choice = Prompt.inputString("선택 : ");
				if(choice.equals("1")) {
					update(b);
					break;
				}
				else if(choice.equals("2")) {
					delete(b);
					break;
				}
				else if(choice.equals("3")) {
					System.out.println("■ 메뉴 - 구매회원 전용 게시판 ■ 으로 이동합니다.");
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


}
