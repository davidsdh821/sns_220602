package com.sns.timeline;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.timeline.bo.TimeLineBO;
import com.sns.timeline.model.CardView;

@RequestMapping("/post")
@Controller
public class TimeLineController {
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private TimeLineBO timelineBO;
	
	//이 패키지에는 dao가 필요가 없다.
	//bo는 필요하다.
	//http:/localhost/post/post_timeline_view
	@RequestMapping("/post_timeline_view")
	public String timeline(Model model, HttpSession session) {
//		List<Post> postList = postBO.getPost();
//		model.addAttribute("result", postList); 
		//bo한테 데이터를 넣을때 필요한 값만 넣자

		List<CardView> cardViewList = timelineBO.generateCardViewList((Integer) session.getAttribute("userId"));
		model.addAttribute("cardViewList", cardViewList);
		
		
		
		model.addAttribute("viewName", "post/timeline"); 
		return "template/layout";
	}

}
