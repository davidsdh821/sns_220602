package com.sns.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;

@Service
public class CommentBO {

	
	@Autowired
	private CommentDAO commentDAO;
	
	public void addComment(int postId, int userId, String comment) {
		
		 commentDAO.insertComment(postId, userId, comment);
		
	}
	
	public List<Comment> getCommentListByPostId(int postId) {
		
		return commentDAO.selectCommentListByPostId(postId);
		
	}
	
	//postId -> List<CommentView> => comment패키지에서
	public List<CommentView> generateCommentViewListBy(int postId) {
		List<Comment> commentList = getCommentListByPostId(postId);
		
		//이곳에서 List<CommentView>로 바꿔줘야 한다 timelineBO에서 변환하는 것과 비슷한 방식이라고 한다
		
		
	}
 	
	
}
