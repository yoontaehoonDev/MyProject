package com.yoon.pms.handler;

import java.util.regex.Pattern;

import com.yoon.pms.domain.Member;
import com.yoon.util.Prompt;

public class MemberHandler {

	static class Node {
		Member member;
		Node prev;
		Node next;
	}

	public static int logCount = 0;
	public static int adminNumber = 0;
	public static int authorization = 0;

	Node first;
	Node last;
	Node currentNode;
	Member memberNumber;

	int memberCount = 0;
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
		m.id = isSame("아이디 입력 : ");
		m.password = minimumLength("비밀번호 입력 : ");
		m.name = Prompt.inputString("성명 입력 : ");
		m.email = emailFormat("이메일 입력 : ");
		m.phone = phoneFormat("핸드폰 번호 입력 : ");
		m.registeredDate = new java.sql.Date(System.currentTimeMillis());

		if(first == null) {
			first = new Node();
			first.member = m;
			last = first;
		}
		else {
			last.next = new Node();
			last.next.prev = last;
			last = last.next;
			last.member = m;
		}

		this.memberCount++;
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
			for(Node cursor = first; cursor != null; cursor = cursor.next) {
				Member m = cursor.member;
				System.out.printf("고유번호 : [%d] ID : [%s] Password : [%s] 이름 : [%s] 이메일 : [%s] 휴대폰 번호 : [%s] 가입일 : [%s]\n",
						m.number, m.id, m.password, m.name, m.email, m.phone, m.registeredDate);
			}
			System.out.println();
			System.out.println("1. [회원 강제탈퇴] 2. [나가기]");
			int setting = Prompt.inputInt("번호를 선택하세요 : ");
			if(setting == 1) {
				System.out.println("삭제할 회원 번호를 입력하세요 : ");
				// 연결리스트 이용
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
		if(memberCount != 0 && logCount == 0) {
			while(true) {
				String id, password;
				boolean pswCheck;

				id = Prompt.inputString("아이디 입력(엔터 - 나가기) : ");
				if(id.length() == 0) {
					System.out.println("메인 메뉴로 돌아갑니다.");
					return;
				}

				Member idCheck = verifyId(id);

				if(idCheck != null) {
					while(true) {
						password = Prompt.inputString("비밀번호 입력(엔터 - 나가기) : ");
						if(password.length() == 0) {
							System.out.println("메인 메뉴로 돌아갑니다.");
							return;
						}

						pswCheck = verifyPassword(password, idCheck);

						if(pswCheck) {
							System.out.println("로그인 성공");
							authorization = 1;
							memberNumber = idCheck;
							logCount = 1;
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
		authorization = 1;
		memberNumber = null;

	}

	public void setting() {
		if(logCount == 1) {
			String name;
			Member m = memberNumber;
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

		Member m = memberNumber;
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

			if(currentNode == first) {
				first = currentNode.next;
			}
			else {
				currentNode.prev.next = currentNode.next;
				if(currentNode.next != null) {
					currentNode.next.prev = currentNode.prev;
				}
			}
			if(currentNode == last) {
				last = currentNode.prev;
			}

			logCount = 0;
			this.memberCount--;

			System.out.println("계정이 탈퇴 처리되었습니다. 그동안 이용해주셔서 감사합니다.\n");
		}
		else {
			System.out.println("로그인 후, 이용 가능합니다.");
		}
	}

	String isSame(String message) {
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
				for(Node cursor = first; cursor != null; cursor = cursor.next) {
					Member m = cursor.member;
					if(id.equalsIgnoreCase(m.id)) {
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

	Member verifyId(String id) {
		for(Node cursor = first; cursor != null; cursor = cursor.next) {
			Member m = cursor.member;
			if(m.id.equalsIgnoreCase(id)) {
				currentNode = cursor;
				return m;
			}
		}
		return null;
	}

	boolean verifyPassword(String password, Member m) {
		if(password.equals(m.password)) {
			return true;
		}
		return false;
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

	String minimumLength(String name) {
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

	String emailFormat(String name) {
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
	String phoneFormat(String name) {
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

}
