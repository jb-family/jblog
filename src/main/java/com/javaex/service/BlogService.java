package com.javaex.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	BlogDao blogDao;
	//블로그 접속자 정보 가져오기
	public BlogVo select(String id) {
		System.out.println("BlogService > blog()");
		
		BlogVo Vo = blogDao.select(id);
		return Vo;
	}
	
	//블로그 글 파일 작성
	public BlogVo upLoad(BlogVo blogVo, MultipartFile file) {
		System.out.println("BlogController > upLoad()");
		System.out.println("파일"+ file);
		//파일명
		String orgName = file.getOriginalFilename();
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));  
		//불러올경로
		String saveDir = "C:\\javaStudy\\uploadFile";
		//저장 파일명
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		//파일경로 (디렉토리+불러올파일명)
		String filePath = saveDir + "\\" + saveName;
		
			blogVo.setLogoFile(saveName);
			
			blogDao.update(blogVo);
			try {
				byte[] fileData = file.getBytes();
				OutputStream os = new FileOutputStream(filePath);
				BufferedOutputStream bos = new BufferedOutputStream(os);
				
				bos.write(fileData);
				bos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return blogVo;
	}
	
}
