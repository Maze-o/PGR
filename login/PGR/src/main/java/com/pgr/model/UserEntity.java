package com.pgr.model;

import org.apache.ibatis.type.Alias;

@Alias("UserEntity")
public class UserEntity {
	private int userPk;
	private String userEmail;
	private String emailchk;
	private String userPw;
	private String userPwRe;
	private String nickname;
	private String regDt;

	public String getUserPwRe() {
		return userPwRe;
	}

	public void setUserPwRe(String userPwRe) {
		this.userPwRe = userPwRe;
	}

	public int getUserPk() {
		return userPk;
	}

	public void setUserPk(int userPk) {
		this.userPk = userPk;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getEmailchk() {
		return emailchk;
	}

	public void setEmailchk(String emailchk) {
		this.emailchk = emailchk;
	}

}
