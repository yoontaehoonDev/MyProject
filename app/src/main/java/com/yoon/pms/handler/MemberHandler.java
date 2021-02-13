package com.yoon.pms.handler;

import java.util.regex.Pattern;

import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.SellerMember;
import com.yoon.util.List;
import com.yoon.util.ListIterator;
import com.yoon.util.Prompt;

public class MemberHandler {

	private boolean logCount = false;
	private int adminNumber = 0;
	private List buyerMemberList = new List();
	private List sellerMemberList = new List();
	private BuyerBoardHandler buyerBoardList = new BuyerBoardHandler();
	private SellerBoardHandler sellerBoardList = new SellerBoardHandler();
	private IntegratedBoardHandler integratedBoardList = new IntegratedBoardHandler();
	public static BuyerMember buyerMemberNumber;
	public static SellerMember sellerMemberNumber;
	public static int logStatus = -1;

	int times = 0;
	int buyerIndex = 1;
	int sellerIndex = 1;
	int flag;


	public void service() {
		while(true) {
			System.out.println("■ 메뉴 / 회원 ■");
			if(adminNumber == 1) {
				System.out.println("1. 회원 목록");
				System.out.println("2. 게시판 관리");
				System.out.println("3. 관리자 로그아웃");
			}
			else {
				if(logCount == false) {
					System.out.println("1. 회원가입");
					System.out.println("2. 로그인");
					System.out.println("3. 주문하기");
					System.out.println("4. 구매자 게시판");
					System.out.println("5. 판매자 게시판");
					System.out.println("6. 통합 게시판");
					System.out.println("7. 신고 게시판");
					System.out.println("8. 고객센터");

				}
				else if(logCount == true) {
					BuyerMember b = buyerMemberNumber;
					SellerMember s = sellerMemberNumber;
					if(s.isDivision() == true) {
						System.out.println("1. 주문 목록");
						System.out.println("2. 판매자 게시판");
						System.out.println("3. 통합 게시판");
						System.out.println("4. 신고 게시판");
						System.out.println("5. 고객센터");
						System.out.println("6. 설정");
						System.out.println("7. 로그아웃");
					}
					else if(b.isDivision() == true){
						System.out.println("1. 주문하기");
						System.out.println("2. 주문현황");
						System.out.println("3. 구매자 게시판");
						System.out.println("4. 통합 게시판");
						System.out.println("5. 신고 게시판");
						System.out.println("6. 고객센터");
						System.out.println("7. 설정");
						System.out.println("8. 로그아웃");

					}
				}
			}
			System.out.println();
			String menu = Prompt.inputString("메뉴 입력 : ");

			switch(menu) {
			case "회원가입":
				this.add();
				break;
			case "로그인":
				this.login();
				break;
			case "로그아웃":
				this.logout();
				break;
			case "관리자로그인":
				this.adminLogin();
				break;
			case "관리자로그아웃":
				this.adminLogout();
				break;
			case "회원목록":
				this.list();
				break;
			case "설정":
				this.setting();
				break;
			case "구매자게시판":
				buyerBoardList.service();
				break;
			case "판매자게시판":
				sellerBoardList.service();
				break;
			case "통합게시판":
				integratedBoardList.service();
				break;
			case "뒤로가기":
				System.out.println("초기 화면으로 전환합니다.\n");
				return;
			}
		}
	}

