package com.yoon.pms;

import com.yoon.pms.handler.BoardHandler;
import com.yoon.pms.handler.MemberHandler;
import com.yoon.pms.handler.ProductHandler;
import com.yoon.util.Iterator;
import com.yoon.util.Prompt;
import com.yoon.util.Queue;
import com.yoon.util.QueueIterator;
import com.yoon.util.Stack;
import com.yoon.util.StackIterator;

public class App {

	static Stack commandStack = new Stack();
	static Queue commandQueue = new Queue();

	public static void main(String[] args) throws CloneNotSupportedException {
		System.out.println("[2030 Project]");

		MemberHandler memberHandler = new MemberHandler();
		ProductHandler productHandler = new ProductHandler();
		BoardHandler boardHandler = new BoardHandler();

		loop:
			while(true) {
				int i = 1;
				System.out.println("■ 메뉴 ■");
				System.out.println("1. 회원가입");
				System.out.println("2. 로그인");
				System.out.println("3. 자유 게시판");
				System.out.println("4. 익명 게시판");
				System.out.println("5. 신고 게시판\n");
				String menu = Prompt.inputString("메뉴 입력 : ");



				commandStack.push(menu);
				commandQueue.offer(menu);

				switch(menu) {
				case "member":
					memberHandler.service();
					break;
				case "board":
					boardHandler.service();
					break;
				case "shistory":
					printCommandHistory(new StackIterator(commandStack.clone()));
					break;
				case "qhistory":
					printCommandHistory(new QueueIterator(commandQueue.clone()));
					break;
				case "FAQ":
					break;
				case "고객센터":
					break;
				case "종료":
					System.out.println("프로그램 종료");
					break loop;
				default:
					System.out.println("실행할 수 없는 명령어입니다.");
				}

			}
		Prompt.close();
	}

	static void printCommandHistory(Iterator iterator) {

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
}
