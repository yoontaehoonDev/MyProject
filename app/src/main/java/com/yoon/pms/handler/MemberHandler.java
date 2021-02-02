package com.yoon.pms.handler;

import com.yoon.pms.domain.Member;
import com.yoon.util.Prompt;

public class MemberHandler {



  public static int logCount = 0;
  public static int adminNumber = 0;
  public static boolean authorization = false;
  public MemberList memberList = new MemberList();


  int uniqueNumber = 1;

  public void service() {
    while(true) {
      int i = 1;
      System.out.println("- 메뉴 / 회원 -");
      if(adminNumber == 1) {
        System.out.printf("%d. 회원 목록\n", i++);
        System.out.printf("%d. 제품 리스트\n", i++);
        System.out.printf("%d. 관리자 로그아웃\n", i++);
      }
      else {
        if(logCount == 0) {
          System.out.printf("%d. 회원가입\n", i++);
          System.out.printf("%d. 로그인\n", i++);
          System.out.printf("%d. 제품 등록\n", i++);
          System.out.printf("%d. 제품 조회\n", i++);
          System.out.printf("%d. 게시판\n", i++);
          System.out.printf("%d. 고객센터\n", i++);
        }
        if(logCount == 1) {
          System.out.printf("%d. 제품 등록\n", i++);
          System.out.printf("%d. 제품 조회\n", i++);
          System.out.printf("%d. 게시판\n", i++);
          System.out.printf("%d. 고객센터\n", i++);
          System.out.printf("%d. 설정\n", i++);
          System.out.printf("%d. 로그아웃\n", i++);
        }
      }
      System.out.println();
      String menu = Prompt.inputString("메뉴 입력 : ");

      switch(menu) {
        case "회원가입":
          this.add();
          break;
        case "로그인":
          this.login();
          break;
        case "로그아웃":
          this.logout();
          break;
        case "관리자로그인":
          this.adminLogin();
          break;
        case "관리자로그아웃":
          this.adminLogout();
          break;
        case "회원 목록":
          this.list();
          break;
        case "설정":
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
    m.setId(memberList.isSame("아이디 입력 : "));
    m.setPassword(memberList.minimumLength("비밀번호 입력 : "));
    m.setName(Prompt.inputString("성명 입력 : "));
    m.setEmail(memberList.emailFormat("이메일 입력 : "));
    m.setPhone(memberList.phoneFormat("핸드폰 번호 입력 : "));
    m.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));

    memberList.add(m);
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

      Member[] members = memberList.toArray();

      for(Member m : members) {
        System.out.printf("회원번호 : [%d]  ID : [%s]  Password : [%s]  이름 : [%s]  E-Mail : [%s]  휴대폰번호 : [%s]  가입일자 : [%s]\n", 
            m.number, m.getId(), m.getPassword(), m.getName(), m.getEmail(), m.getPhone(), m.getRegisteredDate());
      }

      System.out.println();
      System.out.println("1. [회원 강제탈퇴] 2. [나가기]");
      String setting = Prompt.inputString("번호를 선택하세요 : ");
      if(setting.equals("1")) {
        int num = Prompt.inputInt("탈퇴시킬 회원 번호를 입력하세요 : ");
        memberList.get(num);

      }
      else {
        System.out.println("메뉴로 돌아갑니다.\n");
        return;
      }
    }
    else {
      System.out.println("관리자 권한이 필요합니다.\n");
    }
  }

  public void login() {
    if(memberList.memberCount != 0 && logCount == 0) {
      while(true) {
        String id, password;
        boolean pswCheck;

        id = Prompt.inputString("아이디 입력(엔터 - 나가기) : ");
        if(id.length() == 0) {
          System.out.println("메인 메뉴로 돌아갑니다.\n");
          return;
        }

        Member idCheck = memberList.verifyId(id);

        if(idCheck != null) {
          while(true) {
            password = Prompt.inputString("비밀번호 입력(엔터 - 나가기) : ");
            if(password.length() == 0) {
              System.out.println("메인 메뉴로 돌아갑니다.\n");
              return;
            }

            pswCheck = memberList.verifyPassword(password, idCheck);

            if(pswCheck) {
              System.out.println("로그인 성공\n");
              authorization = true;
              logCount = 1;
              memberList.memberNumber = idCheck;
              return;
            }
            else {
              System.out.println("비밀번호가 틀렸습니다.\n");
            }
          }
        }
        else {
          System.out.println("존재하지 않는 아이디 입니다.\n");
        }
      }
    }
    else {
      System.out.println("현재 회원이 없습니다.\n");
    }
  }

  public void logout() {
    logCount = 0;
    memberList.currentNode = null;
    authorization = false;
    memberList.memberNumber = null;

  }

  public void setting() {
    if(logCount == 1) {
      String name;
      Member m = memberList.memberNumber;
      System.out.println("[설정]");
      System.out.printf("내 아이디 : %s 내 이름 : %s 내 이메일 : %s 내 휴대폰번호 : %s\n",
          m.getId(), m.getName(), m.getEmail(), m.getPhone());
      System.out.println("[1. 정보 수정]  [2. 회원 탈퇴]  [3. 뒤로가기]\n");
      String match = Prompt.inputString("입력 : ");
      if(match.equals("1")) {
        System.out.println("[개인정보 수정]");
        name = Prompt.inputString("정말로 수정하시겠습니까? [Y/N] : ");
        if(name.equalsIgnoreCase("y")) {
          update();
        }
        else {
          System.out.println("메뉴 / 회원 으로 돌아갑니다.\n");
          return;
        }
      }
      else if (match.equals("2")){
        System.out.println("[회원 탈퇴]");
        name = Prompt.inputString("정말로 탈퇴하시겠습니까? [Y/N] : ");
        if(name.equalsIgnoreCase("y")) {
          delete();
        }
        else {
          System.out.println("메뉴 / 회원 으로 돌아갑니다.\n");
          return;
        }
      }
      else {
        System.out.println("설정에서 나갑니다.\n");
        return;
      }
    }
    else {
      System.out.println("로그인 후 이용 가능합니다.\n");
    }
  }
  public void update() {

    Member m = memberList.memberNumber;
    m.setId(memberList.isSame("수정할 ID : "));
    m.setPassword(memberList.minimumLength("수정할 Password : "));
    m.setName(Prompt.inputString("수정할 이름 : "));
    m.setEmail(memberList.emailFormat("수정할 E-Mail : "));
    m.setPhone(memberList.phoneFormat("수정할 핸드폰 번호 : "));

    System.out.println("[개인정보 수정 완료]\n");
  }

  public void delete() {
    if(logCount == 1) {
      memberList.delete();
      logCount = 0;

      System.out.println("계정 탈퇴 처리가 완료되었습니다. 그동안 이용해주셔서 감사합니다.\n");
    }
    else {
      System.out.println("로그인 후, 이용 가능합니다.\n");
    }
  }

}
