package com.yoon.pms.handler;

import java.util.List;
import java.util.regex.Pattern;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class MemberValidatorHandler extends AbstractMemberHandler {


  public MemberValidatorHandler(List<BuyerMember> buyerMemberList, List<SellerMember> sellerMemberList) {
    super(buyerMemberList, sellerMemberList);
  }

  @Override
  public void service() {
  }

  public void checkPassword(String originalPassword) {
    while(true) {
      String password = minimumLength("비밀번호 확인 : ");
      if(password.equals(originalPassword)) {
        return;
      }
      else {
        System.out.println("현재 비밀번호와 일치하지 않습니다.\n");
      }
    }
  }

  public String minimumLength(String name) {
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

  public String emailFormat(String name) {
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

  public String phoneFormat(String name) {
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