	public void add() {
		System.out.println("■ 메뉴 - 회원 - 회원가입 ■");

		while(true) {
			String choice = Prompt.inputString("1. 사업자  2. 고객");
			if(choice.equals("1")) {
				SellerMember s = new SellerMember();
				s.setDivision(false);
				s.setHash(s.hashCode());
				s.setNumber(sellerIndex++);
				s.setId(findBySellerId("아이디 입력 : "));
				s.setPassword(minimumLength("비밀번호 입력 : "));
				checkPassword(s.getPassword()); // 비밀번호 확인 메소드
				s.setName(Prompt.inputString("소유주 입력 : "));
				s.setEmail(emailFormat("이메일 입력 : "));
				s.setPhone(phoneFormat("핸드폰 번호 입력 : "));
				s.setBusinessName(Prompt.inputString("상호명 : "));
				s.setBusinessNumber(Prompt.inputString("사업자 등록 번호 : "));
				s.setBusinessHour(Prompt.inputString("영업 시간 : "));
				category(s); // 업종 입력 메소드
				s.setMenu(Prompt.inputString("메뉴명 입력 : "));
				s.setMenuExplaination(Prompt.inputString("메뉴 설명 : "));
				//				while(true) {
				//					m.setMenu();
				//					
				//					m.setMenu(m.setMenu(Prompt.inputString("메뉴명 : ")));
				//
				//					m.setMenu(Prompt.inputString("메뉴명 : "));
				//					if(m.getMenu().equalsIgnoreCase("a"))
				//						break;
				//					m.setMenuExplaination(Prompt.inputString("메뉴 설명 : "));
				//					times++;
				//				}
				s.setPrice(Prompt.inputInt("가격 : "));
				s.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
				this.sellerMemberList.add(s);
				break;
			}
			else if (choice.equals("2")) {
				BuyerMember b = new BuyerMember();
				b.setDivision(false);
				b.setHash(b.hashCode());
				b.setNumber(buyerIndex++);
				b.setId(findByBuyerId("아이디 입력 : "));
				b.setPassword(minimumLength("비밀번호 입력 : "));
				checkPassword(b.getPassword());
				b.setNickname(findByNickname("닉네임 입력 : "));
				b.setName(Prompt.inputString("성명 입력 : "));
				b.setEmail(emailFormat("이메일 입력 : "));
				b.setPhone(phoneFormat("핸드폰 번호 입력 : "));
				b.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
				this.buyerMemberList.add(b);
				break;
			}
			else {
				System.out.println("잘못 누르셨습니다.");
			}
		}


		System.out.println("회원가입이 완료되었습니다.\n");

	}

	public void login() {

		if(logCount == false) {
			System.out.println("■ 메뉴 - 회원 - 로그인 ■");
			String id, password;
			boolean pswCheck;
			while(true) {
				System.out.println("1. 구매자 로그인  2. 판매자 로그인\n");
				String choice = Prompt.inputString("선택 : ");

				if(choice.equals("1")) {
					if(buyerMemberList.size() == 0) {
						System.out.println("존재하는 구매 회원이 없습니다.\n");
						return;
					}
					while(true) {
						id = Prompt.inputString("아이디 입력(엔터 - 나가기) : ");
						if(id.length() == 0) {
							System.out.println("메인 메뉴로 돌아갑니다.\n");
							return;
						}

						BuyerMember idCheck = verifyBuyerId(id);

						if(idCheck != null) {
							while(true) {
								password = Prompt.inputString("비밀번호 입력(엔터 - 나가기) : ");
								if(password.length() == 0) {
									System.out.println("메인 메뉴로 돌아갑니다.\n");
									return;
								}

								pswCheck = verifyBuyerPassword(password, idCheck);

								if(pswCheck) {
									System.out.println("로그인 성공\n");
									logCount = true;
									logStatus = 0;
									buyerMemberNumber = idCheck;
									IntegratedBoardHandler.boardAuthorization = true;
									BuyerBoardHandler.boardAuthorization = true;
									buyerMemberNumber.setDivision(true);
									return;
								}
								else {
									System.out.println("비밀번호가 틀렸습니다.\n");
								}
							}
						}
						else {
							System.out.println("존재하지 않는 ID 입니다.");
						}
					}
				}
				else if (choice.equals("2")) {
					if(sellerMemberList.size() == 0) {
						System.out.println("존재하는 판매 회원이 없습니다.\n");
						return;
					}
					while(true) {
						id = Prompt.inputString("아이디 입력(엔터 - 나가기) : ");
						if(id.length() == 0) {
							System.out.println("메인 메뉴로 돌아갑니다.\n");
							return;
						}

						SellerMember idCheck = verifySellerId(id);

						if(idCheck != null) {
							while(true) {
								password = Prompt.inputString("비밀번호 입력(엔터 - 나가기) : ");
								if(password.length() == 0) {
									System.out.println("메인 메뉴로 돌아갑니다.\n");
									return;
								}

								pswCheck = verifySellerPassword(password, idCheck);

								if(pswCheck) {
									System.out.println("로그인 성공\n");
									logCount = true;
									logStatus = 1;
									sellerMemberNumber = idCheck;
									IntegratedBoardHandler.boardAuthorization = true;
									SellerBoardHandler.boardAuthorization = true;
									sellerMemberNumber.setDivision(true);
									return;
								}
								else {
									System.out.println("비밀번호가 틀렸습니다.\n");
								}
							}
						}
						else {
							System.out.println("존재하지 않는 ID 입니다.");
						}
					}
				}
			}
		}
		else {
			System.out.println("현재 회원이 없습니다.\n");
		}
	}

