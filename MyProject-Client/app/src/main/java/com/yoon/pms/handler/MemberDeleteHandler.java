package com.yoon.pms.handler;

import com.yoon.driver.Statement;
import com.yoon.util.Prompt;

public class MemberDeleteHandler implements Command {

	Statement stmt;

	public MemberDeleteHandler(Statement stmt) {
		this.stmt = stmt;
	}

	@Override
	public void service() throws Exception {
		System.out.println("■ 메뉴 - 회원 - 탈퇴 ■");

		int num = Prompt.inputInt("탈퇴할 회원 번호 선택 : ");
		stmt.executeQuery("buyerMember/select", Integer.toString(num));

		String input = Prompt.inputString("정말 탈퇴시키겠습니까?(Y/N) : ");

		if(input.equalsIgnoreCase("n")) {
			System.out.println("회원 탈퇴를 취소하였습니다.");
			return;
		}

		stmt.executeUpdate("buyerMember/delete", Integer.toString(num));
		System.out.println("회원 탈퇴 완료");
	}

}
