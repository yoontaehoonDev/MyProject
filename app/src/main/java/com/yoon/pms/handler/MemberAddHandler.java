package com.yoon.pms.handler;

import java.util.List;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class MemberAddHandler extends AbstractMemberHandler {

  private MemberValidatorHandler memberHandler;

  public MemberAddHandler(List<BuyerMember> buyerMemberList, List<SellerMember> sellerMemberList, MemberValidatorHandler memberHandler) {
    super(buyerMemberList, sellerMemberList);
    this.memberHandler = memberHandler;
  }



  @Override
  public void service() {
    System.out.println("■ 메뉴 - 회원 - 회원가입 ■");
    while(true) {
      String choice = Prompt.inputString("1. 구매자  2. 판매자");

      if(choice.length() == 0) {
        System.out.println("메뉴로 돌아갑니다.\n");
        return;
      }

      if(choice.equals("1")) {
        BuyerMember b = new BuyerMember();
        b.setDivision(false);
        b.setHash(AbstractMemberHandler.hashNum++);
        b.setNumber(AbstractMemberHandler.buyerIndex++);
        b.setId(findByBuyerId("아이디 입력 : "));
        b.setPassword(memberHandler.minimumLength("비밀번호 입력 : "));
        memberHandler.checkPassword(b.getPassword());
        b.setNickname(memberHandler.nickNameFormat("닉네임 입력 : "));
        b.setName(memberHandler.nameFormat("성명 입력 : "));
        b.setEmail(memberHandler.emailFormat("이메일 입력 : "));
        b.setPhone(memberHandler.phoneFormat("휴대폰 입력 : "));
        b.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
        this.buyerMemberList.add(b);
        break;
      }
      else if (choice.equals("2")) {
        SellerMember s = new SellerMember();
        s.setDivision(false);
        s.setHash(AbstractMemberHandler.hashNum++);
        s.setCount(s.getCount() + 1);
        s.setNumber(s.getCount());

        s.setId(findBySellerId("아이디 입력 : "));
        s.setPassword(memberHandler.minimumLength("비밀번호 입력 : "));
        memberHandler.checkPassword(s.getPassword()); // 비밀번호 확인 메소드
        s.setName(memberHandler.nameFormat("소유주 입력 : "));
        s.setEmail(memberHandler.emailFormat("이메일 입력 : "));
        s.setPhone(memberHandler.phoneFormat("가게 번호 : "));
        s.setBusinessName(Prompt.inputString("상호명 : "));
        s.setBusinessNumber(memberHandler.businessNumberFormat("사업자등록번호 : "));
        s.setBusinessHour(memberHandler.businessHourFormat("영업 시간 : "));
        category(s); // 업종 입력 메소드
        s.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
        this.sellerMemberList.add(s);
        System.out.printf("현재 판매자 가게 번호 : %d  해쉬 : %d\n\n", s.getNumber(), s.getHash());
        break;
      }
      else {
        System.out.println("잘못 누르셨습니다.");
      }
    }
    loginCheck = true;
    System.out.println("회원가입이 완료되었습니다.\n");
  }


}