	public void logout() {
		logCount = false;
		if(logStatus == 1) {
			sellerMemberNumber.setDivision(false);
			SellerBoardHandler.boardAuthorization = false;
		}
		else {
			buyerMemberNumber.setDivision(false);
			BuyerBoardHandler.boardAuthorization = false;
		}

		System.out.println("로그아웃\n");
	}

	public void setting() {
		String name, choice;

		if(logCount == true) {
			if(logStatus == 1) {
				System.out.println("■ 메뉴 - 회원 - 설정 ■");
				SellerMember s = sellerMemberNumber;

				System.out.println("[설정]");
				System.out.printf("회원번호 : [%d]  ID : [%s]  Password : [%s]  소유주 : [%s]  E-Mail : [%s]  휴대폰번호 : [%s]\n", 
						s.getNumber(), s.getId(), s.getPassword(), s.getName(), s.getEmail(), s.getPhone());
				System.out.printf("상호명 : [%s]  사업자등록번호 : [%s]  영업시간 : [%s]  업종 : [%s]\n",
						s.getBusinessName(), s.getBusinessNumber(), s.getBusinessHour(), s.getCategory());


				System.out.println("[1. 정보 수정]  [2. 내 글 보기]  [3. 회원 탈퇴]  [3. 뒤로가기]\n");

				choice = Prompt.inputString("입력 : ");
				if(choice.equals("1")) {
					System.out.println("[개인정보 수정]");
					name = Prompt.inputString("정말로 수정하시겠습니까? [Y/N] : ");
					if(name.equalsIgnoreCase("y")) {
						update();
					}
					else {
						System.out.println("메뉴 - 회원 으로 돌아갑니다.\n");
					}
				}
				else if (choice.equals("2")) {
					System.out.println("[내 글 보기]");

				}

				else if (choice.equals("3")){
					System.out.println("[회원 탈퇴]");
					name = Prompt.inputString("정말로 탈퇴하시겠습니까? [Y/N] : ");
					if(name.equalsIgnoreCase("y")) {
						delete();
					}
					else {
						System.out.println("메뉴 - 회원 으로 돌아갑니다.\n");
					}
				}
				else {
					System.out.println("메뉴 - 회원 으로 돌아갑니다.\n");
				}
			}

			else {
				System.out.println("■ 메뉴 - 회원 - 설정 ■");

				BuyerMember b = buyerMemberNumber;
				System.out.println("[설정]");
				System.out.printf("내 아이디 : [%s]  내 닉네임 : [%s]  내 이름 : [%s] 내 이메일 : [%s] 내 휴대폰번호 : [%s]\n",
						b.getId(), b.getNickname(), b.getName(), b.getEmail(), b.getPhone());
				System.out.println("[1. 정보 수정]  [2. 내 글 보기]  [3. 회원 탈퇴]  [3. 뒤로가기]\n");
				choice = Prompt.inputString("입력 : ");
				if(choice.equals("1")) {
					System.out.println("[개인정보 수정]");
					name = Prompt.inputString("정말로 수정하시겠습니까? [Y/N] : ");
					if(name.equalsIgnoreCase("y")) {
						update();
					}
					else {
						System.out.println("메뉴 / 회원 으로 돌아갑니다.\n");
					}
				}
				else if (choice.equals("2")) {
					System.out.println("[내 글 보기]");

				}

				else if (choice.equals("3")){
					System.out.println("[회원 탈퇴]");
					name = Prompt.inputString("정말로 탈퇴하시겠습니까? [Y/N] : ");
					if(name.equalsIgnoreCase("y")) {
						delete();
					}
					else {
						System.out.println("메뉴 - 회원 으로 돌아갑니다.\n");
					}
				}
				else {
					System.out.println("메뉴 - 회원 으로 돌아갑니다.\n");
				}
			}
		}
		else {
			System.out.println("로그인 후 이용 가능합니다.\n");
		}
	}
	public void update() {

		BuyerMember b = buyerMemberNumber;
		SellerMember s = sellerMemberNumber;

		if(s.isDivision() == true) {
			s.setId(findByBuyerId("수정할 ID : "));
			s.setPassword(minimumLength("수정할 Password : "));
			s.setName(Prompt.inputString("수정할 이름 : "));
			s.setEmail(emailFormat("수정할 E-Mail : "));
			s.setPhone(phoneFormat("수정할 핸드폰 번호 : \n"));
			System.out.println("가게 관련 정보 변경은 고객센터(1542-1542)로 연락 바랍니다.");
			SellerBoardHandler.changeCount = 1;
		}
		else if(b.isDivision() == true){
			b.setId(findByBuyerId("수정할 ID : "));
			b.setPassword(minimumLength("수정할 Password : "));
			b.setName(Prompt.inputString("수정할 이름 : "));
			b.setNickname(findByNickname("수정할 닉네임 : "));
			b.setEmail(emailFormat("수정할 E-Mail : "));
			b.setPhone(phoneFormat("수정할 핸드폰 번호 : "));
			BuyerBoardHandler.changeCount = 1;
		}

		System.out.println("[개인정보 수정 완료]\n");

	}

