package com.memo.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/* template/layout.jsp
-header
- footer
-회원가입/로그인/글쓰기


include/header.jsp
include/footer.jsp


*/
@RequestMapping("/user")
@Controller // 화면 
public class UserController { // 화면만 띄운다.
	
	@GetMapping("/sign-up-view")
	public String singUpView(Model model) {
		model.addAttribute("viewName", "user/signUp");
		return "template/layout";
	}
	
	@GetMapping("/sign-in-view")
	public String signInView(Model model) {
		model.addAttribute("viewName", "user/signIn");
		return "template/layout";
	}
	
	@RequestMapping("/sign-out")
	public String signOut(HttpSession session) {
		// 세션에 있는 내용을 비운다.
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		session.removeAttribute("userLoginId");
		
		// 로그인 화면으로 이동
		return "redirect:/user/sign-in-view";
	}
}

