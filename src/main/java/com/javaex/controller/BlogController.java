package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UsersVo;

@Controller
public class BlogController {
	
	@Autowired
	BlogService blogService;
	
	//블로그 주소
	@RequestMapping(value="/blog/blog-main/{id}", method = {RequestMethod.POST, RequestMethod.GET})
	public String blog(@PathVariable("id") String id, Model model) {
		System.out.println("BlogController > blog()");
		
		UsersVo uVo = blogService.select(id);
		System.out.println("uVo정보"+uVo);
		model.addAttribute("uVo", uVo);
		
		return "/blog/blog-main";
	}
	
	//블로그 로그아웃
	@RequestMapping(value="/blog/logout", method = {RequestMethod.POST, RequestMethod.GET})
	public String blogLogout(HttpSession session) {
		System.out.println("BlogController > blogLogout()");
		session.removeAttribute("user");
		session.invalidate();
		
		return "/blog/blog-main";
	}
	
	//블로그 기본수정폼
	@RequestMapping(value="/blog/{id}/admin/basic", method = {RequestMethod.POST, RequestMethod.GET})
	public String blogBasic(@PathVariable("id") String id, Model model) {
		System.out.println("BlogController > blogBasic()");
		
		UsersVo uVo = blogService.select(id);
		model.addAttribute("uVo", uVo);
		
		BlogVo bVo = blogService.selectBlog(id);
		model.addAttribute("bVo", bVo);
		
		return "/blog/admin/blog-admin-basic";
	}
	
	//블로그 글 파일 작성
	@RequestMapping(value="/blog/{id}/admin/upLoad", method = {RequestMethod.POST, RequestMethod.GET})
	public String blogUpload(@ModelAttribute BlogVo blogVo, @RequestParam("file") MultipartFile file, Model model, HttpSession session) {
		System.out.println("BlogController > upLoad()");
		
		BlogVo bVo = blogService.upLoad(blogVo, file, session);
		
		model.addAttribute("bVo", bVo);
		System.out.println("정보정보"+bVo);
		return "redirect:/blog/{id}/admin/basic";
	}
	
}
