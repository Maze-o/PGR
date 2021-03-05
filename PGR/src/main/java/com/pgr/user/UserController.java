package com.pgr.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgr.Const;
import com.pgr.SecurityUtils;
import com.pgr.model.UserDomain;
import com.pgr.model.UserEntity;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/join")
	public String join() {
		return "menus/sign_up";
	} // 회원가입 화면 맵핑

	@ResponseBody
	@PostMapping("/join") // 회원가입 정보 DB 전달
	public Map<String, Object> join(@RequestBody UserEntity p) throws Exception {
		Map<String, Object> map = new HashMap<>();
		System.out.println("DB EMAIL : " + p.getUserEmail());
		map.put(Const.KEY_RESULT, service.join(p));

		return map;
	}

	@GetMapping("/login")
	public String login() {
		return "menus/sign_in";
	}

	@ResponseBody
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody UserEntity p, HttpSession hs) throws Exception {

		Map<String, Object> map = new HashMap<>();
		map.put(Const.KEY_RESULT, service.login(p, hs));
		return map;
	}

	@GetMapping("/logout")
	public String logout(HttpSession hs) {
		hs.invalidate();
		return "redirect:/";
	}

	@GetMapping("/findpw")
	public String pw_find() {
		return "menus/findpw";
	}

	@ResponseBody
	@PostMapping("/findpw")
	public Map<String, Object> findPw(@RequestBody UserEntity p) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put(Const.KEY_RESULT, service.findPw(p));
		return map;
	}
	
	@GetMapping("/mypage")
	public String myPage() {
		return "menus/mypage";
	}

	@ResponseBody
	@PostMapping("/mypage")
	public Map<String, Object> pwChange(@RequestBody UserDomain p, HttpSession hs) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put(Const.KEY_RESULT, service.profileChange(p, hs));
		return map;
	}

}
