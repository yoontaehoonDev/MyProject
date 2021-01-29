package com.yoon.pms.handler;

import java.util.regex.Pattern;
import com.yoon.pms.domain.Member;
import com.yoon.util.Prompt;

public class MemberHandler {

  static final int DEFAULT_CAPACITY = 3;

  public static int logCount = 0;
  public static int adminNumber = 0;
  public static int memberNumber;
  public static int authorization = 0;
  Member[] members = new Member[DEFAULT_CAPACITY];
  int memberCount = 0;
  int uniqueNumber = 1;

  public void service() {
    while(true) {
      int i = 1;
      System.out.println("- 메뉴 / 회원 -");
      if(adminNumber == 1) {
        System.out.printf("%d. 회원 목록\n", i++);
        System.out.printf("%d. 관리자 로그아웃\n", i++);
      }
      else {
        if(logCount == 0) {
          System.out.printf("%d. 회원가입\n", i++);
          System.out.printf("%d. 로그인\n", i++);
        }
        if(logCount == 1) {
          System.out.printf("%d. 설정\n", i++);
          System.out.printf("%d. 로그아웃\n", i++);
        }
      }
      System.out.println();
      String menu = Prompt.inputString("메뉴 입력 : ");

      switch(menu) {
        case "add":
          this.add();
          break;
        case "login":
          this.login();
          break;
        case "logout":
          this.logout();
          break;
        case "adminlogin":
          this.adminLogin();
          break;
        case "adminlogout":
          this.adminLogout();
          break;
        case "list":
          this.list();
          break;
        case "setting":
          this.setting();
          break;
        case "종료":
          System.out.println("프로그램 종료");
          return;
      }
    }
  }

  public void add() {
    Member m = new Member();
    m.number = uniqueNumber++;
    m.id = minimumLength("아이디 입력 : ");
    m.password = minimumLength("비밀번호 입력 : ");
    m.name = Prompt.inputString("성명 입력 : ");
    m.email = emailFormat("이메일 입력 : ");
    m.phone = phoneFormat("핸드폰 번호 입력 : ");
    m.registeredDate = new java.sql.Date(System.currentTimeMillis());

    if(this.memberCount >= this.members.length) {
      Member[] arr = new Member[this.memberCount + (this.memberCount >> 2)];
      for(int i = 0; i < this.memberCount; i++) {
        arr[i] = members[i];
      }
      members = arr;
    }

    this.members[this.memberCount++] = m;
    System.out.println("회원가입이 완료되었습니다.\n");

  }
  public void adminLogin() {
    if(logCount == 0 && adminNumber == 0) {
      adminNumber = 1;
      System.out.println("관리자 로그인\n");
    }
  }
  public void adminLogout() {
    if(adminNumber == 1) {
      adminNumber = 0;
      System.out.println("관리자 로그아웃\n");
    }
    else {
      System.out.println("접근 권한이 없습니다.\n");
    }
  }

  public void list() {
    if(adminNumber == 1) {
      System.out.println("[회원 목록]");
      for(int i = 0; i < this.memberCount; i++) {
        Member m = this.members[i];
        System.out.printf("고유번호 : [%d] ID : [%s] Password : [%s] 이름 : [%s] 이메일 : [%s] 휴대폰 번호 : [%s] 가입일 : [%s]\n",
            m.number, m.id, m.password, m.name, m.email, m.phone, m.registeredDate);
      }
      System.out.println();
    }
    else {
      System.out.println("관리자 권한이 필요합니다.\n");
    }
  }

  public void login() {
    if(memberCount != 0 && logCount == 0) {
      while(true) {
        String id, password;
        boolean pswCheck;

        id = Prompt.inputString("아이디 입력(엔터 - 나가기) : ");
        if(id.length() == 0) {
          System.out.println("메인 메뉴로 돌아갑니다.");
          return;
        }
        int idCheck = verifyId(id);
        if(idCheck != -1) {
          while(true) {
            password = Prompt.inputString("비밀번호 입력(엔터 - 나가기) : ");
            if(password.length() == 0) {
              System.out.println("메인 메뉴로 돌아갑니다.");
              return;
            }
            pswCheck = verifyPassword(password, idCheck);
            if(pswCheck) {
              System.out.println("로그인 성공");
              authorization = 1;
              memberNumber = idCheck;
              logCount = 1;
              return;
            }
            else {
              System.out.println("비밀번호가 틀렸습니다.");
            }
          }
        }
        else {
          System.out.println("존재하지 않는 아이디 입니다.");
        }
      }
    }
    else {
      System.out.println("현재 회원이 없습니다.\n");
    }
  }
  public void logout() {
    logCount = 0;
    authorization = 1;
    memberNumber = -1;

  }

