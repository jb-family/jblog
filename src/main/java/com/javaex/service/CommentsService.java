package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CommentsDao;

@Service
public class CommentsService {
	
	@Autowired
	CommentsDao commentsDao;
}
