package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.dao.UsersDao;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UsersVo;

@Service
public class CategoryService {
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	BlogDao blogDao;
	
	@Autowired
	UsersDao usersDao;
	
	@Autowired
	PostDao postDao;
	
	//블로그 접속자 정보 가져오기
	public UsersVo select(String id) {
		System.out.println("CategoryService > select()");
		
		UsersVo uVo = usersDao.userInfo(id);
		return uVo;
	}
	
	//블로그 카테고리 리스트
	public Map<String, Object> selectList(String id) {
		System.out.println("CategoryController > selectList()");
		Map<String, Object> cMap = new HashMap<String, Object>();
		
		List<CategoryVo> cList = categoryDao.selectList(id);
		List<PostVo> pList = postDao.postCount(id);
		
		cMap.put("cList", cList);
		cMap.put("pList", pList);
		
		return cMap;
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
	
	//블로그 카테고리 삭제
	public String delete(int cateNo) {
		System.out.println("CategoryController > delete()");
		
		String result;
		System.out.println("카테고리 정보"+cateNo);
		int count = categoryDao.delete(cateNo);
		
		if(count > 0) {
			result = "succeess";
		}else {
			result = "fail";
		}
		return result;
	}
	
	
	
	
	
	
	
}
