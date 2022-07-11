package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.dao.UsersDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UsersVo;

@Service
public class BlogService {
	
	@Autowired
	BlogDao blogDao;
	
	@Autowired
	UsersDao usersDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	PostDao postDao;
	
	//블로그 접속자 정보 가져오기
	public Map<String, Object> select(String id, int cateNo, int postNo) {
		System.out.println("BlogService > blog()");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		
			List<CategoryVo> cList = categoryDao.selectList(id);
			BlogVo bVo = blogDao.select(id);
			UsersVo uVo = usersDao.userInfo(id);
			List<PostVo> postList = postDao.postList(cateNo);
			PostVo post = postDao.post(postNo);
			map.put("cList", cList);
			map.put("bVo", bVo);
			map.put("uVo", uVo);
			map.put("postList", postList);
			map.put("post", post);
			
			return map;
		
		
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
