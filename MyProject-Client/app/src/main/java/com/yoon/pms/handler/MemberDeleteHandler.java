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

	}

}
