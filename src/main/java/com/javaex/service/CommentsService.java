package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CommentsDao;
import com.javaex.dao.PostDao;
import com.javaex.dao.UsersDao;
import com.javaex.vo.CommentsVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UsersVo;

@Service
public class CommentsService {
	
	@Autowired
	CommentsDao commentsDao;
	
	@Autowired
	UsersDao usersDao;
	
	@Autowired
	PostDao postDao;
	
	public Map<String, Object> selectList(CommentsVo commentsVo) {
		System.out.println("CommentsService > selectList()");
		Map<String, Object> coMap = new HashMap<String, Object>();
		
		List<CommentsVo> coList = commentsDao.selectList(commentsVo);
		List<UsersVo> userList = usersDao.selectList();
		
		
		System.out.println("userList정보:"+userList);
		System.out.println("coList정보:"+coList);
		
		
		coMap.put("userList", userList);
		coMap.put("coList", coList);
		return coMap;
	}
	
	//코멘트 추가
	public CommentsVo insert(CommentsVo commentsVo) {
		System.out.println("CommentsService > insert()");
		
		commentsDao.insert(commentsVo);
		
		int cmtNo = commentsVo.getCmtNo();      
		
		CommentsVo cVo = commentsDao.select(cmtNo);
		return cVo;
	}
	
	
}
