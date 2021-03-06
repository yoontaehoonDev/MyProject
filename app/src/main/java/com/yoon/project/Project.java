package com.yoon.project;

import com.yoon.project.Handler.CountryStatus;
import com.yoon.project.Handler.MissileHandler;

public class Project {

	public static void main(String[] args) {
		final int MI_DEV = 1;
		final int MI_LIST = 2;
		final int MI_LAUNCH = 3;
		final int Co_Status = 4;
		MissileHandler MissileList = new MissileHandler();
		CountryStatus Status = new CountryStatus();

		while(true) {

			Menu.choice();

			if (Menu.id == MI_DEV) {

				MissileList.add();

			}
			else if(Menu.id == MI_LIST) {

				MissileList.list();

			}

			else if (Menu.id == MI_LAUNCH) {

				MissileList.launch();

			}
			else if (Menu.id == Co_Status) {

				Status.show();

			}
			else {
				System.out.println("미사일 시스템 종료");
				break;
			}
		}
	}
}
