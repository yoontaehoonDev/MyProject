package com.yoon.pms.handler;

import com.yoon.pms.domain.Member;
import com.yoon.util.Prompt;

public class MemberHandler {



	public static int logCount = 0;
	public static int adminNumber = 0;
	public static boolean authorization = false;
	MemberList memberList;


	int uniqueNumber = 1;

	public void service() {
		while(true) {
			int i = 1;
			System.out.println("- 메뉴 / 회원 -");
			if(adminNumber == 1) {
				System.out.printf("%d. 회원 목록\n", i++);
				System.out.printf("%d. 제품 리스트\n", i++);
				System.out.printf("%d. 관리자 로그아웃\n", i++);
			}
			else {
				if(logCount == 0) {
					System.out.printf("%d. 회원가입\n", i++);
					System.out.printf("%d. 로그인\n", i++);
					System.out.printf("%d. 제품 등록\n", i++);
					System.out.printf("%d. 제품 조회\n", i++);
					System.out.printf("%d. 게시판\n", i++);
					System.out.printf("%d. 고객센터\n", i++);
				}
				if(logCount == 1) {
					System.out.printf("%d. 제품 등록\n", i++);
					System.out.printf("%d. 제품 조회\n", i++);
					System.out.printf("%d. 게시판\n", i++);
					System.out.printf("%d. 고객센터\n", i++);
					System.out.printf("%d. 설정\n", i++);
					System.out.printf("%d. 로그아웃\n", i++);
				}
			}
			System.out.println();
			String menu = Prompt.inputString("메뉴 입력 : ");

			switch(menu) {
			case "add":
				this.add();
				break;
			case "login":
				this.login();
				break;
			case "logout":
				this.logout();
				break;
			case "adminlogin":
				this.adminLogin();
				break;
			case "adminlogout":
				this.adminLogout();
				break;
			case "list":
				this.list();
				break;
			case "setting":
				this.setting();
				break;
			case "종료":
				System.out.println("프로그램 종료");
				return;
			}
		}
	}

	public void add() {
		Member m = new Member();
		m.number = uniqueNumber++;
		m.id = memberList.isSame("아이디 입력 : ");
		m.password = memberList.minimumLength("비밀번호 입력 : ");
		m.name = Prompt.inputString("성명 입력 : ");
		m.email = memberList.emailFormat("이메일 입력 : ");
		m.phone = memberList.phoneFormat("핸드폰 번호 입력 : ");
		m.registeredDate = new java.sql.Date(System.currentTimeMillis());

		memberList.add(m);
		System.out.println("회원가입이 완료되었습니다.\n");

	}
	public void adminLogin() {
		if(logCount == 0 && adminNumber == 0) {
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
			System.out.println("[회원 목록]");

			Member[] members = memberList.toArray();

			for(Member m : members) {
				System.out.printf("회원번호 : [%d]  ID : [%s]  Password : [%s]  이름 : [%s]  E-Mail : [%s]  휴대폰번호 : [%s]  가입일자 : [%s]\n", 
						m.number, m.id, m.password, m.name, m.email, m.phone, m.registeredDate);
			}

			System.out.println();
			System.out.println("1. [회원 강제탈퇴] 2. [나가기]");
			int setting = Prompt.inputInt("번호를 선택하세요 : ");
			if(setting == 1) {
				int num = Prompt.inputInt("삭제할 회원 번호를 입력하세요 : ");
				memberList.find(num);

			}
			else {
				System.out.println("메뉴로 돌아갑니다.");
				return;
			}
		}
		else {
			System.out.println("관리자 권한이 필요합니다.\n");
		}
	}

	public void login() {
		if(memberList.memberCount != 0 && logCount == 0) {
			while(true) {
				String id, password;
				boolean pswCheck;

				id = Prompt.inputString("아이디 입력(엔터 - 나가기) : ");
				if(id.length() == 0) {
					System.out.println("메인 메뉴로 돌아갑니다.");
					return;
				}

				Member idCheck = memberList.verifyId(id);

				if(idCheck != null) {
					while(true) {
						password = Prompt.inputString("비밀번호 입력(엔터 - 나가기) : ");
						if(password.length() == 0) {
							System.out.println("메인 메뉴로 돌아갑니다.");
							return;
						}

						pswCheck = memberList.verifyPassword(password, idCheck);

						if(pswCheck) {
							System.out.println("로그인 성공");
							authorization = true;
							logCount = 1;
							memberList.memberNumber = idCheck;
							return;
						}
						else {
							System.out.println("비밀번호가 틀렸습니다.");
						}
					}
				}
				else {
					System.out.println("존재하지 않는 아이디 입니다.");
				}
			}
		}
		else {
			System.out.println("현재 회원이 없습니다.\n");
		}
	}

	public void logout() {
		logCount = 0;
		authorization = false;
		memberList.memberNumber = null;

	}

	public void setting() {
		if(logCount == 1) {
			String name;
			Member m = memberList.memberNumber;
			System.out.println("[설정]");
			System.out.printf("내 아이디 : %s 내 이름 : %s 내 이메일 : %s 내 휴대폰번호 : %s\n",
					m.id, m.name, m.email, m.phone);
			System.out.println("[1. 정보 수정]  [2. 회원 탈퇴]  [3. 뒤로가기]");
			String match = Prompt.inputString("입력 : ");
			if(match.equals("1")) {
				System.out.println("[개인정보 수정]");
				name = Prompt.inputString("정말로 수정하시겠습니까? [Y/N] : ");
				if(name.equalsIgnoreCase("y")) {
					update();
				}
				else {
					System.out.println("메뉴 / 회원 으로 돌아갑니다.");
					return;
				}
			}
			else if (match.equals("2")){
				System.out.println("[회원 탈퇴]");
				name = Prompt.inputString("정말로 탈퇴하시겠습니까? [Y/N] : ");
				if(name.equalsIgnoreCase("y")) {
					delete();
				}
				else {
					System.out.println("메뉴 / 회원 으로 돌아갑니다.");
					return;
				}
			}
			else {
				System.out.println("설정에서 나갑니다.");
				return;
			}
		}
		else {
			System.out.println("로그인 후 이용 가능합니다.");
		}
	}
	public void update() {

		Member m = memberList.memberNumber;
		String currentId = Prompt.inputString(String.format("현재 아이디 : %s - 수정할 아이디 : ", m.id));
		String currentPassword = Prompt.inputString(String.format("현재 비밀번호 : %s - 수정할 비밀번호 : ", m.password));
		String currentName = Prompt.inputString(String.format("현재 이름 : %s - 수정할 이름 : ", m.name));
		String currentEmail = Prompt.inputString(String.format("현재 이메일 : %s - 수정할 이메일 : ", m.email));
		String currentPhone = Prompt.inputString(String.format("현재 전화번호 : %s - 수정할 전화번호 : ", m.phone));

		m.id = currentId;
		m.password = currentPassword;
		m.name = currentName;
		m.email = currentEmail;
		m.phone = currentPhone;
		System.out.println("[개인정보 수정 완료]");
	}

	public void delete() {
		if(logCount == 1) {

			memberList.delete();
			logCount = 0;

			System.out.println("계정 탈퇴 처리가 완료되었습니다. 그동안 이용해주셔서 감사합니다.\n");
		}
		else {
			System.out.println("로그인 후, 이용 가능합니다.");
		}
	}


	//  삭제 계획
	//  Member findBy(int index) {
	//    for(Node cursor = first; cursor != null; cursor = cursor.next) {
	//      Member m = cursor.member;
	//      if(index == m.number) {
	//        return m;
	//      }
	//    }
	//    return null;
	//  }




}
