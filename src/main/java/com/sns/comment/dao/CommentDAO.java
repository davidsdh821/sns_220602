package com.sns.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.comment.model.Comment;

@Repository
public interface CommentDAO {
	
	public void insertComment(
			@Param("postId") int postId, 
			@Param("userId") int userId, 
			@Param("comment") String comment);
	
	public List<Comment> selectCommentListByPostId(int postId);
	
	public int deleteEveryCommentByPostId(int postId);
	
}
