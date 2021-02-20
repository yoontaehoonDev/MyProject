package com.yoon.pms.handler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.yoon.pms.domain.BuyerMember;
import com.yoon.pms.domain.Log;
import com.yoon.pms.domain.SellerMember;

public class LogAddHandler extends AbstractLogHandler {

	public LogAddHandler(List<Log> logList) {
		super(logList);
	}

	@Override
	public void service() {
		BuyerMember b = AbstractMemberHandler.buyerMemberNumber;
		SellerMember s = AbstractMemberHandler.sellerMemberNumber;

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat login = new SimpleDateFormat("로그인 : yyyy년 MM월 dd일 HH시 mm분 ss초\n");
		SimpleDateFormat logout = new SimpleDateFormat("로그아웃 : yyyy년 MM월 dd일 HH시 mm분 ss초\n");
		String loginTime = login.format(cal.getTime());
		String logoutTime = logout.format(cal.getTime());

		Log log = new Log();

		log.setLoginTime(null);

	}

}
