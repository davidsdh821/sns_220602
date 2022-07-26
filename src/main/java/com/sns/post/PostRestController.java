package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;

@RequestMapping("/post")
@RestController
public class PostRestController {

	@Autowired
	PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, Object> Create(
			@RequestParam("content") String content,
			@RequestParam("file") MultipartFile file,
			HttpSession session 
			) {
		Map<String, Object> result = new HashMap<>();
		//로그인 된 사용자 정보
		Integer userId = (Integer) session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		//세션에서 정보 가져오기
		Object userIdObject =  session.getAttribute("userId"); //세션의 값이 잘 안 꺼내진다면, 로그인 한 곳에서 userId setAttribute가 됬는지 확인해야한다.
		if(userIdObject == null) { //로그인 되어있지 않으면
			result.put("put", "error");
			result.put("errorMessage", "로그인 후 사용 가능합니다");
			return result;
		}
		
		//insert
		postBO.addPost(userId, userLoginId, content, file);
		result.put("result", "success");
		
		return result;
	}

	
}
