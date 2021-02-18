package com.yoon.pms.handler;

import java.util.List;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.SellerMember;

public class MemberAdminLoginHandler extends AbstractMemberHandler {

  public MemberAdminLoginHandler(List<BuyerMember> buyerMemberList, List<SellerMember> sellerMemberList) {
    super(buyerMemberList, sellerMemberList);
  }

  @Override
  public void service() {
    if(logCount == false && adminNumber == 0) {
      System.out.println("■ 메뉴 - 관리자 ■");
      adminNumber = 1;
      System.out.println("관리자 로그인\n");
    }
  }

}
