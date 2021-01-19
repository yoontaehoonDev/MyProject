package com.yoon.project;

public class MissileLaunch {

  static String[] nations = {"미국", "러시아", "이란", "인도", "호주", "캐나다", "뉴질랜드", "파키스탄", "몽골", "필리핀",
      "대만", "태국", "베트남", "인도네시아", "중국", "일본", "북한"
  };
  static int[] population = { 332000000, 145000000, 85000000, 1393000000, 25000000, 38000000,
      4860000, 225000000, 3320000, 111000000, 230000000, 70000000, 98000000,
      276000000, 1444000000, 126000000, 25000000
  };
  static int[] deadPeople = new int[17];

  static int launch = 0;
  static int flag = 0;
  static int nuclearDamage = 5384923;
  static int biochemistryDamage = 2837194;
  static int bombDamage = 134728;
  static String nation;

  static void launch() {
    int i;
    int j;
    if (MissileDevelopment.icbmCount == 0 && MissileDevelopment.cruiseCount == 0 && MissileDevelopment.patriotCount == 0) {
      System.out.println("미사일이 부족합니다.\n");
    }
    else {
      System.out.println("[미사일 발사]\n");
      MissileList.list();
      launch = Prompt.inputInt("미사일 번호 선택 : ");

      launch--;

      MissileDevelopment.index[launch] = 0;

      System.out.println();
      System.out.printf("%s 발사 준비 완료\n", MissileDevelopment.missile[launch]);
      System.out.println();

      if (MissileDevelopment.missile[launch].equals("탄도미사일")) {
        nation = Prompt.inputString("타격권 : 미국   러시아   이란   호주   캐나다\n         인도   뉴질랜드   파키스탄\n\n공격할 국가 선택 : ");
        while(true) {
          if (nation.equals("미국") || nation.equals("러시아") || nation.equals("이란") || 
              nation.equals("호주") || nation.equals("캐나다") || nation.equals("인도")
              || nation.equals("뉴질랜드") || nation.equals("파키스탄")) {
            break;
          }
          else {
            System.out.println();
            nation = Prompt.inputString("잘못 입력하셨습니다.\n"
                + "타격권 : 미국   러시아   이란   호주   캐나다\n         인도   뉴질랜드   파키스탄\n\n공격할 국가 선택 : ");
            if (nation.equals("미국") || nation.equals("러시아") || nation.equals("이란") || 
                nation.equals("호주") || nation.equals("캐나다") || nation.equals("인도")
                || nation.equals("뉴질랜드") || nation.equals("파키스탄")) {
              break;
            }
          }
        }

        flag = 0;

        if (MissileDevelopment.nuclearBomb[launch].equals("장착")) {
          for(i = 1, j = 1; i <= nuclearDamage; i += 1321, j+= 15) {
            System.out.printf("인명피해 : %d명   폭발반경 : %dM  핵탄두 장착으로 인한 피해량 x10\n", i, j);
            flag = 1;
          }
        }
        else if (MissileDevelopment.biochemistryBomb[launch].equals("장착")){
          for(i = 1, j = 1; i <= biochemistryDamage; i += 147, j += 2) {
            System.out.printf("인명피해 : %d명   폭발반경 : %dM  생화학탄 장착으로 인한 피해랑 x5\n", i, j);
            flag = 2;
          }
        }
        else {
          for(i = 1, j = 1; i <= bombDamage; i += 13, j++) {
            System.out.printf("인명피해 : %d명   폭발반경 : %dM\n", i, j);
          }
        }

        if (nation.equals("미국")) {
          if (flag == 1) {
            population[0] -= nuclearDamage;
            deadPeople[0] += nuclearDamage;
          }
          else if (flag == 2) {
            population[0] -= biochemistryDamage;
            deadPeople[0] += biochemistryDamage;
          }
          else {
            population[0] -= bombDamage;
            deadPeople[0] += bombDamage;
          }
        }
        else if (nation.equals("러시아")) {
          if (flag == 1) {
            population[1] -= nuclearDamage;
            deadPeople[1] += nuclearDamage;
          }
          else if (flag == 2) {
            population[1] -= biochemistryDamage;
            deadPeople[1] += biochemistryDamage;
          }
          else {
            population[1] -= bombDamage;
            deadPeople[1] += bombDamage;
          }
        }
        else if (nation.equals("이란")) {
          if (flag == 1) {
            population[2] -= nuclearDamage;
            deadPeople[2] += nuclearDamage;
          }
          else if (flag == 2) {
            population[2] -= biochemistryDamage;
            deadPeople[2] += biochemistryDamage;
          }
          else {
            population[2] -= bombDamage;
            deadPeople[2] += bombDamage;
          }
        }
        else if (nation.equals("인도")) {
          if (flag == 1) {
            population[3] -= nuclearDamage;
            deadPeople[3] += nuclearDamage;
          }
          else if (flag == 2) {
            population[3] -= biochemistryDamage;
            deadPeople[3] += biochemistryDamage;
          }
          else {
            population[3] -= bombDamage;
            deadPeople[3] += bombDamage;
          }
        }
        else if (nation.equals("호주")) {
          if (flag == 1) {
            population[4] -= nuclearDamage;
            deadPeople[4] += nuclearDamage;
          }
          else if (flag == 2) {
            population[4] -= biochemistryDamage;
            deadPeople[4] += biochemistryDamage;
          }
          else {
            population[4] -= bombDamage;
            deadPeople[4] += bombDamage;
          }
        }
        else if (nation.equals("캐나다")) {
          if (flag == 1) {
            population[5] -= nuclearDamage;
            deadPeople[5] += nuclearDamage;
          }
          else if (flag == 2) {
            population[5] -= biochemistryDamage;
            deadPeople[5] += biochemistryDamage;
          }
          else {
            population[5] -= bombDamage;
            deadPeople[5] += bombDamage;
          }
        }
        else if (nation.equals("뉴질랜드")) {
          if (flag == 1) {
            population[6] -= nuclearDamage;
            deadPeople[6] += nuclearDamage;
          }
          else if (flag == 2) {
            population[6] -= biochemistryDamage;
            deadPeople[6] += biochemistryDamage;
          }
          else {
            population[6] -= bombDamage;
            deadPeople[6] += bombDamage;
          }
        }
        else if (nation.equals("파키스탄")) {
          if (flag == 1) {
            population[7] -= nuclearDamage;
            deadPeople[7] += nuclearDamage;
          }
          else if (flag == 2) {
            population[7] -= biochemistryDamage;
            deadPeople[7] += biochemistryDamage;
          }
          else {
            population[7] -= bombDamage;
            deadPeople[7] += bombDamage;
          }
        }

        MissileDevelopment.icbmCount--;
      }
      else if (MissileDevelopment.missile[launch].equals("순항미사일")) {
        nation = Prompt.inputString("타격권 : 몽골   필리핀   대만   태국   베트남   인도네시아\n\n공격할 국가 선택 : ");
        while(true) {
          if (nation.equals("몽골") || nation.equals("필리핀") || nation.equals("대만") || 
              nation.equals("인도네시아") || nation.equals("베트남") || nation.equals("태국")) {
            break;
          }
          else {
            System.out.println();
            nation = Prompt.inputString("잘못 입력하셨습니다.\n"
                + "타격권 : 몽골   필리핀   대만   태국   베트남   인도네시아\n\n공격할 국가 선택 : ");
            if (nation.equals("몽골") || nation.equals("필리핀") || nation.equals("대만") || 
                nation.equals("인도네시아") || nation.equals("베트남") || nation.equals("태국")) {
              break;
            }
          }
        }

        flag = 0;

        if (MissileDevelopment.nuclearBomb[launch].equals("장착")) {
          for(i = 1, j = 1; i <= nuclearDamage; i += 1321, j+= 15) {
            System.out.printf("인명피해 : %d명   폭발반경 : %dM  핵탄두 장착으로 인한 피해량 x10\n", i, j);
            flag = 1;
          }
        }
        else if (MissileDevelopment.biochemistryBomb[launch].equals("장착")){
          for(i = 1, j = 1; i <= biochemistryDamage; i += 147, j += 2) {
            System.out.printf("인명피해 : %d명   폭발반경 : %dM  생화학탄 장착으로 인한 피해랑 x5\n", i, j);
            flag = 2;
          }
        }
        else {
          for(i = 1, j = 1; i <= bombDamage; i += 13, j++) {
            System.out.printf("인명피해 : %d명   폭발반경 : %dM\n", i, j);
          }
        }

        if(flag == 1) {
          System.out.printf("");
        }


        if (nation.equals("몽골")) {
          if (flag == 1) {
            population[8] -= nuclearDamage;
            deadPeople[8] += nuclearDamage;
          }
          else if (flag == 2) {
            population[8] -= biochemistryDamage;
            deadPeople[8] += biochemistryDamage;
          }
          else {
            population[8] -= bombDamage;
            deadPeople[8] += bombDamage;
          }
        }
        else if (nation.equals("필리핀")) {
          if (flag == 1) {
            population[9] -= nuclearDamage;
            deadPeople[9] += nuclearDamage;
          }
          else if (flag == 2) {
            population[9] -= biochemistryDamage;
            deadPeople[9] += biochemistryDamage;
          }
          else {
            population[9] -= bombDamage;
            deadPeople[9] += bombDamage;
          }
        }
        else if (nation.equals("대만")) {
          if (flag == 1) {
            population[10] -= nuclearDamage;
            deadPeople[10] += nuclearDamage;
          }
          else if (flag == 2) {
            population[10] -= biochemistryDamage;
            deadPeople[10] += biochemistryDamage;
          }
          else {
            population[10] -= bombDamage;
            deadPeople[10] += bombDamage;
          }
        }
        else if (nation.equals("태국")) {
          if (flag == 1) {
            population[11] -= nuclearDamage;
            deadPeople[11] += nuclearDamage;
          }
          else if (flag == 2) {
            population[11] -= biochemistryDamage;
            deadPeople[11] += nuclearDamage;
          }
          else {
            population[11] -= bombDamage;
            deadPeople[11] += bombDamage;
          }
        }
        else if (nation.equals("베트남")) {
          if (flag == 1) {
            population[12] -= nuclearDamage;
            deadPeople[12] += nuclearDamage;
          }
          else if (flag == 2) {
            population[12] -= biochemistryDamage;
            deadPeople[12] += biochemistryDamage;
          }
          else {
            population[12] -= bombDamage;
            deadPeople[12] += bombDamage;
          }
        }
        else if (nation.equals("인도네시아")) {
          if (flag == 1) {
            population[13] -= nuclearDamage;
            deadPeople[13] += nuclearDamage;
          }
          else if (flag == 2) {
            population[13] -= biochemistryDamage;
            deadPeople[13] += biochemistryDamage;
          }
          else {
            population[13] -= bombDamage;
            deadPeople[13] += bombDamage;
          }
        }

        MissileDevelopment.cruiseCount--;
      }

      else if (MissileDevelopment.missile[launch].equals("단거리미사일")) {

        nation = Prompt.inputString("타격권 : 북한   중국   일본\n\n공격할 국가 선택 : ");
        while(true) {
          if (nation.equals("북한") || nation.equals("중국") || nation.equals("일본")) {
            break;
          }
          else {
            System.out.println();
            nation = Prompt.inputString("잘못 입력하셨습니다.\n"
                + "타격권 : 북한   중국   일본\n\n공격할 국가 선택 : ");
            if (nation.equals("북한") || nation.equals("중국") || nation.equals("일본")) {
              break;
            }
          }
        }

        flag = 0;

        if (MissileDevelopment.nuclearBomb[launch].equals("장착")) {
          for(i = 1, j = 1; i <= nuclearDamage; i += 1321, j+= 15) {
            System.out.printf("인명피해 : %d명   폭발반경 : %dM  핵탄두 장착으로 인한 피해량 x10\n", i, j);
            flag = 1;
          }
        }
        else if (MissileDevelopment.biochemistryBomb[launch].equals("장착")){
          for(i = 1, j = 1; i <= biochemistryDamage; i += 147, j += 2) {
            System.out.printf("인명피해 : %d명   폭발반경 : %dM  생화학탄 장착으로 인한 피해랑 x5\n", i, j);
            flag = 2;
          }
        }
        else {
          for(i = 1, j = 1; i <= bombDamage; i += 13, j++) {
            System.out.printf("인명피해 : %d명   폭발반경 : %dM\n", i, j);
          }
        }

        if (nation.equals("중국")) {
          if (flag == 1) {
            population[14] -= nuclearDamage;
            deadPeople[14] += nuclearDamage;
          }
          else if (flag == 2) {
            population[14] -= biochemistryDamage;
            deadPeople[14] += biochemistryDamage;
          }
          else {
            population[14] -= bombDamage;
            deadPeople[14] += bombDamage;
          }
        }
        else if (nation.equals("일본")) {
          if (flag == 1) {
            population[15] -= nuclearDamage;
            deadPeople[15] += nuclearDamage;
          }
          else if (flag == 2) {
            population[15] -= biochemistryDamage;
            deadPeople[15] += biochemistryDamage;
          }
          else {
            population[15] -= bombDamage;
            deadPeople[15] += bombDamage;
          }
        }
        else if (nation.equals("북한")) {
          if (flag == 1) {
            population[16] -= nuclearDamage;
            deadPeople[16] += nuclearDamage;
          }
          else if (flag == 2) {
            population[16] -= biochemistryDamage;
            deadPeople[16] += biochemistryDamage;
          }
          else {
            population[16] -= bombDamage;
            deadPeople[16] += bombDamage;
          }
        }

        MissileDevelopment.patriotCount--;
      }

      MissileDevelopment.missile[launch] = null;
      MissileDevelopment.nuclearBomb[launch] = null;
      MissileDevelopment.biochemistryBomb[launch] = null;
      MissileDevelopment.stealthFuction[launch] = null;
      MissileDevelopment.effectiveRange[launch] = 0;

    }
  }
}
