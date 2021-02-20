package com.yoon.pms.handler;

import java.util.List;

import com.yoon.pms.domain.Log;

public abstract class AbstractLogHandler implements Command{

	protected List<Log> logList;

	public AbstractLogHandler(List<Log> logList) {
		this.logList = logList;
	}

}
