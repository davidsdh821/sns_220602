package com.sns.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.post.model.Post;

@Repository
public interface PostDAO {

	//userLoginId는 postbo에만 필요하다
	public void insertPost(
		@Param("userId") int userId, 
		@Param("content") String content, 
		@Param("imagePath") String imagePath);
	
	public List<Post> selectPost(); 
	
}
