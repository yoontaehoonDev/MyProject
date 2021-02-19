package com.yoon.pms;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Comment;
import com.yoon.pms.domain.SellerMember;
import com.yoon.pms.handler.BuyerBoardAddHandler;
import com.yoon.pms.handler.BuyerBoardDetailHandler;
import com.yoon.pms.handler.BuyerBoardListHandler;
import com.yoon.pms.handler.BuyerBoardMyListHandler;
import com.yoon.pms.handler.Command;
import com.yoon.pms.handler.IntegratedBoardAddHandler;
import com.yoon.pms.handler.IntegratedBoardListHandler;
import com.yoon.pms.handler.IntegratedBoardMyListHandler;
import com.yoon.pms.handler.IntegratedDetailBoardHandler;
import com.yoon.pms.handler.MemberAddHandler;
import com.yoon.pms.handler.MemberAdminLoginHandler;
import com.yoon.pms.handler.MemberAdminLogoutHandler;
import com.yoon.pms.handler.MemberDeleteHandler;
import com.yoon.pms.handler.MemberListHandler;
import com.yoon.pms.handler.MemberLoginHandler;
import com.yoon.pms.handler.MemberLogoutHandler;
import com.yoon.pms.handler.MemberSettingHandler;
import com.yoon.pms.handler.MemberUpdateHandler;
import com.yoon.pms.handler.MemberValidatorHandler;
import com.yoon.pms.handler.SellerBoardAddHandler;
import com.yoon.pms.handler.SellerBoardDetailHandler;
import com.yoon.pms.handler.SellerBoardListHandler;
import com.yoon.pms.handler.SellerBoardMyListHandler;
import com.yoon.util.Prompt;

public class App {

  static ArrayDeque<String> commandStack = new ArrayDeque<>();
  static LinkedList<String> commandQueue = new LinkedList<>();

  public static void main(String[] args) throws CloneNotSupportedException {
    System.out.println("[2030 Project]");

    LinkedList<BuyerMember> buyerMemberList = new LinkedList<>();
    LinkedList<SellerMember> sellerMemberList = new LinkedList<>();
    LinkedList<Comment> commentList = new LinkedList<>();
    LinkedList<Board> buyerBoardList = new LinkedList<>();
    LinkedList<Board> sellerBoardList = new LinkedList<>();
    LinkedList<Board> integratedBoardList = new LinkedList<>();

    HashMap<String, Command> commandMap = new HashMap<>();

    MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(buyerMemberList, sellerMemberList);

    commandMap.put("회원가입", new MemberAddHandler(buyerMemberList, sellerMemberList, memberValidatorHandler));
    commandMap.put("로그인", new MemberLoginHandler(buyerMemberList, sellerMemberList));
    commandMap.put("로그아웃", new MemberLogoutHandler(buyerMemberList, sellerMemberList));
    commandMap.put("설정", new MemberSettingHandler(buyerMemberList, sellerMemberList, memberValidatorHandler));
    commandMap.put("변경", new MemberUpdateHandler(buyerMemberList, sellerMemberList, memberValidatorHandler));
    commandMap.put("관리자로그인", new MemberAdminLoginHandler(buyerMemberList, sellerMemberList));
    commandMap.put("관리자로그아웃", new MemberAdminLogoutHandler(buyerMemberList, sellerMemberList));
    commandMap.put("삭제", new MemberDeleteHandler(buyerMemberList, sellerMemberList));
    commandMap.put("회원목록", new MemberListHandler(buyerMemberList, sellerMemberList));

    commandMap.put("글쓰기1", new BuyerBoardAddHandler(buyerBoardList, commentList));
    commandMap.put("글보기1", new BuyerBoardDetailHandler(buyerBoardList, commentList));
    commandMap.put("글목록1", new BuyerBoardListHandler(buyerBoardList, commentList));
    commandMap.put("내글1", new BuyerBoardMyListHandler(buyerBoardList, commentList));

    commandMap.put("글쓰기2", new SellerBoardAddHandler(sellerBoardList, commentList));
    commandMap.put("글보기2", new SellerBoardDetailHandler(sellerBoardList, commentList));
    commandMap.put("글목록2", new SellerBoardListHandler(sellerBoardList, commentList));
    commandMap.put("내글2", new SellerBoardMyListHandler(sellerBoardList, commentList));

    commandMap.put("글쓰기3", new IntegratedBoardAddHandler(integratedBoardList, commentList));
    commandMap.put("글보기3", new IntegratedDetailBoardHandler(integratedBoardList, commentList));
    commandMap.put("글목록3", new IntegratedBoardListHandler(integratedBoardList, commentList));
    commandMap.put("내글3", new IntegratedBoardMyListHandler(integratedBoardList, commentList));



    loop:
      while(true) {
        System.out.println("■ 메뉴 ■");
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("3. 구매자 게시판");
        System.out.println("4. 판매자 게시판");
        System.out.println("5. 통합 게시판");
        System.out.println("6. 고객센터");
        System.out.println("7. 사이트 종료\n");
        String menu = Prompt.inputString("메뉴 입력 : ");
        System.out.println();

        commandStack.push(menu);
        commandQueue.offer(menu);
        try {
          switch(menu) {
            //            case "회원가입":
            //              memberHandler.add();
            //              break;
            //            case "로그인":
            //              memberHandler.login();
            //              break;
            //            case "구매자게시판":
            //              buyerBoardHandler.service();
            //              break;
            //            case "판매자게시판":
            //              sellerBoardHandler.service();
            //              break;
            //            case "통합게시판":
            //              integratedBoardHandler.service();
            //              break;
            case "shistory":
              printCommandHistory(commandStack.iterator());
              break;
            case "qhistory":
              printCommandHistory(commandQueue.iterator());
              break;
            case "종료":
              System.out.println("프로그램 종료");
              break loop;
            default:
              // commandHandler에 명령어를 해쉬맵의 값에서 꺼내고 비교하며, 값이 null이면 실행 X, 아니면 실행 O 
              Command commandHandler = commandMap.get(menu);
              if(commandHandler == null) {
                System.out.println("실행할 수 없는 명령입니다.");
              }
              else {
                commandHandler.service();
                // 이제 명령어와 그 명령어를 처리하는 핸들러를 추가할 때마다
                // case 문을 추가할 필요가 없다.
              }
          }
        } catch (Exception e) {
          System.out.println("---------------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생 : %s - %s\n",e.getClass().getName(), e.getMessage());
          System.out.println("---------------------------------------------");
        }
      }
    Prompt.close();
  }

  static void printCommandHistory(Iterator<String> iterator) {

    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      if ((++count % 5) == 0) {
        String input = Prompt.inputString(": ");
        if (input.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }
}
