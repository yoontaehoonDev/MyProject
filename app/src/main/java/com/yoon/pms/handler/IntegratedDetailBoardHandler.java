package com.yoon.pms.handler;

import java.util.List;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Comment;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class IntegratedDetailBoardHandler extends AbstractIntegratedBoardHandler {

  public IntegratedDetailBoardHandler(List<Board> integratedBoardList, List<Comment> commentList) {
    super(integratedBoardList, commentList);
  }

  @Override
  public void service() {
    if(integratedBoardList.size() == 0) {
      System.out.println("존재하는 게시글이 없습니다.");
      return;
    }
    if(IntegratedBoardWriterChangeCount == 1) {
      changeWriter();
    }

    String choice;
    IntegratedBoardListHandler list = new IntegratedBoardListHandler(integratedBoardList, commentList);
    list.service();


    System.out.println("---------------------------------------");
    System.out.println("■ 메뉴 - 통합게시판 - 게시글 보기 ■");
    int num = Prompt.inputInt("게시글 번호 입력 : ");

    Board board = findByNum(num);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    BuyerMember b = AbstractMemberHandler.buyerMemberNumber;
    SellerMember s = AbstractMemberHandler.sellerMemberNumber;


    board.setView(board.getView() + 1);

    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성자: %s\n", board.getWriter());
    System.out.printf("등록일: %s\n", board.getRegisteredDate());
    System.out.printf("추천수 : %d\n", board.getLike());
    System.out.printf("조회수: %d\n", board.getView());

    commentList(board);

    while(true) {
      if(AbstractMemberHandler.logStatus == 0) {
        flag = 0;
      }
      else if(AbstractMemberHandler.logStatus == 1) {
        flag = 1;
      }
      System.out.println("1. 댓글 작성  2. 대댓글 작성  3. 나가기");

      choice = Prompt.inputString("선택 : ");

      if(choice.equals("1")) {
        commentAdd(board);
        break;
      }
      else if(choice.equals("2")) {
        commentList(board);
        int index = Prompt.inputInt("댓글 번호 선택 : ");
        nestedCommentAdd(board, index);
        break;
      }
      else if(choice.equals("3")) {
        break;
      }
      else {
        System.out.println("잘못 입력하셨습니다.");
      }
    }


    if(AbstractMemberHandler.logStatus == 0) {
      // 구매 or 판매회원 해쉬값 수정 필요
      if(board.getId() == b.getHash()) {
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
    else if (AbstractMemberHandler.logStatus == 1) {
      if(board.getId() == s.getHash()) {
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
          else {
            System.out.println("잘못 누르셨습니다.\n");
            break;
          }
        }
      }
    }
    System.out.println();
  }


}
