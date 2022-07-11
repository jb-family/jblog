package com.javaex.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UsersVo;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	//블로그 카테고리
	@RequestMapping(value="/blog/{id}/admin/category", method = {RequestMethod.POST, RequestMethod.GET})
	public String blogCate(@PathVariable("id") String id, Model model) {
		System.out.println("CategoryController > blogCate()");
		
		UsersVo uVo = categoryService.select(id);
		model.addAttribute("uVo", uVo);
		
		return "/blog/admin/blog-admin-cate";
	}
	
	//블로그 카테고리 리스트
	@ResponseBody
	@RequestMapping(value="/category/list")
	public Map<String, Object> list(HttpSession session) {
		System.out.println("CategoryController > list()");
		
		UsersVo user = (UsersVo)session.getAttribute("user");
		String id = user.getId();
		
		Map<String, Object> cMap = categoryService.selectList(id);
		
		return cMap;
		
	}
	
	//블로그 카테고리 추가
	@ResponseBody
	@RequestMapping(value="/category/insert", method = {RequestMethod.POST, RequestMethod.GET})
	public CategoryVo insert(@ModelAttribute CategoryVo categoryVo, HttpSession session) {
		System.out.println("CategoryController > insert()");
		System.out.println(categoryVo);
		
		CategoryVo cVo = categoryService.insert(categoryVo, session);
		
		return cVo;
	}
	
	//블로그 카테고리 삭제
	@ResponseBody
	@RequestMapping(value="/category/remove", method = {RequestMethod.POST, RequestMethod.GET})
	public String delete(@RequestParam("cateNo") int cateNo) {
		System.out.println("CategoryController > delete()");
		
		System.out.println("카테고리 정보"+cateNo);
		String result = categoryService.delete(cateNo);
		
		return result;
	}
	
	
}
