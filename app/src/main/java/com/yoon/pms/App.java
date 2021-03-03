package com.yoon.pms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yoon.context.ApplicationContextListener;
import com.yoon.pms.domain.Board;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Comment;
import com.yoon.pms.domain.Log;
import com.yoon.pms.domain.Menu;
import com.yoon.pms.domain.Order;
import com.yoon.pms.domain.SellerMember;
import com.yoon.pms.handler.BuyerBoardAddHandler;
import com.yoon.pms.handler.BuyerBoardDetailHandler;
import com.yoon.pms.handler.BuyerBoardListHandler;
import com.yoon.pms.handler.BuyerBoardMyCommentListHandler;
import com.yoon.pms.handler.BuyerBoardMyListHandler;
import com.yoon.pms.handler.BuyerBoardReturnHandler;
import com.yoon.pms.handler.BuyerBoardServiceHandler;
import com.yoon.pms.handler.Command;
import com.yoon.pms.handler.IntegratedBoardAddHandler;
import com.yoon.pms.handler.IntegratedBoardListHandler;
import com.yoon.pms.handler.IntegratedBoardMyListHandler;
import com.yoon.pms.handler.IntegratedBoardReturnHandler;
import com.yoon.pms.handler.IntegratedBoardServiceHandler;
import com.yoon.pms.handler.IntegratedDetailBoardHandler;
import com.yoon.pms.handler.LogHandler;
import com.yoon.pms.handler.MemberAddHandler;
import com.yoon.pms.handler.MemberAdminLoginHandler;
import com.yoon.pms.handler.MemberAdminLogoutHandler;
import com.yoon.pms.handler.MemberListHandler;
import com.yoon.pms.handler.MemberLoginHandler;
import com.yoon.pms.handler.MemberLogoutHandler;
import com.yoon.pms.handler.MemberSettingHandler;
import com.yoon.pms.handler.MemberValidatorHandler;
import com.yoon.pms.handler.MenuAddHandler;
import com.yoon.pms.handler.MenuDeleteHandler;
import com.yoon.pms.handler.MenuMyListHandler;
import com.yoon.pms.handler.MenuReturnHandler;
import com.yoon.pms.handler.MenuServiceHandler;
import com.yoon.pms.handler.MenuUpdateHandler;
import com.yoon.pms.handler.OrderAddHandler;
import com.yoon.pms.handler.SellerBoardAddHandler;
import com.yoon.pms.handler.SellerBoardDetailHandler;
import com.yoon.pms.handler.SellerBoardListHandler;
import com.yoon.pms.handler.SellerBoardMyCommentListHandler;
import com.yoon.pms.handler.SellerBoardMyListHandler;
import com.yoon.pms.handler.SellerBoardReturnHandler;
import com.yoon.pms.handler.SellerBoardServiceHandler;
import com.yoon.pms.handler.Temp;
import com.yoon.util.CsvObject;
import com.yoon.util.Prompt;

public class App {

	static ArrayDeque<String> commandStack = new ArrayDeque<>();
	static LinkedList<String> commandQueue = new LinkedList<>();
	static public int location = -1;

	List<ApplicationContextListener> listeners = new ArrayList<>();

	LinkedList<BuyerMember> buyerMemberList = new LinkedList<>();
	LinkedList<SellerMember> sellerMemberList = new LinkedList<>();
	LinkedList<Comment> buyerCommentList = new LinkedList<>();
	LinkedList<Comment> sellerCommentList = new LinkedList<>();
	LinkedList<Comment> integratedCommentList = new LinkedList<>();
	LinkedList<Board> buyerBoardList = new LinkedList<>();
	LinkedList<Board> sellerBoardList = new LinkedList<>();
	LinkedList<Board> integratedBoardList = new LinkedList<>();
	LinkedList<Log> logList = new LinkedList<>();
	LinkedList<Menu> menuList = new LinkedList<>();
	LinkedList<Order> orderList = new LinkedList<>();

