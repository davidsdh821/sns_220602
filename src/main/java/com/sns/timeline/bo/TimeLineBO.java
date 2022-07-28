package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

//이곳은 화면용 bo이다
@Service
public class TimeLineBO {
	//자기 자신의 dao는 부를 수 있지만 남의 dao는 부르면 안된다
	//상호참조 하면 안된다 bo에서 bo를 '서로' 부른 것은 안된다
	@Autowired
	private PostBO postBO;
	
	@Autowired 
	private CommentBO commentBO;
	
	@Autowired
	private UserBO userBO;
	
	
	public List<CardView> generateCardViewList(Integer userId) {
		List<CardView> result = new ArrayList<>();
		
		
		//글 목록을 가져온다
		List<Post> postList = postBO.getPost();
		for(Post post : postList) {
			CardView card = new CardView(); //반복문 안에 있어야 한다.
			//글정보
			card.setPost(post); //카드에 글 하나를 집어넣음
			
			//글쓴 유저 정보
			User user = userBO.getUserById(post.getUserId()); 
			card.setUser(user); //유저를 넣음
			
			//1:N 글:댓글 댓글들 정보
			//가져오는 댓글이 어떤 글의 댓글인지 판별해야함
			//이곳에서는 오로지 데이터를 가져와 출력해주는 구조로 해줘야한다,
			// 예List<Comment> List<CommentView>로 만들어주는 것은 CommentBO에서 해야한다
			int postId = post.getId(); //글번호
			List<CommentView> commentList = commentBO.generateCommentViewListBy(postId);
			
			card.setCommentList(commentList);
			
			//내가 좋아요 한지 여부

			
			//좋아요의 개수
			
			
			
			
			
			//결과 리스트에 카드 저장
			result.add(card);
		
		}

		
		
		return result;
		
	}
	
}
