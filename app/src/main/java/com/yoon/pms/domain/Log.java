package com.yoon.pms.domain;

import com.yoon.util.CsvObject;

public class Log implements CsvObject {

	// 아이디 구분 로그인 시간, 
	// 아이디 구분 로그아웃 시간, 접속 유지 시간
	private String id;
	private String loginTime;
	private String logoutTime;

	public Log() {}

	public Log(String csv) {
		String[] fields = csv.split(",");
		this.setId(fields[0]);
		this.setLoginTime(fields[1]);
		this.setLogoutTime(fields[2]);
	}

	@Override
	public String toCsvString() {
		return String.format("%s,%s,%s", 
				this.id,
				this.loginTime,
				this.logoutTime);
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((loginTime == null) ? 0 : loginTime.hashCode());
		result = prime * result + ((logoutTime == null) ? 0 : logoutTime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Log other = (Log) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (loginTime == null) {
			if (other.loginTime != null)
				return false;
		} else if (!loginTime.equals(other.loginTime))
			return false;
		if (logoutTime == null) {
			if (other.logoutTime != null)
				return false;
		} else if (!logoutTime.equals(other.logoutTime))
			return false;
		return true;
	}


}
