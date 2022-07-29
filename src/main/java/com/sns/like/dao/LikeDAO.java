package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {

	public int selectCountLike(int postId);
	
	public boolean selectCheckLike(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	public void insertLike(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	
	public void deleteLike(			
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	public int deleteEveryLikeByPostId(int postId);
	
}
