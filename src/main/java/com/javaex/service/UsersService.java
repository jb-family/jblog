package com.javaex.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UsersDao;
import com.javaex.vo.UsersVo;

@Service
public class UsersService {
	
	 @Autowired UsersDao usersDao;
	 
	 //세션
	 public List<UsersVo> selectList() {
		 System.out.println("UsersService > selectList()");
		 List<UsersVo> uList = usersDao.selectList();
		 return uList;
	 }
	 
	 
	 //회원가입
	 public int insert(UsersVo usersVo) {
		 System.out.println("UsersService > insert()");
		 
		 return usersDao.insert(usersVo);
	 }
	 
	 //아이디체크
	 public String check(String id) {
		 System.out.println("UsersService > check()");
		 String result;
		 String userId = usersDao.check(id);
		 
		 System.out.println("id값"+id);
		 System.out.println("가져온유저id값"+userId);
		 
		 if(userId != null) {
			 result = "success";
		 } else {
			 result = "fail";
		 }
		return result;
		
	 }
	 
	//로그인
	public UsersVo login(UsersVo usersVo) {
		System.out.println("UsersService > login()");
		
		UsersVo user = usersDao.login(usersVo);
		return user;
	}
		
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
