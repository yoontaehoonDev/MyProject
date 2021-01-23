package com.yoon.project;

import com.yoon.util.Prompt;

public class Menu {

	static int id;

	static void choice() {
		System.out.println("--------------------");
		System.out.println("- 1. 미사일 개발   -");
		System.out.println("- 2. 미사일 목록   -");
		System.out.println("- 3. 미사일 발사   -");
		System.out.println("- 4. 국가 현황     -");
		System.out.println("- 5. 프로그램 종료 -");
		System.out.println("--------------------\n");
		id = Prompt.inputInt("번호 선택 : \n");
	}
}
