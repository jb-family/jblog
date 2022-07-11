package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	SqlSession sqlSession;
	
	//포스트 정보 추가하기
	public int insert(PostVo postVo) {
		System.out.println("PostDao > insert()");
		System.out.println(postVo);
		return sqlSession.insert("post.insert", postVo);
	}
	
	
	//포스트 글 개수 가져오기
	public List<PostVo> postCount(String id) {
		System.out.println("PostDao > count()");
		List<PostVo> pList = sqlSession.selectList("post.selectCount", id);
		
		return pList;
	}
	
	//포스트 리스트 가져오기
	public List<PostVo> postList(int cateNo) {
		System.out.println("PostDao > count()");
		List<PostVo> pList = sqlSession.selectList("post.pList", cateNo);
		
		return pList;
	}
	
	//포스트 가져오기
	public PostVo post(int postNo) {
		System.out.println("PostDao > post()");
		PostVo post = sqlSession.selectOne("post.post", postNo);
		return post;
	}
	
}
