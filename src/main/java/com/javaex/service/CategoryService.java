package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UsersVo;

@Service
public class CategoryService {
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	BlogDao blogDao;
	
	//블로그 접속자 정보 가져오기
	public BlogVo select(String id) {
		System.out.println("CategoryService > select()");
		
		BlogVo bVo = blogDao.select(id);
		return bVo;
	}
	
	//블로그 카테고리 리스트
	public List<CategoryVo> selectList() {
		System.out.println("CategoryController > selectList()");
		List<CategoryVo> cList = categoryDao.selectList();
		return cList;
	}
	
	
	//블로그 카테고리 추가
	public CategoryVo insert(CategoryVo categoryVo, HttpSession session) {
		System.out.println("CategoryController > insert()");
		System.out.println(categoryVo);
		//세션 가져오기
		UsersVo user = (UsersVo)session.getAttribute("user");
		String id = user.getId();
		
		categoryVo.setId(id);
		categoryDao.insert(categoryVo);
		int no = categoryVo.getCateNo();
		CategoryVo cVo = categoryDao.selectOne(no);
		return cVo;
		
	}
}
