package com.yoon.project;

public class CountryStatus {

  static final int TOTAL = 17;

  static void show() {
    for (int i = 0; i < TOTAL; i++) {
      if (MissileLaunch.population[i] < 0) {
        System.out.printf("%s : 멸망\n", MissileLaunch.nations[i]);
      }
      else {
        System.out.printf("%s 인구 : %d명  - 사망자 수 : %d명\n", MissileLaunch.nations[i], MissileLaunch.population[i], MissileLaunch.deadPeople[i]);
      }
    }
  }
}
