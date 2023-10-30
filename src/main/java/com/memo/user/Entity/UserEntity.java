package com.memo.user.Entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString // 내용이 잘 보이게 한다.
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name="user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;
	@Column(name="loginId")
	private String loginId;
	
	private String password;
	
	private String name;
	
	private String email;
	@UpdateTimestamp
	@Column(name="createdAt", updatable = false)
	private ZonedDateTime createdAt;
	@UpdateTimestamp
	@Column(name="updatedAt")
	private ZonedDateTime updatedAt;
	
	
}
