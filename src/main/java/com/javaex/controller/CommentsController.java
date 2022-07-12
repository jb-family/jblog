package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CommentsService;
import com.javaex.vo.CommentsVo;

@Controller
public class CommentsController {
	
	@Autowired
	CommentsService commentsService;
	
	//코멘트 리스트
	@ResponseBody
	@RequestMapping(value="/comment/list", method = {RequestMethod.POST, RequestMethod.GET})
	public Map<String, Object> commentList() {
		System.out.println("CommentsController > comment()");
		
		Map<String, Object> coMap = commentsService.selectList();
		System.out.println(coMap.get("commentsInfo"));
		return coMap;
	}
	
	//코멘트 추가
	@RequestMapping(value="/comment/insert", method = {RequestMethod.POST, RequestMethod.GET})
	public String comment(@ModelAttribute CommentsVo commentsVo) {
		System.out.println("CommentsController > comment()");
		
		System.out.println("vo정보:"+commentsVo);
		int count = commentsService.insert(commentsVo);
		
		return "redirect:/";
	}
	
}
