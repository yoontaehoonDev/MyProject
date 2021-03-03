package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.SellerMember;

public class MemberNumberCheckHandler extends AbstractMemberHandler {


  public MemberNumberCheckHandler(List<BuyerMember> buyerMemberList, List<SellerMember> sellerMemberList) {
    super(buyerMemberList, sellerMemberList);
  }


  @Override
  public void service() {

    Iterator<BuyerMember> buyerIterator = buyerMemberList.iterator();
    while(buyerIterator.hasNext()) {
      BuyerMember b = buyerIterator.next();
      b.setNumber(b.getNumber() + 1);
    }

    Iterator<SellerMember> sellerIterator = sellerMemberList.iterator();
    while(sellerIterator.hasNext()) {
      SellerMember s = sellerIterator.next();
      s.setNumber(s.getNumber() + 1);
    }
  }

}
