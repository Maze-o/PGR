package com.pgr.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {

	@Autowired
	private EmailService service;

	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

	@PostMapping("/email")
	@ResponseBody
	public void emailConfirm(@RequestBody String m, Model model) throws Exception {
		logger.info("post emailConfirm");
		System.out.println("전달 받은 이메일 : " + m);
		service.sendSimpleMessage(m);
	}

	@PostMapping("/verifyCode")
	@ResponseBody
	public int verifyCode(@RequestBody String code, Model model) {
		logger.info("Post verifyCode");

		int result = 0;
		if (EmailService.ePw.equals(code)) {
			result = 1;
		}

		return result;
	}
}
