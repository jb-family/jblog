package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	SqlSession sqlSession;
	
	/*
	 * //블로그 접속자 정보 가져오기 public PostVo select(String id) {
	 * System.out.println("PostDao > select()");
	 * 
	 * PostVo pVo = sqlSession.select("post.select", id); return pVo; }
	 */
	
	//포스트 정보 추가하기
	public int insert(PostVo postVo) {
		System.out.println("PostDao > insert()");
		System.out.println(postVo);
		return sqlSession.insert("post.insert", postVo);
	}
}
