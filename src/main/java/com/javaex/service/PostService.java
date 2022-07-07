package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PostDao;

@Service
public class PostService {
	
	@Autowired
	PostDao postDao;
}