  public void setting() {
    if(logCount == 1) {
      String name;
      Member m = this.members[memberNumber];
      System.out.println("[설정]");
      System.out.printf("내 아이디 : %s 내 이름 : %s 내 이메일 : %s 내 휴대폰번호 : %s\n",
          m.id, m.name, m.email, m.phone);
      System.out.println("[1. 정보 수정]  [2. 회원 탈퇴]");
      int num = Prompt.inputInt("입력 : ");
      if(num == 1) {
        name = Prompt.inputString("정말로 수정하시겠습니까? [Y/N] : ");
        if(name.equalsIgnoreCase("y")) {
          update();
        }
        else {
          System.out.println("메뉴 / 회원 으로 돌아갑니다.");
          return;
        }
      }
      else if (num == 2){
        name = Prompt.inputString("정말로 탈퇴하시겠습니까? [Y/N] : ");
        if(name.equalsIgnoreCase("y")) {
          delete();
        }
        else {
          System.out.println("메뉴 / 회원 으로 돌아갑니다.");
          return;
        }
      }
      else {
        System.out.println("설정에서 나갑니다.");
        return;
      }
    }
    else {
      System.out.println("로그인 후 이용 가능합니다.");
    }
  }
  public void update() {
    System.out.println("[개인정보 수정]");
    Member m = members[memberNumber];
    String currentId = Prompt.inputString(String.format("현재 아이디 : %s - 수정할 아이디 : ", m.id));
    String currentPassword = Prompt.inputString(String.format("현재 비밀번호 : %s - 수정할 비밀번호 : ", m.password));
    String currentName = Prompt.inputString(String.format("현재 이름 : %s - 수정할 이름 : ", m.name));
    String currentEmail = Prompt.inputString(String.format("현재 이메일 : %s - 수정할 이메일 : ", m.email));
    String currentPhone = Prompt.inputString(String.format("현재 전화번호 : %s - 수정할 전화번호 : ", m.phone));

    m.id = currentId;
    m.password = currentPassword;
    m.name = currentName;
    m.email = currentEmail;
    m.phone = currentPhone;
    System.out.println("[개인정보 수정 완료]");
  }

  public void delete() {
    if(logCount == 1) {
      System.out.println("[회원 탈퇴]");

      for(int i = memberNumber+1; i < this.memberCount; i++) {
        members[i].number--;
        this.members[i-1] = this.members[i];
      }
      this.members[--this.memberCount] = null;
    }
    else {
      System.out.println("로그인 후, 이용 가능합니다.");
    }
  }

  int verifyId(String id) {
    for(int i = 0; i < this.memberCount; i++) {
      Member m = members[i];
      if(id.equalsIgnoreCase(m.id)) {
        return i;
      }
    }
    return -1;
  }

  boolean verifyPassword(String password, int i) {
    if(password.equals(members[i].password)) {
      return true;
    }
    return false;
  }

  // ID 중복 방지 함수 작성

  String minimumLength(String name) {
    while(true) {
      String info = Prompt.inputString(name);
      if(info.length() < 8) {
        System.out.println();
        System.out.println("8자리 이상 입력하세요.");
      }
      else {
        return info;
      }
    }
  }
  String emailFormat(String name) {
    while(true) {
      String pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
      String email = Prompt.inputString(name);
      if(Pattern.matches(pattern, email)) {
        return email;
      }
      else {
        System.out.println("E-Mail 형식(abc@abc.abc)이 아닙니다.");
      }
    }
  }
  String phoneFormat(String name) {
    while(true) {
      String pattern = "^\\d{3}-\\d{3,4}-\\d{4}$";
      String phone = Prompt.inputString(name);
      if(Pattern.matches(pattern, phone)) {
        return phone;
      }
      else {
        System.out.println("전화번호 형식(000-0000-0000)이 아닙니다.");
      }
    }
  }

}
