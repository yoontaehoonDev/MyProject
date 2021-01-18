package com.yoon.project;

import java.util.Scanner;

public class Project {

	static final int MAX = 50;
	static final int TOTAL = 17;
	static Scanner scanner = new Scanner(System.in);

	static int[] index = new int[MAX];
	static String[] missile = new String[MAX];
	static int[] effectiveRange = new int[MAX];
	static String[] nuclearBomb = new String[MAX];
	static String[] biochemistryBomb = new String[MAX];
	static String[] stealthFuction = new String[MAX];
	static int devCount = 0;

	static String[] nations = {"미국", "러시아", "이란", "인도", "호주", "캐나다", "뉴질랜드", "파키스탄", "몽골", "필리핀",
			"대만", "태국", "베트남", "인도네시아", "중국", "일본", "북한"
	};
	static int[] population = { 332000000, 145000000, 85000000, 1393000000, 25000000, 38000000,
			4860000, 225000000, 3320000, 111000000, 230000000, 70000000, 98000000,
			276000000, 1444000000, 126000000, 25000000
	};
	static int[] deadPeople = new int[17];

	static int launch = 0;
	static int flag = 0;
	static int i = 0;
	static int j = 0;

	static int nuclearDamage = 5384923;
	static int biochemistryDamage = 2837194;
	static int bombDamage = 134728;

	static int icbmCount = 0;
	static int cruiseCount = 0;
	static int patriotCount = 0;

	static int missileNumber;

	static String response;
	static String nation;

	static void addMissile() {
		System.out.println("[미사일 개발]");
		System.out.println();
		for (i = devCount; i < MAX; i++) {

			System.out.println("[미사일 분류]");
			System.out.println("1. 탄도미사일    2. 순항미사일    3. 단거리미사일");

			System.out.print("개발할 미사일을 입력하세요[숫자] : ");
			missileNumber = scanner.nextInt();
			scanner.nextLine();

			index[i] = i+1;

			if (missileNumber == 1) {
				missile[i] = "탄도미사일";

				System.out.print("핵탄두 [장착/미장착] : ");
				nuclearBomb[i] = scanner.nextLine();
				if (nuclearBomb[i].equals("장착")) {
					System.out.println("핵탄두 장착 완료");
					biochemistryBomb[i] = "미장착";
				}
				else {
					System.out.println("핵탄두 미장착");
					System.out.println();
					System.out.print("생화학탄 [장착/미장착] : ");
					biochemistryBomb[i] = scanner.nextLine();

					if (biochemistryBomb[i].equals("장착")) {
						System.out.println("생화학탄 장착 완료");
					}
					else {
						biochemistryBomb[i] = "미장착";
						System.out.println("생화학탄 미장착");
					}
				}
				System.out.println();

				System.out.print("스텔스 기능 [추가/미추가] : ");
				stealthFuction[i] = scanner.nextLine();
				if(stealthFuction[i].equals("추가")) {
					System.out.println("스텔스 기능 추가 완료");
				}
				else {
					System.out.println("스텔스 기능 미추가");
				}

				effectiveRange[i] = 12500;
				icbmCount++;

			}

			else if (missileNumber == 2) {
				missile[i] = "순항미사일";

				System.out.print("핵탄두 [장착/미장착] : ");
				nuclearBomb[i] = scanner.nextLine();
				if (nuclearBomb[i].equals("장착")) {
					System.out.println("핵탄두 장착 완료");
					biochemistryBomb[i] = "미장착";
				}
				else {
					System.out.println("핵탄두 미장착");
					System.out.println();
					System.out.print("생화학탄 [장착/미장착] : ");
					biochemistryBomb[i] = scanner.nextLine();
					if (biochemistryBomb[i].equals("장착")) {
						System.out.println("생화학탄 장착 완료");
					}
					else {
						biochemistryBomb[i] = "미장착";
						System.out.println("생화학탄 미장착");
					}
				}
				System.out.println();
				System.out.print("스텔스 기능 [추가/미추가] : ");
				stealthFuction[i] = scanner.nextLine();
				if (stealthFuction[i].equals("추가")) {
					System.out.println("스텔스 기능 추가 완료");
				}
				else {
					System.out.println("스텔스 기능 미추가");
				}

				effectiveRange[i] = 5000;
				cruiseCount++;

			}

			else if(missileNumber == 3) {
				missile[i] = "단거리미사일";

				System.out.print("핵탄두 [장착/미장착] : ");
				nuclearBomb[i] = scanner.nextLine();
				if (nuclearBomb[i].equals("장착")) {
					System.out.println("핵탄두 장착 완료");
					biochemistryBomb[i] = "미장착";
				}
				else {
					System.out.println("핵탄두 미장착");
					System.out.println();
					System.out.print("생화학탄 [장착/미장착] : ");
					biochemistryBomb[i] = scanner.nextLine();
					if (biochemistryBomb[i].equals("장착")) {
						System.out.println("생화학탄 장착 완료");
					}
					else {

						System.out.println("생화학탄 미장착");
					}
				}
				System.out.println();
				System.out.print("스텔스 기능 [추가/미추가] : ");
				stealthFuction[i] = scanner.nextLine();
				if (stealthFuction[i].equals("추가")) {
					System.out.println("스텔스 기능 추가 완료");
				}
				else {
					System.out.println("스텔스 기능 미추가");
				}

				effectiveRange[i] = 350;
				patriotCount++;

			}
			else {
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("초기 화면으로 돌아갑니다.");
				break;
			}

			devCount++;
			System.out.println();
			System.out.println("계속 개발하시겠습니까?[y/N] : ");
			response = scanner.nextLine();
			if (!response.equalsIgnoreCase("y")) {
				break;
			}
			System.out.println();
		}
	}

