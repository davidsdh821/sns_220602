package com.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserController {
	
	
	@RequestMapping("/user/sign_up")
	public String signup() {
		
		return "template/layout";
	}
	
	
}
