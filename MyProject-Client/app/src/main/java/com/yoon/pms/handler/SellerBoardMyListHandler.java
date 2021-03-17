package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.Comment;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class SellerBoardMyListHandler extends AbstractSellerBoardHandler {

  public SellerBoardMyListHandler(List<Board> sellerBoardList, List<Comment> sellerCommentList) {
    super(sellerBoardList, sellerCommentList);
  }


  @Override
  public void service()  {
    System.out.println("■ 메뉴 - 판매자 - 내 글 ■\n");
    SellerMember m = AbstractMemberHandler.sellerMemberNumber;
    Iterator<Board> iterator = sellerBoardList.iterator();

    System.out.printf("[%s 님의 게시글 목록]\n", m.getBusinessName());

    while(iterator.hasNext()) {
      Board b = iterator.next();
      if(b.getId() == m.getHash()) {
        System.out.printf("게시글 번호 : [%d]   제목 : [%s]   내용 : [%s]\n", b.getNumber(), b.getTitle(), b.getContent());
      }
    }
    System.out.println("---------------------------------------------------");
    int num = Prompt.inputInt("게시글 번호 입력 : ");
    Board board = findByNum(num);

    if(board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.\n");
      return;
    }

    if(board.getId() == m.getHash()) {
      Board s = board;
      while(true) {
        System.out.println("1. [수정]  2. [삭제]  3. [뒤로가기]");
        String choice = Prompt.inputString("선택 : ");
        if(choice.equals("1")) {
          update(s);
          break;
        }
        else if(choice.equals("2")) {
          delete(s);
          break;
        }
        else if(choice.equals("3")) {
          System.out.println("■ 메뉴 - 판매자 게시판 ■ 으로 이동합니다.");
          break;
        }
        else {
          System.out.println("잘못 누르셨습니다.\n");
        }
      }
    }

  }

}