	static void listMissile() {
		System.out.println("[미사일 목록]");
		for (i = 0; i < devCount; i++) {
			if (index[i] != 0) {
				System.out.printf("미사일 번호 : [%d]  ▶  미사일 이름 : [%s]  ▶  핵탄두 장착 여부 : [%s]\n", index[i], missile[i], nuclearBomb[i]);
				System.out.printf("생화학탄 장착 여부 : [%s]  ▶  스텔스 기능 : [%s]  ▶  사정거리 : [%dKM]\n", biochemistryBomb[i], stealthFuction[i], effectiveRange[i]);
				System.out.println("--------------------------------------------------------------------------------------");
			}
		}
		System.out.printf("탄도미사일 : %d대  순항미사일 : %d대  단거리미사일 : %d대\n", icbmCount, cruiseCount, patriotCount);
		System.out.println();
	}

	static void launchMissile() {
		if (icbmCount == 0 && cruiseCount == 0 && patriotCount == 0) {
			System.out.println("미사일이 부족합니다.");
			System.out.println();
		}
		else {
			System.out.println("[미사일 발사]");
			System.out.println();
			for (i = 0; i < devCount; i++) {
				if (index[i] != 0) {
					System.out.printf("미사일 번호 : [%d]  ▶  미사일 이름 : [%s]  ▶  핵탄두 장착 여부 : [%s]\n", index[i], missile[i], nuclearBomb[i]);
					System.out.printf("생화학탄 장착 여부 : [%s]  ▶  스텔스 기능 : [%s]  ▶  사정거리 : [%dKM]\n", biochemistryBomb[i], stealthFuction[i], effectiveRange[i]);
					System.out.println("-----------------------------------------------------------------------------------------------------------");
				}
			}
			System.out.printf("탄도미사일 : %d대  순항미사일 : %d대  단거리미사일 : %d대\n", icbmCount, cruiseCount, patriotCount);
			System.out.println();
			System.out.print("미사일 번호 선택 : ");
			launch = scanner.nextInt();
			scanner.nextLine();
			launch--;
			index[launch] = 0;

			System.out.println();
			System.out.printf("%s 발사 준비 완료\n", missile[launch]);
			System.out.println();

			if (missile[launch].equals("탄도미사일")) {
				System.out.println("타격권 : 미국   러시아   이란   호주   캐나다");
				System.out.println("         인도   뉴질랜드   파키스탄");
				System.out.println();
				System.out.print("공격할 국가 선택 : ");
				nation = scanner.nextLine();
				while(true) {
					if (nation.equals("미국") || nation.equals("러시아") || nation.equals("이란") || 
							nation.equals("호주") || nation.equals("캐나다") || nation.equals("인도")
							|| nation.equals("뉴질랜드") || nation.equals("파키스탄")) {
						break;
					}
					else {
						System.out.println();
						System.out.println("잘못 입력하셨습니다.");
						System.out.println("타격권 : 미국   러시아   이란   호주   캐나다");
						System.out.println("         인도   뉴질랜드   파키스탄");
						System.out.print("다시 입력 : ");
						nation = scanner.nextLine();
						if (nation.equals("미국") || nation.equals("러시아") || nation.equals("이란") || 
								nation.equals("호주") || nation.equals("캐나다") || nation.equals("인도")
								|| nation.equals("뉴질랜드") || nation.equals("파키스탄")) {
							break;
						}
					}
				}

				flag = 0;

				if (nuclearBomb[launch].equals("장착")) {
					for(i = 1, j = 1; i <= nuclearDamage; i += 1321, j+= 15) {
						System.out.printf("인명피해 : %d명	폭발반경 : %dM	핵탄두 장착으로 인한 피해량 x10\n", i, j);
						flag = 1;
					}
				}
				else if (biochemistryBomb[launch].equals("장착")){
					for(i = 1, j = 1; i <= biochemistryDamage; i += 147, j += 2) {
						System.out.printf("인명피해 : %d명	폭발반경 : %dM	생화학탄 장착으로 인한 피해랑 x5\n", i, j);
						flag = 2;
					}
				}
				else {
					for(i = 1, j = 1; i <= bombDamage; i += 13, j++) {
						System.out.printf("인명피해 : %d명	폭발반경 : %dM\n", i, j);
					}
				}

				if (nation.equals("미국")) {
					if (flag == 1) {
						population[0] -= nuclearDamage;
						deadPeople[0] += nuclearDamage;
					}
					else if (flag == 2) {
						population[0] -= biochemistryDamage;
						deadPeople[0] += biochemistryDamage;
					}
					else {
						population[0] -= bombDamage;
						deadPeople[0] += bombDamage;
					}
				}
				else if (nation.equals("러시아")) {
					if (flag == 1) {
						population[1] -= nuclearDamage;
						deadPeople[1] += nuclearDamage;
					}
					else if (flag == 2) {
						population[1] -= biochemistryDamage;
						deadPeople[1] += biochemistryDamage;
					}
					else {
						population[1] -= bombDamage;
						deadPeople[1] += bombDamage;
					}
				}
				else if (nation.equals("이란")) {
					if (flag == 1) {
						population[2] -= nuclearDamage;
						deadPeople[2] += nuclearDamage;
					}
					else if (flag == 2) {
						population[2] -= biochemistryDamage;
						deadPeople[2] += biochemistryDamage;
					}
					else {
						population[2] -= bombDamage;
						deadPeople[2] += bombDamage;
					}
				}
				else if (nation.equals("인도")) {
					if (flag == 1) {
						population[3] -= nuclearDamage;
						deadPeople[3] += nuclearDamage;
					}
					else if (flag == 2) {
						population[3] -= biochemistryDamage;
						deadPeople[3] += biochemistryDamage;
					}
					else {
						population[3] -= bombDamage;
						deadPeople[3] += bombDamage;
					}
				}
				else if (nation.equals("호주")) {
					if (flag == 1) {
						population[4] -= nuclearDamage;
						deadPeople[4] += nuclearDamage;
					}
					else if (flag == 2) {
						population[4] -= biochemistryDamage;
						deadPeople[4] += biochemistryDamage;
					}
					else {
						population[4] -= bombDamage;
						deadPeople[4] += bombDamage;
					}
				}
				else if (nation.equals("캐나다")) {
					if (flag == 1) {
						population[5] -= nuclearDamage;
						deadPeople[5] += nuclearDamage;
					}
					else if (flag == 2) {
						population[5] -= biochemistryDamage;
						deadPeople[5] += biochemistryDamage;
					}
					else {
						population[5] -= bombDamage;
						deadPeople[5] += bombDamage;
					}
				}
				else if (nation.equals("뉴질랜드")) {
					if (flag == 1) {
						population[6] -= nuclearDamage;
						deadPeople[6] += nuclearDamage;
					}
					else if (flag == 2) {
						population[6] -= biochemistryDamage;
						deadPeople[6] += biochemistryDamage;
					}
					else {
						population[6] -= bombDamage;
						deadPeople[6] += bombDamage;
					}
				}
				else if (nation.equals("파키스탄")) {
					if (flag == 1) {
						population[7] -= nuclearDamage;
						deadPeople[7] += nuclearDamage;
					}
					else if (flag == 2) {
						population[7] -= biochemistryDamage;
						deadPeople[7] += biochemistryDamage;
					}
					else {
						population[7] -= bombDamage;
						deadPeople[7] += bombDamage;
					}
				}

				icbmCount--;
			}
			else if (missile[launch].equals("순항미사일")) {
				System.out.println("타격권 : 몽골   필리핀   대만   태국   베트남   인도네시아");
				System.out.println();
				System.out.print("공격할 국가 선택 : ");
				nation = scanner.nextLine();
				while(true) {
					if (nation.equals("몽골") || nation.equals("필리핀") || nation.equals("대만") || 
							nation.equals("인도네시아") || nation.equals("베트남") || nation.equals("태국")) {
						break;
					}
					else {
						System.out.println();
						System.out.println("잘못 입력하셨습니다.");
						System.out.println("타격권 : 몽골   필리핀   대만   태국   베트남   인도네시아");
						System.out.print("다시 입력 : ");
						nation = scanner.nextLine();
						if (nation.equals("몽골") || nation.equals("필리핀") || nation.equals("대만") || 
								nation.equals("인도네시아") || nation.equals("베트남") || nation.equals("태국")) {
							break;
						}
					}
				}

				flag = 0;

				if (nuclearBomb[launch].equals("장착")) {
					for(i = 1, j = 1; i <= nuclearDamage; i += 1321, j+= 15) {
						System.out.printf("인명피해 : %d명	폭발반경 : %dM	핵탄두 장착으로 인한 피해량 x10\n", i, j);
						flag = 1;
					}
				}
				else if (biochemistryBomb[launch].equals("장착")){
					for(i = 1, j = 1; i <= biochemistryDamage; i += 147, j += 2) {
						System.out.printf("인명피해 : %d명	폭발반경 : %dM	생화학탄 장착으로 인한 피해랑 x5\n", i, j);
						flag = 2;
					}
				}
				else {
					for(i = 1, j = 1; i <= bombDamage; i += 13, j++) {
						System.out.printf("인명피해 : %d명	폭발반경 : %dM\n", i, j);
					}
				}

				if(flag == 1) {
					System.out.printf("");
				}


				if (nation.equals("몽골")) {
					if (flag == 1) {
						population[8] -= nuclearDamage;
						deadPeople[8] += nuclearDamage;
					}
					else if (flag == 2) {
						population[8] -= biochemistryDamage;
						deadPeople[8] += biochemistryDamage;
					}
					else {
						population[8] -= bombDamage;
						deadPeople[8] += bombDamage;
					}
				}
				else if (nation.equals("필리핀")) {
					if (flag == 1) {
						population[9] -= nuclearDamage;
						deadPeople[9] += nuclearDamage;
					}
					else if (flag == 2) {
						population[9] -= biochemistryDamage;
						deadPeople[9] += biochemistryDamage;
					}
					else {
						population[9] -= bombDamage;
						deadPeople[9] += bombDamage;
					}
				}
				else if (nation.equals("대만")) {
					if (flag == 1) {
						population[10] -= nuclearDamage;
						deadPeople[10] += nuclearDamage;
					}
					else if (flag == 2) {
						population[10] -= biochemistryDamage;
						deadPeople[10] += biochemistryDamage;
					}
					else {
						population[10] -= bombDamage;
						deadPeople[10] += bombDamage;
					}
				}
				else if (nation.equals("태국")) {
					if (flag == 1) {
						population[11] -= nuclearDamage;
						deadPeople[11] += nuclearDamage;
					}
					else if (flag == 2) {
						population[11] -= biochemistryDamage;
						deadPeople[11] += nuclearDamage;
					}
					else {
						population[11] -= bombDamage;
						deadPeople[11] += bombDamage;
					}
				}
				else if (nation.equals("베트남")) {
					if (flag == 1) {
						population[12] -= nuclearDamage;
						deadPeople[12] += nuclearDamage;
					}
					else if (flag == 2) {
						population[12] -= biochemistryDamage;
						deadPeople[12] += biochemistryDamage;
					}
					else {
						population[12] -= bombDamage;
						deadPeople[12] += bombDamage;
					}
				}
				else if (nation.equals("인도네시아")) {
					if (flag == 1) {
						population[13] -= nuclearDamage;
						deadPeople[13] += nuclearDamage;
					}
					else if (flag == 2) {
						population[13] -= biochemistryDamage;
						deadPeople[13] += biochemistryDamage;
					}
					else {
						population[13] -= bombDamage;
						deadPeople[13] += bombDamage;
					}
				}

				cruiseCount--;
			}

			else if (missile[launch].equals("단거리미사일")) {
				System.out.println("타격권 : 북한   중국   일본");
				System.out.println();
				System.out.print("공격할 국가 선택 : ");
				nation = scanner.nextLine();
				while(true) {
					if (nation.equals("북한") || nation.equals("중국") || nation.equals("일본")) {
						break;
					}
					else {
						System.out.println();
						System.out.println("잘못 입력하셨습니다.");
						System.out.println("타격권 : 북한   중국   일본");
						System.out.print("다시 입력 : ");
						nation = scanner.nextLine();
						if (nation.equals("북한") || nation.equals("중국") || nation.equals("일본")) {
							break;
						}
					}
				}

				flag = 0;

				if (nuclearBomb[launch].equals("장착")) {
					for(i = 1, j = 1; i <= nuclearDamage; i += 1321, j+= 15) {
						System.out.printf("인명피해 : %d명	폭발반경 : %dM	핵탄두 장착으로 인한 피해량 x10\n", i, j);
						flag = 1;
					}
				}
				else if (biochemistryBomb[launch].equals("장착")){
					for(i = 1, j = 1; i <= biochemistryDamage; i += 147, j += 2) {
						System.out.printf("인명피해 : %d명	폭발반경 : %dM	생화학탄 장착으로 인한 피해랑 x5\n", i, j);
						flag = 2;
					}
				}
				else {
					for(i = 1, j = 1; i <= bombDamage; i += 13, j++) {
						System.out.printf("인명피해 : %d명	폭발반경 : %dM\n", i, j);
					}
				}

				if (nation.equals("중국")) {
					if (flag == 1) {
						population[14] -= nuclearDamage;
						deadPeople[14] += nuclearDamage;
					}
					else if (flag == 2) {
						population[14] -= biochemistryDamage;
						deadPeople[14] += biochemistryDamage;
					}
					else {
						population[14] -= bombDamage;
						deadPeople[14] += bombDamage;
					}
				}
				else if (nation.equals("일본")) {
					if (flag == 1) {
						population[15] -= nuclearDamage;
						deadPeople[15] += nuclearDamage;
					}
					else if (flag == 2) {
						population[15] -= biochemistryDamage;
						deadPeople[15] += biochemistryDamage;
					}
					else {
						population[15] -= bombDamage;
						deadPeople[15] += bombDamage;
					}
				}
				else if (nation.equals("북한")) {
					if (flag == 1) {
						population[16] -= nuclearDamage;
						deadPeople[16] += nuclearDamage;
					}
					else if (flag == 2) {
						population[16] -= biochemistryDamage;
						deadPeople[16] += biochemistryDamage;
					}
					else {
						population[16] -= bombDamage;
						deadPeople[16] += bombDamage;
					}
				}

				patriotCount--;
			}

			missile[launch] = null;
			nuclearBomb[launch] = null;
			biochemistryBomb[launch] = null;
			stealthFuction[launch] = null;
			effectiveRange[launch] = 0;

		}
	}

	static void showPopulation() {
		for (i = 0; i < TOTAL; i++) {
			if (population[i] < 0) {
				System.out.printf("%s : 멸망\n", nations[i]);
			}
			else {
				System.out.printf("%s 인구 : %d명  - 사망자 수 : %d명\n", nations[i], population[i], deadPeople[i]);
			}
		}
	}

	public static void main(String[] args) {


		while(true) {
			System.out.println("--------------------");
			System.out.println("- 1. 미사일 개발   -");
			System.out.println("- 2. 미사일 목록   -");
			System.out.println("- 3. 미사일 발사   -");
			System.out.println("- 4. 국가 현황     -");
			System.out.println("- 5. 프로그램 종료 -");
			System.out.println("--------------------");
			System.out.println();
			System.out.print("번호 선택 : ");
			int choice = scanner.nextInt();
			System.out.println();

			if (choice == 1) {

				addMissile();

			}
			else if(choice == 2) {

				listMissile();

			}

			else if (choice == 3) {

				launchMissile();

			}
			else if (choice == 4) {

				showPopulation();

			}
			else {
				System.out.println("미사일 시스템 종료");
				break;
			}
		}
	}
}
