package com.yoon.project.Handler;

import com.yoon.util.Prompt;

public class MissileHandler {

	static final int MAX = 50;

	static class Missile {
		int index;
		int effectiveRange;
		String missile;
		String nuclearBomb;
		String biochemistryBomb;
		String stealthFunction;

	}
	static class MissileCount {
		int devCount = 0;
		int icbmCount = 0;
		int cruiseCount = 0;
		int patriotCount = 0;
	}

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
	static int nuclearDamage = 5384923;
	static int biochemistryDamage = 2837194;
	static int bombDamage = 134728;
	static String nation;


	static int i;
	static int missileNumber;
	static String response;
	static Missile[] missiles = new Missile[MAX];
	static MissileCount c = new MissileCount();


	public static void add() {
		System.out.println("[미사일 개발]\n");

		for (i = c.devCount; i < MAX; i++) {
			Missile m = new Missile();
			System.out.println("[미사일 분류]");
			System.out.println("1. 탄도미사일    2. 순항미사일    3. 단거리미사일");

			missileNumber = Prompt.inputInt("개발할 미사일을 입력하세요[숫자] : ");
			m.index = i+1;

			if (missileNumber == 1) {
				m.missile = "탄도미사일";

				m.nuclearBomb = Prompt.inputString("핵탄두 [장착/미장착] : ");
				if (m.nuclearBomb.equals("장착")) {
					System.out.println("핵탄두 장착 완료\n");
					m.biochemistryBomb = "미장착";
				}
				else {
					System.out.println("핵탄두 미장착\n");
					m.biochemistryBomb = Prompt.inputString("생화학탄 [장착/미장착] : ");
					if (m.biochemistryBomb.equals("장착")) {
						System.out.println("생화학탄 장착 완료\n");
					}
					else {
						m.biochemistryBomb = "미장착";
						System.out.println("생화학탄 미장착\n");
					}
				} 
				m.stealthFunction = Prompt.inputString("스텔스 기능 [추가/미추가] : ");
				if(m.stealthFunction.equals("추가")) {
					System.out.println("스텔스 기능 추가 완료");
				}
				else {
					System.out.println("스텔스 기능 미추가");
				}

				m.effectiveRange = 12500;
				c.icbmCount++;

			}

			else if (missileNumber == 2) {
				m.missile = "순항미사일";

				m.nuclearBomb = Prompt.inputString("핵탄두 [장착/미장착] : ");
				if (m.nuclearBomb.equals("장착")) {
					System.out.println("핵탄두 장착 완료\n");
					m.biochemistryBomb = "미장착";
				}
				else {
					System.out.println("핵탄두 미장착\n");
					m.biochemistryBomb = Prompt.inputString("생화학탄 [장착/미장착] : ");
					if (m.biochemistryBomb.equals("장착")) {
						System.out.println("생화학탄 장착 완료\n");
					}
					else {
						m.biochemistryBomb = "미장착";
						System.out.println("생화학탄 미장착\n");
					}
				} 
				m.stealthFunction = Prompt.inputString("스텔스 기능 [추가/미추가] : ");
				if(m.stealthFunction.equals("추가")) {
					System.out.println("스텔스 기능 추가 완료");
				}
				else {
					System.out.println("스텔스 기능 미추가");
				}

				m.effectiveRange = 5000;
				c.cruiseCount++;

			}

			else if(missileNumber == 3) {
				m.missile = "단거리미사일";

				m.nuclearBomb = Prompt.inputString("핵탄두 [장착/미장착] : ");
				if (m.nuclearBomb.equals("장착")) {
					System.out.println("핵탄두 장착 완료\n");
					m.biochemistryBomb = "미장착";
				}
				else {
					System.out.println("핵탄두 미장착\n");
					m.biochemistryBomb = Prompt.inputString("생화학탄 [장착/미장착] : ");
					if (m.biochemistryBomb.equals("장착")) {
						System.out.println("생화학탄 장착 완료\n");
					}
					else {
						m.biochemistryBomb = "미장착";
						System.out.println("생화학탄 미장착\n");
					}
				} 
				m.stealthFunction = Prompt.inputString("스텔스 기능 [추가/미추가] : ");
				if(m.stealthFunction.equals("추가")) {
					System.out.println("스텔스 기능 추가 완료");
				}
				else {
					System.out.println("스텔스 기능 미추가");
				}
				m.effectiveRange = 350;
				c.patriotCount++;

			}


			else {
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("초기 화면으로 돌아갑니다.");
				break;
			}

			missiles[c.devCount++] = m;
			System.out.println();
			response = Prompt.inputString("계속 개발하시겠습니까?[y/N] : ");
			if (!response.equalsIgnoreCase("y")) {
				break;
			}
			System.out.println();
		}
	}

