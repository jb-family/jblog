package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CommentsVo;

@Repository
public class CommentsDao {
	
	@Autowired
	SqlSession sqlSession;
	
	
	//코멘트 리스트
	public List<CommentsVo> selectList(CommentsVo commentsVo) {
		System.out.println("CommentsDao > selectList()");
		
		List<CommentsVo> coList = sqlSession.selectList("comments.selectList", commentsVo);
		return coList;
	}
	
	//코멘트 추가
	public int insert(CommentsVo commentsVo) {
		System.out.println("CommentsDao > insert()");
		
		return sqlSession.insert("comments.insert", commentsVo);
	}
	
	//코멘트 추가된정보 가져오기
	public CommentsVo select(int cmtNo) {
		System.out.println("CommentsDao > cVo()");
		
		CommentsVo cVo = sqlSession.selectOne("comments.select", cmtNo);
		return cVo;
		
		
	}
	
}
