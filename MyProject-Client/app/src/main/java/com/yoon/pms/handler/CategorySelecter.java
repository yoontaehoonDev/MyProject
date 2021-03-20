package com.yoon.pms.handler;

import com.yoon.util.Prompt;

public class CategorySelecter {
	public static String service() {
		System.out.println("[업종 선택]");
		String choice;
		while(true) {
			System.out.println("1. 한식 2. 양식 3. 일식 4. 중식 5. 분식");
			System.out.println("6. 치킨 7. 피자 8. 디저트 9. 야식\n");
			choice = Prompt.inputString("번호 선택 : ");

			if(choice.length() == 0) {
				continue;
			}

			switch(choice) {
			case "1": return "한식";
			case "2": return "양식";
			case "3": return "일식";
			case "4": return "중식";
			case "5": return "분식";
			case "6": return "치킨";
			case "7": return "피자";
			case "8": return "디저트";
			case "9": return "야식";
			default: System.out.println("다시 선택하세요.\n");
			}
		}
	}
}
