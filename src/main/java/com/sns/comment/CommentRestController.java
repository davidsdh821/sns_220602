package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

@RestController
@RequestMapping("/comment")
public class CommentRestController {
	@Autowired
	private CommentBO commentBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("postId") int postId,
			@RequestParam("comment") String comment,
			 HttpSession session) {
		Map<String, Object> result2 = new HashMap<>();
		
		//댓글다는 사용자 정보
		int userId = (int) session.getAttribute("userId");
		Object userIdObject =  session.getAttribute("userId"); //세션의 값이 잘 안 꺼내진다면, 로그인 한 곳에서 userId setAttribute가 됬는지 확인해야한다.
		if(userIdObject == null) { //로그인 되어있지 않으면
			result2.put("put", "error");
			result2.put("errorMessage", "로그인 후 사용 가능합니다");
			return result2;
		}
		
		//insert
		commentBO.addComment(postId, userId, comment);
		result2.put("result2", "success");
		
		return result2;
	}
	
}
