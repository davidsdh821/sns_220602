package com.sns.timeline.model;

import java.util.List;

import com.sns.comment.model.CommentView;
import com.sns.post.model.Post;
import com.sns.user.model.User;

public class CardView {
	private User user; //글쓴이 ${card.user.name}
	private Post post; // 글 내용 ${card.post.userId}
	private List<CommentView> commentList; //댓글 내용 ${card.commentList.comment.comment}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public List<CommentView> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentView> commentList) {
		this.commentList = commentList;
	}
	
	
//	private List<Like> likeList;
	//getters, setter

	



	
}
