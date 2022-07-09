package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	//블로그 카테고리
	@RequestMapping(value="/blog/{id}/admin/category", method = {RequestMethod.POST, RequestMethod.GET})
	public String blogCate(@PathVariable("id") String id, Model model) {
		System.out.println("CategoryController > blogCate()");
		
		BlogVo bVo = categoryService.select(id);
		model.addAttribute("bVo", bVo);
		
		return "/blog/admin/blog-admin-cate";
	}
	
	//블로그 카테고리 리스트
	@ResponseBody
	@RequestMapping(value="/category/list")
	public List<CategoryVo> list() {
		System.out.println("CategoryController > list()");
		
		List<CategoryVo> cList = categoryService.selectList();
		System.out.println("리스트"+cList);
		return cList;
		
	}
	
	//블로그 카테고리 추가
	@ResponseBody
	@RequestMapping(value="/category/insert")
	public CategoryVo insert(@ModelAttribute CategoryVo categoryVo, HttpSession session) {
		System.out.println("CategoryController > insert()");
		System.out.println(categoryVo);
		
		CategoryVo cVo = categoryService.insert(categoryVo, session);
		return cVo;
	}
	
	
}
