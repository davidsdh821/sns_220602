package com.sns.user;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@RequestMapping("/user")
@RestController
public class UserRestController {
	@Autowired
	UserBO userBO;
	
	//http://localhost/user/user_list
	@RequestMapping("/user_list")
	public List<User> userList() {
		
		
		
		return userBO.getUserList();
	}
	//중복확인
	@RequestMapping("/is_dup")
	public Map<String, Object> dup(
			@RequestParam("loginId") String loginId
			) {
		
		
		
		boolean exist = userBO.getUserId(loginId);
		
		Map<String, Object> result = new HashMap<>();
 		result.put("exist", exist);
		
		
		
		return result;
	}
	
	
	@PostMapping("sign_up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email
			) {
		//비밀번호 암호화
		String encryptUtils = EncryptUtils.md5(password);
		
		
		//db insert
		userBO.addUser(loginId, password, name, email);
		
		
		//결과 출력
		Map<String, Object> result =  new HashMap<>();
		result.put("result", "success");
		
		
		return result;
	}
	
	//로그인
	@PostMapping("sign_in")
	public Map<String, Object> signIn(
			@RequestParam("logigId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request //세션에 저장
			) {
		//비밀번호 해싱
		String encrypt = EncryptUtils.md5(password);
		
		//db insert
		User user = userBO.getUserData(loginId, encrypt); //암호화된 비밀번호
		//데이터가 db에 남기 때문에 암호화할 필요가 있다.(개발자들도 보면 안되기 때문에 암호화를 시켜주는 것이다)
		
		//세션에 남길 값 만들기
		HttpSession session = request.getSession();
		session.setAttribute("userId", user.getId());
		session.setAttribute("loginId", user.getLoginId());
		session.setAttribute("userName", user.getName());
		
		
		//리턴
		Map<String, Object> result = new HashMap<>();
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	 
}
