package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.Comment;
import com.yoon.util.Prompt;

public class IntegratedBoardListHandler extends AbstractIntegratedBoardHandler {

  public IntegratedBoardListHandler(List<Board> integratedBoardList, List<Comment> commentList) {
    super(integratedBoardList, commentList);
  }

  @Override
  public void service() {
    if(integratedBoardList.size() == 0) {
      System.out.println("존재하는 게시글이 없습니다.");
      return;
    }
    if(integratedBoardWriterChangeCount == 1) {
      changeWriter();
    }

    System.out.println("■ 메뉴 - 통합게시판 - 게시글 목록 ■\n");

    Iterator<Board> iterator = integratedBoardList.iterator();

    while(true) {
      System.out.println("1. 모든 게시글  2. 구매회원 게시글  3. 판매회원 게시글");

      String choice = Prompt.inputString("선택 : ");

      if(choice.equals("1")) {
        System.out.println("■ 메뉴 - 통합게시판 - 게시글 목록 - 통합 게시글 ■\n");
        while(iterator.hasNext()) {

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
        buyerIntegratedBoardCount = 0;
        System.out.println("■ 메뉴 - 통합게시판 - 게시글 목록 - 구매회원 게시글 ■\n");
        while(iterator.hasNext()) {
          Board i = iterator.next();
          if(i.getOwner() == buyerNum) {
            System.out.printf("번호 : [%d]  제목 : [%s]  작성자 : [%s]  추천 : [%d]  조회수 : [%d]  작성일 : [%s]\n",
                i.getNumber(), i.getTitle(), i.getWriter(), i.getLike(), i.getView(), i.getRegisteredDate());
            buyerIntegratedBoardCount = 1;
          }
        }
        if(buyerIntegratedBoardCount == 0) {
          System.out.println("구매회원 게시글이 존재하지 않습니다.\n");
          return;
        }
        break;
      }
      else if(choice.equals("3")) {
        sellerIntegratedBoardCount = 0;
        System.out.println("■ 메뉴 - 통합게시판 - 게시글 목록 - 판매회원 게시글 ■\n");
        while(iterator.hasNext()) {
          Board i = iterator.next();
          if(i.getOwner() == sellerNum) {
            System.out.printf("번호 : [%d]  제목 : [%s]  작성자 : [%s]  추천 : [%d]  조회수 : [%d]  작성일 : [%s]\n",
                i.getNumber(), i.getTitle(), i.getWriter(), i.getLike(), i.getView(), i.getRegisteredDate());
            sellerIntegratedBoardCount = 1;
          }  
        }
        if(sellerIntegratedBoardCount == 0) {
          System.out.println("판매회원 게시글이 존재하지 않습니다.\n");
          return;
        }
        break;
      }
      else {
        System.out.println("잘못 입력하셨습니다.");
      }
    }
    System.out.println();
  }


}
