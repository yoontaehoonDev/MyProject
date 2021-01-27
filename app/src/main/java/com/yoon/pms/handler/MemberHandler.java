package com.yoon.pms.handler;

import com.yoon.pms.domain.Member;
import com.yoon.util.Prompt;

public class MemberHandler {

  static final int MAX = 100;
  Member memberList;
  static int logCount = 0;
  Member[] members = new Member[MAX];
  int memberCount = 0;

  public void add() {

    this.members[this.memberCount].id = Prompt.inputString("아이디 입력 : ");
    this.members[this.memberCount].psw = Prompt.inputString("비밀번호 입력 : ");
    this.members[this.memberCount].name = Prompt.inputString("성명 : ");
    this.members[this.memberCount].email = Prompt.inputString("이메일 입력 : ");
    this.members[this.memberCount].phone = Prompt.inputString("핸드폰 번호 입력 : ");
    System.out.println("회원가입이 완료되었습니다.");
    this.memberCount++;
  }

  public void login() {
    if(logCount == 0) {
      while(true) {
        String id = Prompt.inputString("아이디 입력 : ");
        int idCheck = idVerify(id);
        if(idCheck != -1) {
          String password = Prompt.inputString("비밀번호 입력 : ");
          boolean pswCheck = pswVerify(password, idCheck);
          if(pswCheck) {
            System.out.println("로그인 성공");
          }
        };

        logCount = 1;

        return;
      }
    }
    else {
      System.out.println("이미 로그인 상태 입니다.");
    }
  }

  public void logout() {
    logCount = 0;
  }

  int idVerify(String id) {
    for(int i = 0; i < this.memberCount; i++) {
      Member m = members[i];
      if(id.equalsIgnoreCase(m.id)) {
        return i;
      }
    }
    return -1;
  }

  boolean pswVerify(String password, int i) {
    if(password.equals(members[i].psw)) {
      return true;
    }
    return false;
  }
}
