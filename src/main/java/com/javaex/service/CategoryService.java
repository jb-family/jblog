package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;

@Service
public class CategoryService {
	@Autowired
	CategoryDao categoryDao;
}
