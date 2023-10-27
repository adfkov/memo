package com.memo.user;

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
}