	private void delete() {
		if(logCount == true) {
			if(logStatus == 1) {
				SellerMember seller = sellerMemberNumber;
				sellerMemberList.delete(seller);
			}
			else {
				BuyerMember buyer = buyerMemberNumber;
				buyerMemberList.delete(buyer);
			}
			logCount = false;
			logStatus = -1;
			System.out.println("계정 탈퇴 처리가 완료되었습니다. 그동안 이용해주셔서 감사합니다.\n");
		}
		else {
			int num = Prompt.inputInt("탈퇴시킬 회원 번호를 입력하세요 : ");
			if(flag == 0) {
				BuyerMember buyer = findByBuyerNum(num);

				if(buyer == null) {
					System.out.println("회원 번호가 존재하지 않습니다.\n");
					return;
				}
				else {
					buyerMemberList.delete(buyer);
					System.out.println("회원 탈퇴 처리가 완료되었습니다.\n");
				}
			}

			else {
				SellerMember seller = findBySellerNum(num);

				if(seller == null) {
					System.out.println("회원 번호가 존재하지 않습니다.\n");
					return;
				}
				else {
					sellerMemberList.delete(seller);
					System.out.println("회원 탈퇴 처리가 완료되었습니다.\n");
				}
			}

		}
	}

	public void adminLogin() {
		if(logCount == false && adminNumber == 0) {
			System.out.println("■ 메뉴 - 관리자 ■");
			adminNumber = 1;
			System.out.println("관리자 로그인\n");
		}
	}
	public void adminLogout() {
		if(adminNumber == 1) {
			adminNumber = 0;
			System.out.println("관리자 로그아웃\n");
		}
		else {
			System.out.println("접근 권한이 없습니다.\n");
		}
	}

