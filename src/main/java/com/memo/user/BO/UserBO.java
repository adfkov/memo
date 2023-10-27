package com.memo.user.BO;

import org.springframework.stereotype.Service;

import com.memo.user.Entity.UserEntity;

@Service
public class UserBO {
	
	// input: loginId
	
	// output: count쿼리로 boolean 아니면 하나의 행을 리턴 => UserEntity(null 이거나 entity)
	public UserEntity getUserEntityByLoginId(String loginId) {
		return null; 
	}
}
