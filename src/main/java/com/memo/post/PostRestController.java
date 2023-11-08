package com.memo.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.memo.post.BO.PostBO;

@RequestMapping("/post")
@RestController
public class PostRestController {
	@Autowired
	private PostBO postBO;
	
	/**
	 * 글쓰기 API
	 * @param subject
	 * @param content
	 * @param file
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String ,Object> create(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		// session 에 들어있는 userId를 꺼낸다.
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String) session.getAttribute("userLoginId");
		
		// db insert
		postBO.addPostList(userId, userLoginId ,subject, content, file);
	
		result.put("code", 200);
		result.put("result", "성공");
//		
		// 응답값
//		if(a != null) {
//			// 성공
//			
//		} else {
//			result.put("code", 500);
//			result.put("result", "문제 발생");
//		}
		return result;
		
	}
	
		@PutMapping("/update")
		public Map<String, Object> update(
				@RequestParam("postId") int postId,
				@RequestParam("subject") String subject,
				@RequestParam("content") String content,
				@RequestParam(value = "file", required = false) MultipartFile file,
				HttpSession session) {
			int userId = (int)session.getAttribute("userId");
			String userLoginId = (String) session.getAttribute("userLoginId");
			
			// db update
			postBO.updatePost(userId, userLoginId, postId, subject, content, file);
			
			Map<String, Object> result = new HashMap<>();
			
			result.put( "code" , 200 );
			result.put("success", "성공");
			return result;
		}
}
