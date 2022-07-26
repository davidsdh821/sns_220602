package com.sns.user.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.dao.UserDAO;
import com.sns.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	UserDAO userDAO;
	
	
	public List<User> getUserList() {
		
		
		return userDAO.SelectUserList();
	}
	
	public boolean getUserId(String loginId) {
		
		return userDAO.selectUserId(loginId);
	}
	
	public void addUser(String loginId, String password, String name, String email) {
		
		userDAO.insertUser(loginId, password, name, email);
	}
	
	public User getUserData(String loginId, String password) { //비밀번호가 암호화 됬다는 것을 알려줄 필요가 없다
		//어차피 서버로 보내지는 것이기 때문에 비밀번호를 가로 챌 수 가 없다(https기준)
		return userDAO.selectUserData(loginId, password);
	}
	
	public User getUserById(int id) {
		
		return userDAO.selectUserById(id);
		
	}
	
	
	
	
	
	
	
}
