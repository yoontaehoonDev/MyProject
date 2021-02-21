package com.yoon.pms.handler;

import java.util.Iterator;
import java.util.List;

import com.yoon.pms.domain.Log;

public class LogHandler implements Command {

	protected List<Log> logList;

	public LogHandler(List<Log> logList) {
		this.logList = logList;
	}

	@Override
	public void service() {
		Iterator<Log> iterator = logList.iterator();

		System.out.println("------------------------ 접속 기록 ------------------------");
		while(iterator.hasNext()) {
			Log log = iterator.next();
			System.out.println(log.getLoginTime());

		}
		System.out.println("-----------------------------------------------------------");
	}

}
