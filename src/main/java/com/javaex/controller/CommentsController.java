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
	public Map<String, Object> commentList(@ModelAttribute CommentsVo commentsVo) {
		System.out.println("CommentsController > comment()");
		Map<String, Object> coMap = commentsService.selectList(commentsVo);
		System.out.println("정보정보:"+coMap.get("userList"));
		
		return coMap;
	}
	
	//코멘트 추가
	@ResponseBody
	@RequestMapping(value="/comment/insert", method = {RequestMethod.POST, RequestMethod.GET})
	public CommentsVo comment(@ModelAttribute CommentsVo commentsVo) {
		System.out.println("CommentsController > comment()");
		
		CommentsVo cVo = commentsService.insert(commentsVo);
		System.out.println("vo정보:"+cVo);
		
		return cVo;
	}
	
}
