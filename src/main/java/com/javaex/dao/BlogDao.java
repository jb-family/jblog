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

		BlogVo Vo = sqlSession.selectOne("blog.select", id);
		return Vo;
	}
	
	
	//블로그 타이틀, 파일경로추가
	public int insert(BlogVo blogVo) {
		System.out.println("BlogDao > insert()");
		
		return sqlSession.insert("blog.insert", blogVo);
	}
	
	//블로그 파일업데이트
	public int update(BlogVo blogVo) {
		System.out.println("BlogDao > update()");
		
		return sqlSession.update("blog.update", blogVo);
	}
	
	
	//블로그 파일삭제
	public int delete(BlogVo blogVo) {
		System.out.println("BlogDao > fileUpdate()");
		System.out.println("vvvvvvvvvvvvvvvvv"+blogVo);
		return sqlSession.delete("blog.delete", blogVo);
	}
	
	
}
