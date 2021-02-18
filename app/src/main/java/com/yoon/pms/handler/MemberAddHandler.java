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

  int buyerIndex = 1;
  int sellerIndex = 1;
  int hashNum = 1;

  @Override
  public void service() {
    System.out.println("■ 메뉴 - 회원 - 회원가입 ■");

    while(true) {
      String choice = Prompt.inputString("1. 구매자  2. 판매자");
      if(choice.equals("1")) {
        BuyerMember b = new BuyerMember();
        b.setDivision(false);
        b.setHash(this.hashNum++);
        b.setNumber(buyerIndex++);
        b.setId(findByBuyerId("아이디 입력 : "));
        b.setPassword(memberHandler.minimumLength("비밀번호 입력 : "));
        memberHandler.checkPassword(b.getPassword());
        b.setNickname(findByNickname("닉네임 입력 : "));
        b.setName(Prompt.inputString("성명 입력 : "));
        b.setEmail(memberHandler.emailFormat("이메일 입력 : "));
        b.setPhone(memberHandler.phoneFormat("핸드폰 번호 입력 : "));
        b.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
        this.buyerMemberList.add(b);
        break;
      }
      else if (choice.equals("2")) {
        SellerMember s = new SellerMember();
        s.setDivision(false);
        s.setHash(this.hashNum++);
        s.setNumber(sellerIndex++);

        s.setId(findBySellerId("아이디 입력 : "));
        s.setPassword(memberHandler.minimumLength("비밀번호 입력 : "));
        memberHandler.checkPassword(s.getPassword()); // 비밀번호 확인 메소드
        s.setName(Prompt.inputString("소유주 입력 : "));
        s.setEmail(memberHandler.emailFormat("이메일 입력 : "));
        s.setPhone(memberHandler.phoneFormat("핸드폰 번호 입력 : "));
        s.setBusinessName(Prompt.inputString("상호명 : "));
        s.setBusinessNumber(Prompt.inputString("사업자 등록 번호 : "));
        s.setBusinessHour(Prompt.inputString("영업 시간 : "));
        category(s); // 업종 입력 메소드
        s.setMenu(Prompt.inputString("메뉴명 입력 : "));
        s.setMenuExplaination(Prompt.inputString("메뉴 설명 : "));
        //              while(true) {
        //                  m.setMenu();
        //                  
        //                  m.setMenu(m.setMenu(Prompt.inputString("메뉴명 : ")));
        //
        //                  m.setMenu(Prompt.inputString("메뉴명 : "));
        //                  if(m.getMenu().equalsIgnoreCase("a"))
        //                      break;
        //                  m.setMenuExplaination(Prompt.inputString("메뉴 설명 : "));
        //                  times++;
        //              }
        s.setPrice(Prompt.inputInt("가격 : "));
        s.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
        this.sellerMemberList.add(s);
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
