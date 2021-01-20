package com.yoon.project.Handler;

public class CountryStatus {

	static final int TOTAL = 17;

	public static void show() {
		for (int i = 0; i < TOTAL; i++) {
			if (MissileHandler.population[i] < 0) {
				System.out.printf("%s : 멸망\n", MissileHandler.nations[i]);
			}
			else {
				System.out.printf("%s 인구 : %d명  - 사망자 수 : %d명\n", MissileHandler.nations[i], MissileHandler.population[i], MissileHandler.deadPeople[i]);
			}
		}
	}
}
