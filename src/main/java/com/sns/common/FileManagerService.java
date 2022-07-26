package com.sns.common;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {
	public final static String FILE_UPLOAD_PATH ="D:\\spring_project\\sns\\workspace\\images/";
	
	
	public String saveFile(String userLoginId, MultipartFile file) {
		
		String directoryName = userLoginId + "_" + System.currentTimeMillis() + "/";
		//이름_시간날짜/ 부분까지 완성
		String filePath = FILE_UPLOAD_PATH + directoryName;
		//경로와 주소연결
		
		//파일 생성
		File directory = new File(filePath); //
		if (directory.mkdir() == false) {
			return null; //만약 이미지가 없으면(false) null로 리턴
		}
		
		//업로드
		try {
			byte[] bytes = file.getBytes();
			
			Path path = Paths.get(filePath + file.getOriginalFilename());
			
			//매핑 WebMvcConfig로 이동
			Files.write(path, bytes);
			
			//매핑 후 리턴
			return "/images/" + directoryName + file.getOriginalFilename();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}
}
