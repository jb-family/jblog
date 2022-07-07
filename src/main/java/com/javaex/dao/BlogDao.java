package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	SqlSession sqlSession;
	
	//블로그 접속자 정보 가져오기
	public BlogVo select(String id) {
		System.out.println("BlogDao > blog()");

		BlogVo bVo = sqlSession.selectOne("blog.select", id);
		return bVo;
	}
	
	
	//블로그 타이틀, 파일경로추가
	public int insert(BlogVo blogVo) {
		System.out.println("BlogDao > insert()");
		System.out.println("정보"+blogVo);
		return sqlSession.insert("blog.insert", blogVo);
	}
	
	/*
	//블로그 파일변경
	public int fileUpdate(BlogVo blogVo) {
		System.out.println("BlogDao > fileUpdate()");
		
		return sqlSession.update("blog.update", blogVo);
		
	}
	*/
	
}
