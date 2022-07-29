package com.sns.post.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.Comment;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.like.model.Like;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {
	private Logger logger = LoggerFactory.getLogger(this.getClass()); // 이방식이 편하다
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired 
	private LikeBO likeBO;
	

	//이곳에서 이미지 매핑, 파일 처리
	public void addPost(int userId, String userLoginId, String content, MultipartFile file) {
		String imagePath = null; //있을 때 없을 때를 위해 둘다 처리
		//하지만 지금은 파일이 무조건 있어야 함으로 필요없지만 만약을 위해 만들어 둔다.
		
		if(file != null) {
			imagePath = fileManagerService.saveFile(userLoginId, file);
			//경로 받음
		}
		postDAO.insertPost(userId, content, imagePath);
		
	}
	
	public List<Post> getPost() {
		
		return postDAO.selectPost();
	}
	
	public Post getPostBypPostId(int postId) {
		
		return postDAO.selectPostBypPostId(postId);
	}
	
	public void deletePostByPostId(int postId) {
		//사진, 댓글등 모든 내용들을 지워버려야 한다
		//삭제할 글을 가져온다(select)
		Post post = getPostBypPostId(postId);
		if(post == null) {
			logger.error("[delete post] 삭제할 게시글이 존재하지 않습니다. psotId{}", postId); //이런식으로 계속 확인을 해줘야한다. 비정상적인 루트로 이곳에 들어 올 수 있기 때문이다
			return;
		}
		//이미지가 있으면 이미지 삭제
		if(post.getImagePath() != null) {
			try {
				fileManagerService.deleteFile(post.getImagePath());
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("이미지 삭제 실패. postId{}", postId);// 
			}
		}
		
		
		//글 삭제
		postDAO.deletePostByPostId(postId);
		
		
		//댓글 가져오기
		List<Comment> comment = commentBO.getCommentListByPostId(postId);
		//댓글이 있으면 댓글 삭제 byPostId
		if(comment != null) {
			commentBO.deleteEveryCommentByPostId(postId);
		} else {
			return;
		}
		
		
		int like = likeBO.getCountLike(postId);
 		//좋아요 삭제 byPostId
		if(like != 0) {
			likeBO.deleteEveryLikeByPostId(postId);
		} else {
			return;
		}
		
		
	}
	
	
	
}
