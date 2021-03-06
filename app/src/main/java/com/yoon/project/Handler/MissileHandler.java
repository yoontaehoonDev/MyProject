package com.yoon.project.Handler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import com.yoon.project.domain.Missile;
import com.yoon.util.Prompt;

public class MissileHandler {

  static final int MAX = 50;

  static String[] nations = {"미국", "러시아", "이란", "인도", "호주", "캐나다", "뉴질랜드", "파키스탄", "몽골", "필리핀",
      "대만", "태국", "베트남", "인도네시아", "중국", "일본", "북한"
  };
  static int[] population = { 332000000, 145000000, 85000000, 1393000000, 25000000, 38000000,
      4860000, 225000000, 3320000, 111000000, 230000000, 70000000, 98000000,
      276000000, 1444000000, 126000000, 25000000
  };
  static int[] deadPeople = new int[17];

  static int launch = 0;
  static int nuclearDamage = 5384923;
  static int biochemistryDamage = 2837194;
  static int bombDamage = 134728;
  static String nation;
  static int i;
  static int missileNumber;
  static String response;
  static int count;

  Missile[] missiles = new Missile[MAX];
  int devCount = 0;
  int icbmCount = 0;
  int cruiseCount = 0;
  int patriotCount = 0;
  int flag = 0;

  public void add() {
    System.out.println("[미사일 개발]\n");

    for (i = this.devCount; i < MAX; i++) {
      Missile m = new Missile();
      System.out.println("[미사일 분류]");
      System.out.println("1. 탄도미사일    2. 순항미사일    3. 단거리미사일");

      missileNumber = Prompt.inputInt("개발할 미사일을 입력하세요[숫자] : ");
      m.index = i+1;

      if (missileNumber == 1) {
        m.missile = "탄도미사일";

        armed(m);
        m.effectiveRange = 12500;
        this.icbmCount++;
      }

      else if (missileNumber == 2) {
        m.missile = "순항미사일";

        armed(m);
        m.effectiveRange = 5000;
        this.cruiseCount++;
      }

      else if(missileNumber == 3) {
        m.missile = "단거리미사일";

        armed(m);
        m.effectiveRange = 350;
        this.patriotCount++;
      }

      else {
        System.out.println("잘못 입력하셨습니다.");
        System.out.println("초기 화면으로 돌아갑니다.");
        break;
      }

      this.missiles[this.devCount++] = m;
      System.out.println();
      response = Prompt.inputString("계속 개발하시겠습니까?[y/N] : ");
      if (!response.equalsIgnoreCase("y")) {
        break;
      }
      System.out.println();
    }
  }

  public void list() {
    System.out.println("[미사일 목록]");

    for (i = 0; i < this.devCount; i++) {
      Missile m = this.missiles[i];
      if (m.index != 0) {
        System.out.printf("미사일 번호 : [%d]  ▶  미사일 이름 : [%s]  ▶  핵탄두 장착 여부 : [%s]\n", m.index, m.missile, m.nuclearBomb);
        System.out.printf("생화학탄 장착 여부 : [%s]  ▶  스텔스 기능 : [%s]  ▶  사정거리 : [%dKM]\n", m.biochemistryBomb, m.stealthFunction, m.effectiveRange);
        System.out.println("--------------------------------------------------------------------------------------");
      }
    }
    System.out.printf("탄도미사일 : %d대  순항미사일 : %d대  단거리미사일 : %d대\n", this.icbmCount, this.cruiseCount, this.patriotCount);
    System.out.println();
  }

