package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class MemberListHandler extends AbstractMemberHandler {

  public MemberListHandler(List<BuyerMember> buyerMemberList, List<SellerMember> sellerMemberList) {
    super(buyerMemberList, sellerMemberList);
  }

  @Override
  public void service() {
    if(adminNumber == 1) {
      System.out.println("■ 메뉴 - 관리자 - 회원목록 ■");
      if(sellerMemberList.size() == 0 && buyerMemberList.size() == 0) {
        System.out.println("존재하는 회원이 없습니다.\n");
        return;
      }
      MemberDeleteHandler delete = new MemberDeleteHandler(buyerMemberList, sellerMemberList);
      System.out.println("1. 구매자 회원 목록  2. 판매자 회원 목록\n");
      String choice = Prompt.inputString("선택 : ");


      if(choice.equals("1")) {
        System.out.println("[구매자 회원 목록]");
        Iterator<BuyerMember> iterator = buyerMemberList.iterator();
        if(buyerMemberList.size() == 0) {
          System.out.println("회원이 없습니다. \n메뉴로 돌아갑니다.\n");
          return;
        }
        while(iterator.hasNext()) {
          BuyerMember b = iterator.next();
          if(b.isDivision() == false) {
            System.out.printf("회원번호 : [%d]  ID : [%s]  Password : [%s]  닉네임 : [%s]  이름 : [%s]  E-Mail : [%s]  휴대폰번호 : [%s]  가입일자 : [%s]\n", 
                b.getNumber(), b.getId(), b.getPassword(), b.getNickname(), b.getName(), b.getEmail(), b.getPhone(), b.getRegisteredDate());
          }
        }
        System.out.println();
        System.out.println("1. [회원 강제탈퇴]  2. [나가기]");
        String setting = Prompt.inputString("번호를 선택하세요 : ");
        if(setting.equals("1")) {
          flag = 0;

          delete.service();
        }
        else {
          System.out.println("메뉴로 돌아갑니다.\n");
          return;
        }
      }

      else if(choice.equals("2")) {
        System.out.println("[판매자 회원 목록]");
        Iterator<SellerMember> iterator = sellerMemberList.iterator();
        if(sellerMemberList.size() == 0) {
          System.out.println("회원이 없습니다. \n메뉴로 돌아갑니다.\n");
          return;
        }
        while(iterator.hasNext()) {
          SellerMember s = iterator.next();
          System.out.printf("회원번호 : [%d]  ID : [%s]  Password : [%s]  소유주 : [%s]  E-Mail : [%s]  휴대폰번호 : [%s]\n", 
              s.getNumber(), s.getId(), s.getPassword(), s.getName(), s.getEmail(), s.getPhone());
          System.out.printf("상호명 : [%s]  사업자등록번호 : [%s]  영업시간 : [%s]  업종 : [%s]\n",
              s.getBusinessName(), s.getBusinessNumber(), s.getBusinessHour(), s.getCategory());
        }
        System.out.println();
        System.out.println("1. [회원 강제탈퇴] 2. [나가기]");
        String setting = Prompt.inputString("번호를 선택하세요 : ");
        if(setting.equals("1")) {
          flag = 1;
          delete.service();
        }
        else {
          System.out.println("메뉴로 돌아갑니다.\n");
          return;
        }
      }
    }
    else {
      System.out.println("관리자 권한이 필요합니다.\n");
    }
  }

}
