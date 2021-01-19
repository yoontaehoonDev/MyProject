package com.yoon.project;

public class Project {

  public static void main(String[] args) {


    while(true) {

      Menu.choice();

      if (Menu.choice == 1) {

        MissileDevelopment.add();

      }
      else if(Menu.choice == 2) {

        MissileList.list();

      }

      else if (Menu.choice == 3) {

        MissileLaunch.launch();

      }
      else if (Menu.choice == 4) {

        CountryStatus.show();

      }
      else {
        System.out.println("미사일 시스템 종료");
        break;
      }
    }
  }
}
