package com.memo.post.BO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.common.FileManagerService;
import com.memo.post.domain.Post;
import com.memo.post.mapper.PostMapper;

@Service
public class PostBO {
	@Autowired
	private PostMapper postMapper;
	@Autowired
	private FileManagerService fileManager; // Componet 어노테이션으로 spring bean 이 되었다.
	
	// input : userId  // output: List<Post>
	public List<Post> getPostListByUserId(int userId) {
		return postMapper.selectPostListByUserId(userId);
	}
	// input : postId, userId output: post
	public Post getPostByPostIdUserId(int postId, int userId) {
		return postMapper.selectPostByPostIdUserId(postId, userId);
	}
	
	
	public void addPostList(int userId ,String userLoginId ,String subject, String content , MultipartFile file) {
		String imagePath = null;
		
		//이미지가 있으면 업로드 => 클래스로 분리하여 재활용
		if(file != null) {
			imagePath = fileManager.saveFile(userLoginId, file); // 예언 리턴값을 imagePath에 저장
			
		}
		
		postMapper.insertPostList(userId ,subject, content , imagePath);
	}
}