	public static void list() {
		System.out.println("[미사일 목록]");
		for (i = 0; i < c.devCount; i++) {
			Missile m = missiles[i];
			if (m.index != 0) {
				System.out.printf("미사일 번호 : [%d]  ▶  미사일 이름 : [%s]  ▶  핵탄두 장착 여부 : [%s]\n", m.index, m.missile, m.nuclearBomb);
				System.out.printf("생화학탄 장착 여부 : [%s]  ▶  스텔스 기능 : [%s]  ▶  사정거리 : [%dKM]\n", m.biochemistryBomb, m.stealthFunction, m.effectiveRange);
				System.out.println("--------------------------------------------------------------------------------------");
			}
		}
		System.out.printf("탄도미사일 : %d대  순항미사일 : %d대  단거리미사일 : %d대\n", c.icbmCount, c.cruiseCount, c.patriotCount);
		System.out.println();
	}

	public static void launch() {

		int i;
		int j;
		if (c.icbmCount == 0 && c.cruiseCount == 0 && c.patriotCount == 0) {
			System.out.println("미사일이 부족합니다.\n");
		}
		else {
			System.out.println("[미사일 발사]\n");
			list();
			launch = Prompt.inputInt("미사일 번호 선택 : ");

			launch--;
			missiles[launch].index = 0;

			System.out.println();
			System.out.printf("%s 발사 준비 완료\n", missiles[launch].missile);
			System.out.println();

			if (missiles[launch].missile.equals("탄도미사일")) {
				nation = Prompt.inputString("타격권 : 미국   러시아   이란   호주   캐나다\n         인도   뉴질랜드   파키스탄\n\n공격할 국가 선택 : ");
				while(true) {
					if (nation.equals("미국") || nation.equals("러시아") || nation.equals("이란") || 
							nation.equals("호주") || nation.equals("캐나다") || nation.equals("인도")
							|| nation.equals("뉴질랜드") || nation.equals("파키스탄")) {
						break;
					}
					else {
						System.out.println();
						nation = Prompt.inputString("잘못 입력하셨습니다.\n"
								+ "타격권 : 미국   러시아   이란   호주   캐나다\n         인도   뉴질랜드   파키스탄\n\n공격할 국가 선택 : ");
						if (nation.equals("미국") || nation.equals("러시아") || nation.equals("이란") || 
								nation.equals("호주") || nation.equals("캐나다") || nation.equals("인도")
								|| nation.equals("뉴질랜드") || nation.equals("파키스탄")) {
							break;
						}
					}
				}

				flag = 0;

				if (missiles[launch].nuclearBomb.equals("장착")) {
					for(i = 1, j = 1; i <= nuclearDamage; i += 1321, j+= 15) {
						System.out.printf("인명피해 : %d명   폭발반경 : %dM  핵탄두 장착으로 인한 피해량 x10\n", i, j);
						flag = 1;
					}
				}
				else if (missiles[launch].biochemistryBomb.equals("장착")){
					for(i = 1, j = 1; i <= biochemistryDamage; i += 147, j += 2) {
						System.out.printf("인명피해 : %d명   폭발반경 : %dM  생화학탄 장착으로 인한 피해랑 x5\n", i, j);
						flag = 2;
					}
				}
				else {
					for(i = 1, j = 1; i <= bombDamage; i += 13, j++) {
						System.out.printf("인명피해 : %d명   폭발반경 : %dM\n", i, j);
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
				c.icbmCount--;

			}
			else if (missiles[launch].missile.equals("순항미사일")) {
				nation = Prompt.inputString("타격권 : 몽골   필리핀   대만   태국   베트남   인도네시아\n\n공격할 국가 선택 : ");
				while(true) {
					if (nation.equals("몽골") || nation.equals("필리핀") || nation.equals("대만") || 
							nation.equals("인도네시아") || nation.equals("베트남") || nation.equals("태국")) {
						break;
					}
					else {
						System.out.println();
						nation = Prompt.inputString("잘못 입력하셨습니다.\n"
								+ "타격권 : 몽골   필리핀   대만   태국   베트남   인도네시아\n\n공격할 국가 선택 : ");
						if (nation.equals("몽골") || nation.equals("필리핀") || nation.equals("대만") || 
								nation.equals("인도네시아") || nation.equals("베트남") || nation.equals("태국")) {
							break;
						}
					}
				}

				flag = 0;

				if (missiles[launch].nuclearBomb.equals("장착")) {
					for(i = 1, j = 1; i <= nuclearDamage; i += 1321, j+= 15) {
						System.out.printf("인명피해 : %d명   폭발반경 : %dM  핵탄두 장착으로 인한 피해량 x10\n", i, j);
						flag = 1;
					}
				}
				else if (missiles[launch].biochemistryBomb.equals("장착")){
					for(i = 1, j = 1; i <= biochemistryDamage; i += 147, j += 2) {
						System.out.printf("인명피해 : %d명   폭발반경 : %dM  생화학탄 장착으로 인한 피해랑 x5\n", i, j);
						flag = 2;
					}
				}
				else {
					for(i = 1, j = 1; i <= bombDamage; i += 13, j++) {
						System.out.printf("인명피해 : %d명   폭발반경 : %dM\n", i, j);
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

				c.cruiseCount--;
			}

			else if (missiles[launch].missile.equals("단거리미사일")) {

				nation = Prompt.inputString("타격권 : 북한   중국   일본\n\n공격할 국가 선택 : ");
				while(true) {
					if (nation.equals("북한") || nation.equals("중국") || nation.equals("일본")) {
						break;
					}
					else {
						System.out.println();
						nation = Prompt.inputString("잘못 입력하셨습니다.\n"
								+ "타격권 : 북한   중국   일본\n\n공격할 국가 선택 : ");
						if (nation.equals("북한") || nation.equals("중국") || nation.equals("일본")) {
							break;
						}
					}
				}

				flag = 0;

				if (missiles[launch].nuclearBomb.equals("장착")) {
					for(i = 1, j = 1; i <= nuclearDamage; i += 1321, j+= 15) {
						System.out.printf("인명피해 : %d명   폭발반경 : %dM  핵탄두 장착으로 인한 피해량 x10\n", i, j);
						flag = 1;
					}
				}
				else if (missiles[launch].biochemistryBomb.equals("장착")){
					for(i = 1, j = 1; i <= biochemistryDamage; i += 147, j += 2) {
						System.out.printf("인명피해 : %d명   폭발반경 : %dM  생화학탄 장착으로 인한 피해랑 x5\n", i, j);
						flag = 2;
					}
				}
				else {
					for(i = 1, j = 1; i <= bombDamage; i += 13, j++) {
						System.out.printf("인명피해 : %d명   폭발반경 : %dM\n", i, j);
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

				c.patriotCount--;
			}

			missiles[launch].missile = null;
			missiles[launch].nuclearBomb = null;
			missiles[launch].biochemistryBomb = null;
			missiles[launch].stealthFunction = null;
			missiles[launch].effectiveRange = 0;
		}
	}

}
