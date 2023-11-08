package com.memo.post.BO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.common.FileManagerService;
import com.memo.post.domain.Post;
import com.memo.post.mapper.PostMapper;



@Service
public class PostBO {
	private Logger logger = LoggerFactory.getLogger(this.getClass()); // this = postBO , Slf4j 로 선택한다.
	
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
	
	// INPUT: 파라미터들 	OUTPUT: X
	public void updatePost(int userId, String userLoginId,
				int postId, String subject,
				String content, MultipartFile file) {
		// 이유1: 이미지 교체 시 기존의 이미지가 붕 뜨지 않게 폴더에서 지운다.
		// 이유2: validation 목적 그러려면 기존 글을 가져와야 한다.
		Post post = postMapper.selectPostByPostIdUserId(postId, userId);
		if (post == null) {
		//	logger.info(); // 그럴 수 있지
		//	logger.error(); // 이상한데?
			logger.error("[글 수정] post is null. post id: {}, userId: {}", postId, userId);
			return;
			
		}
		
		// 파일이 있다면
		// 1) 새 이미지를 업로드 한다.
		// 2) 새 이미지 업로드 성공 시 기존 이미지 제거(기존 이미지가 있을 때)
		String imagePath = null;
		if (file != null) {
			// 업로드
			// 실패하면 null을 리턴한다.
			imagePath = fileManager.saveFile(userLoginId, file);
			// 업로드 성공 시 기존 이미지 제거(있으면)
			if(imagePath != null && post.getImagePath() != null) { 
				// 업로드가 성공을 했고, 기존 이미지가 존재한다면 => 삭제
				// 이미지 제거
				if(imagePath != null && post.getImagePath() != null) {
					fileManager.deleteFile(post.getImagePath());
				}
			}
		}
		
		
		// DB 글 update
		postMapper.updatePostByPostIdUserId(postId, userId, subject, content, imagePath); // 로그인 아이디는 내리지 않는다.
	}
}
