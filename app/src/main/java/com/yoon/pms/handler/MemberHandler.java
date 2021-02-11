package com.yoon.pms.handler;

import java.util.regex.Pattern;

import com.yoon.pms.domain.Member;
import com.yoon.util.List;
import com.yoon.util.ListIterator;
import com.yoon.util.Prompt;

public class MemberHandler {

	private boolean logCount = false;
	private int adminNumber = 0;
	private List memberList = new List();
	private BuyerBoardHandler buyerBoardList = new BuyerBoardHandler();
	private SellerBoardHandler sellerBoardList = new SellerBoardHandler();
	public static Member memberNumber;

	int times = 0;
	int buyerIndex = 1;
	int sellerIndex = 1;
	int memberHash = 1;
	int buyerCount = 0;
	int sellerCount = 0;

	@Override
	public int hashCode() {
		return memberHash++;
	}

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
					System.out.println("6. 신고 게시판");
					System.out.println("7. 고객센터");

				}
				else if(logCount == true) {
					Member m = memberNumber;
					if(m.isDivision() == true) {
						System.out.println("1. 주문 목록");
						System.out.println("2. 판매자 게시판");
						System.out.println("3. 신고 게시판");
						System.out.println("4. 고객센터");
						System.out.println("5. 설정");
						System.out.println("6. 로그아웃");
					}
					else {
						System.out.println("1. 주문하기");
						System.out.println("2. 주문현황");
						System.out.println("3. 구매자 게시판");
						System.out.println("4. 신고 게시판");
						System.out.println("5. 고객센터");
						System.out.println("6. 설정");
						System.out.println("7. 로그아웃");

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
			case "뒤로가기":
				System.out.println("초기 화면으로 전환합니다.\n");
				return;
			}
		}
	}

	public void add() {
		System.out.println("■ 메뉴 - 회원 - 회원가입 ■");
		Member m = new Member();
		while(true) {
			String choice = Prompt.inputString("1. 사업자  2. 고객");
			if(choice.equals("1")) {
				m.setDivision(true);
				m.setHash(hashCode());
				m.setSellerNumber(sellerIndex++);
				m.setId(findById("아이디 입력 : "));
				m.setPassword(minimumLength("비밀번호 입력 : "));
				checkPassword(m.getPassword());
				m.setName(Prompt.inputString("소유주 입력 : "));
				m.setEmail(emailFormat("이메일 입력 : "));
				m.setPhone(phoneFormat("핸드폰 번호 입력 : "));
				m.setBusinessName(Prompt.inputString("상호명 : "));
				m.setBusinessNumber(Prompt.inputString("사업자 등록 번호 : "));
				m.setBusinessHour(Prompt.inputString("영업 시간 : "));
				category(m); // 업종 입력 메소드
				m.setMenu(Prompt.inputString("메뉴명 입력 : "));
				m.setMenuExplaination(Prompt.inputString("메뉴 설명 : "));
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
				m.setPrice(Prompt.inputInt("가격 : "));
				m.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
				sellerCount++;
				break;
			}
			else if (choice.equals("2")) {
				m.setDivision(false);
				m.setHash(hashCode());
				m.setBuyerNumber(buyerIndex++);
				m.setId(findById("아이디 입력 : "));
				m.setPassword(minimumLength("비밀번호 입력 : "));
				checkPassword(m.getPassword());
				m.setNickname(findByNickname("닉네임 입력 : "));
				m.setName(Prompt.inputString("성명 입력 : "));
				m.setEmail(emailFormat("이메일 입력 : "));
				m.setPhone(phoneFormat("핸드폰 번호 입력 : "));
				m.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));
				buyerCount++;
				break;
			}
			else {
				System.out.println("잘못 누르셨습니다.");
			}
		}

		this.memberList.add(m);
		System.out.println("회원가입이 완료되었습니다.\n");

	}

	public void login() {


		if(memberList.size != 0 && logCount == false) {
			System.out.println("■ 메뉴 - 회원 - 로그인 ■");
			int flag;
			while(true) {
				System.out.println("1. 구매자 로그인  2. 판매자 로그인\n");
				String choice = Prompt.inputString("선택 : ");
				if(choice.equals("1")) {
					flag = 1;
					break;
				}
				else if (choice.equals("2")) {
					flag = 0;
					break;
				}
				else {
					System.out.println("잘못 입력하셨습니다.");
				}
			}


			while(true) {
				if(flag == 0 && sellerCount == 0) {
					System.out.println("판매자 회원이 존재하지 않습니다.\n");
					break;
				}
				else if(flag == 1 && buyerCount == 0) {
					System.out.println("구매자 회원이 존재하지 않습니다.\n");
					break;
				}

				String id, password;
				boolean pswCheck;

				id = Prompt.inputString("아이디 입력(엔터 - 나가기) : ");
				if(id.length() == 0) {
					System.out.println("메인 메뉴로 돌아갑니다.\n");
					return;
				}

				Member idCheck = verifyId(id);

				if(idCheck != null) {
					while(true) {
						password = Prompt.inputString("비밀번호 입력(엔터 - 나가기) : ");
						if(password.length() == 0) {
							System.out.println("메인 메뉴로 돌아갑니다.\n");
							return;
						}

						pswCheck = verifyPassword(password, idCheck);

						if(pswCheck) {
							System.out.println("로그인 성공\n");
							logCount = true;
							if(flag == 1) {
								BuyerBoardHandler.boardAuthorization = true;
							}
							else {
								SellerBoardHandler.boardAuthorization = true;
							}

							memberNumber = idCheck;
							return;
						}
						else {
							System.out.println("비밀번호가 틀렸습니다.\n");
						}
					}
				}
				else {
					System.out.println("존재하지 않는 아이디 입니다.\n");
				}
			}
		}
		else {
			System.out.println("현재 회원이 없습니다.\n");
		}
	}

	public void logout() {
		logCount = false;
		memberNumber = null;
		BuyerBoardHandler.boardAuthorization = false;
		BuyerBoardHandler.likeCount = 0;
		System.out.println("로그아웃\n");
	}

	public void setting() {
		if(logCount == true) {
			System.out.println("■ 메뉴 - 회원 - 설정 ■");
			String name;
			Member m = memberNumber;
			System.out.println("[설정]");
			System.out.printf("내 아이디 : [%s]  내 닉네임 : [%s]  내 이름 : [%s] 내 이메일 : [%s] 내 휴대폰번호 : [%s]\n",
					m.getId(), m.getNickname(), m.getName(), m.getEmail(), m.getPhone());
			System.out.println("[1. 정보 수정]  [2. 내 글 보기]  [3. 회원 탈퇴]  [3. 뒤로가기]\n");
			String match = Prompt.inputString("입력 : ");
			if(match.equals("1")) {
				System.out.println("[개인정보 수정]");
				name = Prompt.inputString("정말로 수정하시겠습니까? [Y/N] : ");
				if(name.equalsIgnoreCase("y")) {
					update();
				}
				else {
					System.out.println("메뉴 / 회원 으로 돌아갑니다.\n");
				}
			}
			else if (match.equals("2")) {
				System.out.println("[내 글 보기]");

			}

			else if (match.equals("3")){
				System.out.println("[회원 탈퇴]");
				name = Prompt.inputString("정말로 탈퇴하시겠습니까? [Y/N] : ");
				if(name.equalsIgnoreCase("y")) {
					delete();
				}
				else {
					System.out.println("메뉴 / 회원 으로 돌아갑니다.\n");
				}
			}
			else {
				System.out.println("메뉴 / 회원 으로 돌아갑니다.\n");
			}
		}
		else {
			System.out.println("로그인 후 이용 가능합니다.\n");
		}
	}
	public void update() {

		Member m = memberNumber;

		if(m.isDivision() == true) {
			m.setId(findById("수정할 ID : "));
			m.setPassword(minimumLength("수정할 Password : "));
			m.setName(Prompt.inputString("수정할 이름 : "));
			m.setEmail(emailFormat("수정할 E-Mail : "));
			m.setPhone(phoneFormat("수정할 핸드폰 번호 : \n"));
			System.out.println("가게 관련 정보 변경은 고객센터(1542-1542)로 연락 바랍니다.");
		}
		else {
			m.setId(findById("수정할 ID : "));
			m.setPassword(minimumLength("수정할 Password : "));
			m.setName(Prompt.inputString("수정할 이름 : "));
			m.setNickname(findByNickname("수정할 닉네임 : "));
			m.setEmail(emailFormat("수정할 E-Mail : "));
			m.setPhone(phoneFormat("수정할 핸드폰 번호 : "));
			BuyerBoardHandler.changeCount = 1;
		}


		System.out.println("[개인정보 수정 완료]\n");


	}


	private void delete() {
		if(logCount == true) {
			Member member = memberNumber;
			memberList.delete(member);
			if(member.isDivision() == true) {
				this.sellerCount--;
			}
			else {
				this.buyerCount--;
			}
			logCount = false;

			System.out.println("계정 탈퇴 처리가 완료되었습니다. 그동안 이용해주셔서 감사합니다.\n");
		}
		else {
			int num = Prompt.inputInt("탈퇴시킬 회원 번호를 입력하세요 : ");
			Member member = findByNum(num);

			if(member == null) {
				System.out.println("회원 번호가 존재하지 않습니다.\n");
				return;
			}
			else {
				memberList.delete(member);
				if(member.isDivision() == true) {
					this.sellerCount--;
				}
				else {
					this.buyerCount--;
				}
				System.out.println("회원 탈퇴 처리가 완료되었습니다.\n");
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
			System.out.println("1. 구매자 회원 목록  2. 판매자 회원 목록\n");
			String choice = Prompt.inputString("선택 : ");
			ListIterator iterator = new ListIterator(this.memberList);

			if(choice.equals("1")) {
				// 구매자 회원만 보여주려면, 
				System.out.println("[구매자 회원 목록]");
				if(this.buyerCount == 0) {
					System.out.println("회원이 없습니다. \n메뉴로 돌아갑니다.\n");
					return;
				}
				while(iterator.hasNext()) {
					Member m = (Member)iterator.next();
					if(m.isDivision() == false) {
						System.out.printf("회원번호 : [%d]  ID : [%s]  Password : [%s]  닉네임 : [%s]  이름 : [%s]  E-Mail : [%s]  휴대폰번호 : [%s]  가입일자 : [%s]\n", 
								m.getBuyerNumber(), m.getId(), m.getPassword(), m.getNickname(), m.getName(), m.getEmail(), m.getPhone(), m.getRegisteredDate());
					}
				}
				System.out.println();
				System.out.println("1. [회원 강제탈퇴] 2. [나가기]");
				String setting = Prompt.inputString("번호를 선택하세요 : ");
				if(setting.equals("1")) {
					delete();
				}
				else {
					System.out.println("메뉴로 돌아갑니다.\n");
					return;
				}
			}

			else if(choice.equals("2")) {
				System.out.println("[판매자 회원 목록]");
				if(this.sellerCount == 0) {
					System.out.println("회원이 없습니다. \n메뉴로 돌아갑니다.\n");
					return;
				}
				while(iterator.hasNext()) {
					Member m = (Member)iterator.next();
					if(m.isDivision() == true) {
						System.out.printf("회원번호 : [%d]  ID : [%s]  Password : [%s]  소유주 : [%s]  E-Mail : [%s]  휴대폰번호 : [%s]\n", 
								m.getSellerNumber(), m.getId(), m.getPassword(), m.getName(), m.getEmail(), m.getPhone());
						System.out.printf("상호명 : [%s]  사업자등록번호 : [%s]  영업시간 : [%s]  업종 : [%s]\n",
								m.getBusinessName(), m.getBusinessNumber(), m.getBusinessHour(), m.getCategory());
					}
				}
				System.out.println();
				System.out.println("1. [회원 강제탈퇴] 2. [나가기]");
				String setting = Prompt.inputString("번호를 선택하세요 : ");
				if(setting.equals("1")) {
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

	private String findById(String message) {
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
				Object[] list = memberList.toArray();
				for(Object obj : list) {
					Member m = (Member) obj;
					if(id.equalsIgnoreCase(m.getId())) {
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
				Object[] list = memberList.toArray();
				for(Object obj : list) {
					Member m = (Member) obj;
					if(nickname.equalsIgnoreCase(m.getNickname())) {
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

	private Member verifyId(String id) {
		Object[] list = memberList.toArray();
		for(Object obj : list) {
			Member m = (Member)obj;
			if(m.getId().equalsIgnoreCase(id)) {
				return m;
			}
		}
		return null;
	}

	private boolean verifyPassword(String password, Member m) {
		if(password.equals(m.getPassword())) {
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

	private Member findByNum(int memberNum) {
		Object[] list = memberList.toArray();
		for(Object obj : list) {
			Member m = (Member)obj;
			if(m.isDivision() == true) {
				if(memberNum == m.getSellerNumber())
					return m;
			}
			else {
				if(memberNum == m.getBuyerNumber())
					return m;
			}
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
	public void category(Member m) {
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
				case "1": m.setCategory("한식"); break Loop;
				case "2": m.setCategory("양식"); break Loop;
				case "3": m.setCategory("일식"); break Loop;
				case "4": m.setCategory("중식"); break Loop;
				case "5": m.setCategory("분식"); break Loop;
				case "6": m.setCategory("치킨"); break Loop;
				case "7": m.setCategory("피자"); break Loop;
				case "8": m.setCategory("디저트"); break Loop;
				case "9": m.setCategory("야식"); break Loop;
				default: System.out.println("다시 선택하세요.\n");
				}
			}
		System.out.println("업종 선택 완료\n");
	}

}
