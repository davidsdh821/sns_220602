package com.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	
	//http:/localhost/user/sign_up_view
	@RequestMapping("/sign_up_view")
	public String signup(Model model) {
		
		model.addAttribute("viewName", "user/sign_up");
		
		return "template/layout";
	}
	
	//http:/localhost/user/sign_in_view
	@RequestMapping("/sign_in_view")
	public String signin(Model model) {
		
		model.addAttribute("viewName", "user/sign_in");
		return "template/layout";
	}
	@RequestMapping("/sign_out")
	public String signout() {
		
		return "redirect:/user/sign_in";
	}
	
	
}
