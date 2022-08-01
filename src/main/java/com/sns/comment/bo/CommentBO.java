package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class CommentBO {
	@Autowired
	private UserBO userBO;
	
	
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
		List<CommentView> resultList = new ArrayList<>();
		
		List<Comment> commentList = getCommentListByPostId(postId);
		
		//이곳에서 List<CommentView>로 바꿔줘야 한다 timelineBO에서 변환하는 것과 비슷한 방식이라고 한다
		
		for(Comment comment : commentList) {
			CommentView commentView = new CommentView();
			//댓글내용
			commentView.setComment(comment);
			
			//댓글 쓴이
			int userId = comment.getUserId();
			User user = userBO.getUserById(userId);
			commentView.setUser(user);
			
			//결과 리스트에 넣기
			resultList.add(commentView);
			
		}
		
		return resultList;
	}
 	
	
	public int deleteEveryCommentByPostId(int postId) {
		
		return commentDAO.deleteEveryCommentByPostId(postId);
	}

	public void deleteCommentByCommentId(int commentId) {
		
		commentDAO.deleteCommentByCommentId(commentId);
		
	}
	
}
