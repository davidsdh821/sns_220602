package com.sns.like.bo;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	
	public void Checkedlike(int postId, int userId) {
			boolean check = likeDAO.selectCheckLike(postId, userId);
		if(check == true) {
			likeDAO.deleteLike(postId, userId);
		} else {
			likeDAO.insertLike(postId, userId);
		}
		
	}
	
	public int getCountLike(int postId) {
		
		return likeDAO.selectCountLike(postId);
	}
	

	
	

}
