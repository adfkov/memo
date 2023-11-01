package com.memo.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memo.common.EncryptUtils;
import com.memo.user.BO.UserBO;
import com.memo.user.Entity.UserEntity;

@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	private UserBO userBO;
	
	
	/**
	 *로그인 아이디 중복 확인 API 
	  @param loginId
	  @return
	 
	 *
	 */
	@RequestMapping("/is_duplicated_id") // 아이디 중복확인 get, post
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId) {
		// db 조회
		UserEntity user = userBO.getUserEntityByLoginId(loginId);
		// 응답값 만들고 리턴 => JSON 리턴
		Map<String, Object> result = new HashMap<>();
		
		result.put("code", 200);
		if(user == null) {
			// 중복 아님
			result.put("isDuplicated", false);
		} else { // 중복
			result.put("isDuplicated", true);
		}
		return result;
	}
	
	
	
	
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,@RequestParam("name") String name
			,@RequestParam("email") String email
			) {
		// 패스워드 원본을 저장하면 안되고 패스워드를 해싱 -> 가장 보안이 취약한 방식 - md5 알고리즘
		// aaaa => 74b8733745420d4d33f80c4663dc5e5
		 String hashedPassword = EncryptUtils.md5(password);
		 
		// DB insert
		Integer id = userBO.addUser(loginId, hashedPassword, name, email);
		// 응답값(성공 여부)
		Map<String, Object> result = new HashMap<>();
		if(id == null) {
			result.put("code", 500);
			result.put("errorMessage", "회원가입 하는데 실패했습니다.");
		} else {
		
			result.put("code", 200);
			result.put("result", "성공");
		}
		return result;
	}
	// 로그인 , 비밀번호 노출되면 안됨
	/**
	 * 
	 * @param loginId
	 * @param password
	 * @param request
	 * @return
	 */
		@PostMapping("/sign-in")
		public Map<String, Object> signIn(
				@RequestParam("loginId") String loginId,
				@RequestParam("password") String password,
				HttpServletRequest request) {
			
			// 비밀번호 hashing
			String hashedPassword = EncryptUtils.md5(password);
			// db 조회(loginId, 해싱된 비밀번호) => null or 있음
			UserEntity user = userBO.getUserEntityByLoginIdPassword(loginId, hashedPassword);

			// 응답값 처리
			Map<String, Object> result = new HashMap<>();
			if(user != null) {
				// 로그인 처리
				HttpSession session = request.getSession();
				session.setAttribute("userId" ,  user.getId()); // key , value / 모든 페이지에서 사용 가능한 세션 주머니
				session.setAttribute("userName", user.getName());
				session.setAttribute("userLoginId", user.getLoginId());
				
				result.put("code",200);
				result.put("result", "성공");
			} else {
				// 로그인 불가
				result.put("code", 500);
				result.put("errorMessage", "존재하지 않는 사용자입니다.");
			}
			return result;
		}
		
		
	}


