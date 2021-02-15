package com.yoon.pms;

import com.yoon.pms.handler.BuyerBoardHandler;
import com.yoon.pms.handler.IntegratedBoardHandler;
import com.yoon.pms.handler.MemberHandler;
import com.yoon.pms.handler.SellerBoardHandler;
import com.yoon.util.Iterator;
import com.yoon.util.Prompt;
import com.yoon.util.Queue;
import com.yoon.util.Stack;

public class App {

  static Stack commandStack = new Stack();
  static Queue commandQueue = new Queue();

  public static void main(String[] args) throws CloneNotSupportedException {
    System.out.println("[2030 Project]");

    MemberHandler memberHandler = new MemberHandler();
    BuyerBoardHandler buyerBoardHandler = new BuyerBoardHandler();
    SellerBoardHandler sellerBoardHandler = new SellerBoardHandler();
    IntegratedBoardHandler integratedBoardHandler = new IntegratedBoardHandler();

    loop:
      while(true) {
        System.out.println("■ 메뉴 ■");
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("3. 구매자 게시판");
        System.out.println("4. 판매자 게시판");
        System.out.println("5. 고객센터\n");
        String menu = Prompt.inputString("메뉴 입력 : ");
        System.out.println();

        commandStack.push(menu);
        commandQueue.offer(menu);

        switch(menu) {
          case "회원":
            memberHandler.service();
            break;
          case "구매자게시판":
            buyerBoardHandler.service();
            break;
          case "판매자게시판":
            sellerBoardHandler.service();
            break;
          case "통합게시판":
            integratedBoardHandler.service();
            break;
          case "shistory":
            printCommandHistory(commandStack.iterator());
            break;
          case "qhistory":
            printCommandHistory(commandQueue.iterator());
            break;
          case "FAQ":
            break;
          case "고객센터":
            break;
          case "종료":
            System.out.println("프로그램 종료");
            break loop;
          default:
            System.out.println("실행할 수 없는 명령어입니다.");
        }

      }
    Prompt.close();
  }

  static void printCommandHistory(Iterator iterator) {

    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      if ((++count % 5) == 0) {
        String input = Prompt.inputString(": ");
        if (input.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }
}
