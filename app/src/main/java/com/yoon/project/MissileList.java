package com.yoon.project;

public class MissileList {


  static int i;

  static void list() {
    System.out.println("[미사일 목록]");
    for (i = 0; i < MissileDevelopment.devCount; i++) {
      if (MissileDevelopment.index[i] != 0) {
        System.out.printf("미사일 번호 : [%d]  ▶  미사일 이름 : [%s]  ▶  핵탄두 장착 여부 : [%s]\n", MissileDevelopment.index[i], MissileDevelopment.missile[i], MissileDevelopment.nuclearBomb[i]);
        System.out.printf("생화학탄 장착 여부 : [%s]  ▶  스텔스 기능 : [%s]  ▶  사정거리 : [%dKM]\n", MissileDevelopment.biochemistryBomb[i], MissileDevelopment.stealthFuction[i], MissileDevelopment.effectiveRange[i]);
        System.out.println("--------------------------------------------------------------------------------------");
      }
    }
    System.out.printf("탄도미사일 : %d대  순항미사일 : %d대  단거리미사일 : %d대\n", MissileDevelopment.icbmCount, MissileDevelopment.cruiseCount, MissileDevelopment.patriotCount);
    System.out.println();
  }
}
