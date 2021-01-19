package com.yoon.project;

public class Project {

  public static void main(String[] args) {
    final int MI_DEV = 1;
    final int MI_LIST = 2;
    final int MI_LAUNCH = 3;
    final int Co_Status = 4;

    while(true) {

      Menu.choice();

      if (Menu.id == MI_DEV) {

        MissileDevelopment.add();

      }
      else if(Menu.id == MI_LIST) {

        MissileDevelopment.list();

      }

      else if (Menu.id == MI_LAUNCH) {

        MissileLaunch.launch();

      }
      else if (Menu.id == Co_Status) {

        CountryStatus.show();

      }
      else {
        System.out.println("미사일 시스템 종료");
        break;
      }
    }
  }
}
