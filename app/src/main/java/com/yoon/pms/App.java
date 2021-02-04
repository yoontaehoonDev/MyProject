package com.yoon.pms;

import java.util.Scanner;
import com.yoon.pms.handler.BoardHandler;
import com.yoon.pms.handler.MemberHandler;
import com.yoon.pms.handler.ProductHandler;
import com.yoon.util.Prompt;

public class App {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("[2030 Project]");

    MemberHandler memberHandler = new MemberHandler();
    ProductHandler productHandler = new ProductHandler();
    BoardHandler boardHandler = new BoardHandler();

    while(true) {
      System.out.println("- 메뉴 -");
      System.out.println("1. 회원");
      System.out.println("2. 제품");
      System.out.println("3. 게시판\n");
      String menu = Prompt.inputString("메뉴 입력 : ");

      switch(menu) {
        case "member":
          memberHandler.service();
          break;
        case "board":
          boardHandler.service();
          break;
        case "FAQ":
          break;
        case "고객센터":
          break;
        case "종료":
          System.out.println("프로그램 종료");
          return;

      }
    }
  }
}
