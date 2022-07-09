package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.PostService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.PostVo;

@Controller
public class PostController {
	
	@Autowired
	PostService postService;
	
	//블로그 포스트작성폼
	@RequestMapping(value="/blog/{id}/admin/writeForm", method = {RequestMethod.POST, RequestMethod.GET})
	public String blogWrite(@PathVariable("id") String id, Model model) {
		System.out.println("BlogController > blogWriteForm()");
		
		
		//PostVo pVo = postService.select(id);
		//model.addAttribute("bVo", pVo);
		
		
		return "/blog/admin/blog-admin-write";
	}
	
	//블로그 포스트작성
	@RequestMapping(value="/blog/admin/write", method = {RequestMethod.POST, RequestMethod.GET})
	public String blogWrite(@ModelAttribute BlogVo blogVo) {
		System.out.println("BlogController > blogWrite()");
		System.out.println(blogVo);
		//redirect:/blog/admin/blog-admin-basic
		return "";
	}
}
