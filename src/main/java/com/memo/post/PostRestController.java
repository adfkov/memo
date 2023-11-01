package com.memo.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memo.post.BO.PostBO;
import com.memo.user.Entity.UserEntity;

@RequestMapping("/post")
@RestController
public class PostRestController {
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String ,Object> create(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		// session 에 들어있는 userId를 꺼낸다.
		int userId = (int)session.getAttribute("userId");
		
		// db insert
		postBO.addPostList(userId,subject, content);
	
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
}
