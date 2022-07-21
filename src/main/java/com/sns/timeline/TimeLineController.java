package com.sns.timeline;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class TimeLineController {
	//이 패키지에는 dao가 필요가 없다.
	//bo는 필요하다.
	//http:/localhost/user/sign_up_view
	@RequestMapping("/timeline_view")
	public String signup(Model model) {
		
		model.addAttribute("viewName", "post/timeline");
		
		return "template/layout";
	}

}
