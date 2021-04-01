package com.yoon.pms.handler;

import com.yoon.pms.dao.BuyerMemberDao;
import com.yoon.pms.dao.SellerMemberDao;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.util.Prompt;

public class MemberDetailHandler implements Command {
  BuyerMemberDao buyerMemberDao;
  SellerMemberDao sellerMemberDao;

  public MemberDetailHandler(BuyerMemberDao buyerMemberDao, SellerMemberDao sellerMemberDao) {
    this.buyerMemberDao = buyerMemberDao;
    this.sellerMemberDao = sellerMemberDao;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[구매회원 상세보기]");

    int num = Prompt.inputInt("회원 번호 : ");
    BuyerMember b =  buyerMemberDao.findByNo(num);

    if(b == null) {
      System.out.println("존재하지 않는 회원입니다.");
      return;
    }

    System.out.printf("회원 번호 : %d\n", b.getNumber());
    System.out.printf("아이디 : %s\n", b.getId());
    System.out.printf("이름 : %s\n", b.getName());
    System.out.printf("닉네임 : %s\n", b.getNickname());
    System.out.printf("이메일 : %s\n", b.getEmail());
    System.out.printf("전화번호 : %s\n", b.getPhone());
    System.out.printf("주소 : %s\n", b.getAddress());
    System.out.printf("가입일 : %s\n", b.getRegisteredDate());
  }
}
