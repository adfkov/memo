package com.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {
	@RequestMapping("/is_duplicated_id") // 아이디 중복확인 get, post
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId) {
		// db 조회
		
		// 응답값 만들고 리턴 => JSON 리턴
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("isDuplicated", true);
		return result;
	}
}

