package com.memo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.memo.common.FileManagerService;

@Configuration // 설정을 위한 spring bean
public class WebMvcConfig implements WebMvcConfigurer {
	// 웹 이미지 path와 서버에 업로드 된 이미지와 매핑 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) { // 주소를 치고 들어왔을 때 검사
		registry
		.addResourceHandler("/images/**") // 웹의 image path D:\\정재우\\5_springProject\\Memo\\workspace\\images/aaaa_178945646/sun.png
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); // 실제 파일 윛; // 서버에서 위치를 찾겠다. windows : 3개
	}
}
