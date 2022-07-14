package com.javaex.controller;

import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {
	
	@Autowired
	BlogService blogService;
	
	//블로그 주소
	@RequestMapping(value="/blog/blog-main/{id}", method = {RequestMethod.POST, RequestMethod.GET})
	public String blog(@PathVariable("id") String id, Model model,
			@RequestParam(value="cateNo", required=false, defaultValue="0") int cateNo,
			@RequestParam(value="postNo", required=false, defaultValue= "0") int postNo) {
		System.out.println("BlogController > blog()");
		
		Map<String, Object> map = blogService.select(id, cateNo, postNo);
		System.out.println("postVo:"+ map.get("post"));
		System.out.println("bVo정보:"+map.get("bVo"));
		model.addAttribute("cList", map.get("cList"));
		model.addAttribute("bVo", map.get("bVo"));
		model.addAttribute("uVo", map.get("uVo"));
		model.addAttribute("userList", map.get("userList"));
		model.addAttribute("postList", map.get("postList"));
		model.addAttribute("post", map.get("post"));
			
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
		
		Map<String, Object> map = blogService.select(id, 0, 0);
		
		
		model.addAttribute("uVo", map.get("uVo"));
		model.addAttribute("bVo", map.get("bVo"));
		
		return "/blog/admin/blog-admin-basic";
	}
	
	//블로그 글 파일 작성
	@RequestMapping(value="/blog/{id}/admin/upLoad", method = {RequestMethod.POST, RequestMethod.GET})
	public String blogUpload(@ModelAttribute BlogVo blogVo, @RequestParam("file") MultipartFile file, Model model, HttpSession session) {
		System.out.println("BlogController > upLoad()");
		
		BlogVo bVo = blogService.upLoad(blogVo, file, session);
		
		model.addAttribute("bVo", bVo);
		return "redirect:/blog/{id}/admin/basic";
	}
	
}
