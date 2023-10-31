package com.memo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.memo.user.Entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	// null 이거나 채워져 있거나
	public UserEntity findByLoginId(String loginId); // 쿼리 문을 만들어라 , 단건 조회
	
	public UserEntity findByLoginIdAndPassword(String loginId, String password);
}
