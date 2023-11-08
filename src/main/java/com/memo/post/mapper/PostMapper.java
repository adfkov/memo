package com.memo.post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.memo.post.domain.Post;

@Repository
public interface PostMapper {
	public List<Map<String, Object>> selectPostList();
	
	public List<Post> selectPostListByUserId(int userId);
	
	public Integer insertPostList
			(@Param("userId") int userId
			,@Param("subject") String subject 
			,@Param("content") String content
			,@Param("imagePath") String imagePath);
	
	public Post selectPostByPostIdUserId
			(@Param("postId") int postId,
			@Param("userId") int userId);
	
	public void updatePostByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId,
			@Param("subject") String subject, 
			@Param("content") String content,
			@Param("imagePath") String imagePath);


}
