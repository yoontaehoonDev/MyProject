package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public abstract class AbstractMemberHandler implements Command {

  protected List<BuyerMember> buyerMemberList;
  protected List<SellerMember> sellerMemberList;

  public AbstractMemberHandler(List<BuyerMember> buyerMemberList, List<SellerMember> sellerMemberList) {
    this.buyerMemberList = buyerMemberList;
    this.sellerMemberList = sellerMemberList;
  } 

  public static boolean logCount = false;
  public static int adminNumber = 0;
  public static BuyerMember buyerMemberNumber;
  public static SellerMember sellerMemberNumber;
  public static int logStatus = -1;
  public static boolean loginCheck = false;
  public static int point;


  public String findByBuyerId(String message) {
    System.out.println("find 접근");
    String id;
    while(true) {
      int flag = 0;
      id = Prompt.inputString(message);
      if(id.length() < 8) {
        System.out.println();
        System.out.println("8자리 이상 입력하세요.");
        flag = 1;
      }
      else {
        // 정지된 부분
        Iterator<BuyerMember> iterator = buyerMemberList.iterator();
        while(iterator.hasNext()) {
          BuyerMember b = iterator.next();
          if(id.equalsIgnoreCase(b.getId())) {
            System.out.println("이미 사용중인 아이디 입니다.\n");
            flag = 1;
            break;
          }
        }
      }
      if(flag == 0) {
        break;
      }
    }
    return id;
  }

  public String findBySellerId(String message) {
    String id;
    while(true) {
      int flag = 0;
      id = Prompt.inputString(message);
      if(id.length() < 8) {
        System.out.println();
        System.out.println("8자리 이상 입력하세요.");
        flag = 1;
      }
      else {
        Object[] list = sellerMemberList.toArray();
        for(Object obj : list) {
          SellerMember s = (SellerMember) obj;
          if(id.equalsIgnoreCase(s.getId())) {
            System.out.println("이미 사용중인 아이디 입니다.\n");
            flag = 1;
            break;
          }
        }
      }
      if(flag == 0) {
        break;
      }
    }
    return id;
  }

  public String findByNickname(String message) {
    String nickname;
    while(true) {
      int flag = 0;
      nickname = Prompt.inputString(message);
      if(nickname.length() < 2 && nickname.length() > 8 ) {
        System.out.println();
        System.out.println("2 ~ 8자리 사이로 입력하세요.");
        flag = 1;
      }
      else {
        Iterator<BuyerMember> iterator = buyerMemberList.iterator();
        while(iterator.hasNext()) {
          BuyerMember b = iterator.next();
          if(nickname.equalsIgnoreCase(b.getNickname())) {
            System.out.println("이미 사용중인 닉네임 입니다.\n");
            flag = 1;
            break;
          }
        }
      }
      if(flag == 0) {
        break;
      }
    }
    return nickname;
  }

  public BuyerMember verifyBuyerId(String id) {
    Iterator<BuyerMember> iterator = buyerMemberList.iterator();
    while(iterator.hasNext()) {
      BuyerMember b = iterator.next();
      if(b.getId().equalsIgnoreCase(id)) {
        return b;
      }
    }
    return null;
  }

  public SellerMember verifySellerId(String id) {

    Iterator<SellerMember> iterator = sellerMemberList.iterator();
    while(iterator.hasNext()) {
      SellerMember s = iterator.next();
      if(s.getId().equalsIgnoreCase(id)) {
        return s;
      }
    }
    return null;

  }

  public boolean verifyBuyerPassword(String password, BuyerMember b) {
    if(password.equals(b.getPassword())) {
      return true;
    }
    return false;
  }

  public boolean verifySellerPassword(String password, SellerMember s) {
    if(password.equals(s.getPassword())) {
      return true;
    }
    return false;
  }



  public BuyerMember findByBuyerNum(int buyerMemberNum) {

    Iterator<BuyerMember> iterator = buyerMemberList.iterator();

    while(iterator.hasNext()) {
      BuyerMember b = iterator.next();
      if(b.getNumber() == buyerMemberNum) {
        return b;
      }
    }
    return null;
  }

  public SellerMember findBySellerNum(int sellerMemberNum) {

    Iterator<SellerMember> iterator = sellerMemberList.iterator();

    while(iterator.hasNext()) {
      SellerMember s = iterator.next();
      if(s.getNumber() == sellerMemberNum) {
        return s;
      }
    }
    return null;

  }


  public void category(SellerMember s) {
    System.out.println("[업종 선택]");
    String choice;
    Loop:
      while(true) {
        System.out.println("1. 한식 2. 양식 3. 일식 4. 중식 5. 분식");
        System.out.println("6. 치킨 7. 피자 8. 디저트 9. 야식\n");
        choice = Prompt.inputString("번호 선택 : ");

        if(choice.length() == 0) {
          continue;
        }

        switch(choice) {
          case "1": s.setCategory("한식"); break Loop;
          case "2": s.setCategory("양식"); break Loop;
          case "3": s.setCategory("일식"); break Loop;
          case "4": s.setCategory("중식"); break Loop;
          case "5": s.setCategory("분식"); break Loop;
          case "6": s.setCategory("치킨"); break Loop;
          case "7": s.setCategory("피자"); break Loop;
          case "8": s.setCategory("디저트"); break Loop;
          case "9": s.setCategory("야식"); break Loop;
          default: System.out.println("다시 선택하세요.\n");
        }
      }
    s.setCategoryId(Integer.parseInt(choice));
    System.out.println("업종 선택 완료\n");
  }

}
