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
      String memberId = Prompt.inputString("아이디 입력 : ");
      idVerify();
    }
  }

  public void logout() {

  }

  public void idVerify() {

  }

  public void pswVerify() {

  }
}
