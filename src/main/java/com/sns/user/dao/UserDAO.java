package com.sns.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sns.user.model.User;

@Repository
public interface UserDAO {

	public List<User> SelectUserList();
	
	public boolean selectUserId(String loginId);
	
	public void insertUser(String loginId, String password, String name, String email);
	
	
	
}
