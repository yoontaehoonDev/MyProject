package com.yoon.pms.handler;

import java.util.regex.Pattern;

import com.yoon.pms.domain.Member;
import com.yoon.util.List;
import com.yoon.util.Prompt;

public class MemberHandler {

	private int logCount = 0;
	private int adminNumber = 0;
	private List memberList = new List();
	public static Member memberNumber;

	int memberIndex = 1;

	public void service() {
		while(true) {
			int i = 1;
			System.out.println("■ 메뉴 / 회원 ■");
			if(adminNumber == 1) {
				System.out.printf("%d. 회원 목록\n", i++);
				System.out.printf("%d. 게시판관리\n", i++);
				System.out.printf("%d. 관리자 로그아웃\n", i);
			}
			else {
				if(logCount == 0) {
					System.out.printf("%d. 회원가입\n", i++);
					System.out.printf("%d. 로그인\n", i++);
					System.out.printf("%d. 게시판\n", i++);
					System.out.printf("%d. 고객센터\n", i);
				}
				else if(logCount == 1) {
					System.out.printf("%d. 제품 등록\n", i++);
					System.out.printf("%d. 제품 조회\n", i++);
					System.out.printf("%d. 게시판\n", i++);
					System.out.printf("%d. 고객센터\n", i++);
					System.out.printf("%d. 설정\n", i++);
					System.out.printf("%d. 로그아웃\n", i);
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
			case "뒤로가기":
				System.out.println("초기 화면으로 전환합니다.\n");
				return;
			}
		}
	}

	public void add() {
		System.out.println("■ 메뉴 / 회원 / 회원가입 ■\n");
		Member m = new Member();

		m.setNumber(memberIndex++);
		m.setId(findById("아이디 입력 : "));
		m.setPassword(minimumLength("비밀번호 입력 : "));
		checkPassword(m.getPassword());
		m.setNickname(findByNickname("닉네임 입력 : "));
		m.setName(Prompt.inputString("성명 입력 : "));
		m.setEmail(emailFormat("이메일 입력 : "));
		m.setPhone(phoneFormat("핸드폰 번호 입력 : "));
		m.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));

		this.memberList.add(m);
		this.memberList.size++;
		System.out.println("회원가입이 완료되었습니다.\n");

	}

	public void login() {
		System.out.println("■ 메뉴 / 회원 / 로그인 ■\n");
		if(memberList.size != 0 && logCount == 0) {
			while(true) {
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
							logCount = 1;
							BoardHandler.boardAuthorization = true;
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
		logCount = 0;
		memberNumber = null;
		BoardHandler.boardAuthorization = false;
		BoardHandler.likeCount = 0;
		System.out.println("로그아웃\n");
	}

	public void setting() {
		if(logCount == 1) {
			System.out.println("■ 메뉴 / 회원 / 설정 ■\n");
			String name;
			Member m = memberNumber;
			System.out.println("[설정]");
			System.out.printf("내 아이디 : [%s]  내 닉네임 : [%s]  내 이름 : [%s] 내 이메일 : [%s] 내 휴대폰번호 : [%s]\n",
					m.getId(), m.getNickname(), m.getName(), m.getEmail(), m.getPhone());
			System.out.println("[1. 정보 수정]  [2. 회원 탈퇴]  [3. 뒤로가기]\n");
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
			else if (match.equals("2")){
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
		m.setId(findById("수정할 ID : "));
		m.setPassword(minimumLength("수정할 Password : "));
		m.setNickname(findByNickname("수정할 닉네임 : "));
		m.setName(Prompt.inputString("수정할 이름 : "));
		m.setEmail(emailFormat("수정할 E-Mail : "));
		m.setPhone(phoneFormat("수정할 핸드폰 번호 : "));

		System.out.println("[개인정보 수정 완료]\n");
	}

	private void delete() {
		if(logCount == 1) {
			Member member = memberNumber;
			memberList.delete(member);
			logCount = 0;

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
				System.out.println("회원 탈퇴 처리가 완료되었습니다.\n");
			}
		}
	}

	public void adminLogin() {
		if(logCount == 0 && adminNumber == 0) {
			System.out.println("■ 메뉴 / 관리자 ■\n");
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
			System.out.println("■ 메뉴 / 회원 / 목록 ■\n");
			System.out.println("[회원 목록]");
			Object[] list = memberList.toArray();

			for(Object obj : list) {
				Member m = (Member)obj;
				System.out.printf("회원번호 : [%d]  ID : [%s]  Password : [%s]  닉네임 : [%s]  이름 : [%s]  E-Mail : [%s]  휴대폰번호 : [%s]  가입일자 : [%s]\n", 
						m.getNumber(), m.getId(), m.getPassword(), m.getNickname(), m.getName(), m.getEmail(), m.getPhone(), m.getRegisteredDate());
			}

			if(memberList.size == 0) {
				System.out.println("회원이 없습니다. \n메뉴로 돌아갑니다.\n");
				return;
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

	/* 작성자 찾기 보류
	public String findByWriter() {
		Object[] list = memberList.toArray();
		for(Object obj : list) {
			Member m = (Member)obj;
			if(m.getNickname().equals(memberNumber.getNickname())) {
				return m.getNickname();
			}
		}
		return null;
	}
	 */

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
			if(memberNum == m.getNumber()) {
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

}
