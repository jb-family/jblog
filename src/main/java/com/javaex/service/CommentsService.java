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
	
	public Map<String, Object> selectList() {
		System.out.println("CommentsService > selectList()");
		Map<String, Object> coMap = new HashMap<String, Object>();
		
		//List<PostVo> pList = postDao.pList(postNo);
		//int postNo = coList.get(0).getPostNo();
		
		List<CommentsVo> coList = commentsDao.selectList();
		int userNo = coList.get(0).getUserNo();
		
		UsersVo commentsInfo = usersDao.commentsInfo(userNo);
		System.out.println("coList정뵈:"+coList);
		System.out.println("commentsInfo정뵈:"+commentsInfo);
		coMap.put("coList", coList);
		coMap.put("commentsInfo", commentsInfo);
		
		return coMap;
	}
	
	//코멘트 추가
	public int insert(CommentsVo commentsVo) {
		System.out.println("CommentsService > insert()");
		
		return commentsDao.insert(commentsVo);
	}
	
	
}
