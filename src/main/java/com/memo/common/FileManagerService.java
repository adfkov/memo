package com.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component  // spring bean(불러올 때는 Autowired 사용)
public class FileManagerService {
	// 실제 업로드가 된 이미지가 저장될 경로(서버)
	public static final String FILE_UPLOAD_PATH = "D:\\정재우\\5_springProject\\Memo\\workspace\\images/"; // 마지막 / 붙이기
//	public static final String FILE_UPLOAD_PATH = "D:\\정재우\\5_springProject\\Memo\\workspace\\images";  집용
	
	// 메소드의
	// INPUT: userLoginId, file(이미지)
	public String saveFile(String loginId, MultipartFile file) {
		// 서버에 폴더 생성(ex. aaaa_현재시간)
		// 예: aaaa_178945646/sun.png 
		String directoryName = loginId + "_" + System.currentTimeMillis();
		String filePath = FILE_UPLOAD_PATH + directoryName;
//		D:\\정재우\\5_springProject\\Memo\\workspace\\images/aaaa_178945646
		
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			// 폴더 생성 실패 시 이미지 경로 null로 리턴
			return null;
			
		} 

		
		// 폴더 내에 파일 업로드 : byte 단위로 업로드
		try {
			byte[] bytes = file.getBytes();
			// ★★★★★★★★★★★★★★★★★★ 한글 이름 이미지는 올릴 수 없으므로 나중에 영문자로 바꿔서 올리기 : 로직 구현 개인프로젝트에서는
			Path path = Paths.get(filePath+ "/" + file.getOriginalFilename()); // 디렉토리 경로 + 사용자가 올린 파일명
			Files.write(path, bytes); // 파일 업로드
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			return null;// 이미지 업로드 실패 시 null 리턴
		}
		
		// 파일 업로드 성공 시 웹 이미지 url path를 리턴
		// 주소는 이렇게 될 것이다. (예언?) 
			// http://localhost/images/aaaa_현재시간/sun.png
		// /images/aaaa_178945646/sun.png
		return "/images/" + directoryName + "/" + file.getOriginalFilename();
		
	}
	
	// OUTPUT: web imagepath
	
}
