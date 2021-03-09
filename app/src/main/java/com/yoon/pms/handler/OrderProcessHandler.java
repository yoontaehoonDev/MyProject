package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Menu;
import com.yoon.pms.domain.Order;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.Prompt;

public class OrderProcessHandler extends AbstractOrderHandler {

  protected List<BuyerMember> buyerMemberList;
  protected List<SellerMember> sellerMemberList;
  protected List<Menu> menuList;

  public OrderProcessHandler(List<Order> orderList, List<BuyerMember> buyerMemberList, List<SellerMember> sellerMemberList, List<Menu> menuList) {
    super(orderList);
    this.buyerMemberList = buyerMemberList;
    this.sellerMemberList = sellerMemberList;
    this.menuList = menuList;
  }

  @Override
  public void service() {
    System.out.println("■ 메뉴 - 주문 - 가게 목록 ■");
    int num = Integer.parseInt(AbstractOrderHandler.categoryNumber);
    int flag = 0;

    Iterator<SellerMember> iterator = sellerMemberList.iterator();
    while(iterator.hasNext()) {
      SellerMember s = iterator.next();
      if(s.getCategoryId() == num) {
        System.out.printf("가게 번호 : [%d]  업종 : [%s]  상호명 : [%s]  전화번호 : [%s]\n",
            s.getNumber(), s.getCategory(), s.getBusinessName(), s.getBusinessNumber());
        flag = 1;
      }
    }
    if(flag == 0) {
      System.out.println("등록된 가게가 없습니다.");
      return;
    }

    System.out.println();
    int id =  AbstractOrderHandler.categoryId = Prompt.inputInt("가게 선택 : ");

    flag = 0;
    Iterator<Menu> menu = menuList.iterator();
    while(menu.hasNext()) {
      Menu m = menu.next();
      if(num == m.getCategoryId() && id == m.getId()) {
        System.out.printf("메뉴번호 : %d  메뉴명 : %s  메뉴가격 : %d  메뉴설명 : %s\n", 
            m.getNumber(), m.getName(), m.getPrice(), m.getExplain());
        flag = 1;
      }
    }
    // 메뉴 개수 만큼 연결리스트 생성 후,
    // 메뉴 선택할 때, 카운트를 세고, 영수증에 개수 기입하기
    //		LinkedList a = new LinkedList();
    //		a.add(menu)
    if(flag == 0) {
      System.out.println("등록된 메뉴가 없습니다.");
      return;
    }
    int sum = 0;
    while(true) {
      // 1. 메뉴 당 개수 세기
      //    배열 or 연결리스트로 생성
      //    빌지 저장 후, 삭제
      int choice = Prompt.inputInt("메뉴 선택 : ");
      flag = 0;
      // 임시 연결리스트 생성
      LinkedList temp = new LinkedList();
      int count = 0;
      Iterator<Menu> list = menuList.iterator();
      while(list.hasNext()) {
        Menu m = list.next();
        if(num == m.getCategoryId() && id == m.getId()) {
          if(choice == m.getNumber()) {
            sum += m.getPrice();
            // 임시로 문자열 저장
            temp.add(new String("adsf"));
            temp.add(count++);
            flag = 1;
            break;
          }
        }
      }

      // 조리 시작 시, 주문자에게 메시지 전달하기
      // 조리 시작 전에 메뉴 변경 가능 여부 고려
      // 최종 주문 완료 전, 메뉴 변경과 메뉴 개수 수정 기능 구현
      // 영수증이 발행되면, 기존에 저장된 모든 데이터 삭제
      temp.removeAll(temp);
      if(flag == 0) {
        System.out.println("유효한 번호를 선택하세요.");
      }
      else {
        System.out.printf("현재 금액 : %d원\n", sum);
        String repeat = Prompt.inputString("계속 추가하시겠습니까?[Y/N] : ");
        if(repeat.equalsIgnoreCase("n")) {
          // 총 금액 합계

          break;
        }
      }
    }

  }
}
