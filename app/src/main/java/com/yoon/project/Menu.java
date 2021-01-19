package com.yoon.project;

public class Menu {

  static int choice;

  static void choice() {
    System.out.println("--------------------");
    System.out.println("- 1. 미사일 개발   -");
    System.out.println("- 2. 미사일 목록   -");
    System.out.println("- 3. 미사일 발사   -");
    System.out.println("- 4. 국가 현황     -");
    System.out.println("- 5. 프로그램 종료 -");
    System.out.println("--------------------");
    System.out.println();
    choice = Prompt.inputInt("번호 선택 : ");
    System.out.println();
  }
}