	public void list() {
		if(adminNumber == 1) {
			System.out.println("■ 메뉴 - 관리자 - 회원목록 ■");
			if(sellerMemberList.size() == 0 && buyerMemberList.size() == 0) {
				System.out.println("존재하는 회원이 없습니다.");
				return;
			}
			System.out.println("1. 구매자 회원 목록  2. 판매자 회원 목록\n");
			String choice = Prompt.inputString("선택 : ");


			if(choice.equals("1")) {
				System.out.println("[구매자 회원 목록]");
				ListIterator buyerIterator = new ListIterator(this.buyerMemberList);
				if(buyerMemberList.size() == 0) {
					System.out.println("회원이 없습니다. \n메뉴로 돌아갑니다.\n");
					return;
				}
				while(buyerIterator.hasNext()) {
					BuyerMember b = (BuyerMember)buyerIterator.next();
					if(b.isDivision() == false) {
						System.out.printf("회원번호 : [%d]  ID : [%s]  Password : [%s]  닉네임 : [%s]  이름 : [%s]  E-Mail : [%s]  휴대폰번호 : [%s]  가입일자 : [%s]\n", 
								b.getNumber(), b.getId(), b.getPassword(), b.getNickname(), b.getName(), b.getEmail(), b.getPhone(), b.getRegisteredDate());
					}
				}
				System.out.println();
				System.out.println("1. [회원 강제탈퇴] 2. [나가기]");
				String setting = Prompt.inputString("번호를 선택하세요 : ");
				if(setting.equals("1")) {
					flag = 0;
					delete();
				}
				else {
					System.out.println("메뉴로 돌아갑니다.\n");
					return;
				}
			}

			else if(choice.equals("2")) {
				System.out.println("[판매자 회원 목록]");
				ListIterator sellerIterator = new ListIterator(this.sellerMemberList);
				if(sellerMemberList.size() == 0) {
					System.out.println("회원이 없습니다. \n메뉴로 돌아갑니다.\n");
					return;
				}
				while(sellerIterator.hasNext()) {
					SellerMember s = (SellerMember)sellerIterator.next();
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
					delete();
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

	private String findByBuyerId(String message) {
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
				Object[] list = buyerMemberList.toArray();
				for(Object obj : list) {
					BuyerMember b = (BuyerMember) obj;
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

	private String findBySellerId(String message) {
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

	private String findByNickname(String message) {
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
				Object[] list = buyerMemberList.toArray();
				for(Object obj : list) {
					BuyerMember b = (BuyerMember) obj;
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

	private BuyerMember verifyBuyerId(String id) {
		Object[] list = buyerMemberList.toArray();
		for(Object obj : list) {
			BuyerMember b = (BuyerMember)obj;
			if(b.getId().equalsIgnoreCase(id)) {
				return b;
			}
		}
		return null;
	}

	private SellerMember verifySellerId(String id) {
		Object[] list = sellerMemberList.toArray();
		for(Object obj : list) {
			SellerMember s = (SellerMember)obj;
			if(s.getId().equalsIgnoreCase(id)) {
				return s;
			}
		}
		return null;
	}

	private boolean verifyBuyerPassword(String password, BuyerMember b) {
		if(password.equals(b.getPassword())) {
			return true;
		}
		return false;
	}

	private boolean verifySellerPassword(String password, SellerMember s) {
		if(password.equals(s.getPassword())) {
			return true;
		}
		return false;
	}

	private void checkPassword(String originalPassword) {
		while(true) {
			String password = minimumLength("비밀번호 확인 : ");
			if(password.equals(originalPassword)) {
				return;
			}
			else {
				System.out.println("현재 비밀번호와 일치하지 않습니다.\n");
			}
		}
	}

	private BuyerMember findByBuyerNum(int buyerMemberNum) {
		Object[] list = buyerMemberList.toArray();
		for(Object obj : list) {
			BuyerMember b = (BuyerMember)obj;
			if(buyerMemberNum == b.getNumber())
				return b;
		}
		return null;
	}

	private SellerMember findBySellerNum(int sellerMemberNum) {
		Object[] list = sellerMemberList.toArray();
		for(Object obj : list) {
			SellerMember s = (SellerMember)obj;
			if(sellerMemberNum == s.getNumber())
				return s;
		}
		return null;
	}

	private String minimumLength(String name) {
		while(true) {
			String info = Prompt.inputString(name);
			if(info.length() < 8) {
				System.out.println();
				System.out.println("8자리 이상 입력하세요.");
			}
			else {
				return info;
			}
		}
	}

	private String emailFormat(String name) {
		while(true) {
			String pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
			String email = Prompt.inputString(name);
			if(Pattern.matches(pattern, email)) {
				return email;
			}
			else {
				System.out.println("E-Mail 형식(abc@abc.abc)이 아닙니다.");
			}
		}
	}

	private String phoneFormat(String name) {
		while(true) {
			String pattern = "^\\d{3}-\\d{3,4}-\\d{4}$";
			String phone = Prompt.inputString(name);
			if(Pattern.matches(pattern, phone)) {
				return phone;
			}
			else {
				System.out.println("전화번호 형식(000-0000-0000)이 아닙니다.");
			}
		}
	}
	public void category(SellerMember s) {
		System.out.println("[업종 선택]");
		Loop:
			while(true) {
				System.out.println("1. 한식 2. 양식 3. 일식 4. 중식 5. 분식");
				System.out.println("6. 치킨 7. 피자 8. 디저트 9. 야식\n");
				String choice = Prompt.inputString("번호 선택 : ");

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
		System.out.println("업종 선택 완료\n");
	}

}
