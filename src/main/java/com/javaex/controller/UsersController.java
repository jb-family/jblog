package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UsersService;
import com.javaex.vo.UsersVo;

@Controller
public class UsersController {

	
	@Autowired UsersService usersService;
	 
	
	//주소 아무것도 입력하지않았을 때 메인페이지 이동
	@RequestMapping(value="", method = {RequestMethod.GET, RequestMethod.POST})
	public String jblog() {
		System.out.println("UsersController > main()");
		
		return "/main/index";
	}
	
	//메인페이지
	@RequestMapping(value="/main/index", method = {RequestMethod.GET, RequestMethod.POST})
	public String main() {
		System.out.println("UsersController > main()");
		
		return "/main/index";
	}
	
	
	//로그인 폼
	@RequestMapping(value="/user/loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UsersController > loginForm()");
		
		
		return "/user/loginForm";
	}
	
	//로그인
	@RequestMapping(value="/user/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UsersVo usersVo, HttpSession session) {
		System.out.println("UsersController > login()");
		
		UsersVo user = usersService.login(usersVo);
		System.out.println(user);
		
		if(user != null) {
			session.setAttribute("user", user);
			return "redirect:/main/index";
		}else {
			return "redirect:/user/loginForm";
		}
	}
	
	//로그아웃
	@RequestMapping(value="/user/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("UsersController > logout()");
		session.removeAttribute("user");
		session.invalidate();
		
		return "redirect:/main/index";
	}
	
	
	//회원가입 폼
	@RequestMapping(value="/user/joinForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UsersController > joinForm()");
		
		return "/user/joinForm";
	}
	
	//회원가입
	@RequestMapping(value="/user/join", method = {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UsersVo usersVo) {
		System.out.println("UsersController > join()");
		System.out.println(usersVo);
		
		usersService.insert(usersVo);
		return "/user/joinSuccess";
	}
	
	//아이디체크
	@ResponseBody
	@RequestMapping(value="user/idCheck", method = {RequestMethod.POST, RequestMethod.GET})
	public String idCheck(@RequestParam("id") String id) {
		System.out.println("UsersController > idCheck()");
		System.out.println(id);
		
		String result = usersService.check(id);
		System.out.println("user정보"+result);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
