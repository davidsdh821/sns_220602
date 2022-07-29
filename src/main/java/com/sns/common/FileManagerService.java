package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {
	public final static String FILE_UPLOAD_PATH ="C:\\SDH\\spring_project\\sns\\workspace\\images/";
	//C:\SDH\spring_project\sns\workspace\images
	//D:\\spring_project\\sns\\workspace\\images
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
	
	public void deleteFile(String imagePath) throws IOException {
		// /images/mrhong_1658478158330/default.jpg
		// C:\\SDH\\spring_project\\memo\\workspace\\images/ /images/mrhong_1658478158330/default.jpg
		// 전체 경로와 imagePath간의 중복되는 /images/ 문자열을 제거한 후 실제 저장 경로를 찾는다
		//imagePath의 /images/를 지운다
		imagePath.replace("/images/", ""); //제거함
		
		Path path = Paths.get(FILE_UPLOAD_PATH + imagePath);
		//폴더안의 사진 제거 후, 폴더 제거
		if(Files.exists(path)) { //이미지 파일이 있으면 삭제, == true를 붙여도 된다
			Files.delete(path);
			
		
			
		}
		//디렉토리를 삭제
		path = path.getParent(); //주소의 부모(폴더)를 path로 지정
		if(Files.exists(path)) {
			Files.delete(path);
		}
		
		
	}
}
