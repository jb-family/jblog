package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	//블로그 카테고리
	@RequestMapping(value="/blog/{id}/admin/cate", method = {RequestMethod.POST, RequestMethod.GET})
	public String blogCate(@PathVariable("id") String id) {
		System.out.println("CategoryController > blogCate()");
		
		return "/blog/admin/blog-admin-cate";
	}
}
