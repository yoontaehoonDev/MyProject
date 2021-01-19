package com.yoon.project;

public class MissileDevelopment {

  static final int MAX = 50;

  static int[] index = new int[MAX];
  static String[] missile = new String[MAX];
  static int[] effectiveRange = new int[MAX];
  static String[] nuclearBomb = new String[MAX];
  static String[] biochemistryBomb = new String[MAX];
  static String[] stealthFuction = new String[MAX];
  static int devCount = 0;
  static int icbmCount = 0;
  static int cruiseCount = 0;
  static int patriotCount = 0;
  static int missileNumber;
  static String response;

  static void add() {
    int i;
    System.out.println("[미사일 개발]\n");
    for (i = devCount; i < MAX; i++) {

      System.out.println("[미사일 분류]");
      System.out.println("1. 탄도미사일    2. 순항미사일    3. 단거리미사일");

      missileNumber = Prompt.inputInt("개발할 미사일을 입력하세요[숫자] : ");


      index[i] = i+1;

      if (missileNumber == 1) {
        missile[i] = "탄도미사일";

        nuclearBomb[i] = Prompt.inputString("핵탄두 [장착/미장착] : ");
        if (nuclearBomb[i].equals("장착")) {
          System.out.println("핵탄두 장착 완료\n");
          biochemistryBomb[i] = "미장착";
        }
        else {
          System.out.println("핵탄두 미장착\n");
          biochemistryBomb[i] = Prompt.inputString("생화학탄 [장착/미장착] : ");
          if (biochemistryBomb[i].equals("장착")) {
            System.out.println("생화학탄 장착 완료\n");
          }
          else {
            biochemistryBomb[i] = "미장착";
            System.out.println("생화학탄 미장착\n");
          }
        }
        stealthFuction[i] = Prompt.inputString("스텔스 기능 [추가/미추가] : ");
        if(stealthFuction[i].equals("추가")) {
          System.out.println("스텔스 기능 추가 완료");
        }
        else {
          System.out.println("스텔스 기능 미추가");
        }

        effectiveRange[i] = 12500;
        icbmCount++;

      }

      else if (missileNumber == 2) {
        missile[i] = "순항미사일";

        nuclearBomb[i] = Prompt.inputString("핵탄두 [장착/미장착] : ");
        if (nuclearBomb[i].equals("장착")) {
          System.out.println("핵탄두 장착 완료\n");
          biochemistryBomb[i] = "미장착";
        }
        else {
          System.out.println("핵탄두 미장착\n");
          biochemistryBomb[i] = Prompt.inputString("생화학탄 [장착/미장착] : ");
          if (biochemistryBomb[i].equals("장착")) {
            System.out.println("생화학탄 장착 완료\n");
          }
          else {
            biochemistryBomb[i] = "미장착";
            System.out.println("생화학탄 미장착\n");
          }
        }
        stealthFuction[i] = Prompt.inputString("스텔스 기능 [추가/미추가] : ");
        if(stealthFuction[i].equals("추가")) {
          System.out.println("스텔스 기능 추가 완료");
        }
        else {
          System.out.println("스텔스 기능 미추가");
        }

        effectiveRange[i] = 5000;
        cruiseCount++;

      }

      else if(missileNumber == 3) {
        missile[i] = "단거리미사일";

        nuclearBomb[i] = Prompt.inputString("핵탄두 [장착/미장착] : ");
        if (nuclearBomb[i].equals("장착")) {
          System.out.println("핵탄두 장착 완료\n");
          biochemistryBomb[i] = "미장착";
        }
        else {
          System.out.println("핵탄두 미장착\n");
          biochemistryBomb[i] = Prompt.inputString("생화학탄 [장착/미장착] : ");
          if (biochemistryBomb[i].equals("장착")) {
            System.out.println("생화학탄 장착 완료\n");
          }
          else {
            biochemistryBomb[i] = "미장착";
            System.out.println("생화학탄 미장착\n");
          }
        }
        stealthFuction[i] = Prompt.inputString("스텔스 기능 [추가/미추가] : ");
        if(stealthFuction[i].equals("추가")) {
          System.out.println("스텔스 기능 추가 완료");
        }
        else {
          System.out.println("스텔스 기능 미추가");
        }

        effectiveRange[i] = 350;
        patriotCount++;

      }


      else {
        System.out.println("잘못 입력하셨습니다.");
        System.out.println("초기 화면으로 돌아갑니다.");
        break;
      }
      devCount++;
      System.out.println();
      response = Prompt.inputString("계속 개발하시겠습니까?[y/N] : ");
      if (!response.equalsIgnoreCase("y")) {
        break;
      }
      System.out.println();
    }
  }

}
