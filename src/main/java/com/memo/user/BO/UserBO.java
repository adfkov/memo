package com.memo.user.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memo.user.Entity.UserEntity;
import com.memo.user.repository.UserRepository;

@Service
public class UserBO {
	@Autowired
	private UserRepository userRepository;
	// input: loginId
	
	// output: count쿼리로 boolean 아니면 하나의 행을 리턴 => UserEntity(null 이거나 entity)
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId); 
	}

	// input: 4개 파라미터		output:id(pk)
	public Integer addUser(String loginId, String password , String name, String email) { // 해싱된 것
		// UserEntity = save(UserEntity);
		UserEntity userEntity = userRepository.save(
				UserEntity.builder()
				.loginId(loginId)
				.password(password)
				.name(name)
				.email(email)
				.build()); 
		return userEntity == null ? null : userEntity.getId();
	}
}
