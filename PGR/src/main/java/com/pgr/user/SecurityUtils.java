package com.pgr.user;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.pgr.Const;
import com.pgr.model.UserEntity;

@Component
public class SecurityUtils {
	public UserEntity getLoginUser(HttpSession hs) {
		return (UserEntity)hs.getAttribute(Const.KEY_LOGINUSER);
	}
	
	public String getSalt() {
		return BCrypt.gensalt();
	}

	public String getHashPw(String pw, String salt) {
		return BCrypt.hashpw(pw, salt);
	}

}

