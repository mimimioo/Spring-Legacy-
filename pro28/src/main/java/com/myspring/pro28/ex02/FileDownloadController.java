package com.myspring.pro28.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileDownloadController {
	private static String CURR_IMAGE_REPO_PATH = "c:\\spring\\image_repo";
	
	@RequestMapping("/download")
	protected void download(@RequestParam("imageFileName") String imageFileName,
			                 HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath = CURR_IMAGE_REPO_PATH + "\\" + imageFileName;
		File image = new File(filePath);
		int lastIndex = imageFileName.lastIndexOf(".");
		// 파일의 이름만 잘라내기
		String fileName = imageFileName.substring(0,lastIndex);
		// 썸네일 이미지 파일 형식으로 저장할 위치.
		File thumbnail = new File(CURR_IMAGE_REPO_PATH+"\\"+"thumbnail"+"\\"+fileName+".png");
		if (image.exists()) {
			// 이미지가 존재한다면
			// 해당 부모 경로를 만들고 
			thumbnail.getParentFile().mkdirs();
			// 파일이름.png 라고 해서 옮기기. 
			// 실제 저장소 저장.
		    Thumbnails.of(image).size(50,50).outputFormat("png").toFile(thumbnail);
		}

		// 웹 브라우저 출력. 
		// in 인스턴스 -> out 인스턴스로 옮기 , 
		// 중간에 임시 저장 배열 buffer 8byte씩 옮기기
		FileInputStream in = new FileInputStream(thumbnail);
		byte[] buffer = new byte[1024 * 8];
		while (true) {
			int count = in.read(buffer); // ���ۿ� �о���� ���ڰ���
			if (count == -1) // ������ �������� �����ߴ��� üũ
				break;
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
	}
	
/*	
	@RequestMapping("/download")
	protected void download(@RequestParam("imageFileName") String imageFileName,
			                 HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath = CURR_IMAGE_REPO_PATH + "\\" + imageFileName;
		File image = new File(filePath);
		int lastIndex = imageFileName.lastIndexOf(".");
		String fileName = imageFileName.substring(0,lastIndex);
		File thumbnail = new File(CURR_IMAGE_REPO_PATH+"\\"+"thumbnail"+"\\"+fileName+".png");
		if (image.exists()) { 
			Thumbnails.of(image).size(50,50).outputFormat("png").toOutputStream(out);
		}else {
			return;
		}
		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
		out.close();
	}
	*/
}
