package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.PostService;
import com.javaex.vo.PostVo;
import com.javaex.vo.UsersVo;

@Controller
public class PostController {
	
	@Autowired
	PostService postService;
	
	
	//블로그 포스트작성폼
	@RequestMapping(value="/blog/{id}/admin/writeForm", method = {RequestMethod.POST, RequestMethod.GET})
	public String blogWrite(@PathVariable("id") String id, Model model) {
		System.out.println("PostController > blogWriteForm()");
		
		UsersVo uVo = postService.select(id);
		model.addAttribute("uVo", uVo);
		
		
		return "/blog/admin/blog-admin-write";
	}
	
	
	
	//블로그 포스트작성
	@RequestMapping(value="/blog/{id}/admin/postWrite", method = {RequestMethod.POST, RequestMethod.GET})
	public String postWrite(@ModelAttribute PostVo postVo) {
		System.out.println("PostController > postWrite()");
		System.out.println("포스트 정보 :"+postVo);
		
		postService.insert(postVo);
		return "redirect:/blog/{id}/admin/writeForm";
	}
}
