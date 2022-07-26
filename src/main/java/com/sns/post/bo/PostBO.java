package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	

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
	
	
	
}
