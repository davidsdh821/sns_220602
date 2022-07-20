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
	
}
