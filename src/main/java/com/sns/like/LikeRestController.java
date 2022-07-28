package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

@RestController
public class LikeRestController {
	
	
	@Autowired
	private LikeBO likeBO;
	
	//좋아요/해제
	@RequestMapping("/like/{postId}")
	public Map<String, Object> like (
			@PathVariable int postId, //mapping의 post숫자와 같아야 한다
			HttpSession session ) {
		Map<String, Object> result = new HashMap<>();
	
		
		//가져 올 것, 유저 테이블, post 테이블, 유저 데이터 위에서 postId는 가져왔다
		
		//누르는 사용자 정보
		Object userIdObject =session.getAttribute("userId");
		if(userIdObject == null) { //로그인 되어있지 않으면
			result.put("result2", "error");
			result.put("errorMessage", "로그인 후 사용 가능합니다");
			return result;
		}
		int userId = (int) session.getAttribute("userId");
		
		//
		likeBO.Checkedlike(postId, userId);
		
		
		
		
		result.put("result", "success");
		return result;
	}
	
}
