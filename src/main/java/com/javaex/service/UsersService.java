package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.CommentsDao;
import com.javaex.dao.PostDao;
import com.javaex.dao.UsersDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UsersVo;

@Service
public class UsersService {
	
	 @Autowired UsersDao usersDao;
	 @Autowired BlogDao blogDao;
	 @Autowired CategoryDao CategoryDao;
	 @Autowired PostDao postDao;
	 @Autowired CommentsDao commentsDao;
	 
	 
	 //세션
	 public List<UsersVo> selectList() {
		 System.out.println("UsersService > selectList()");
		 List<UsersVo> uList = usersDao.selectList();
		 return uList;
	 }
	 
	 
	 //회원가입
	 public int insert(UsersVo usersVo) {
		 System.out.println("UsersService > insert()");
		 		
		 		
		 int count = usersDao.insert(usersVo);
		 if(count > 0) {
			 
			 String blogTitle = "기본제목입니다.";
			 String logoFile = "파일경로";
			 String cateName = "기본카테고리";
			 String description = "카테고리설명";
			 BlogVo blogVo = new BlogVo(usersVo.getId(), blogTitle, logoFile);
			 blogDao.insert(blogVo);
			 
			 CategoryVo categoryVo = new CategoryVo();
			 
			 categoryVo.setId(usersVo.getId());
			 categoryVo.setCateName(cateName);
			 categoryVo.setDescription(description);
			 CategoryDao.insert(categoryVo);
			 
			 
		 }
		return count;
	 }
	 
	 //아이디체크
	 public String check(String id) {
		 System.out.println("UsersService > check()");
		 String result;
		 String userId = usersDao.check(id);
		 
		 System.out.println("id값"+id);
		 System.out.println("가져온유저id값"+userId);
		 
		 if(userId != null) {
			 result = "success";
		 } else {
			 result = "fail";
		 }
		return result;
		
	 }
	 
	//로그인
	public UsersVo login(UsersVo usersVo) {
		System.out.println("UsersService > login()");
		
		UsersVo user = usersDao.login(usersVo);
		return user;
	}
		
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
