package com.javaex.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.javaex.vo.UsersVo;

@Repository
public class UsersDao {
	
	 @Autowired SqlSession sqlSession;
	 
	 //회원가입
	 public int insert(UsersVo usersVo) {
		 System.out.println("UsersDao > insert()");
		 
		 return sqlSession.insert("users.insert",usersVo);
	 } 
	
	 //아이디체크
	 public String check(String id) {
		 System.out.println("UsersDao > check()");
		 
		 String userId = sqlSession.selectOne("idCheck", id);
		 return userId;
	 }
	
	//로그인
	public UsersVo login(UsersVo usersVo) {
		System.out.println("UsersDao > login()");
		UsersVo user = sqlSession.selectOne("users.session", usersVo);
		return user;
	}
	
	//유저 정보가져오기
	public UsersVo userInfo(String id) {
		System.out.println("UsersDao > userInfo()");
		
		UsersVo user = sqlSession.selectOne("users.userInfo", id);
		return user;
	}
	
	//코멘트 유저 가져오기
	public UsersVo commentsInfo(int userNo) {
		System.out.println("UsersDao > commentsInfo()");
		
		UsersVo commentsInfo = sqlSession.selectOne("users.commentsInfo", userNo);
		return commentsInfo;
	}
	
	//유저이름 리스트
	public List<UsersVo> selectList() {
		System.out.println("UsersDao > selectList()");
		
		List<UsersVo> userList = sqlSession.selectList("users.selectList");
		return userList;
	}
	
	
	
}
