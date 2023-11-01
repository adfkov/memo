package com.memo.post;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.memo.post.BO.PostBO;
import com.memo.post.domain.Post;
@RequestMapping("/post")
@Controller
public class PostController {
	@Autowired
	private PostBO postBO; 
	@GetMapping("/post-list-view") // 화면은 getmapping
	// http://localhost/post/post-list-view
	public String postListView(Model model, HttpSession session) {
		// 로그인이 안되어 있을 수 있다.
		 Integer userId = (Integer) session.getAttribute("userId");
		 if(userId == null) {
			 // 비로그인이면 로그인 페이지로 이동
			 return "redirect:/user/sign-in-view";
		 }
		List<Post> postList = postBO.getPostListByUserId(userId);
		model.addAttribute("postList",postList);
		model.addAttribute("viewName","post/postList");
		
		return "template/layout";
	}
	/**
	 * 글쓰기 화면 
	 * @param model
	 * @return
	 */
	@GetMapping("/post-create-view")
	public String postCreateView(Model model) {
		model.addAttribute("viewName", "post/postCreate");
		return "template/layout";
	}
}	
