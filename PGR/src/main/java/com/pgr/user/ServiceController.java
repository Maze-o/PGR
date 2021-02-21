package com.pgr.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServiceController {

	@Autowired
	private EmailServiceImpl service;

	private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);
	
	@PostMapping("/email")
	@ResponseBody
	public void emailConfirm(@RequestBody String m) throws Exception {
		logger.info("post emailConfirm");
		service.sendSimpleMessage(m);
	}

	@PostMapping("/verifyCode")
	@ResponseBody
	public int verifyCode(@RequestBody String code) {
		logger.info("Post verifyCode");

		int result = 0;
		if (EmailServiceImpl.ePw.equals(code)) {
			result = 1;
		}

		return result;
	}
}
