package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.dao.UsersDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UsersVo;

@Service
public class PostService {
	
	@Autowired
	PostDao postDao;
	
	@Autowired
	UsersDao usersDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	BlogDao blogDao;
	
	//블로그 접속자 정보 가져오기
	public UsersVo select(String id) {
		System.out.println("PostService > select()");
		
		UsersVo uVo = usersDao.userInfo(id);
		return uVo;
	}
	
	//카테고리 리스트 불러오기
	public List<CategoryVo> selectList(String id) {
		System.out.println("PostService > selectList()");
		
		List<CategoryVo> cList = categoryDao.selectList(id);
		return cList;
	}
		
	//포스트 추가하기
	public int insert(PostVo postVo) {
		System.out.println("PostService > insert()");
		return postDao.insert(postVo);
	}
	
	
	//포스트 리스트 불러오기
	public List<PostVo> postList(int cateNo) {
		System.out.println("PostService > postList()");
		
		List<PostVo> pList = postDao.postList(cateNo);
		return pList;
	}
	
	
	
		
}
