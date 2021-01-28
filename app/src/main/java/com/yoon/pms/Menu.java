package com.yoon.pms;

import com.yoon.pms.handler.MemberHandler;

public class Menu {

	static void main() {
		System.out.println("--------------------");
		System.out.println("- 회원등록         -");
		if(MemberHandler.logCount == 0) 
			System.out.println("- 로그인      -");
		System.out.println("- 제품등록      -");
		System.out.println("- 제품조회      -");
		System.out.println("- 인기제품      -");
		System.out.println("- 배송관리      -");
		System.out.println("- 게시판        -");
		System.out.println("- FAQ           -");
		System.out.println("- 고객센터      -");
		if(MemberHandler.logCount == 1) {
			System.out.println("- 로그아웃      -");
			System.out.println("- 내 설정       -");
		}
		System.out.println("--------------------\n");
	}
}
