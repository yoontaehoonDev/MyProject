package com.yoon.pms.handler;

import com.yoon.pms.dao.BuyerMemberDao;
import com.yoon.pms.dao.SellerMemberDao;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class MemberAddHandler implements Command {

  BuyerMemberDao buyerMemberDao;
  SellerMemberDao sellerMemberDao;
  MemberValidator memberValidator;

  public MemberAddHandler(BuyerMemberDao buyerMemberDao, SellerMemberDao sellerMemberDao, MemberValidator memberValidator) {
    this.buyerMemberDao = buyerMemberDao;
    this.sellerMemberDao = sellerMemberDao;
    this.memberValidator = memberValidator;
  }


  @Override
  public void service() throws Exception {
    System.out.println("■ 메뉴 - 회원 - 회원가입 ■");
    while(true) {
      String choice = Prompt.inputString("1. 구매자  2. 판매자");

      if(choice.length() == 0) {
        System.out.println("메뉴로 돌아갑니다.\n");
        return;
      }

      if(choice.equals("1")) {

        BuyerMember b = new BuyerMember();
        b.setId(Prompt.inputString("아이디 입력 : "));
        b.setPassword(memberValidator.minimumLength("비밀번호 입력 : "));
        b.setNickname(memberValidator.nickNameFormat("닉네임 입력 : "));
        b.setName(memberValidator.nameFormat("성명 입력 : "));
        b.setEmail(memberValidator.emailFormat("이메일 입력 : "));
        b.setPhone(memberValidator.phoneFormat("휴대폰 입력 : "));
        b.setAddress(Prompt.inputString("주소 : "));
        b.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
        b.setDivision(1);
        buyerMemberDao.insert(b);
        break;
      }
      else if (choice.equals("2")) {
        SellerMember s = new SellerMember();
        s.setId(Prompt.inputString("아이디 입력 : "));
        s.setPassword(memberValidator.minimumLength("비밀번호 입력 : "));
        s.setName(memberValidator.nameFormat("소유주 입력 : "));
        s.setEmail(memberValidator.emailFormat("이메일 입력 : "));
        s.setPhone(memberValidator.phoneFormat("가게 번호 : "));
        s.setBusinessName(Prompt.inputString("상호명 : "));
        s.setAddress(Prompt.inputString("가게 주소 : "));
        s.setBusinessNumber(memberValidator.businessNumberFormat("사업자등록번호 : "));
        s.setBusinessHour(memberValidator.businessHourFormat("영업 시간 : "));
        s.setCategory(CategorySelecter.service());
        System.out.println("업종 선택 완료\n");
        s.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
        break;
      }
      else {
        System.out.println("잘못 누르셨습니다.");
      }
    }
    System.out.println("회원가입이 완료되었습니다.\n");
  }

}
