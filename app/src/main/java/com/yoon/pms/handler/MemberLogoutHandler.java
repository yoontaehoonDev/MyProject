package com.yoon.pms.handler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.yoon.pms.App;
import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Log;
import com.yoon.pms.domain.SellerMember;

public class MemberLogoutHandler extends AbstractMemberHandler{

	private List<Log> logList;
	public MemberLogoutHandler(List<BuyerMember> buyerMemberList, List<SellerMember> sellerMemberList, List<Log> logList) {
		super(buyerMemberList, sellerMemberList);
		this.logList = logList;
	}

	@Override
	public void service() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat logout = new SimpleDateFormat("님 로그아웃 : yyyy년 MM월 dd일 HH시 mm분 ss초");

		String temp;
		logCount = false;
		Log log = new Log();
		if(logStatus == 1) {
			temp = sellerMemberNumber.getId() + logout.format(cal.getTime());
			log.setLogoutTime(temp);
			sellerMemberNumber.setDivision(false);
			AbstractSellerBoardHandler.boardAuthorization = false;
		}
		else if (logStatus == 0){
			temp = buyerMemberNumber.getId() + logout.format(cal.getTime());
			log.setLogoutTime(temp);
			buyerMemberNumber.setDivision(false);
			AbstractBuyerBoardHandler.boardAuthorization = false;
		}


		logList.add(log);
		App.location = -1;
		logStatus = -1;
		System.out.println("로그아웃\n");
	}



}
