package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	SqlSession sqlSession;
	
	
	//블로그 카테고리 리스트
	public List<CategoryVo> selectList(String id) {
		System.out.println("CategoryDao > selectList()");
		
		List<CategoryVo> cList = sqlSession.selectList("category.selectList", id);
		return cList;
	}
	
	//블로그 카테고리 추가
	public int insert(CategoryVo categoryVo) {
		System.out.println("CategoryDao > insert()");
		System.out.println(categoryVo);
		return sqlSession.insert("category.insert", categoryVo);
		
	}
	
	//블로그 카테고리 정보 가져오기
	public CategoryVo selectOne(int no) {
		System.out.println("CategoryDao > selectOne()");
		
		CategoryVo cVo = sqlSession.selectOne("category.selectOne", no);
		return cVo;
	}
	

	//블로그 카테고리 삭제
	public int delete(int cateNo) {
		System.out.println("CategoryController > delete()");
		
		System.out.println("카테고리 정보"+cateNo);
		return sqlSession.delete("category.delete", cateNo);
		
	}
	
	
}
