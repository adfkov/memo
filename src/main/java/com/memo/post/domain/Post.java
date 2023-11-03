package com.memo.post.domain;

import java.util.Date;

import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Table(name="post")
public class Post {
	
	private int id;
	private int userId;
	private String subject;
	private String content;
	private String imagePath;
	private Date createdAt;
	private Date updatedAt;
	
	
}
