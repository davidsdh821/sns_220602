package com.sns.like;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeRestController {
	
	//좋아요/해제
	@RequestMapping("/like/{postId)")
	public Map<String, Object> like (
			@PathVariable int postId //mapping의 post숫자와 같아야 한다
			) {
		
		
		
		return null;
	}
	
}