	File buyerMemberListFile = new File("buyerMemberList.json");
	File sellerMemberListFile = new File("sellerMemberList.json");
	File buyerCommentListFile = new File("buyerCommentList.json");
	File sellerCommentListFile = new File("sellerCommentList.json");
	File integratedCommentListFile = new File("integratedCommentList.json");
	File buyerBoardListFile = new File("buyerBoardList.json");
	File sellerBoardListFile = new File("sellerBoardList.json");
	File integratedBoardListFile = new File("integratedBoardList.json");
	File logListFile = new File("logList.json");
	File menuListFile = new File("menuList.json");
	File orderListFile = new File("orderList.json");

	public static void main(String[] args) {
		App app = new App();

		app.service();
	}

	public void addApplicationContextListener(ApplicationContextListener listener) {
		listeners.add(listener);
	}

	public void removeApplicationContextListener(ApplicationContextListener listener) {
		listeners.remove(listener);
	}

	public void service() {

		notifyOnServiceStarted();

		loadObjects(buyerMemberListFile, buyerMemberList, BuyerMember.class);
		loadObjects(sellerMemberListFile, sellerMemberList, SellerMember.class);
		loadObjects(buyerCommentListFile, buyerCommentList, Comment.class);
		loadObjects(sellerCommentListFile, sellerCommentList, Comment.class);
		loadObjects(integratedCommentListFile, integratedCommentList, Comment.class);
		loadObjects(buyerBoardListFile, buyerBoardList, Board.class);
		loadObjects(sellerBoardListFile, sellerBoardList, Board.class);
		loadObjects(integratedBoardListFile, integratedBoardList, Board.class);
		loadObjects(logListFile, logList, Log.class);
		loadObjects(menuListFile, menuList, Menu.class);
		loadObjects(orderListFile, orderList, Order.class);

		HashMap<String, Command> commandMap = new HashMap<>();
		MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(buyerMemberList, sellerMemberList);

		commandMap.put("관리자로그인", new MemberAdminLoginHandler(buyerMemberList, sellerMemberList));

		loop:
			while(true) {
				if(location == -1) /* 메인 메뉴 */ {
					System.out.println("■ 메뉴 ■");
					System.out.println("1. 회원가입");
					System.out.println("2. 로그인");
					System.out.println("3. 구매자 게시판");
					System.out.println("4. 판매자 게시판");
					System.out.println("5. 통합 게시판");
					System.out.println("6. 고객센터");
					System.out.println("7. 사이트 종료\n");
					commandMap.put("1", new MemberAddHandler(buyerMemberList, sellerMemberList, memberValidatorHandler));
					commandMap.put("2", new MemberLoginHandler(buyerMemberList, sellerMemberList, logList));
					commandMap.put("3", new BuyerBoardServiceHandler(buyerBoardList, buyerCommentList));
					commandMap.put("4", new SellerBoardServiceHandler(sellerBoardList, sellerCommentList));
					commandMap.put("5", new IntegratedBoardServiceHandler(integratedBoardList, integratedCommentList));
					commandMap.put("6", new Temp());
				}
				else if(location == 0) /*구매자 로그인 상태 */ {
					System.out.println("■ 메뉴 - 구매자 ■");
					System.out.println("1. 주문하기");
					System.out.println("2. 구매자 게시판");
					System.out.println("3. 통합 게시판");
					System.out.println("4. 설정");
					System.out.println("5. 고객센터");
					System.out.println("6. 로그아웃");
					System.out.println("7. 종료\n");
					commandMap.put("1", new OrderAddHandler(orderList, buyerMemberList, sellerMemberList));
					commandMap.put("2", new BuyerBoardServiceHandler(buyerBoardList, buyerCommentList));
					commandMap.put("3", new IntegratedBoardServiceHandler(integratedBoardList, integratedCommentList));
					commandMap.put("4", new MemberSettingHandler(buyerMemberList, sellerMemberList, buyerBoardList, buyerCommentList, sellerBoardList, sellerCommentList, memberValidatorHandler));
					commandMap.put("5", new Temp());
					commandMap.put("6", new MemberLogoutHandler(buyerMemberList, sellerMemberList, logList));
				}
				else if(location == 1) /*판매자 로그인 상태 */ {
					System.out.println("■ 메뉴 - 판매자 ■");
					System.out.println("1. 메뉴추가");
					System.out.println("2. 주문확인");
					System.out.println("3. 판매자 게시판");
					System.out.println("4. 통합 게시판");
					System.out.println("5. 설정");
					System.out.println("6. 고객센터");
					System.out.println("7. 로그아웃");
					System.out.println("8. 종료\n");
					commandMap.put("1", new MenuServiceHandler(menuList));
					commandMap.put("2", new Temp());
					commandMap.put("3", new SellerBoardServiceHandler(sellerBoardList, sellerCommentList));
					commandMap.put("4", new IntegratedBoardServiceHandler(integratedBoardList, integratedCommentList));
					commandMap.put("5", new MemberSettingHandler(buyerMemberList, sellerMemberList, buyerBoardList, buyerCommentList, sellerBoardList, sellerCommentList, memberValidatorHandler));
					commandMap.put("6", new Temp());
					commandMap.put("7", new MemberLogoutHandler(buyerMemberList, sellerMemberList, logList));
				}
				else if(location == 2) /* 구매게시판 관리 */ {
					System.out.println("■ 메뉴 - 구매자 게시판 ■");
					System.out.println("1. 게시글 쓰기");
					System.out.println("2. 게시글 목록");
					System.out.println("3. 게시글 보기");
					System.out.println("4. 내 글 보기");
					System.out.println("5. 내 댓글 보기");
					System.out.println("6. 나가기\n");
					commandMap.put("1", new BuyerBoardAddHandler(buyerBoardList, buyerCommentList));
					commandMap.put("2", new BuyerBoardListHandler(buyerBoardList, buyerCommentList));
					commandMap.put("3", new BuyerBoardDetailHandler(buyerBoardList, buyerCommentList));
					commandMap.put("4", new BuyerBoardMyListHandler(buyerBoardList, buyerCommentList));
					commandMap.put("5", new BuyerBoardMyCommentListHandler(buyerBoardList, buyerCommentList));
					commandMap.put("6", new BuyerBoardReturnHandler(buyerBoardList, buyerCommentList));
				}
				else if(location == 3) /* 판매게시판 관리 */ {
					System.out.println("■ 메뉴 - 판매자 게시판 ■");
					System.out.println("1. 게시글 쓰기");
					System.out.println("2. 게시글 목록");
					System.out.println("3. 게시글 보기");
					System.out.println("4. 내 글 보기");
					System.out.println("5. 내 댓글 보기");
					System.out.println("6. 나가기\n");
					commandMap.put("1", new SellerBoardAddHandler(sellerBoardList, sellerCommentList));
					commandMap.put("2", new SellerBoardListHandler(sellerBoardList, sellerCommentList));
					commandMap.put("3", new SellerBoardDetailHandler(sellerBoardList, sellerCommentList));
					commandMap.put("4", new SellerBoardMyListHandler(sellerBoardList, sellerCommentList));
					commandMap.put("5", new SellerBoardMyCommentListHandler(sellerBoardList, sellerCommentList));
					commandMap.put("6", new SellerBoardReturnHandler(sellerBoardList, sellerCommentList));

				}
				else if(location == 4) /* 통합게시판 관리 */ {
					System.out.println("■ 메뉴 - 통합 게시판 ■");
					System.out.println("1. 게시글 쓰기");
					System.out.println("2. 게시글 목록");
					System.out.println("3. 게시글 보기");
					System.out.println("4. 내가 쓴 글 보기");
					System.out.println("5. 나가기\n");
					commandMap.put("1", new IntegratedBoardAddHandler(integratedBoardList, integratedCommentList));
					commandMap.put("2", new IntegratedBoardListHandler(integratedBoardList, integratedCommentList));
					commandMap.put("3", new IntegratedDetailBoardHandler(integratedBoardList, integratedCommentList));
					commandMap.put("4", new IntegratedBoardMyListHandler(integratedBoardList, integratedCommentList));
					commandMap.put("5", new IntegratedBoardReturnHandler(integratedBoardList, integratedCommentList));
				}
				else if(location == 5) /* 판매자 메뉴 관리 */ {
					System.out.println("■ 메뉴 - 판매자 메뉴 ■");
					System.out.println("1. 메뉴 추가");
					System.out.println("2. 메뉴 목록");
					System.out.println("3. 메뉴 변경");
					System.out.println("4. 메뉴 삭제");
					System.out.println("5. 나가기\n");
					commandMap.put("1", new MenuAddHandler(menuList));
					commandMap.put("2", new MenuMyListHandler(menuList));
					commandMap.put("3", new MenuUpdateHandler(menuList));
					commandMap.put("4", new MenuDeleteHandler(menuList));
					commandMap.put("5", new MenuReturnHandler(menuList));
				}
				else if(location == 6) /* 관리자 */ {
					System.out.println("■ 메뉴 - 관리자 메뉴 ■");
					System.out.println("1. 회원 관리");
					System.out.println("2. 회원 로그");
					System.out.println("3. 로그아웃\n");
					commandMap.put("1", new MemberListHandler(buyerMemberList, sellerMemberList));
					commandMap.put("2", new LogHandler(logList));
					commandMap.put("3", new MemberAdminLogoutHandler(buyerMemberList, sellerMemberList));

				}
				else if(location == 7) /* 구매자 주문 관리 */ {
					System.out.println("■ 메뉴 - 주문 메뉴 ■");
					System.out.println("1. 주문하기");
					System.out.println("2. 주문 취소");
					System.out.println("3. 나가기\n");

					//					commandMap.put("1", new OrderAddHandler(orderList));
					//          commandMap.put("2", new OrderAddHandler(orderList));
					//          commandMap.put("3", new OrderAddHandler(orderList));
					//          commandMap.put("4", new OrderAddHandler(orderList));
				}


				else if(location == 8) /* 판매자 주문 관리 */ {
					System.out.println("■ 메뉴 - 주문 관리 ■");
					System.out.println("1. 주문 확인");
					System.out.println("2. 주문 취소");
				}


				//				else if(location == 9) {
				//
				//				}


				String menu = Prompt.inputString("메뉴 입력 : ");
				System.out.println();


				commandStack.push(menu);
				commandQueue.offer(menu);
				try {
					switch(menu) {
					case "shistory":
						printCommandHistory(commandStack.iterator());
						break;
					case "qhistory":
						printCommandHistory(commandQueue.iterator());
						break;
					case "종료":
						MemberLogoutHandler m = new MemberLogoutHandler(buyerMemberList, sellerMemberList, logList);
						m.service();
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

		saveObjects(buyerMemberListFile, buyerMemberList);
		saveObjects(sellerMemberListFile, sellerMemberList);
		saveObjects(buyerCommentListFile, buyerCommentList);
		saveObjects(sellerCommentListFile, sellerCommentList);
		saveObjects(integratedCommentListFile, integratedCommentList);
		saveObjects(buyerBoardListFile, buyerBoardList);
		saveObjects(sellerBoardListFile, sellerBoardList);
		saveObjects(integratedBoardListFile, integratedBoardList);
		saveObjects(logListFile, logList);
		saveObjects(menuListFile, menuList);
		saveObjects(orderListFile, orderList);


		notifyOnServiceStopped();
		Prompt.close();
	}


	void notifyOnServiceStarted() {
		for(ApplicationContextListener listener : listeners) {
			listener.contextInitialized();
		}
	}

	void notifyOnServiceStopped() {
		for(ApplicationContextListener listener : listeners) {
			listener.contextDestroyed();
		}
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

	static <T> void loadObjects(File file, List<T> list, Class<T> elementType) {
		try(BufferedReader in = new BufferedReader(new FileReader(file))) {

			StringBuilder strBuilder = new StringBuilder();
			String str = null;
			while((str = in.readLine()) != null) {
				strBuilder.append(str);
			}


			Type collectionType = TypeToken.getParameterized(Collection.class, elementType).getType();
			Collection<T> collection = new Gson().fromJson(strBuilder.toString(), collectionType);

			list.addAll(collection);

			System.out.printf("%s 파일 데이터 로딩\n", file.getName());
		}
		catch (Exception e) {
			System.out.printf("%s 파일 데이터 로딩 실패\n", file.getName());
			e.printStackTrace();
		}
	}

	static <T extends CsvObject> void saveObjects(File file, List<T> list) {
		try(BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
			out.write(new Gson().toJson(list));
			System.out.printf("%s 파일 데이터 로딩\n", file.getName());
		}
		catch (Exception e) {
			System.out.printf("%s 파일 데이터 로딩 실패\n", file.getName());
			e.printStackTrace();
		}
	}

}
