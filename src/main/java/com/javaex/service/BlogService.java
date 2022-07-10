package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UsersDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UsersVo;

@Service
public class BlogService {
	
	@Autowired
	BlogDao blogDao;
	
	@Autowired
	UsersDao usersDao;
	
	//블로그 접속자 정보 가져오기
	public UsersVo select(String id) {
		System.out.println("BlogService > blog()");
		
		UsersVo uVo = usersDao.userInfo(id);
		return uVo;
	}
	
	//블로그 접속자 정보 가져오기
		public BlogVo selectBlog(String id) {
			System.out.println("BlogService > blog()");
			
			BlogVo bVo = blogDao.select(id);
			return bVo;
		}
	
	//블로그 글 파일 작성
	public BlogVo upLoad(BlogVo blogVo, MultipartFile file, HttpSession session) {
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
			
			UsersVo user = (UsersVo)session.getAttribute("user");
			String id = user.getId();
			blogVo.setLogoFile(saveName);
			blogVo.setId(id);
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