  public void launch() {

    if (this.icbmCount == 0 && this.cruiseCount == 0 && this.patriotCount == 0) {
      System.out.println("미사일이 부족합니다.\n");
    }
    else {
      System.out.println("[미사일 발사]\n");
      list();
      launch = Prompt.inputInt("미사일 번호 선택 : ");

      launch--;
      this.missiles[launch].index = 0;

      System.out.println();
      System.out.printf("%s 발사 준비 완료\n", missiles[launch].missile);
      System.out.println();

      if (this.missiles[launch].missile.equals("탄도미사일")) {
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
        Timer();
        flag = 0;
        damage(flag);
        isSame(nation, flag);
        this.icbmCount--;
      }
      else if (this.missiles[launch].missile.equals("순항미사일")) {
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
        Timer();
        flag = 0;
        damage(flag);
        isSame(nation, flag);
        this.cruiseCount--;
      }
      else if (this.missiles[launch].missile.equals("단거리미사일")) {
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
        Timer();
        flag = 0;
        damage(flag);
        isSame(nation, flag);
        this.patriotCount--;
      }

      this.missiles[launch].missile = null;
      this.missiles[launch].nuclearBomb = null;
      this.missiles[launch].biochemistryBomb = null;
      this.missiles[launch].stealthFunction = null;
      this.missiles[launch].effectiveRange = 0;
    }
  }

  public void isSame(String nation, int flag) {
    for(int i = 0; i < 17; i++) {
      if(nation.equals(nations[i])) {
        if(this.flag == 1) {
          population[i] -= nuclearDamage;
          deadPeople[i] += nuclearDamage;
        }
        else if (this.flag == 2) {
          population[i] -= biochemistryDamage;
          deadPeople[i] += biochemistryDamage;
        }
        else {
          population[i] -= bombDamage;
          deadPeople[i] += bombDamage;
        }	
      }
    }
  }
  public void damage(int flag) {
    int i, j;
    if (this.missiles[launch].nuclearBomb.equals("장착")) {
      for(i = 1, j = 1; i <= nuclearDamage; i += 1321, j+= 15) {
        System.out.printf("인명피해 : %d명   폭발반경 : %dM  핵탄두 장착으로 인한 피해량 x10\n", i, j);
        this.flag = 1;
      }
    }
    else if (this.missiles[launch].biochemistryBomb.equals("장착")){
      for(i = 1, j = 1; i <= biochemistryDamage; i += 147, j += 2) {
        System.out.printf("인명피해 : %d명   폭발반경 : %dM  생화학탄 장착으로 인한 피해랑 x5\n", i, j);
        this.flag = 2;
      }
    }
    else {
      for(i = 1, j = 1; i <= bombDamage; i += 13, j++) {
        System.out.printf("인명피해 : %d명   폭발반경 : %dM\n", i, j);

      }
    }
    System.out.println();
  }

  public void armed(Missile m) {
    m.nuclearBomb = Prompt.inputString("핵탄두 [장착/미장착] : ");
    if (m.nuclearBomb.equals("장착")) {
      System.out.println("핵탄두 장착 완료\n");
      m.biochemistryBomb = "미장착";
    }
    else {
      m.nuclearBomb = "미장착";
      System.out.println("핵탄두 미장착\n");
      m.biochemistryBomb = Prompt.inputString("생화학탄 [장착/미장착] : ");
      if (m.biochemistryBomb.equals("장착")) {
        System.out.println("생화학탄 장착 완료\n");
      }
      else {
        m.biochemistryBomb = "미장착";
        System.out.println("생화학탄 미장착\n");
      }
    } 
    m.stealthFunction = Prompt.inputString("스텔스 기능 [추가/미추가] : ");
    if(m.stealthFunction.equals("추가")) {
      System.out.println("스텔스 기능 추가 완료");
    }
    else {
      m.stealthFunction = "미추가";
      System.out.println("스텔스 기능 미추가");
    }

  }
  public void Timer() {
    System.out.println();
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat date = new SimpleDateFormat("발사 시간 : yyyy년 MM월 dd일 HH시 mm분 ss초\n");
    String TTL = date.format(cal.getTime());
    System.out.println(TTL);

    count = 5;
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        if(count >= 0) {
          System.out.printf("발사 시작 : %d초 전\n", count);
          count--;
        }
        else {
          timer.cancel();
        }
      }
    }; timer.schedule(task, 2000, 1000);

    try {
      Thread.sleep(8000);
    } catch (InterruptedException e) {
      System.out.println("정지" + e.getMessage());
    }
  }

}
